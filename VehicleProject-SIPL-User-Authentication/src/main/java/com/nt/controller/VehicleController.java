package com.nt.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.DocumentException;
import com.nt.dto.VehicleDTO;
import com.nt.response.VehicleApiResponse;


@RestController
@RequestMapping("/vehicle")
public interface VehicleController {

	@Autowired
	public static final RestTemplate restTemplate = new RestTemplate();
	
	
	@PostMapping("/register")
	public ResponseEntity<VehicleApiResponse> addvehicle(@RequestBody VehicleDTO vehicledto);
	
	@PutMapping("/update")
	public ResponseEntity<VehicleApiResponse> updateVehicle(@RequestBody VehicleDTO vehicledto);
	
	
	@GetMapping("/getall")
	public ResponseEntity<VehicleApiResponse> getAll();

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<VehicleApiResponse> deleteVehicle(@PathVariable("id") int id );

	
	@GetMapping("/page")
	public ResponseEntity<VehicleApiResponse> pageing(@RequestParam(defaultValue = "0") Integer pageNumber,
			                                                                                               @RequestParam(defaultValue="4") Integer size);
	
	
	
	
	@PostMapping("/callapi")
	  public ResponseEntity<String> getVehicleList();
	


	@GetMapping("/mokoon")
	ResponseEntity<VehicleApiResponse> getVehicleFromMockoon();

	@GetMapping("/exportpdf")
	void exportPdf(HttpServletResponse httpServletResponse, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws DocumentException, IOException;

	
	
	@GetMapping("/exportcsv")
	   public void exportCSV(HttpServletResponse httpServletResponse,@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
				@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) ;
		   
		  
}
