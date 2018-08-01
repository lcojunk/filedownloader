/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leo.example.filedownloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author sophora
 */
public class ApacheCommonDownloader {
    
    private int connectionTimeout = 5000;
    private int readTimeout = 5000;
    
    
    public void download (String url, String filename) throws MalformedURLException, IOException {
        FileUtils.copyURLToFile( new URL(url), new File(filename), connectionTimeout, readTimeout);
    }
}
