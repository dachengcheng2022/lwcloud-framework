package com.autumn.web3j;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.exceptions.ContractCallException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

@Slf4j
public class SyncedWeb3jProvider {

    private static final int MAX_RETRIES = 100;


    protected Web3j stickyWeb3j;

    protected Web3jService web3jService;

    protected String rpcEndPoint;

    public SyncedWeb3jProvider(String endPoint) {
        this.rpcEndPoint = endPoint;
    }

    /**
     * Build a new web3j instance which connected to synced geth node
     */
    public Web3j buildSyncedWeb3jInstance() {
        Web3j stickyWeb3j = null;
        int tries = 0;
        while (tries++ < MAX_RETRIES) {
            if (isSynced(stickyWeb3j)) {
                return stickyWeb3j;
            } else {
                if (stickyWeb3j != null) {
                    stickyWeb3j.shutdown();
                }
                stickyWeb3j = buildStickyWeb3j(rpcEndPoint);
            }
        }
        if (tries == MAX_RETRIES) {
            throw new RuntimeException("Plz ignore this exception.web3j is syncing block.");
        }

        throw new RuntimeException("Unable to build synced sticky web3j");
    }


    /**
     * Get a web3j instance which connected to synced geth node
     */
    public synchronized Web3j getSyncedWeb3j() {
        if (isSynced(stickyWeb3j)) {
            return stickyWeb3j;
        } else {
            if (stickyWeb3j != null) {
                stickyWeb3j.shutdown();
            }
            stickyWeb3j = buildSyncedWeb3jInstance();
            return stickyWeb3j;
        }
    }

    /**
     * Get web3jServce
     * @return
     */
    public Web3jService getWeb3jService() {
        return web3jService;
    }

    private boolean isSynced(Web3j stickyWeb3j) {
        if (stickyWeb3j == null) return false;
        try {
            boolean syncing = true;
//          syncing = stickyWeb3j.ethSyncing().send().isSyncing();
            return syncing;
        } catch (Throwable e) {
            log.info("Exception while checking has synced {}",e.getMessage());
            return false;
        }
    }


    public BigInteger getBlockNumber(DefaultBlockParameter defaultBlockParameter) throws IOException {
        if (defaultBlockParameter instanceof DefaultBlockParameterNumber) {
            return ((DefaultBlockParameterNumber) defaultBlockParameter).getBlockNumber();
        } else {
            EthBlock latestEthBlock = this.getSyncedWeb3j().ethGetBlockByNumber(defaultBlockParameter, false).send();
            return latestEthBlock.getBlock().getNumber();
        }
    }

    /**
     * Web3j client disabled cookie, by default,
     * use the sticky session/cookie, which enables the AWS load balancer to bind a user's session to a specific instance.
     */
    protected Web3j buildStickyWeb3j(String rpcEndPoint) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> true)
                .cookieJar(new StickyCookieJar())
                .sslSocketFactory(createSSLSocketFactory(),new TrustAllCerts()).build();
        this.web3jService = new HttpService(rpcEndPoint,okHttpClient, false);
        return Web3j.build(this.web3jService);
    }

    //这里是创建一个SSLSocketFactory,提供给上面的 .sslSocketFactory()
    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public BigDecimal getCallValue(String from, String to, Function function) throws IOException {
        List<Type> types = this.executeCall(from, to, function);
        if (!types.isEmpty()) {
            Uint256 balance = (Uint256) types.get(0);
            return new BigDecimal(balance.getValue(), 0);
        } else {
            throw new ContractCallException("Empty value (0x) returned from contract");
        }
    }

    /**
     * Execute constant function call - i.e. a call that does not change state of the contract
     *
     * @param function to call
     * @return {@link List} of values returned by function call
     */
    public List<Type> executeCall(String from, String to, Function function) throws IOException {
        String encodedFunction = FunctionEncoder.encode(function);
        org.web3j.protocol.core.methods.request.Transaction ethCallTransaction = org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(from, to, encodedFunction);
        String value = getSyncedWeb3j().ethCall(ethCallTransaction, DefaultBlockParameterName.LATEST).send().getValue();
        return FunctionReturnDecoder.decode(value, function.getOutputParameters());
    }
}
