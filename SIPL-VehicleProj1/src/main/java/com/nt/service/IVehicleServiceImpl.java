package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Vehicle;
import com.nt.repository.IVehicleRepo;

@Service("VehicleMgmtservice")
public class IVehicleServiceImpl implements IVehicleMgmtService{

	
	@Autowired
	private IVehicleRepo vehiclerepo;
	
	
	@Override
	public String register(Vehicle vehicle) {
		
		  Optional<Vehicle> opt=vehiclerepo.findById(vehicle.getRegistration_number());
		   if(opt.isPresent()) {
			 
			   return "Vehicle has been  already registered with "+vehicle.getRegistration_number();
		   }else {
			   Vehicle svehicle=vehiclerepo.save(vehicle);
				return "vehicle is registerd with "+vehicle.getRegistration_number();
			  
		   }//else
		
		
		
	}//method
	
}	//class
		
		
	
