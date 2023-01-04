package com.nt.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nt.Response.VehicleApiResponse;
import com.nt.dto.VehicleDTO;
import com.nt.model.Vehicle;
import com.nt.repository.IVehicleRepo;
import com.nt.service.IVehicleMgmtService;





@Service("VehicleMgmtservice")
public class IVehicleServiceImpl implements IVehicleMgmtService {

	@Autowired
	private IVehicleRepo vehiclerepo;
	
	

	ModelMapper modelMapper = new ModelMapper();
	
	

//@Autowired
//private   VehicleMapper vehiclemapper;

	//===============================================================================================
	
	@Override
	public VehicleApiResponse add(VehicleDTO vehicledto) {
		
	
		Vehicle vehicle = modelMapper.map(vehicledto, Vehicle.class);
		
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
	
	
	@Override
	public VehicleApiResponse update( VehicleDTO vehicledto) {

	//	Vehicle vehicle=modelMapper.map(vehicledto,Vehicle.class);
		if (Optional.of(vehiclerepo.getReferenceById(vehicledto.getId())).isEmpty()) {
			return new VehicleApiResponse(null, null, HttpStatus.NO_CONTENT, "No data found", true);
		} else {

			Vehicle vehicle = modelMapper.map(vehicledto,Vehicle.class);
			vehicle.setId(vehicledto.getId());
			vehiclerepo.save(vehicle);
			VehicleDTO vehicleDtoResponse = modelMapper.map(vehicle,VehicleDTO.class);
			return new VehicleApiResponse(vehicleDtoResponse, null, HttpStatus.OK, "Data Updated Successfully", false);
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
	}
//====================================================================================================

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
	
	
	
	@Override
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
	
	
	
	
	
	
	
	
	
	

/*		
	//	GetAll
		public VehicleApiResponse getAll() {
			try {
				List<Vehicle> findAll = vehiclerepo.findAll();
				if(findAll.isEmpty()) {
					return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while getting data",true);
				}
				else {
					return  new VehicleApiResponse(null,mapper.mapVehicleListToVehicleDtoList(findAll),HttpStatus.OK,"Data Retrived Successfully",false);
				}	
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return new VehicleApiResponse(null,null,HttpStatus.NOT_FOUND,"Error Occured while Retrieving Data",true);
		}
	
	*/

	
	
} // class
}