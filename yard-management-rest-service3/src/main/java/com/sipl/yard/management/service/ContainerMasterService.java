package com.sipl.yard.management.service;

import com.sipl.yard.management.dtos.ContainerMasterDto;
import com.sipl.yard.management.responses.ContainerMasterApiResponse;

public interface ContainerMasterService {

	ContainerMasterApiResponse getAll();

	ContainerMasterApiResponse deleteById(ContainerMasterDto containerMasterDto);

	ContainerMasterApiResponse updateContainer(ContainerMasterDto containerMasterDto);

	ContainerMasterApiResponse add(ContainerMasterDto containerMasterDto);

}
