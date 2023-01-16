package com.sipl.yard.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.yard.management.dtos.YardMasterDto;
import com.sipl.yard.management.entities.YardMaster;
import com.sipl.yard.management.mapper.YardMasterMapper;
import com.sipl.yard.management.repository.YardMasterRepository;
import com.sipl.yard.management.responses.YardMasterApiResponse;
import com.sipl.yard.management.service.YardMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class YardMasterServiceImpl implements YardMasterService {

	@Autowired
	private YardMasterRepository yardMasterRepository;

	@Autowired
	private YardMasterMapper yardMasterMapper;
	
	@CacheEvict(value = "getAllYardMaster", allEntries = true)
	@Override
	public YardMasterApiResponse add(YardMasterDto yardMasterDto) {
		log.info("<<start>>In add method<<start>>");
		try {
			if (yardMasterRepository.findByYardName(yardMasterDto.getYard()).isPresent()) {
				return new YardMasterApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
						yardMasterDto.getYard() + " already present", true);
			} else {
				YardMaster dataFromdb = yardMasterRepository
						.save(yardMasterMapper.mapYardMasterDtoToYardMaster(yardMasterDto));
				return new YardMasterApiResponse(yardMasterMapper.mapYardMasterToYardMasterDto(dataFromdb), null,
						HttpStatus.CREATED, "Yard added successfully", false);
			}
		} catch (Exception e) {
			log.error("Exception in add method of YardMaster",e);
		}
		return new YardMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	@CacheEvict(value = "getAllYardMaster", allEntries = true)
	@Override
	public YardMasterApiResponse updateYard(YardMasterDto yardMasterDto) {
		log.info("<<start>>In updateYard method<<start>>");
		try {
			Optional<YardMaster> yardMasterFatchFromDb = yardMasterRepository.findById(yardMasterDto.getId());
			if (yardMasterFatchFromDb.isPresent()) {
				YardMaster yardDetailsFromDb = yardMasterFatchFromDb.get();
				yardDetailsFromDb.setColumns(yardMasterDto.getColumns());
				yardDetailsFromDb.setRows(yardMasterDto.getRows());
				yardDetailsFromDb.setTier(yardMasterDto.getTier());
				YardMaster updatedYard = yardMasterRepository.save(yardDetailsFromDb);
				log.info("<<end>>updateYard<<end>>");
				return new YardMasterApiResponse(yardMasterMapper.mapYardMasterToYardMasterDto(updatedYard),
						null, HttpStatus.OK, "Yard Details Updated successfully", false);

			} else {
				return new YardMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Yard Id Does Not Exists",
						true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Yard updateYard JDBCConnectionException: ", e);
			return new YardMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}

	}
	
	@CacheEvict(value = "getAllYardMaster", allEntries = true)
	@Override
	public YardMasterApiResponse deleteById(YardMasterDto yardMasterDto) {
		log.info("<<start>>In deleteById method<<start>>");
		try {
			int id = yardMasterDto.getId();
			log.debug("In deleteById method Id: " + id);
			final Optional<YardMaster> yardFetchedFromDb = yardMasterRepository.findById(id);
			log.debug("Yard Details Fetched From Db " + yardFetchedFromDb);
			if (yardFetchedFromDb.isPresent()) {
				YardMaster yardMaster = yardFetchedFromDb.get();
				yardMasterRepository.deleteById(id);
				return new YardMasterApiResponse(yardMasterMapper.mapYardMasterToYardMasterDto(yardMaster), null,
						HttpStatus.OK, "Yard " + yardMaster.getYard() + " deleted successfully", false);
			} else {
				log.info("<<end>>In deleteById method<<end>>");
				return new YardMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Yard id doesn't exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Yard deleteById JDBCConnectionException:", e);
		} catch (final DataIntegrityViolationException e) {
			log.error("Yard deleteById DataIntegrityViolationException:", e.getMessage());
			return new YardMasterApiResponse(null, null, HttpStatus.CONFLICT,
					"This yard could not be deleted because it is associated with another record.", true);
		} catch (final Exception e) {
			log.error("Yard deleteById Exception: ", e);
		}
		return new YardMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Cacheable("getAllYardMaster")
	@Override
	public YardMasterApiResponse getAll() {
		log.info("<<start>>In getAll method<<start>>");
		try {
			List<YardMaster> yardMasters = yardMasterRepository.findAll();
			if (!yardMasters.isEmpty()) {
				return new YardMasterApiResponse(null, yardMasterMapper.yardMasterListToYardMasterDtoList(yardMasters),
						HttpStatus.FOUND, "Yard master found successfully", false);
			} else {
				return new YardMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Records not found", true);
			}
		} catch (Exception e) {
			log.error("Exception in getAll ", e);
		}
		return new YardMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
	}
}
