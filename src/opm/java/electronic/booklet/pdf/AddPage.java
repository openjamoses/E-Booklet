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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import opm.java.electronic.booklet.utils.Constants;

/**
 *
 * @author john
 */
public class AddPage {

    public static void add(Document document, int page) {
        document.newPage();
        document.setMargins(0, 0, 90, 0);
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            //special font sizes
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            Paragraph paragraph = new Paragraph("\n\n");

            PdfPTable lines = new PdfPTable(1);
            lines.setWidths(new int[]{60});
            lines.setTotalWidth(627);
            //footer.setLockedWidth(true);
            lines.getDefaultCell().setFixedHeight(60f);
            lines.getDefaultCell().setBorder(Rectangle.TOP);

            PdfPTable header = new PdfPTable(3);
            header.setWidths(new int[]{5, 70, 5});
            header.setTotalWidth(520f);
            //header.getDefaultCell().setFixedHeight(30f);
            header.setLockedWidth(true);
            header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            header.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            //header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            PdfPTable table_ = new PdfPTable(1);

            //table_.setWidths(new int[]{10, 10, 10});
            table_.getDefaultCell().setFixedHeight(15f);
            table_.addCell("");
            table_.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            table_.getDefaultCell().setBorderWidthTop(0f);
            table_.getDefaultCell().setBorderWidthBottom(0f);
            table_.getDefaultCell().setBorderColorBottom(BaseColor.WHITE);
            table_.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            for (int x = 1; x < 34; x++) {
                header.addCell("");
                header.addCell(table_);
                header.addCell("");
            }

            //add the PDF table to the paragraph 
            paragraph.add(header);

            //create a paragraph
            document.add(paragraph);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
