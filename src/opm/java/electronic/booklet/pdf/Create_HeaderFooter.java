/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author john
 */
public class Create_HeaderFooter {

    //private static PdfTemplate t;
    //private static Image total;
    public static void cover(PdfStamper stamper, PdfWriter writer, int i, String newSerial) {

        try {
            PdfPTable header = new PdfPTable(2);
            // set defaults
            header.setWidths(new int[]{30, 20});
            header.setTotalWidth(520);
            header.setLockedWidth(true);
            //header.getDefaultCell().setHorizontalAlignment(i);
            header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            header.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.getDefaultCell().setFixedHeight(40);
            //header.getDefaultCell().setBorderWidth(0);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            String filename = "logo.png";
            Image image = Image.getInstance(filename);

            header.addCell(new Phrase("STUDENT NUMBER\nDO NOT WRITE YOUR NAME ANYWHERE ON THIS ANSWER BOOK,              ", new Font(Font.FontFamily.TIMES_ROMAN, 8)));
            float[] columnWidths = {2f};
            PdfPTable table = new PdfPTable(1);
            //table.addCell("");
            //table.getDefaultCell().setFixedHeight(50f);
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            PDF_InsertCells.insertCell(table, "-", Element.ALIGN_LEFT, 1, bf12);
            header.addCell(table);

            // write content
            if (writer != null) {
                header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
            } else {
                header.writeSelectedRows(0, -1, 34, 803, stamper.getOverContent(i));
            }
            addFooter(stamper, writer, i, newSerial);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void others(PdfStamper stamper, PdfWriter writer, int i, String newSerial) {
        try {
            Paragraph paragraph = new Paragraph();
            PdfPTable header = new PdfPTable(4);
            header.setWidths(new int[]{5,50, 30, 10});
            header.setTotalWidth(520f);
            header.getDefaultCell().setFixedHeight(30f);
            header.setLockedWidth(true);
            header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            header.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            header.addCell("");

            String filename = "logo.png";
            Image image = Image.getInstance(filename);
            image.scaleToFit(50, 40);;
            //header.addCell(image);
            PdfPCell text1 = new PdfPCell();
            text1.setPaddingBottom(15);
            text1.setPaddingLeft(10);
            text1.setBorder(Rectangle.BOTTOM);
            text1.setBorderColor(BaseColor.LIGHT_GRAY);
            text1.setHorizontalAlignment(Element.ALIGN_CENTER);
            text1.setVerticalAlignment(Element.ALIGN_CENTER);
            text1.addElement(new Phrase("Write on both sides of the Paper\n\n", new Font(Font.FontFamily.HELVETICA, 6)));
            text1.addElement(image);
            header.addCell(text1);
            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            text.setHorizontalAlignment(Element.ALIGN_CENTER);
            text.setVerticalAlignment(Element.ALIGN_CENTER);
            text.addElement(new Phrase("Question ----------------------", new Font(Font.FontFamily.HELVETICA, 10)));
            //text.addElement(new Phrase("P.O BOX 1410,", new Font(Font.FontFamily.HELVETICA, 8)));
            //text.addElement(new Phrase("Mbarara, (Uganda)", new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);
            
            
            text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            text.setHorizontalAlignment(Element.ALIGN_RIGHT);
            text.setVerticalAlignment(Element.ALIGN_CENTER);
            text.addElement(new Phrase("Do not write in either margin", new Font(Font.FontFamily.HELVETICA, 6)));
            header.addCell(text);
            //paragraph.add(header);
            //paragraph.add(image);

            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
            PdfPTable table = new PdfPTable(columnWidths);
            // write content
            if (writer != null) {
                header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());

            } else {
                header.writeSelectedRows(0, -1, 34, 803, stamper.getOverContent(i));

            }

            addFooter(stamper, writer, i, newSerial);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void addFooter(PdfStamper stamper, PdfWriter writer, int i, String newSerial){
        
        try{
        PdfPTable footer = new PdfPTable(2);
            footer.setWidths(new int[]{24, 10});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add copyright
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            Font f1 = FontFactory.getFont(FontFactory.TIMES, 16);
            f1.setColor(BaseColor.RED);

            footer.addCell(new Phrase(DateTime.getCurrent(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            // add current page count
            footer.addCell(new Phrase(String.format(newSerial), f1));

            // add placeholder for total page count
            //PdfPCell totalPageCount = new PdfPCell(total);
            //totalPageCount.setBorder(Rectangle.TOP);
            //totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            //footer.addCell(totalPageCount);
            // write page
            PdfContentByte canvas = null;
            if (writer != null) {
                canvas = writer.getDirectContent();
            } else {
                canvas = stamper.getOverContent(i);
            }
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
