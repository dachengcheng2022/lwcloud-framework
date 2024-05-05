package com.autumn.component;

import com.autumn.web3.NodeProvider;
import com.autumn.web3j.SyncedWeb3jProvider;
import org.checkerframework.common.value.qual.ArrayLen;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
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

    @Before
    public void init(){
        String endpoint = "https://ethereum.blockpi.network/v1/rpc/public";
        SyncedWeb3jProvider syncedWeb3jProvider = new SyncedWeb3jProvider(endpoint);
        nodeProvider= new NodeProvider(syncedWeb3jProvider);

    }

    @Test
    public void query_tokenBlance() throws IOException {
        String contract = "0x961C8c0B1aaD0c0b10a51FeF6a867E3091BCef17";

        List<String> strings = way2ByFile();
        strings.stream().forEach(v->{
            try {
                BigDecimal tokenBalance = nodeProvider.getTokenBalance(v, contract, 18);
                System.err.println(v + "  _______ " + tokenBalance.toPlainString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        });
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
