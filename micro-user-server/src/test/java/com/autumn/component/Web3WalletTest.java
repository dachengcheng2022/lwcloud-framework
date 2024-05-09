package com.autumn.component;

import com.alibaba.fastjson2.JSONObject;
import com.autumn.utils.HttpClient;
import com.autumn.utils.HttpUtils;
import com.autumn.web3.NodeProvider;
import com.autumn.web3j.SyncedWeb3jProvider;
import org.apache.ibatis.transaction.Transaction;
import org.checkerframework.common.value.qual.ArrayLen;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionDecoder;
import org.web3j.protocol.core.DefaultBlockParameter;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Web3WalletTest {
    private NodeProvider nodeProvider;

    private String str = "https://explorer.chiliz.com/api?module=account&action=tokenbalance&contractaddress=%s&address=%s";
    @Before
    public void init(){
        String endpoint = "https://ethereum.blockpi.network/v1/rpc/public";
        SyncedWeb3jProvider syncedWeb3jProvider = new SyncedWeb3jProvider(endpoint);
        nodeProvider= new NodeProvider(syncedWeb3jProvider);



    }

    @Test
    public void query_tokenBlance() throws IOException {
        String contract = "0x4c3e460b8e8285de57c8b1b2b688847b995b71d6";
        String mainAddress = "0xf6cec788bfff5c9c00debb97d184b28069824d84";
        String formatmain = String.format(str, contract, mainAddress);
        String resultmain = HttpUtils.doGetSSL(formatmain, null, null);

        System.err.println("mainaddress= " + JSONObject.parseObject(resultmain).getString("result"));

        List<String> strings = way2ByFile();
        BigDecimal all = strings.stream().map(v -> {
            try {
                String format = String.format(str, contract, v);
                String result = HttpUtils.doGetSSL(format, null, null);
                System.err.println("dacheng__  " + v + "  _______ " + JSONObject.parseObject(result).getString("result"));
//                amount = amount.add(new BigDecimal(result));
                return new BigDecimal(JSONObject.parseObject(result).getString("result") == null?"0":JSONObject.parseObject(result).getString("result"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.err.println("all="+all);
    }

    private List<String> way2ByFile() {
        List<String> address = new ArrayList<>();
        try {
            String thisLine = null;
            File file = ResourceUtils.getFile("classpath:address.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((thisLine = bufferedReader.readLine()) != null) {
                address.add(thisLine);
            }
//            System.out.println("方式2 文件内容:" + bufferedReader.readLine());
        } catch (Exception e) {
            System.out.println("方式2 错误:" + e);
        }
        return address;
    }

    public static void main(String[] args) {
        RawTransaction decode = TransactionDecoder.decode("0xf86e808402faf080830493e094e82c8a9b11ddf3075b0ffec5f1d06803713aeff9872386f26fc10000808302b693a02c0ad3c4614f6b0980959e689d7d1cb4b80bb6c16e4d5427db9d05d5d85d1e77a06532445cc8f2773a8175c4d0628b52d37887a16ec47d1ae59da4df26227ed336");
        System.err.println(decode);
    }
}
