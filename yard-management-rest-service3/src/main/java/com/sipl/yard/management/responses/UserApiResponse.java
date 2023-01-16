package com.sipl.yard.management.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.sipl.yard.management.dtos.UserMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserApiResponse {
	
    private UserMasterDto userMasterDto;
    private List<UserMasterDto> userMasterDtos;
    private HttpStatus status;
    private String message;
    private boolean error;
    
}
