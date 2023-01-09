package com.nt.service;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;
import com.nt.dto.VehicleDTO;
import com.nt.response.VehicleApiResponse;

@Service	
public interface IVehicleMgmtService {

	
	public VehicleApiResponse add(VehicleDTO vehicledto) ;
	
	public VehicleApiResponse update(VehicleDTO vehicledto);
	 
	 public VehicleApiResponse deletebyid(int id);
	 
	 public VehicleApiResponse  getAll();
	 
	 public VehicleApiResponse showPageRecords(int pageno , int pagesize);
	 
	 
	 public String callapi();
	 


	void generatePdfFile(HttpServletResponse response, LocalDate startDate, LocalDate endDate)
			throws DocumentException, IOException;

	 public VehicleApiResponse audit();

	public Object getVehicleFromMockoon();
	 
}
