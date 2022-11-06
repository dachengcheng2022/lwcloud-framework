package com.autumn.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * HTTP 请求工具类
 *
 * @author lxl
 */
@Slf4j
public class HttpUtils {

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 20000;

    private HttpClientContext context = HttpClientContext.create();

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }


    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @param headers 需要添加的httpheader参数
     * @return
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, Object> params) {
        if (url.startsWith("https://")) {
            return doGetSSL(url, headers, params);
        } else {
            return doGetHttp(url, headers, params);
        }
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param headers 需要添加的httpheader参数
     * @param params
     * @return
     */
    public static String doGetHttp(String url, Map<String, String> headers, Map<String, Object> params) {
        HttpClient httpclient = new DefaultHttpClient();
        String apiUrl = url;
        if (params != null) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (String key : params.keySet()) {
                if (i == 0)
                    param.append("?");
                else
                    param.append("&");
                param.append(key).append("=").append(params.get(key));
                i++;
            }
            apiUrl += param;
        }

        String result = null;
        log("url: " + apiUrl);
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            //添加http headers
            if (headers != null && headers.size() > 0) {
                for (String key : headers.keySet()) {
                    httpGet.addHeader(key, headers.get(key));
                }
            }

            HttpResponse response = httpclient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            log("code : " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, Charset.defaultCharset());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送 SSL Get 请求（HTTPS），K-V形式
     * apiUrl API接口URL
     *
     * @param headers
     * @param params  参数map
     * @return
     */
    public static String doGetSSL(String url, Map<String, String> headers, Map<String, Object> params) {
        String apiUrl = url;
        String httpStr = null;
        if (null != params) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (String key : params.keySet()) {
                if (i == 0)
                    param.append("?");
                else
                    param.append("&");
                param.append(key).append("=").append(params.get(key));
                i++;
            }
            apiUrl += param;
        }
        CloseableHttpResponse response = null;
        try {

            //信任所有
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setConfig(requestConfig);
            //添加http headers
            if (headers != null && headers.size() > 0) {
                for (String key : headers.keySet()) {
                    httpGet.addHeader(key, headers.get(key));
                }
            }
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }


    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param url
     * @param headers 需要添加的httpheader参数
     * @return
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, Object> params) {
        if (url.startsWith("https://")) {
            return doPostSSL(url, headers, params);
        } else {
            return doPostHttp(url, headers, params);
        }
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl  API接口URL
     * @param headers 需要添加的httpheader参数
     * @param params  参数map
     * @return
     */
    public static String doPostHttp(String apiUrl, Map<String, String> headers, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                httpPost.addHeader(key, headers.get(key));
            }
        }
        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String value = StringUtils.EMPTY;
                if (entry.getValue() != null) {
                    value = entry.getValue().toString();
                }
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), value);
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            log(response.toString());
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param headers 需要添加的httpheader参数
     * @param json    json对象
     * @return
     */
    public static String doPostHttp(String apiUrl, Map<String, String> headers, Object json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                httpPost.addHeader(key, headers.get(key));

            }
        }
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            log(response.getStatusLine().getStatusCode() + "");
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSL(String apiUrl, Map<String, String> headers, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                httpPost.addHeader(key, headers.get(key));

            }
        }
        try {
            //信任所有
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            httpPost.setConfig(requestConfig);
            if (null != params) {
                List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                            .getValue().toString());
                    pairList.add(pair);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            }

            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl API接口URL
     * @param json   JSON对象
     * @return
     */
    public static String doPostSSLV2(String apiUrl, Map<String, String> headers, Object json) {
        HttpPost httpPost = new HttpPost(apiUrl);

        CloseableHttpResponse response = null;
        String httpStr = null;
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                httpPost.addHeader(key, headers.get(key));
            }
        }
        try {
            //信任所有 basic认证  https://blog.csdn.net/qincidong/article/details/82416589
//            CredentialsProvider provider = new BasicCredentialsProvider();
//            // Create the authentication scope
//            AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
//            // Create credential pair，在此处填写用户名和密码
//            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("test","test");
//            // Inject the credentials
//            provider.setCredentials(scope, credentials);

            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                String string = EntityUtils.toString(response.getEntity(), "utf-8");
                System.err.println(string);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        // 创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        // TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext   
        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("TLS");
            // 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[]{xtm}, null);
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(ctx);
            return sslsf;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置信任自签名证书
     *
     * @param inputStream  密钥库路径流
     * @param keyStorepass 密钥库密码
     * @return
     */
    private static SSLContext custom(InputStream inputStream, String keyStorepass) {
        System.setProperty("jsse.enableSNIExtension", "false");
        SSLContext sslContext = null;

        try {
            if (null == inputStream) {
                return null;
            }
            //获取证书路径
            //File certFile = new File(keyStorePath);  //测试证书，生产环境需要替换
            //访问Java密钥库，JKS是keytool创建的Java密钥库，保存密钥。
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(inputStream, keyStorepass.toCharArray());
            //创建用于管理JKS密钥库的密钥管理器
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            //初始化证书
            kmf.init(ks, keyStorepass.toCharArray());
            //同位体验证信任决策源//同位体验证可信任的证书来源
            TrustManager[] tm = {new MyX509TrustManager()};
            //初始化安全套接字
            sslContext = SSLContext.getInstance("TLSv1.2");
            //初始化SSL环境。第二个参数是告诉JSSE使用的可信任证书的来源，设置为null是从javax.net.ssl.trustStore中获得证书。
            //第三个参数是JSSE生成的随机数，这个参数将影响系统的安全性，设置为null是个好选择，可以保证JSSE的安全性。
            sslContext.init(kmf.getKeyManagers(), tm, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext;
    }


    /**
     * 发送 SSL POST 请求（HTTPS），XML形式
     *
     * @param url         请求地址
     * @param inputStream 密钥库路径流
     * @param jksPwd      密钥库密码
     * @param tr1XML      xml参数
     * @param auth        Authorization
     * @param encoding    编码 默认 UTF-8
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doPostSSLSend(String url, InputStream inputStream, String jksPwd, String tr1XML, String auth, String encoding) throws ClientProtocolException, IOException {
        if (StringUtils.isBlank(encoding)) {
            encoding = "UTF-8";
        }
        String body = "";
        //jksPwd是我自己的密钥库的密码，你可以替换成自己的
        //如果密码为空，则用nopassword代替
        SSLContext sslcontext = custom(inputStream, jksPwd);
        if (null == sslcontext) {

            return null;
        }
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 设置连接池
        // 设置连接池大小
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();
        // CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        HttpEntity entity = new StringEntity(tr1XML, encoding);
        //设置header信息
        httpPost.addHeader("Content-Type", "application/soap+xml;charset=UTF-8");
        httpPost.setHeader("Authorization", auth);
        httpPost.setEntity(entity);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity Rentity = response.getEntity();
        if (Rentity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(Rentity, encoding);

        }
        if (response != null) {
            EntityUtils.consume(Rentity); //释放链接
            response.close();
        }
        return body;
    }


    public static void log(String msg) {
        log.info("{} : {}", LocalDateTime.now(), msg);
    }

    public void addUserOAuth(String username, String password) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        org.apache.http.auth.Credentials credentials = new org.apache.http.auth.UsernamePasswordCredentials(username, password);
        credsProvider.setCredentials(org.apache.http.auth.AuthScope.ANY, credentials);
        this.context.setCredentialsProvider(credsProvider);
    }
}