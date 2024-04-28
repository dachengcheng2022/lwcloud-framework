package com.autumn.web3j;


import com.alibaba.fastjson2.JSONObject;
import com.autumn.exception.custom.CommonException;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.filters.FilterException;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Web3jUtils {

    private static final Logger logger = LoggerFactory.getLogger("web3jUtils");

    private final static int MAX_RETRIES = 15; // retry at most about 15 seconds which is blocks generation time

    /**
     * get logs for all event topics at a blocks number.
     *
     * @param web3j                  an instance of Web3j to make RPC request
     * @param blockHash              the blocks of where the logs are being retrieved
     * @param tokenContractAddresses the contract addresses of where the logs are originated
     */
    public static List<EthLog.LogObject> getAllEventLogsFromBlock(Web3j web3j,
                                                                  String blockHash,
                                                                  Set<String> tokenContractAddresses)  {
        EthBlock ethBlock;
        try {
            ethBlock = web3j.ethGetBlockByHash(blockHash, false).send();
        } catch (IOException ex) {
            throw new CommonException(String.format("Failed to get blocks from hash '%s'", blockHash));
        }
        if (ethBlock.hasError()) {
            Response.Error error = ethBlock.getError();
            throw new CommonException(String.format("Failed to get blocks from hash '%s' due to error [%d - %s]",
                    blockHash, error.getCode(), error.getMessage()));
        }
        if (ethBlock.getBlock() == null) {
            throw new CommonException(String.format("No blocks found with hash '%s'", blockHash));
        }

        return getAllEventLogsFromBlock(web3j, ethBlock.getBlock(), tokenContractAddresses);
    }

    /**
     * get logs for all event topics at a blocks number.
     *
     * @param web3j             an instance of Web3j to make RPC request
     * @param block             the blocks of which the logs are being retrieved
     * @param contractAddresses the contract addresses of where the logs are originated
     */
    public static List<EthLog.LogObject> getAllEventLogsFromBlock(Web3j web3j,
                                                                  EthBlock.Block block,
                                                                  Set<String> contractAddresses) {
        DefaultBlockParameter fromBlockParams = DefaultBlockParameter.valueOf(block.getNumber());
        DefaultBlockParameter toBlockParams = DefaultBlockParameter.valueOf(block.getNumber().add(BigInteger.ONE));
        EthFilter ethFilter = new EthFilter(fromBlockParams,toBlockParams,new ArrayList<>(contractAddresses));
        EthLog ethLog;

        try {
            ethLog = web3j.ethGetLogs(ethFilter).send();
        } catch (IOException ex) {
            logger.error("IOException {} ",ex);
            throw new CommonException(String.format("Failed to get logs for blocks '%s'", block.getHash()));
        }

        if (ethLog == null) {
            return null;
        }

        if (ethLog.hasError()) {
            logger.error("contract address {},height {} error {}  ", JSONObject.toJSONString(contractAddresses),block.getNumber(),JSONObject.toJSONString(ethLog));
            Response.Error error = ethLog.getError();
            throw new CommonException(String.format("Failed to get logs for blocks '%s' due to error [%d - %s]",
                    block.getHash(), error.getCode(), error.getMessage()));
        }

        List<EthLog.LogObject> logs = ethLog.getLogs()
                .stream()
                .map(logResult -> (EthLog.LogObject) logResult)
                .collect(Collectors.toList());

        validateLogs(block, logs);

        if (CollectionUtils.isNotEmpty(contractAddresses)) {
            return logs
                    .stream()
                    .filter(logObject -> contractAddresses.contains(logObject.getAddress()))
                    .collect(Collectors.toList());
        } else {
            return logs;
        }
    }


    public static EthBlock getBlockByBlockHash(Web3j web3j, String blockHash, boolean returnFullTransactionObjects) throws IOException {
        int tries = 0;
        EthBlock ethBlock = null;
        do {
            ethBlock = web3j.ethGetBlockByHash(blockHash, returnFullTransactionObjects).send();
            if (ethBlock.getBlock() == null) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }
        } while (ethBlock.getBlock() == null && tries++ < MAX_RETRIES);
        if (ethBlock.getBlock() == null) {
            throw new RuntimeException(String.format("Unable to get blocks object for blockHash '%s' after retry, will reinstall filter.", blockHash));
        }
        return ethBlock;
    }

    public static EthBlock getBlock(Web3j web3j, DefaultBlockParameter defaultBlockParameter, boolean returnFullTransactionObjects) throws IOException {
        int tries = 0;
        EthBlock ethBlock = null;
        do {
            ethBlock = web3j.ethGetBlockByNumber(defaultBlockParameter, returnFullTransactionObjects).send();
            if (ethBlock.getBlock() == null) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }
        } while (ethBlock.getBlock() == null && tries++ < MAX_RETRIES);
        if (ethBlock.getBlock() == null) {
            throw new FilterException(String.format("Unable to get blocks object for blockNumber '%s' after retry, will reinstall filter.", defaultBlockParameter.getValue()));
        }
        return ethBlock;
    }

    private static void validateLogs(EthBlock.Block block, List<EthLog.LogObject> logs) {
        if (CollectionUtils.isEmpty(logs)) {
            // No logs. Logs bloom should be empty or 0x000 as well.
            if (StringUtils.isNotEmpty(block.getLogsBloom())
                    && Numeric.decodeQuantity(block.getLogsBloom()).compareTo(BigInteger.ZERO) != 0) {
//                String msg = String.format("Empty logs retrieved for blocks number '%d' and blocks hash '%s' while logs bloom is '%s'. Looks like we are connected to stale geth node. Will retry processing blocks.", block.getNumber(), block.getHash(), block.getLogsBloom());
//                logger.info(msg);
//                throw new FilterException(msg);
            }
        }
    }
}
