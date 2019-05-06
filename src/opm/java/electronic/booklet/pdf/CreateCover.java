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
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import opm.java.electronic.booklet.utils.Constants;

/**
 *
 * @author dnd
 */
public class CreateCover {

    public static String create(String upload_path, String newSerial) throws FileNotFoundException, DocumentException {
        // create document
        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(upload_path + "/" + Constants.cons.COVER_PAGE + ".pdf"));
        document.open();
        document.newPage();
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            //add cover page
            Cover.cover(document);
            Create_HeaderFooter.cover(null, writer, 0, newSerial);
        } catch (Exception dex) {
            dex.printStackTrace();
        }  finally {
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
            return PDF_PNGConverter.render(upload_path + "/" + Constants.cons.COVER_PAGE + ".pdf", Constants.cons.COVER_PAGE, upload_path);
            //Desktop.getDesktop().open(new File(file_path+"/HeaderFooter.pdf"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
