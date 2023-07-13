/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import Repositories.ImplThongke;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ADMIN
 */
public class NewClass1 {
    public static void main(String[] args) {
       Date date=java.util.Calendar.getInstance().getTime();
       SimpleDateFormat formatThang = new SimpleDateFormat("yyyy-MM-dd");
System.out.println(formatThang.format(date));

            
    }
}
