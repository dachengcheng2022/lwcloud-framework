package com.bt.utils;

import com.bt.common.CoinSeriesType;
import com.bt.component.log.logback.LoggerBuilder;
import com.bt.component.model.erc20.Erc20Log;
import org.apache.commons.lang3.StringUtils;
import org.chain3j.protocol.core.methods.response.Log;
import org.chain3j.protocol.core.methods.response.TransactionReceipt;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoacUtils {

	private final static Logger logger = LoggerBuilder.getLogger(CoinSeriesType.ethseries.logName);
	
    public final static String ERC20TRANSFER_CODE = "0xa9059cbb";

    public final static Map<String, String> EVENT_LOG_ID_MAP = new HashMap<String, String>();

    static {
    	EVENT_LOG_ID_MAP.put(ERC20TRANSFER_CODE, "0xddf252ad");
    }
    
    public static List<Log> filterResultEvent(String eventId, List<Log> logList) {
        List<Log> filterList = new ArrayList<>();
        for (Log log : logList) {
            if (log.getTopics() != null && log.getTopics().size() > 0) {
                String logEventId = log.getTopics().get(0);
                if (StringUtils.isNotBlank(logEventId) && logEventId.startsWith(eventId)) {
                    filterList.add(log);
                }
            }
        }
        return filterList;
    }

    //标准ERC20合约必须是以0xa9059cbb开头的138字符串组成
	public static boolean isErc20Tx(String inputs) {
		if (StringUtils.isNotBlank(inputs) && inputs.length() == 138) {
			String method = inputs.substring(0, 10);
			if (ERC20TRANSFER_CODE.equalsIgnoreCase(method)) {
				return true;
			}
		}
		return false;
	}

    public static String getAddress(String addrStr) {
        int length = addrStr.length();
        return "0x" + addrStr.substring(length - 40, length).toLowerCase();
    }

    public static String getTokenAddress(String inputs) {
    	return getAddress(inputs.substring(10, 74));
    }
    
    public static BigInteger getTokenValue(String inputs) {
    	return new BigInteger(inputs.substring(74, 138), 16);
    }

    /**
     * 标准ERC20合约必须是
     * 1.日志的topics必须3部分组成，第一个是固定标识(以0xddf252ad开头),第二部分表示真实发送地址,第三部分表示真实接收地址
     * 2.日志的data部分只有66字节长0x+64字节转移金额
     * 3.input部分是138字节组成的：0xa9059cbb+64字节目的地址+64字节转移金额
     * 优先考虑日志(第一，二点)，如果日志不存在的情况下再考虑第三点直接解析input部分
     * @return
     */
    public static List<Erc20Log> parserErc20Log(String inputs, TransactionReceipt receipt) {
    	boolean status = receipt.getStatus().equals("0x1");
    	List<Erc20Log> erc20Logs = new ArrayList<Erc20Log>();
    	if (status) {
    		//先按日志解析
    		List<Log> logs = filterResultEvent(EVENT_LOG_ID_MAP.get(EthUtils.ERC20TRANSFER_CODE), receipt.getLogs());
    		
    		for (Log log : logs) {
    			if (log.getTopics().size() != 3) {
    				logger.warn("no support token tx:{}",receipt.getTransactionHash());
        			continue;
        		}
    			if (log.getData() == null) {
    				continue;
    			}
                BigInteger quantity = new BigInteger(log.getData().substring(2), 16);
                int logindex = log.getLogIndex().intValue();
                String toAddr = getAddress(log.getTopics().get(2));
                String contractAddress = log.getAddress();
                
                Erc20Log erc20Log = new Erc20Log();
                erc20Log.setAddress(toAddr);
                erc20Log.setAmount(quantity);
                erc20Log.setLogIndex(logindex);
                erc20Log.setContractAddress(contractAddress);
                erc20Logs.add(erc20Log);
    		}
    	}
    	//如果日志解析不到数据，只能从inputs解析了
    	if (CollectionUtils.isEmpty(erc20Logs)) {
    		Erc20Log erc20Log = parserErc20Log(inputs, receipt.getTo());
    		if (erc20Log != null) {
    			erc20Logs.add(erc20Log);
    		}
    	}
    	return erc20Logs;
    }
    
    public static Erc20Log parserErc20Log(String inputs, String contractAddress) {
    	if (EthUtils.isErc20Tx(inputs)) {
            String toAddr = EthUtils.getAddress(inputs.substring(10, 74));
            BigInteger quantity = new BigInteger(inputs.substring(74, 138), 16);
            
            Erc20Log erc20Log = new Erc20Log();
            erc20Log.setAddress(toAddr);
            erc20Log.setAmount(quantity);
            erc20Log.setContractAddress(contractAddress);
            return erc20Log;
    	}
    	return null;
    }
}
