package com.autumn.component;

import com.alibaba.fastjson2.JSONObject;
import com.autumn.utils.HttpUtils;
import com.autumn.web3.NodeProvider;
import com.autumn.web3j.SyncedWeb3jProvider;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.math.BigDecimal;
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
    public void query_tokenBlance_withWeb3j() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        String contract = "0x5faa989af96af85384b8a938c2ede4a7378d9875";
        String mainAddress = "0x8fca4ade3a517133ff23ca55cdaea29c78c990b8";
        Integer decimal = 18;

        BigDecimal maintokenBalance = nodeProvider.getTokenBalance(mainAddress, contract, decimal);

        System.err.println("mainaddress= " + maintokenBalance);

        List<String> strings = way2ByFile();
        BigDecimal all = strings.stream().map(v -> {
            try {
                BigDecimal tokenBalance = nodeProvider.getTokenBalance(v, contract, decimal);
                System.err.println("dacheng__  " + v + "  _______ " + tokenBalance);
                return tokenBalance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.err.println("all="+all);
    }

    @Test
    public void query_tokenBlance() throws IOException {
        String contract = "0x5faa989af96af85384b8a938c2ede4a7378d9875";
        String mainAddress = "0x8fca4ade3a517133ff23ca55cdaea29c78c990b8";
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


}
