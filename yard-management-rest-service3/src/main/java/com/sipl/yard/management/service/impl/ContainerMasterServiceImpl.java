package com.sipl.yard.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.yard.management.dtos.ContainerMasterDto;
import com.sipl.yard.management.entities.ContainerMaster;
import com.sipl.yard.management.mapper.ContainerMasterMapper;
import com.sipl.yard.management.repository.ContainerMasterRepository;
import com.sipl.yard.management.responses.ContainerMasterApiResponse;
import com.sipl.yard.management.service.ContainerMasterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContainerMasterServiceImpl implements ContainerMasterService {

	@Autowired
	private ContainerMasterRepository containerMasterRepository;

	@Autowired
	private ContainerMasterMapper containerMasterMapper;
	
	@CacheEvict(value = "getAllContainerMaster", allEntries = true)
	@Override
	public ContainerMasterApiResponse add(ContainerMasterDto containerMasterDto) {
		log.info("<<start>>In add method<<start>>");
		try {
			if (containerMasterRepository.findBySize(containerMasterDto.getSize()).isPresent()) {
				return new ContainerMasterApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
						containerMasterDto.getSize() + " already present", true);
			} else {
				ContainerMaster dataFromdb = containerMasterRepository
						.save(containerMasterMapper.mapContainerMasterDtoToContainerMaster(containerMasterDto));
				return new ContainerMasterApiResponse(containerMasterMapper.mapContainerMasterToContainerMasterDto(dataFromdb), null,
						HttpStatus.CREATED, "Container added successfully", false);
			}
		} catch (Exception e) {
			log.error("Exception in add method of ContainerMaster",e);
		}
		return new ContainerMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	@CacheEvict(value = "getAllContainerMaster", allEntries = true)
	@Override
	public ContainerMasterApiResponse updateContainer(ContainerMasterDto containerMasterDto) {
		log.info("<<start>>In updateContainer method<<start>>");
		try {
			Optional<ContainerMaster> containerMasterFatchFromDb = containerMasterRepository.findById(containerMasterDto.getId());
			if (containerMasterFatchFromDb.isPresent()) {
				ContainerMaster containerDetailsFromDb = containerMasterFatchFromDb.get();
				containerDetailsFromDb.setHeight(containerMasterDto.getHeight());
				containerDetailsFromDb.setLength(containerMasterDto.getLength());
				containerDetailsFromDb.setWidth(containerMasterDto.getWidth());
				containerDetailsFromDb.setSize(containerMasterDto.getSize());
				ContainerMaster updatedContainer = containerMasterRepository.save(containerDetailsFromDb);
				log.info("<<end>>updateContainer<<end>>");
				return new ContainerMasterApiResponse(containerMasterMapper.mapContainerMasterToContainerMasterDto(updatedContainer),
						null, HttpStatus.OK, "Container Details Updated successfully", false);

			} else {
				return new ContainerMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Container Id Does Not Exists",
						true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Container updateContainer JDBCConnectionException: ", e);
			return new ContainerMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}

	}
	
	@CacheEvict(value = "getAllContainerMaster", allEntries = true)
	@Override
	public ContainerMasterApiResponse deleteById(ContainerMasterDto containerMasterDto) {
		log.info("<<start>>In deleteById method<<start>>");
		try { 
			int id = containerMasterDto.getId();
			log.debug("In deleteById method Id: " + id);
			final Optional<ContainerMaster> yardFetchedFromDb = containerMasterRepository.findById(id);
			log.debug("Container Details Fetched From Db " + yardFetchedFromDb);
			if (yardFetchedFromDb.isPresent()) {
				ContainerMaster containerMaster = yardFetchedFromDb.get();
				containerMasterRepository.deleteById(id);
				return new ContainerMasterApiResponse(containerMasterMapper.mapContainerMasterToContainerMasterDto(containerMaster), null,
						HttpStatus.OK, "Container Size " + containerMaster.getSize() + " deleted successfully", false);
			} else {
				log.info("<<end>>In deleteById method<<end>>");
				return new ContainerMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Container id doesn't exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Container deleteById JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("Container deleteById Exception: ", e);
		}
		return new ContainerMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Cacheable("getAllContainerMaster")
	@Override
	public ContainerMasterApiResponse getAll() {
		log.info("<<start>>In getAll method<<start>>");
		try {
			List<ContainerMaster> containerMasters = (List<ContainerMaster>) containerMasterRepository.findAll();
			if (!containerMasters.isEmpty()) {
				return new ContainerMasterApiResponse(null, containerMasterMapper.containerMasterListToContainerMasterDtoList(containerMasters),
						HttpStatus.FOUND, "Container master found successfully", false);
			} else {
				return new ContainerMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Records not found", true);
			}
		} catch (Exception e) {
			log.error("Exception in getAll ", e);
		}
		return new ContainerMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
	}

}
