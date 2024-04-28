package com.autumn.web3;

import com.autumn.exception.custom.CommonException;
import com.autumn.web3j.SimilarErc20;
import com.autumn.web3j.SyncedWeb3jProvider;
import com.autumn.web3j.Web3jUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.web3j.protocol.core.*;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static org.web3j.abi.FunctionEncoder.encode;

@Slf4j
public class NodeProvider {


    private final SyncedWeb3jProvider syncedWeb3JProvider;
    private final static String TX_SUCCESS_FLAG = "0x1";

    public NodeProvider(SyncedWeb3jProvider syncedWeb3JProvider) {
        this.syncedWeb3JProvider = syncedWeb3JProvider;
    }

    public EthBlock getBlock(DefaultBlockParameter defaultBlockParameter) throws IOException {
        return Web3jUtils.getBlock(syncedWeb3JProvider.getSyncedWeb3j(), defaultBlockParameter, false);
    }

    public EthBlock getBlock(BigInteger blockHeight) throws IOException {
        return Web3jUtils.getBlock(syncedWeb3JProvider.getSyncedWeb3j(), new DefaultBlockParameterNumber(blockHeight), false);
    }

    public boolean isTransactionSuccess(String txHash) throws ExecutionException, InterruptedException {
        EthGetTransactionReceipt ethGetTransactionReceipt = this.syncedWeb3JProvider.getSyncedWeb3j().ethGetTransactionReceipt(txHash).sendAsync().get();
        if (!Objects.isNull(ethGetTransactionReceipt)) {
            String status = ethGetTransactionReceipt.getTransactionReceipt().get().getStatus();
            if (StringUtils.isNotEmpty(status)) {
                status = status.substring(2);
                return TX_SUCCESS_FLAG.equals(status);
            }
        }
        return false;
    }

    public List<EthLog.LogObject> getLogResultsFromBlock(EthBlock.Block block, Set<String> contractAddress){
        return Web3jUtils.getAllEventLogsFromBlock(syncedWeb3JProvider.getSyncedWeb3j(), block, contractAddress);

    }

    public BigInteger getAddressNonce(String fromAddress) throws IOException {
        EthGetTransactionCount ethGetTransactionCount = syncedWeb3JProvider.getSyncedWeb3j().ethGetTransactionCount(
                fromAddress, DefaultBlockParameterName.LATEST).send();
        if (ethGetTransactionCount.hasError()) {
            Response.Error error = ethGetTransactionCount.getError();
            throw new CommonException(String.format("Unable to get transaction count for address '%s' due to '%s', error code '%s'", fromAddress, error.getMessage(), error.getCode()));
        }
        return ethGetTransactionCount.getTransactionCount();
    }

    public BigInteger getCurrentBlockHeight() throws IOException {
        return this.syncedWeb3JProvider.getBlockNumber(DefaultBlockParameterName.LATEST);
    }

    public BigInteger getTargetBalance(String address, DefaultBlockParameter defaultBlockParameter) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<EthGetBalance> balanceFuture = this.syncedWeb3JProvider.
                getSyncedWeb3j().ethGetBalance(address, defaultBlockParameter).sendAsync();

        if (balanceFuture != null) {
            EthGetBalance egb = balanceFuture.get(10L, TimeUnit.SECONDS);
            if (!egb.hasError()) {
                return egb.getBalance();
            } else {
                log.error("Getting balance for {} resulted in an error: {}", address, egb.getError().getMessage());
            }
        }

        return null;
    }

    public EthCall getTokenBalance(String address, String contractAddress, DefaultBlockParameter defaultBlockParameter) throws InterruptedException, ExecutionException, TimeoutException {
        String data = encode(SimilarErc20.balanceOf(address));
        org.web3j.protocol.core.methods.request.Transaction ethCallTransaction = org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(address, contractAddress, data);
        CompletableFuture<EthCall> ethCall = this.syncedWeb3JProvider.getSyncedWeb3j().ethCall(ethCallTransaction, defaultBlockParameter).sendAsync();
        EthCall ethCall1 = ethCall.get(10, TimeUnit.SECONDS);
        String value = ethCall1.getValue();
        return ethCall1;
    }

    public EthCall ethCallContract(org.web3j.protocol.core.methods.request.Transaction ethCallTransaction,DefaultBlockParameter defaultBlockParameter) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<EthCall> ethCall = this.syncedWeb3JProvider.getSyncedWeb3j().ethCall(ethCallTransaction, defaultBlockParameter).sendAsync();
        return ethCall.get(10, TimeUnit.SECONDS);
    }

    public BigInteger getLatestBalance(String address) throws InterruptedException, ExecutionException, TimeoutException {
        return getTargetBalance(address, DefaultBlockParameterName.LATEST);
    }

    public Transaction getTransactionByHash(String txHash) {
        try {
            return this.syncedWeb3JProvider.getSyncedWeb3j().ethGetTransactionByHash(txHash).send().getTransaction().orElse(null);
        } catch (IOException e) {
            log.warn("Failed to get txHash %s", txHash);
            return null;
        }
    }

    public EthSendTransaction broadcastTransaction(String signedTx) throws IOException {
        return syncedWeb3JProvider.getSyncedWeb3j().ethSendRawTransaction(signedTx).send();
    }

    /**
     * Return contract bytecode without HEX_PREFIX '0x'
     */
    public String getContractBytecode(String contractAddress) throws Exception {
        EthGetCode ethGetCode = this.syncedWeb3JProvider.getSyncedWeb3j()
                .ethGetCode(contractAddress, DefaultBlockParameterName.LATEST)
                .send();
        if (ethGetCode.hasError()) {
            throw new UnsupportedOperationException(
                    "Error while getting contract binary with web3j");
        }
        return Numeric.cleanHexPrefix(ethGetCode.getCode());
    }

    public BigInteger getBlockNumber(
            DefaultBlockParameter defaultBlockParameter) throws IOException {
        if (defaultBlockParameter instanceof DefaultBlockParameterNumber) {
            return ((DefaultBlockParameterNumber) defaultBlockParameter).getBlockNumber();
        } else {
            EthBlock latestEthBlock = this.syncedWeb3JProvider.getSyncedWeb3j().ethGetBlockByNumber(
                    defaultBlockParameter, false).send();
            return latestEthBlock.getBlock().getNumber();
        }
    }


    public List<EthLog.LogObject> getLogResultsFromBlock(EthBlock.Block block)  {

        return Web3jUtils.getAllEventLogsFromBlock(syncedWeb3JProvider.getSyncedWeb3j(), block, Collections.emptySet());

    }

    public List<String> getValidTransactions(List<String> txHashs) {
        try {
            if (txHashs == null || txHashs.size() == 0) {
                log.warn("params txHashs is null or size is zero");
                return new ArrayList<String>();
            }
            BatchRequest batchRequest = this.syncedWeb3JProvider.getSyncedWeb3j().newBatch();
            txHashs.stream().forEach(txHash ->
                    batchRequest.add(new Request<String, EthGetTransactionReceipt>("eth_getTransactionReceipt",
                            Arrays.asList(txHash), this.syncedWeb3JProvider.getWeb3jService(), EthGetTransactionReceipt.class))
            );
            List<EthGetTransactionReceipt> ethTransactions = (List<EthGetTransactionReceipt>) batchRequest.send().getResponses();
            return ethTransactions.stream().filter(tx -> tx.getTransactionReceipt().get().getStatus().equals(TX_SUCCESS_FLAG))
                    .map(tx -> tx.getTransactionReceipt().get().getTransactionHash()).collect(Collectors.toList());
        } catch (IOException e) {
            log.warn(String.format("Failed to get txHash '%s'", txHashs.size()), e);
            return null;
        }
    }

    public List<Transaction> getTransactionsByHashsBatch(List<String> txHashs) {
        try {
            if (txHashs == null || txHashs.size() == 0) {
                log.warn("params txHashs is null or size is zero");
                return new ArrayList<Transaction>();
            }
            BatchRequest batchRequest = this.syncedWeb3JProvider.getSyncedWeb3j().newBatch();
            txHashs.stream().forEach(txHash ->
                    batchRequest.add(new Request<String, EthTransaction>("eth_getTransactionByHash",
                            Arrays.asList(txHash), this.syncedWeb3JProvider.getWeb3jService(), EthTransaction.class))
            );
            List<EthTransaction> ethTransactions = (List<EthTransaction>) batchRequest.send().getResponses();
            return ethTransactions.stream().filter(tx -> tx != null && tx.getResult() != null)
                    .map(tx -> tx.getResult()).collect(Collectors.toList());
        } catch (IOException e) {
            log.warn(String.format("Failed to get txHash '%s'", txHashs.size()), e);
            return null;
        }
    }

    public BigInteger getGasPrice(){
        try {
            return this.syncedWeb3JProvider.getSyncedWeb3j().ethGasPrice().send().getGasPrice();
        } catch (IOException e) {
            log.warn("Failed to get gasPrice", e);
            return null;
        }
    }
}