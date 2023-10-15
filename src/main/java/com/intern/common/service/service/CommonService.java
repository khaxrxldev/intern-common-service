package com.intern.common.service.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.intern.common.service.dto.CompanyRequest;
import com.intern.common.service.dto.CompanyResponse;
import com.intern.common.service.dto.QuestionRequest;
import com.intern.common.service.dto.QuestionResponse;

public interface CommonService {

	List<CompanyResponse> getCompanies();
	
	CompanyResponse getCompanyByCompanyId(String companyId);
	
	CompanyResponse insertCompany(CompanyRequest companyRequest, MultipartFile brochureFile) throws Exception;
	
	CompanyResponse updateCompany(CompanyRequest companyRequest, MultipartFile brochureFile) throws Exception;
	
	Boolean deleteCompanyByCompanyId(String companyId);
	
	List<QuestionResponse> getQuestions();
	
	List<QuestionResponse> filterQuestions(QuestionRequest questionRequest);
	
	QuestionResponse getQuestionByQuestionId(String questionId);
	
	QuestionResponse insertQuestion(QuestionRequest questionRequest) throws Exception;
	
	QuestionResponse updateQuestion(QuestionRequest questionRequest) throws Exception;
	
	Boolean deleteQuestionByQuestionId(String questionId);
}
