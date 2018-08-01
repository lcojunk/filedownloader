/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leo.example.filedownloader.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import static org.apache.commons.io.IOUtils.EOF;

/**
 *
 * @author sophora
 */
public class InputOutputStreamCopier {
    private final static Logger LOG =  Logger.getLogger(InputOutputStreamCopier.class.getName());
    private final int bufferSize;
    private long count = 0;

    public InputOutputStreamCopier(int bufferSize) {
        this.bufferSize = bufferSize;
    }



    /**
     * Copies bytes from an {@link InputStream} <code>source</code> to a file
     * <code>destination</code>. The directories up to <code>destination</code>
     * will be created if they don't already exist. <code>destination</code>
     * will be overwritten if it already exists.
     * The {@code source} stream is closed.
     * See {@link #copyToFile(InputStream, File)} for a method that does not close the input stream.
     *
     * @param source      the <code>InputStream</code> to copy bytes from, must not be {@code null}, will be closed
     * @param destination the non-directory <code>File</code> to write bytes to
     *                    (possibly overwriting), must not be {@code null}
     * @throws IOException if <code>destination</code> is a directory
     * @throws IOException if <code>destination</code> cannot be written
     * @throws IOException if <code>destination</code> needs creating but can't be
     * @throws IOException if an IO error occurs during copying
     * @since 2.0
     */
    public void copyInputStreamToFile(final InputStream source, final File destination) throws IOException {
        try (InputStream in = source) {
            copyToFile(in, destination);
        }
//            LOG.log(Level.INFO, "==========================");
//            LOG.log(Level.INFO, "Total " + count + " bytes copied");
        System.out.println("==========================\n"+"Total " + count + " bytes copied");
    }


    private void copyToFile(final InputStream source, final File destination) throws IOException {
        try (InputStream in = source;
            OutputStream out = FileUtils.openOutputStream(destination)) {
            copy(in, out);
        }
    }
    
    private long copy(final InputStream input, final OutputStream output) throws IOException {
        count = copyLarge(input, output, new byte [bufferSize]);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return count;
    }
    
    private long copyLarge(final InputStream input, final OutputStream output, final byte[] buffer)
           throws IOException {
        count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
            //LOG.log(Level.INFO, count + " bytes copied...");
            System.out.println(count + " bytes copied...");
        }
        return count;
    }   
    
}
