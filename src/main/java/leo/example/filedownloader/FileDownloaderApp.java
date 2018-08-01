/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leo.example.filedownloader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author sophora
 */
public class FileDownloaderApp {
    private static void appacheCommonExample(){
        String filename = "picture.jpg";
        String url = "http://www.baeldung.com/wp-content/uploads/2016/05/baeldung-rest-widget-main-1.2.0.jpg";
        try {
            new ApacheCommonDownloader().download(url, filename);
        } catch (IOException ex) {
            //Logger.getLogger(FileDownloaderApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    private static void appacheHttpComponentsExample(){
        String filename = "baeldung-rest-widget-main-1.2.0.jpg";
        String url = "http://www.baeldung.com/wp-content/uploads/2016/05/baeldung-rest-widget-main-1.2.0.jpg";
        //new HttpComponentsDownloader().download(url, filename);
        String filename2 = "juengster-mathematik-professor-120~_v-gseapremiumxl.jpg";
        String url2 = "https://www1.wdr.de/nachrichten/rheinland/juengster-mathematik-professor-120~_v-gseapremiumxl.jpg";
        new HttpComponentsDownloader(HttpClients.createDefault()).download(url, filename).download(url2, filename2);
    }

    public static void javaIoExample() {
        String fileURL = "http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar";
        String url = "http://www.baeldung.com/wp-content/uploads/2016/05/baeldung-rest-widget-main-1.2.0.jpg";
        String saveDir = "";
        try {
            new JavaIoDownloader().downloadFile(url, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
    
    public static void main(String[] args) {
        System.out.println("FileDownloaderApp");
        appacheHttpComponentsExample();
    }
}
