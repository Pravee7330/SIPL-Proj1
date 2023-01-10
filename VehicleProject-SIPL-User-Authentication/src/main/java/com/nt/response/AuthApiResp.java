package com.nt.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthApiResp {

	private final String jwt;
}
