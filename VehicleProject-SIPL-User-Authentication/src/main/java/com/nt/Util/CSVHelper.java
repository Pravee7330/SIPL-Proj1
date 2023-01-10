package com.nt.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.nt.model.Vehicle;


@Component
public class CSVHelper {

	
	 
	
  public static ByteArrayInputStream vehiclesToCSV(List<Vehicle> vehicles,HttpServletResponse response , LocalDate startDate , LocalDate endDate) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);



    
    
    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
    		
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
    
    	
    	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    	 String[] csvHeader = {" ID", " reg_num", "Brand", "Colour", "Vehicle_type","Weight"};
    	 String[] nameMapping = {"id", "registrationNumber", "brand", "color", "vehicleType","weight"};
    	 
    	 csvWriter.writeHeader(csvHeader);
    	 
    	 
         
         for (Vehicle vehicle : vehicles) {
             csvWriter.write(vehicle, nameMapping);
         }
         
         csvWriter.close();
			/*
			 * for (Vehicle vehicle : vehicles) { List<String> data = Arrays.asList(
			 * String.valueOf(vehicle.getId()),
			 * String.valueOf(vehicle.getRegistrationNumber()), vehicle.getBrand(),
			 * vehicle.getColor(), vehicle.getVehicleType(),
			 * String.valueOf(vehicle.getWeight())
			 * 
			 * );
			 * 
			 * csvPrinter.printRecord(data); }
			 */

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}