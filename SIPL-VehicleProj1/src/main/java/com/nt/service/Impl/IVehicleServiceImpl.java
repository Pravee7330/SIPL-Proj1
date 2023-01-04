package com.nt.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.Response.VehicleApiResponse;
import com.nt.dto.VehicleDTO;
import com.nt.mapper.VehicleMapper;
import com.nt.model.Vehicle;
import com.nt.repository.IVehicleRepo;
import com.nt.service.IVehicleMgmtService;





@Service("VehicleMgmtservice")
public class IVehicleServiceImpl implements IVehicleMgmtService {

	@Autowired
	private IVehicleRepo vehiclerepo;
	
	@Autowired
	private RestTemplate template;
	

	ModelMapper modelMapper = new ModelMapper();
	
	

  @Autowired
   private   VehicleMapper vehiclemapper;

	//===============================================================================================
	//ADD
	@Override
	public VehicleApiResponse add(VehicleDTO vehicledto) {
		
	
	//	Vehicle vehicle = modelMapper.map(vehicledto, Vehicle.class);
		Vehicle vehicle = vehiclemapper.mapVehicleDtoToVehicle(vehicledto);
		
		String no=vehicledto.getRegistrationNumber();

		if (vehiclerepo.findByRegno(no).isPresent()) {

			return new VehicleApiResponse(vehicledto, null,HttpStatus.ALREADY_REPORTED , "Vehicle Already Registered "+no, false);
		} 
		else {
			vehiclerepo.save(vehicle);

			return new VehicleApiResponse(vehicledto, null,HttpStatus.CREATED , "Vehicle Registered with  "+no, false);
			

		} // else

	}// method
//================================================================================================================
	//UPDATE
	
		@Override
		public VehicleApiResponse update( VehicleDTO vehicledto) {
	
		//	Vehicle vehicle=modelMapper.map(vehicledto,Vehicle.class);
//			Update	
		
					if (Optional.of(vehiclerepo.findById(vehicledto.getId())) != null) {
						Vehicle vehicle = vehiclemapper.mapVehicleDtoToVehicle(vehicledto);
						vehicle.setId(vehicledto.getId());
						vehiclerepo.save(vehicle);
						VehicleDTO vehicleDtoResponse = vehiclemapper.mapVehicleToVehicleDto(vehicle);
						return new VehicleApiResponse(vehicleDtoResponse, null, HttpStatus.OK, "Data Updated Successfully", false);
						
					} else {

						return new VehicleApiResponse(null, null, HttpStatus.NO_CONTENT, "No data found", true);
						
					}
			
		}
		
		/*   if( vehiclerepo.existsById(vehicledto.getId())){
			     vehicle.setId(vehicledto.getId());
			     vehicle.setRegistrationNumber(vehicledto.getRegistrationNumber());
			     vehicle.setColor(vehicledto.getColor());
			     vehicle.setBrand(vehicle.getBrand());
			     vehicle.setVehicleType(vehicle.getVehicleType());
			     vehicle.setWeight(vehicle.getWeight());
			             vehiclerepo.save(vehicle);  
			             VehicleDTO vehicledto1=modelMapper.map(vehicle,VehicleDTO.class);
		
			             return new VehicleApiResponse(vehicledto1, null,HttpStatus.CREATED , "Vehicle is updated  ", false);
		   }
			   else {
				   return new VehicleApiResponse(vehicledto, null,HttpStatus.NOT_FOUND, "Vehicle Not Found  ", true);
			   }*/
	//}
//====================================================================================================
 //DELETE
	@Override
	public VehicleApiResponse deletebyid(int id) {
		   Optional<Vehicle> opt=vehiclerepo.findById(id);
		   if(opt.isPresent()) {
			   vehiclerepo.deleteById(id);
			  return new  VehicleApiResponse(null, null,HttpStatus.OK, "Vehicle Found And Deleted ", false);
		   }else {
			   return new  VehicleApiResponse(null, null,HttpStatus.NOT_FOUND, "Vehicle  Not Found  for  Deletion ", true);
		   }
	}
	

	//========================================================================================

/*	@Override
	public  VehicleApiResponse getAll() {
		
		
		//TODO CAST TO LIST
		List<Vehicle> it =(List<Vehicle>) vehiclerepo.findAll();
		
		if(((List<Vehicle>) it).isEmpty()) {
			return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while getting data",true);
		}else {
		//	Vehicle listType = new TypeToken<List<VehicleDTO>>(){}.getType();
			//List<VehicleDTO> postDtoList = modelMapper.map(it,listType);
			
			List<VehicleDTO> postDtoList = Arrays.asList(modelMapper.map(it, VehicleDTO[].class));
			
		 return  new VehicleApiResponse(null,postDtoList,HttpStatus.OK,"Data Retrived Successfully",false);
	}
	*/
	
	//=======================================================================================================

		//	GetAll
			public VehicleApiResponse getAll() {
				try {
					List<Vehicle> findAll = (List<Vehicle>) vehiclerepo.findAll();
					if(findAll.isEmpty()) {
						return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while getting data",true);
					}
					else {
						return  new VehicleApiResponse(null,vehiclemapper.mapVehicleListToVehicleDtoList(findAll),HttpStatus.OK,"Data Retrived Successfully",false);
					}	
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while Retrieving Data",true);
			}
//===========================================================================================================

			@Override
			public VehicleApiResponse showPageRecords(int pageno, int pagesize) {
				Pageable pageable = PageRequest.of(pageno, pagesize);
				List<Vehicle> vehicleList = vehiclerepo.findAll(pageable).toList();
				
				return  new VehicleApiResponse(null,vehiclemapper.mapVehicleListToVehicleDtoList(vehicleList),HttpStatus.OK,"Data Retrived Successfully",false);
			
			}

//==============================================================================
	
//REST-TEMPLATE
			@Override
			public String callapi() {
				
				HttpHeaders headers = new HttpHeaders();
	               headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	            HttpEntity<String> entity = new HttpEntity<String>(headers);
	      
	            return template.exchange("http://localhost:3000/demo", HttpMethod.POST, entity, String.class).getBody();
		
			}
			



	
	
} // class
