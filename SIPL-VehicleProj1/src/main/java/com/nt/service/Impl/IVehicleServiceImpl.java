package com.nt.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nt.Response.VehicleApiResponse;
import com.nt.dto.VehicleDTO;
import com.nt.mapper.VehicleMapper;
import com.nt.model.Vehicle;
import com.nt.repository.IVehicleRepo;
import com.nt.service.IVehicleMgmtService;

import lombok.extern.slf4j.Slf4j;






@Slf4j
@Service("VehicleMgmtservice")
public class IVehicleServiceImpl implements IVehicleMgmtService {
     

	@Autowired
	private IVehicleRepo vehiclerepo;
	
	@Autowired
	private RestTemplate template;
	

  @Autowired
   private   VehicleMapper vehiclemapper;

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
					return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while Retrieving Data",true);
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
								return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while Retrieving Data",true);
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
			



	
			
			@Override
			public String getdatafrommockoo() {
                   log.info("<<start>> getdatafrommockoo method started <<start>>");
				   HttpHeaders headers = new HttpHeaders();
				   try {
				       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			            HttpEntity<String> entity = new HttpEntity<String>(headers);
			            
			            String  s  =	 template.exchange("http://localhost:3000/demo", HttpMethod.POST, entity, String.class).getBody();
			            Object obj=s;	      
			           log.info("<<start>>  Data received from templete <<start>> ");
			            Vehicle vehicle =vehiclemapper.mapVehicleDtoToVehicle((VehicleDTO) obj);
			          log.info("<<start>> received data converted from string to object ");
			            String no=vehicle.getRegistrationNumber();
			          if(vehiclerepo.findByRegno(no).isPresent() ) {
			            return " Vehicle is already saved ";
			          }else {
			       	  log.info("<<end>> data is saved  into the database <<end>>"+no);
			        	  return " Vehicle is registered "+no+ vehiclerepo.save(vehicle);
			          }
			          }catch (ClassCastException e) {
			       	  log.error("<<end>> Request Resource Not found <<end>>");
			        	  e.printStackTrace();
					return "  MediaType Should be in APPLICATION_JSON format  ";
					}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
} // class
