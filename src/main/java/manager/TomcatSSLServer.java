package manager;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

public class TomcatSSLServer {
    private static final String SSL_TYPE = "SSL";
    private static final String KS_TYPE = "JKS";
    private static final String X509 = "SunX509";
    private static final int port = 443;
    private static TomcatSSLServer sslServer;
    private SSLServerSocket serverSocket;

    public static TomcatSSLServer getInstance() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        if (sslServer==null){
            sslServer = new TomcatSSLServer();
        }
        return sslServer;
    }
    private TomcatSSLServer() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        SSLContext sslContext = createSSLContext();
        SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
        serverSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);
        serverSocket.setNeedClientAuth(true);
        String[] supported = serverSocket.getEnabledCipherSuites();
        serverSocket.setEnabledCipherSuites(supported);
    }
    private SSLContext createSSLContext() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(X509);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(X509);
        String serverKeyStoreFile = "d://tomcat.jks";
        String pass = "tomcat";
        char[] password = pass.toCharArray();
        KeyStore keyStore = KeyStore.getInstance(KS_TYPE);
        keyStore.load(new FileInputStream(serverKeyStoreFile),password);
        keyManagerFactory.init(keyStore,password);
        String clientKeyStoreFile = "";
        String clientPass = "client";
        char[] clientPassword = clientPass.toCharArray();
        KeyStore clientKeyStore = KeyStore.getInstance(KS_TYPE);
        clientKeyStore.load(new FileInputStream(clientKeyStoreFile),clientPassword);
        trustManagerFactory.init(clientKeyStore);
        SSLContext sslContext = SSLContext.getInstance(SSL_TYPE);
        sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),null);
        return sslContext;
    }

    public void startService(){
        SSLSocket sslSocket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        String tmpMsg = null;
        while (true){
            try {
                sslSocket = (SSLSocket) serverSocket.accept();
                reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                writer = new PrintWriter(sslSocket.getOutputStream());
                while ((tmpMsg=reader.readLine())!=null){
                    tmpMsg = "q";
                    writer.println(tmpMsg);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (sslSocket!=null){
                    try {
                        sslSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        TomcatSSLServer.getInstance().startService();
    }
}
