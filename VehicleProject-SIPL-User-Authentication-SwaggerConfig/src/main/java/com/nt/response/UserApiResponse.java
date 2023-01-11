
package com.nt.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.nt.dto.UserMasterDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApiResponse {

	private UserMasterDTO userMasterDto;
	private List<UserMasterDTO> userMasterDtos;
	private HttpStatus httpStatus;
	private String message;
	private boolean error;
}
