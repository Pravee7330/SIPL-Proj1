package com.nt.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.DocumentException;
import com.nt.Util.PdfGenerator;
import com.nt.dto.VehicleDTO;
import com.nt.helper.CSVHelper;
import com.nt.mapper.VehicleMapper;
import com.nt.model.Vehicle;
import com.nt.repository.IVehicleRepo;
import com.nt.response.VehicleApiResponse;
import com.nt.service.IVehicleMgmtService;

import lombok.extern.slf4j.Slf4j;






@Slf4j
@Service("VehicleMgmtservice")
public class IVehicleServiceImpl implements IVehicleMgmtService {
     
	
	@Autowired
	AuditReader auditReader;


	@Autowired
	private IVehicleRepo vehiclerepo;
	
	@Autowired
	private RestTemplate template;
	

  @Autowired
   private   VehicleMapper vehiclemapper;
  
  @Autowired
	PdfGenerator pdfGenerator;
  
  @Autowired
 private CSVHelper csvhelper;
	
	@Value("${mockoonUrl}")
	String mockoonUrl;

	//ADD
	@Override
	public VehicleApiResponse add(VehicleDTO vehicledto) {
		try {
		      log.debug("<< started >> ADD methos <<started >>");
			Vehicle vehicle = vehiclemapper.mapVehicleDtoToVehicle(vehicledto);
				String no=vehicledto.getRegistrationNumber();
            		if (vehiclerepo.findByRegno(no).isPresent()) {
			            log.info("<< user response is not valide >>");
			                 return new VehicleApiResponse(vehicledto, null,HttpStatus.ALREADY_REPORTED , "Vehicle Already Registered "+no, true);
		              } else {
			                vehiclerepo.save(vehicle);
                        log.info(" <<end>> Vehicle is added into the DB <<end>>");
			                return new VehicleApiResponse(vehicledto, null,HttpStatus.CREATED , "Vehicle Registered with  "+no, false);
		              } // else
		}catch (Exception e) {
			        e.printStackTrace();
			       log.error("<<internal server error >>");
			        return new VehicleApiResponse(vehicledto, null,HttpStatus.UNAUTHORIZED , " Registration Failed  ",true);
		}
	}// method

	
	//UPDATE
			@Override
		public VehicleApiResponse update( VehicleDTO vehicledto) {
               try {
				 	if (Optional.of(vehiclerepo.findById(vehicledto.getId())) != null) {
				 	log.info("<<start>> object found for updation  <<start>>");
						Vehicle vehicle = vehiclemapper.mapVehicleDtoToVehicle(vehicledto);
						vehicle.setId(vehicledto.getId());
						vehiclerepo.save(vehicle);
						log.info("<<end>> Data updated <<end>>");
						VehicleDTO vehicleDtoResponse = vehiclemapper.mapVehicleToVehicleDto(vehicle);
						return new VehicleApiResponse(vehicleDtoResponse, null, HttpStatus.OK, "Data Updated Successfully", false);
					} else {
						log.info("<<end >> no data found for updation <<end>>");
						return new VehicleApiResponse(null, null, HttpStatus.NO_CONTENT, "No data found", true);
				    }
				 	}catch (Exception e) {
						log.error("<<internal server error >>");
						 return new VehicleApiResponse(vehicledto, null,HttpStatus.UNAUTHORIZED , " Updation Failed  ",true);
					}
			
		}
		
		
 //DELETE
                 	@Override
	             public VehicleApiResponse deletebyid(int id) {
                 		try {
                			log.info("<<start>> delete method started <<start>>");
		               Optional<Vehicle> opt=vehiclerepo.findById(id);
		                     if(opt.isPresent()) {
		                  	   log.info("<<start>> id found for deletion <<start>>");
			                         vehiclerepo.deleteById(id);
			                     log.info("<<end>> id deleted <<end>>");
			                              return new  VehicleApiResponse(null, null,HttpStatus.OK, "Vehicle Found And Deleted ", false);
		                     }else {
			                               return new  VehicleApiResponse(null, null,HttpStatus.NOT_FOUND, "Vehicle  Not Found  for  Deletion ", true);
		                    }}catch (Exception e) {
		                  	log.error("<<internal server error >>");
								 return new VehicleApiResponse(null, null,HttpStatus.UNAUTHORIZED , " Invalid   ",true);	
		                    }
						
               	}
	

	
		//	GetAll
			public VehicleApiResponse getAll() {
				log.info("<<started>> get All method started  <<started>> ");
				try {
					List<Vehicle> findAll = (List<Vehicle>) vehiclerepo.findAll();
					if(findAll.isEmpty()) {
				log.info("<<end>>  no data found for retrieval <<end>>");
						return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"No data in the database  ",true);
					}else {
					log.info("<<end>> data found and retrieved <<end>>");
						return  new VehicleApiResponse(null,vehiclemapper.mapVehicleListToVehicleDtoList(findAll),HttpStatus.OK,"Data Retrived Successfully",false);
					}}catch (Exception e) {
					e.printStackTrace();
					log.error("<<end >> internal server error <<end>>");
					return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while RetrievingÂ Data",true);
				}
			}

		//PAGENATION	
         	@Override
			public VehicleApiResponse showPageRecords(int pageno, int pagesize) {
         		log.info("<<start>> showPageRecords method started execution>>");
	           		try {
						 if(pagesize >4) {
							 log.info("<<end>> pagenation failed  default value is 4 <<end>>");
							return  new VehicleApiResponse(null,null,HttpStatus.BAD_REQUEST,"Data Retrived Unsuccessfully default value is 4",false);
						}else {
						    Pageable pageable = PageRequest.of(pageno, pagesize);
						      List<Vehicle> vehicleList = vehiclerepo.findAll(pageable).toList();
				      log.info("<<end>>pagination process done <<end>>");
						         return  new VehicleApiResponse(null,vehiclemapper.mapVehicleListToVehicleDtoList(vehicleList),HttpStatus.OK,"Data Retrived Successfully",false);
						}}catch (Exception e) {
	   						e.printStackTrace();
		    			log.error("<<end >> internal server error <<end>>");
								return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while RetrievingÂ Data",true);
					  }
			}
	

	
//REST-TEMPLATE
			@Override
			public String callapi() {
				log.info("<<start>> callapi method started <<start>> ");
				try {
				HttpHeaders headers = new HttpHeaders();
				log.info("<<start>>httpheaders created<<start>>");
	               headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	            HttpEntity<String> entity = new HttpEntity<String>(headers);
	             log.info("<<end >> response body found <<end>>");
	            return template.exchange("http://localhost:3000/demo", HttpMethod.POST, entity, String.class).getBody();
				}catch (Exception e) {
					e.printStackTrace();
				log.error(" <<end>> request resource not found <<end>>");
					return "  no response Body present  ";
				}
			}
			

			
//			Save from Mockoon
			@Override
			public VehicleApiResponse getVehicleFromMockoon() {
				try {
					System.out.println(mockoonUrl);
					log.info("<<start>>In getVehicleFromMockoon method<<start>>");
					ResponseEntity<VehicleDTO> responseEntity = template.getForEntity(mockoonUrl + "/addVehicle",
							VehicleDTO.class);
					if (responseEntity.hasBody()) {
						if (vehiclerepo.findByRegno(responseEntity.getBody().getRegistrationNumber()).isPresent()) {
							return new VehicleApiResponse(null, null, HttpStatus.ALREADY_REPORTED, "Reg Number Aleady exists",
									true);
						} else {
							log.debug("Got Vehicle Response :" + responseEntity.getBody());
							Vehicle vehicle = vehiclemapper.mapVehicleDtoToVehicle(responseEntity.getBody());
							vehiclerepo.save(vehicle);
							return new VehicleApiResponse(vehiclemapper.mapVehicleToVehicleDto(vehicle), null, HttpStatus.OK,"Data Saved Successfully", false);
						}

					} else {
						log.info("<<end>>In getVehicleFromMockoon method<<end>>");
						return new VehicleApiResponse(null, null, HttpStatus.NO_CONTENT, "No Data Available", true);
					}

				} catch (Exception e) {
					log.error("Vehicle getVehicleFromMockoon Exception: ", e);
				}

				return new VehicleApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured while Saving Data",
						true);
			}

			
//			Generate PDF
			@Override
			 public void generatePdfFile(HttpServletResponse response , LocalDate startDate, LocalDate endDate) throws DocumentException, IOException 
			  {
			    response.setContentType("application/pdf");
			    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
			    String currentDateTime = dateFormat.format(new Date());
			    String headerkey = "Content-Disposition";
			    String headervalue = "attachment; filename=vehicle" + currentDateTime + ".pdf";
			    response.setHeader(headerkey, headervalue);
			    List < Vehicle > listofVehicles = vehiclerepo.findAllByDateGreaterThanEqualAndDateLessThanEqual(startDate,
						endDate);
			    pdfGenerator.generatePdf(listofVehicles, response, startDate, endDate);
			  }


			
			// Get Deleted Data
			@Override
			public VehicleApiResponse audit() {
			try {
			AuditQuery q = auditReader.createQuery().forRevisionsOfEntity(Vehicle.class,
			true, true);
			q.add(AuditEntity.revisionType().eq(RevisionType.DEL));
			List<Vehicle> audit = q.getResultList();
			if (audit.isEmpty()) {
			return new VehicleApiResponse(null, null, HttpStatus.NO_CONTENT, "No Data Available", true);
			}
			else {
			return new VehicleApiResponse(null, vehiclemapper.mapVehicleListToVehicleDtoList(audit), HttpStatus.FOUND, "Data Found",
			true);
			}
			} catch (Exception e) {
			}
			return new VehicleApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Erro	r Occured Retriving Data",
			true);
			}

			
			
			
			
			@Override 
			  public ByteArrayInputStream load( HttpServletResponse response,LocalDate startDate, LocalDate endDate ) {
				response.setContentType("application/csv");
				  DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
				    String currentDateTime = dateFormat.format(new Date());
				  String headerkey = "Content-Disposition";
				    String headervalue = "attachment; filename=vehicle" + currentDateTime + ".csv";
				    response.setHeader(headerkey, headervalue);
			    List<Vehicle> vehicles = vehiclerepo.findAllByDateGreaterThanEqualAndDateLessThanEqual(startDate,	endDate);
			//    ByteArrayInputStream in = CSVHelper.vehiclesToCSV(vehicles);
			    
			    
			    return csvhelper.vehiclesToCSV(vehicles, response,startDate ,endDate);
			  }
			
			
			
			
			
			
			
			
} // class
