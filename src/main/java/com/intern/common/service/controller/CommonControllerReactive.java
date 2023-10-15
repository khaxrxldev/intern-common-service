package com.intern.common.service.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.common.service.dto.CompanyResponse;
import com.intern.common.service.dto.Response;
import com.intern.common.service.service.CommonService;
import com.intern.common.service.utility.BaseUtility;

@RestController
@RequestMapping("/common/reactive")
public class CommonControllerReactive {

	@Autowired
	CommonService commonService;
	
	@GetMapping("/companies")
	public ResponseEntity<Response> getCompanies() {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<CompanyResponse> companyResponses = commonService.getCompanies();

		if (!BaseUtility.isListNull(companyResponses)) {
			message_status = true;
			
			object_map.put("response", companyResponses);
		} else {
			message_status = false;
			message_desc = "NULL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	public ResponseEntity<Response> returnResponse(Errors errors, HttpStatus http_status, String error_desc, Boolean msg_status, String msg_desc, String msg_code, String msg_dev, Map<Object, Object> object_map) {
		if (BaseUtility.isObjectNotNull(errors)) {
			if (errors.hasErrors()) {
				for (ObjectError objectError: errors.getAllErrors()) {
					error_desc = error_desc + objectError.getDefaultMessage();
					
					if (errors.getErrorCount() > 1) {
						error_desc = error_desc + ", ";
					}
				}
			}
		}
		
		Response response = new Response();
		response.setTime_stamp(LocalDateTime.now());
		response.setStatus_code(http_status.value());
		response.setStatus_desc(http_status);
		response.setError_desc(error_desc);
		response.setMessage_status(msg_status);
		response.setMessage_desc(msg_desc);
		response.setMessage_code(msg_code);
		response.setMessage_dev(msg_dev);
		response.setData(object_map);
		
		return ResponseEntity.ok(response);
	}
}
