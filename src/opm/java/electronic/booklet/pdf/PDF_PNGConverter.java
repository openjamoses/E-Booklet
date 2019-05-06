/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.pdf;

import com.qoppa.pdfImages.PDFImages;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import opm.java.electronic.booklet.utils.Constants;

/**
 *
 * @author john
 */
public class PDF_PNGConverter {
    public static String render(String input, String output_file, String path) {
        // open PDF document
        try {
            PDFImages pdfDoc = new PDFImages(input, null);
            for (int count = 0; count < pdfDoc.getPageCount(); ++count) {
                // convert current page to PNG at a resolution of 150 dpi
                pdfDoc.savePageAsPNG(count, path+"/" + output_file + ".png", 150);
            }
            return path + "/"+output_file+".png";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
