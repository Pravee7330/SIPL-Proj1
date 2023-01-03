package com.nt.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.VehicleDTO;
import com.nt.mapper.VehicleMapper;
import com.nt.model.Vehicle;
import com.nt.repository.IVehicleRepo;





@Service("VehicleMgmtservice")
public class IVehicleServiceImpl implements IVehicleMgmtService {

	@Autowired
	private IVehicleRepo vehiclerepo;

@Autowired
private   VehicleMapper vehiclemapper;

	
	@Override
	public String add(VehicleDTO vehicledto) {
		
		ModelMapper modelMapper = new ModelMapper();
		// user here is a prepopulated User instance
		
		//Vehicle vehicle = modelMapper.map(vehicledto, Vehicle.class);
		Vehicle vehicle = vehiclemapper.toVehicle(vehicledto);
		
		String no=vehicledto.getRegistrationNumber();

		if (vehiclerepo.findByRegno(no).isPresent()) {

			return "Vehicle has been  already registered with " +no;
		} else {

			vehiclerepo.save(vehicle);

			return "vehicle is registerd with " +no;

		} // else

	}// method

} // class
