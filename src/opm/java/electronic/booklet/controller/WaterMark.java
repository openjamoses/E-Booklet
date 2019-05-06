/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileOutputStream;

/**
 *
 * @author john
 */
public class WaterMark {

    public static boolean addWater(String pdfInput, String pdfOutput) {

        System.out.println(pdfInput+" \t "+pdfOutput);
        // read existing pdf
        
        try {
            PdfReader reader = new PdfReader(pdfInput);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pdfOutput));
            ///PdfStamper stamper1 = new PdfStamper
            // text watermark
            Font FONT = new Font(Font.FontFamily.HELVETICA, 34, Font.BOLD, new GrayColor(0.5f));
            Phrase p = new Phrase("Mbarara University of Science and Technology", FONT);

            // image watermark
            Image img = Image.getInstance("logo.png");
            float w = img.getScaledWidth();
            float h = img.getScaledHeight();

            // properties
            PdfContentByte over;
            Rectangle pagesize;
            float x, y;

            // loop over every page
            int n = reader.getNumberOfPages();
            for (int i = 1; i <= n; i++) {

                // get page size and position
                pagesize = reader.getPageSizeWithRotation(i);
                x = (pagesize.getLeft() + pagesize.getRight()) / 2;
                y = (pagesize.getTop() + pagesize.getBottom()) / 2;
                over = stamper.getOverContent(i);
                over.saveState();
                // set transparency
                PdfGState state = new PdfGState();
                state.setFillOpacity(0.2f);
                over.setGState(state);

                // add watermark text and image
                if (i % 2 != 1) {
                    ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
                } else {
                    over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
                }

                over.restoreState();
            }
            stamper.close();
            reader.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        }
        return false;
    }
    
    
    public static boolean addWater2(String pdfInput, String pdfOutput) {

        System.out.println(pdfInput+" \t "+pdfOutput);
        // read existing pdf
        
        try {
            PdfReader reader = new PdfReader(pdfInput);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pdfOutput));
            ///PdfStamper stamper1 = new PdfStamper
            // text watermark
            Font FONT = new Font(Font.FontFamily.HELVETICA, 34, Font.BOLD, new GrayColor(0.5f));
            Phrase p = new Phrase("Mbarara University of Science and Technology", FONT);

            // image watermark
            Image img = Image.getInstance("logo.png");
            float w = img.getScaledWidth();
            float h = img.getScaledHeight();

            // properties
            PdfContentByte over;
            Rectangle pagesize;
            float x, y;

            // loop over every page
            int n = reader.getNumberOfPages();
            for (int i = 1; i <= n; i++) {

                // get page size and position
                pagesize = reader.getPageSizeWithRotation(i);
                x = (pagesize.getLeft() + pagesize.getRight()) / 2;
                y = (pagesize.getTop() + pagesize.getBottom()) / 2;
                over = stamper.getOverContent(i);
                over.saveState();
                // set transparency
                PdfGState state = new PdfGState();
                state.setFillOpacity(0.2f);
                over.setGState(state);

                // add watermark text and image
                if (i > 1) {
                     over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
                    //ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
                }
                //else {
                 //   over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
                //}

                over.restoreState();
            }
            stamper.close();
            reader.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        }
        return false;
    }
}
