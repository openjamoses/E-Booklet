/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.pdf;

/**
 *
 * @author john
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
public class DateTime {
    public static String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(date);
        return strDate;
    }
    public  static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strTime = sdf.format(date);
        return strTime;
    }

    /**
     * 
     * @return 
     */
    public static int year() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        //System.out.println(formatter.format(date));
        return Integer.parseInt(formatter.format(date));
    }

    /**
     * 
     * @return 
     */
    public static int month() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        //System.out.println(formatter.format(date));
        return Integer.parseInt(formatter.format(date));
    }
    public static String getCurrent() {
        int year1 = year();
        int year2 = year();
        if (month() < 8) {
            year1 = year1-1;
        }else{
            year2 = year2+1;
        }
        return year1+"/"+year2+"/";
    }
    
    public static String getSheet() {
        int year1 = year();
        int year2 = year();
        if (month() < 8) {
            year1 = year1-1;
        }else{
            year2 = year2+1;
        }
        return year1+"_"+year2;
    }
    public static String getSerial(int number) {
        //int a = 1;
        try{
            return new DecimalFormat("00000").format(number);
        }catch (Exception e){
            e.printStackTrace();
             return number+"";
        }
    }
}
