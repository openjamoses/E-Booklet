/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.util.*;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import opm.java.electronic.booklet.controller.WaterMark;
import opm.java.electronic.booklet.excel.Create_Excel;
import opm.java.electronic.booklet.excel.File_Details;
import opm.java.electronic.booklet.excel.ReadExcelFile_1Column;
import opm.java.electronic.booklet.utils.Constants;

/**
 *
 * @author john
 */
public class CompileAllPDF {

    public static void create(String upload_path, int pages, String newSerial) throws FileNotFoundException, DocumentException {
        // create document
        String path = "final_outputs";
        String excel_file = path+"/Serial.xlsx";
        Object[] datas = null;
        ArrayList< Object[]> allobj = new ArrayList<Object[]>();
        datas = new Object[]{"Serial-No", "Total-Pages", "Date", "Time"};// end of assigning the header to the object..
        allobj.add(datas);

        int serial_counts = 0;
        try {
            if (File_Details.isExcelExist(excel_file)) {
                List<String> serialList = ReadExcelFile_1Column.readColumnAsString(excel_file, 0, 0, 1);
                List<Double> pagesList = ReadExcelFile_1Column.readColumnAsNumeric(excel_file, 0, 1, 1);
                List<String> dateList = ReadExcelFile_1Column.readColumnAsString(excel_file, 0, 2, 1);
                List<String> timeList = ReadExcelFile_1Column.readColumnAsString(excel_file, 0, 3, 1);
                for (int a = 0; a < serialList.size(); a++) {
                    datas = new Object[]{serialList.get(a), pagesList.get(a), dateList.get(a), timeList.get(a)};
                    allobj.add(datas);
                }
                serial_counts = serialList.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        serial_counts = serial_counts + 1;
           //String newSerial = DateTime.getSerial(serial_counts);

        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(upload_path + "/" + Constants.cons.PRINTABLE + ".pdf"));
        
        // write to document
        document.open();
        document.newPage();
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            //add the cover page
            Cover.cover(document);
            for (int i = 0; i < pages; i++) {
                // add new blank page
                AddPage.add(document, i);
            }

        } catch (Exception dex) {
            dex.printStackTrace();
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

        
        String input = upload_path + "/" + Constants.cons.PRINTABLE + ".pdf";
        String output = upload_path + "/" + Constants.cons.PRINTABLE + "_.pdf";
        int n = 0;
        try {
            PdfReader reader = new PdfReader(input);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(output));

            // loop over every page
            n = reader.getNumberOfPages();
            
            for (int i = 1; i <= n; i++) {

                //add header
                if (i == 1) {
                    //Add cover page header and footer
                    Create_HeaderFooter.cover(stamper,null, i, newSerial);
                } else {
                    //add blank page header and footer
                    Create_HeaderFooter.others(stamper, null, i, newSerial);
                }

            }
            stamper.close();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        }

        try {
        
            //String output = path + "/" + Constants.cons.BLANK_PAGE + "_" + page + ".pdf";
            String pdfOutnput = path + "/" + newSerial + ".pdf";
            boolean create_waterMark = WaterMark.addWater2(output, pdfOutnput);

            //Create the object to be added to the excel file..
            datas = new Object[]{newSerial, Double.parseDouble(n+""), DateTime.getCurrentDate(), DateTime.getCurrentTime()};// end of assigning the header to the object..
            allobj.add(datas);
            //Create the excel file
            Create_Excel.createExcel(allobj,excel_file, DateTime.getSheet());
            if (create_waterMark) {
                // Open the pdf document using default pdf reader..
                Desktop.getDesktop().open(new File(pdfOutnput));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
