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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intern.common.service.dto.CompanyRequest;
import com.intern.common.service.dto.CompanyResponse;
import com.intern.common.service.dto.QuestionRequest;
import com.intern.common.service.dto.QuestionResponse;
import com.intern.common.service.dto.Response;
import com.intern.common.service.service.CommonService;
import com.intern.common.service.utility.BaseUtility;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	CommonService commonService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to intern common service";
	}
	
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
			
			object_map.put("companies", companyResponses);
		} else {
			message_status = false;
			message_desc = "NULL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/company/{companyId}")
	public ResponseEntity<Response> getCompanyByCompanyId(@PathVariable("companyId") String companyId)  {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isNotBlank(companyId)) {
			CompanyResponse companyResponse = commonService.getCompanyByCompanyId(companyId);
			
			if (BaseUtility.isObjectNotNull(companyResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("company", companyResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/company")
	public ResponseEntity<Response> insertCompany(@RequestPart("companyRequest") CompanyRequest companyRequest, @RequestParam(name = "brochureFile", required = false) MultipartFile brochureFile) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isObjectNotNull(companyRequest)) {
			CompanyResponse companyResponse = commonService.insertCompany(companyRequest, brochureFile);
			
			if (BaseUtility.isObjectNotNull(companyResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("company", companyResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PutMapping("/company")
	public ResponseEntity<Response> updateCompany(@RequestPart("companyRequest") CompanyRequest companyRequest, @RequestParam(name = "brochureFile", required = false) MultipartFile brochureFile) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isObjectNotNull(companyRequest)) {
			CompanyResponse companyResponse = commonService.updateCompany(companyRequest, brochureFile);
			
			if (BaseUtility.isObjectNotNull(companyResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("company", companyResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@DeleteMapping("/company/{companyId}")
	public ResponseEntity<Response> deleteCompanyByCompanyId(@PathVariable("companyId") String companyId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(companyId)) {
			Boolean deleteCompanyStatus = commonService.deleteCompanyByCompanyId(companyId);
			
			if (deleteCompanyStatus) {
				message_status = true;
				message_desc = "SUCCESS";
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/questions")
	public ResponseEntity<Response> getQuestions() {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<QuestionResponse> questionResponses = commonService.getQuestions();

		if (!BaseUtility.isListNull(questionResponses)) {
			message_status = true;
			
			object_map.put("questions", questionResponses);
		} else {
			message_status = false;
			message_desc = "NULL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("questions/filter")
	public ResponseEntity<Response> filterQuestions(@RequestPart("questionRequest") QuestionRequest questionRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		List<QuestionResponse> questionResponses = commonService.filterQuestions(questionRequest);
		object_map.put("questions", questionResponses);
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/question/{questionId}")
	public ResponseEntity<Response> getQuestionByQuestionId(@PathVariable("questionId") String questionId)  {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isNotBlank(questionId)) {
			QuestionResponse questionResponse = commonService.getQuestionByQuestionId(questionId);
			
			if (BaseUtility.isObjectNotNull(questionResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("question", questionResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/question")
	public ResponseEntity<Response> insertQuestion(@RequestPart("questionRequest") QuestionRequest questionRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isObjectNotNull(questionRequest)) {
			QuestionResponse questionResponse = commonService.insertQuestion(questionRequest);
			
			if (BaseUtility.isObjectNotNull(questionResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("company", questionResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PutMapping("/question")
	public ResponseEntity<Response> updateQuestion(@RequestPart("questionRequest") QuestionRequest questionRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isObjectNotNull(questionRequest)) {
			QuestionResponse questionResponse = commonService.updateQuestion(questionRequest);
			
			if (BaseUtility.isObjectNotNull(questionResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("question", questionResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@DeleteMapping("/question/{questionId}")
	public ResponseEntity<Response> deleteQuestionByQuestionId(@PathVariable("questionId") String questionId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(questionId)) {
			Boolean deleteQuestionStatus = commonService.deleteQuestionByQuestionId(questionId);
			
			if (deleteQuestionStatus) {
				message_status = true;
				message_desc = "SUCCESS";
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
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
