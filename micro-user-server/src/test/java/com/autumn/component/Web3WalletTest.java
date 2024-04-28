package com.autumn.component;

import com.autumn.web3.NodeProvider;
import com.autumn.web3j.SyncedWeb3jProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Web3WalletTest {
    private NodeProvider nodeProvider;

    @Before
    public void init(){
        String endpoint = "";
        SyncedWeb3jProvider syncedWeb3jProvider = new SyncedWeb3jProvider(endpoint);
        nodeProvider= new NodeProvider(syncedWeb3jProvider);

    }

    @Test
    public void query_tokenBlance() throws IOException {
        nodeProvider.getAddressNonce("");
    }
}
