
package com.sipl.yard.management.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.sipl.yard.management.entities.ContainerPosition;

@Component
public class CSVHelper {

	public static ByteArrayInputStream containerPositionToCSV(List<ContainerPosition> containerpositions,
			HttpServletResponse response, LocalDate startDate, LocalDate endDate) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();

				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

			ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { " Sr No","Vehicle id", "Container Code", "Pickup Time", "Pickup Stack",
					"Pickup Row", "Pickup Column", "Pickup Tire", "Drop Time", "Drop Stack", "Drop Row", "Drop Column",
					"Drop Tire" };

			String[] nameMapping = { "id", "vehicleId", "containerNumber", "pickupTime",
					"pickupYard", "pickupRow", "pickupColumn", "pickupTier", "dropTime", "dropYard", "dropRow",
					"dropColumn", "dropTier" };

			csvWriter.writeHeader(csvHeader);

			for (ContainerPosition containerposition : containerpositions) {
				csvWriter.write(containerposition, nameMapping);
			}

			csvWriter.close();
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
}
