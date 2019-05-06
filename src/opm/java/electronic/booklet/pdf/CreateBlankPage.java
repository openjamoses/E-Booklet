/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import opm.java.electronic.booklet.controller.WaterMark;
import opm.java.electronic.booklet.utils.Constants;

/**
 *
 * @author john
 */
public class CreateBlankPage {

    public static String create(int page, String path, String newSerial) throws FileNotFoundException, DocumentException {
        // create document
        Document document = new Document(PageSize.A4, 5, 5, 90, 36);
        //document = new Document
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "/" + Constants.cons.BLANK_PAGE + page + ".pdf"));
        document.open();
        document.newPage();
        DecimalFormat df = new DecimalFormat("0.00");
        AddPage.add(document, page);
        try {
           
            //Add blank page
            AddPage.add(document, page);
            Create_HeaderFooter.others(null, writer, page, newSerial);
        

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (document != null) {
                //close the document
                document.close();
            }
            if (writer != null) {
                //close the writer
                writer.close();
            }
        }

        try {
            String output = path + "/" + Constants.cons.BLANK_PAGE + "_" + page + ".pdf";
            String pdfInput = path + "/" + Constants.cons.BLANK_PAGE + page + ".pdf";
            boolean create_waterMark = WaterMark.addWater(pdfInput, output);
            
            if (create_waterMark) {
                return PDF_PNGConverter.render(output, Constants.cons.BLANK_PAGE + page, path);

            } else {
                return null;
            }

            //Desktop.getDesktop().open(new File(file_path+"/HeaderFooter.pdf"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
