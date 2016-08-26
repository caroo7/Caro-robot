package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.*;
import javax.print.Doc;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class PageLoader {

    private static final String userAgent = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:48.0) Gecko/20100101 Firefox/48.0";

    public Document getPage(String requestUrl) {
        Document document = null;
        try {
            Connection.Response response = Jsoup.connect(requestUrl)
                    .userAgent(userAgent)
                    .method(Connection.Method.GET)
                    .timeout(0)
                    .execute();
            document = response.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}