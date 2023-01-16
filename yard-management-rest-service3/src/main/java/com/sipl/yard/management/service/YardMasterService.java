package com.sipl.yard.management.service;

import com.sipl.yard.management.dtos.YardMasterDto;
import com.sipl.yard.management.responses.YardMasterApiResponse;

public interface YardMasterService {
	
	YardMasterApiResponse add(YardMasterDto yardMasterDto);
	
	YardMasterApiResponse updateYard(YardMasterDto yardMasterDto);
	
	YardMasterApiResponse deleteById(YardMasterDto yardMasterDto);
	
	YardMasterApiResponse getAll();

}
