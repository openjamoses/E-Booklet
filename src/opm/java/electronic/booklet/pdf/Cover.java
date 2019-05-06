/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author john
 */
public class Cover {

    public static void cover(Document document) {
        try {
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            Paragraph paragraph = new Paragraph("MBARARA UNIVERSITY OF SCIENCE AND TECHNOLOGY\n", bfBold12);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);

            String filename = "logo.png";
            Image image = Image.getInstance(filename);
            image.scaleToFit(100, 100);
            image.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(image);
            paragraph.add(new Phrase("Examination Answer Book\n", new Font(Font.FontFamily.TIMES_ROMAN, 16)));
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            //specify column widths
            float[] columnWidths = {2f, 5f, 2f};
            //create PDF table with the given widths

            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Phrase("number", bfBold12));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell.setPhrase(new Phrase("square", bfBold12));
            table.addCell(cell);

            List list = new List(List.ORDERED);
            list.setAutoindent(false);
            list.setSymbolIndent(42);
            Chunk line = new Chunk("\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0");
            list.add(new ListItem("FACULTY: --------------------------------------\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            //list.add(new ListItem(line));
            list.add(new ListItem("DEGREES OR OTHER AWARDS\n", new Font(Font.FontFamily.TIMES_ROMAN, 12)));

            list.add(new ListItem("FOR WHICH A CANDIDATE: -------------------------\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            list.add(new ListItem("SUBJECT OF EXAMINATION: ------------------------\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            list.add(new ListItem("COURSE: ---------------------COURSE CODE: -------\n", new Font(Font.FontFamily.TIMES_ROMAN, 1)));
            list.add(new ListItem("YEAR OF STUDY: -------------- SEMESTER: ----------\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            list.add(new ListItem("DATE OF EXAMINATION ------------------------------\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            //list.add(new ListItem("", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
            list.add(new ListItem("\n\n\n\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
            list.add(new ListItem("Instructions for Candidate", new Font(Font.FontFamily.TIMES_ROMAN, 12)));

            PdfPTable bodyTable = new PdfPTable(2);
            bodyTable.setWidths(new int[]{50, 18});
            //bodyTable.setTotalWidth(520);
            //bodyTable.setLockedWidth(true);
            bodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            bodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
            //bodyTable.getDefaultCell().setFixedHeight(40);
            //bodyTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
            //bodyTable.getDefaultCell().setBorderColor(BaseColor.WHITE);
            bodyTable.getDefaultCell().setBorderWidth(0);

            List list2 = new List(List.ORDERED);
            list2.setAutoindent(false);
            list2.setSymbolIndent(42);
            list2.setNumbered(true);
            ///list2.add(new ListItem());
            list2.add(new ListItem("Write on both sides of the pages\n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            list2.add(new ListItem("Begin each answer on a fresh page.\n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            list2.add(new ListItem("Write the number of each question at the top of each page.\n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            list2.add(new ListItem("All rough work must be done in the Answer Booklet and crossed through.\n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));

            //list2.setNumbered(false);
            list2.add(new ListItem("If suppementary page are use, they must be fastened all together at the END of this and inside the cover. Answers must not be written on the supplementary pages, unless all the leaves in this book have already been used.\n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            list2.add(new ListItem("In your own interest you should enter in the space provided below, the numbers of the question which you have attempted (with sub-sections where neccessary). \n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            list2.add(new ListItem("In no circumstances must answer books, used or unused, be removed from the Examination Room by a candidate.\n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            list2.add(new ListItem("Candidates are warned that importance is attached by the Examiners to accuracy and clearness of expression\n", new Font(Font.FontFamily.TIMES_ROMAN, 14)));

            PdfPTable tables = new PdfPTable(3);
            tables.setTotalWidth(120f);//table size
            tables.getDefaultCell().setFixedHeight(150f);
            tables.setLockedWidth(true);
            tables.setSpacingBefore(2f);//both are used to mention the space from heading
            Paragraph phrase = new Paragraph("For Examination use only", new Font(Font.FontFamily.TIMES_ROMAN, 6));
            Paragraph paragraph_right = new Paragraph();
            //paragraph_right.add(new Phrase("For Examination use only"));
            PdfPTable table_ = new PdfPTable(3);
            //table_.setWidths(new int[]{10, 10, 10});
            table_.getDefaultCell().setFixedHeight(20f);
            //table_.getDefaultCell().set
            for (int aw = 0; aw < 9; aw++) {               
                if (aw > 2 && aw < 6) {
                    table_.getDefaultCell().setFixedHeight(120f);
                }else{
                    table_.getDefaultCell().setFixedHeight(20f);
                }
                
                if (aw == 0) {
                    table_.addCell(new Phrase("Q", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                }else if (aw == 1) {
                    table_.addCell(new Phrase("I.E.", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                }else if (aw == 2) {
                    table_.addCell(new Phrase("E.E.", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                }else if (aw == 6) {
                    table_.addCell(new Phrase("TOTAL", new Font(Font.FontFamily.TIMES_ROMAN, 8)));
                }else{
                    table_.addCell("");
                }
               
            }
            //paragraph_right.add(phrase);
            paragraph_right.add(table_);

            tables.addCell(new Phrase("", bf12));
            tables.addCell(new Phrase(" ", bf12));
            tables.addCell(new Phrase("", bf12));
            Paragraph paragraph1 = new Paragraph();
            paragraph1.add(list);
            //paragraph1.add(list2);

            Paragraph paragraph2 = new Paragraph();
            bodyTable.addCell(paragraph1);
            bodyTable.addCell(table_);
            paragraph2.add(bodyTable);
            paragraph2.add(list2);
            Paragraph paragraph3 = new Paragraph("Insert here the number of the Questions you have attempted\n "
                    + " In the order in which you have done them.", new Font(Font.FontFamily.TIMES_ROMAN, 8));
            paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

            PdfPTable tables_footer = new PdfPTable(15);
            tables_footer.setTotalWidth(520f);//table size
            tables_footer.getDefaultCell().setFixedHeight(20f);
            tables_footer.setLockedWidth(true);
            tables_footer.setSpacingBefore(2f);//both are used to mention the space from heading
            for (int a = 0; a < 15; a++) {
                tables_footer.addCell(new Phrase("", bf12));
            }
            //add the PDF table to the paragraph 
            paragraph.add(paragraph2);
            paragraph.add(paragraph3);
            paragraph.add(tables_footer);
            // add the paragraph to the document
            document.add(paragraph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
