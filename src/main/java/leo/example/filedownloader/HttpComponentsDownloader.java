/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leo.example.filedownloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import leo.example.filedownloader.utils.InputOutputStreamCopier;
import org.apache.commons.io.FileUtils;
import static org.apache.commons.io.FileUtils.openOutputStream;
import org.apache.commons.io.IOUtils;
import static org.apache.commons.io.IOUtils.EOF;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author sophora
 */
public class HttpComponentsDownloader {
    
    private CloseableHttpClient httpClient;
    private boolean ok;
    private int bufferSize = 32756;

    public HttpComponentsDownloader(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
    
    public HttpComponentsDownloader download(String url, String imgSavePath) {
        ok = true;
        HttpGet httpGet = createRequest(url);
        try {
            System.out.println("Downloading: '" + url + "' => '" + imgSavePath + "'");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity imageEntity = httpResponse.getEntity();
            if (imageEntity != null) {
                new InputOutputStreamCopier(bufferSize).copyInputStreamToFile(imageEntity.getContent(), new File(imgSavePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
            ok = false;
        }
        httpGet.releaseConnection();
        return this;
    }    

    private HttpGet createRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.11 Safari/537.36");
        httpGet.addHeader("Referer", "https://www.google.com");
        return httpGet;
    }

    public boolean isOk() {
        return ok;
    }
    
}
