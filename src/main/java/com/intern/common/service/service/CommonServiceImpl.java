package com.intern.common.service.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intern.common.service.dao.CompanyRepository;
import com.intern.common.service.dao.QuestionRepository;
import com.intern.common.service.dto.CompanyRequest;
import com.intern.common.service.dto.CompanyResponse;
import com.intern.common.service.dto.QuestionRequest;
import com.intern.common.service.dto.QuestionResponse;
import com.intern.common.service.entity.CompanyEntity;
import com.intern.common.service.entity.QuestionEntity;
import com.intern.common.service.utility.BaseUtility;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public List<CompanyResponse> getCompanies() {
		List<CompanyResponse> companyResponses = new ArrayList<CompanyResponse>();
		List<CompanyEntity> companyEntities = companyRepository.findAll();
		
		for (CompanyEntity companyEntity : companyEntities) {
			CompanyResponse companyResponse = new CompanyResponse();
			
			companyResponse.setCompanyId(companyEntity.getCompanyId());
			companyResponse.setCompanyName(companyEntity.getCompanyName());
			companyResponse.setCompanyAddress(companyEntity.getCompanyAddress());
			companyResponse.setCompanyPhone(companyEntity.getCompanyPhone());
			companyResponse.setCompanyEmail(companyEntity.getCompanyEmail());
			companyResponse.setCompanyWebsite(companyEntity.getCompanyWebsite());
			companyResponse.setCompanyBrochure(companyEntity.getCompanyBrochure());
			companyResponse.setCompanyBrochureFileName(companyEntity.getCompanyBrochureFileName());
			companyResponse.setCompanyHrName(companyEntity.getCompanyHrName());
			companyResponse.setCompanyHrPhone(companyEntity.getCompanyHrPhone());
			companyResponse.setCompanyHrEmail(companyEntity.getCompanyHrEmail());
			companyResponse.setCompanyHrGender(companyEntity.getCompanyHrGender());
			
			companyResponses.add(companyResponse);
		}
		
		return companyResponses;
	}

	@Override
	public CompanyResponse getCompanyByCompanyId(String companyId) {
		CompanyResponse companyResponse = new CompanyResponse();
		CompanyEntity existedCompanyEntity = companyRepository.findByCompanyId(companyId);
		
		if (BaseUtility.isObjectNotNull(existedCompanyEntity)) {
			companyResponse.setCompanyName(existedCompanyEntity.getCompanyName());
			companyResponse.setCompanyAddress(existedCompanyEntity.getCompanyAddress());
			companyResponse.setCompanyPhone(existedCompanyEntity.getCompanyPhone());
			companyResponse.setCompanyEmail(existedCompanyEntity.getCompanyEmail());
			companyResponse.setCompanyWebsite(existedCompanyEntity.getCompanyWebsite());
			companyResponse.setCompanyBrochure(existedCompanyEntity.getCompanyBrochure());
			companyResponse.setCompanyBrochureFileName(existedCompanyEntity.getCompanyBrochureFileName());
			companyResponse.setCompanyHrName(existedCompanyEntity.getCompanyHrName());
			companyResponse.setCompanyHrPhone(existedCompanyEntity.getCompanyHrPhone());
			companyResponse.setCompanyHrEmail(existedCompanyEntity.getCompanyHrEmail());
			companyResponse.setCompanyHrGender(existedCompanyEntity.getCompanyHrGender());
			
			return companyResponse;
		} else {
			return null;
		}
	}

	@Override
	public CompanyResponse insertCompany(CompanyRequest companyRequest, MultipartFile brochureFile) throws Exception {
		CompanyResponse companyResponse = new CompanyResponse();
		CompanyEntity newCompanyEntity = new CompanyEntity();
		
		newCompanyEntity.setCompanyId(BaseUtility.generateId());
		newCompanyEntity.setCompanyName(companyRequest.getCompanyName());
		newCompanyEntity.setCompanyAddress(companyRequest.getCompanyAddress());
		newCompanyEntity.setCompanyPhone(companyRequest.getCompanyPhone());
		newCompanyEntity.setCompanyEmail(companyRequest.getCompanyEmail());
		newCompanyEntity.setCompanyWebsite(companyRequest.getCompanyWebsite());
		if (brochureFile != null) {
			newCompanyEntity.setCompanyBrochure(brochureFile.getBytes());
			newCompanyEntity.setCompanyBrochureFileName(brochureFile.getOriginalFilename());
		}
		newCompanyEntity.setCompanyHrName(companyRequest.getCompanyHrName());
		newCompanyEntity.setCompanyHrPhone(companyRequest.getCompanyHrPhone());
		newCompanyEntity.setCompanyHrEmail(companyRequest.getCompanyHrEmail());
		newCompanyEntity.setCompanyHrGender(companyRequest.getCompanyHrGender());

		CompanyEntity insertedCompanyEntity = companyRepository.save(newCompanyEntity);
		
		if (BaseUtility.isObjectNotNull(insertedCompanyEntity)) {
			companyResponse.setCompanyId(insertedCompanyEntity.getCompanyId());
			companyResponse.setCompanyName(insertedCompanyEntity.getCompanyName());
			companyResponse.setCompanyAddress(insertedCompanyEntity.getCompanyAddress());
			companyResponse.setCompanyPhone(insertedCompanyEntity.getCompanyPhone());
			companyResponse.setCompanyEmail(insertedCompanyEntity.getCompanyEmail());
			companyResponse.setCompanyWebsite(insertedCompanyEntity.getCompanyWebsite());
			companyResponse.setCompanyHrName(insertedCompanyEntity.getCompanyHrName());
			companyResponse.setCompanyHrPhone(insertedCompanyEntity.getCompanyHrPhone());
			companyResponse.setCompanyHrEmail(insertedCompanyEntity.getCompanyHrEmail());
			companyResponse.setCompanyHrGender(insertedCompanyEntity.getCompanyHrGender());
		} else {
			throw new Exception();
		}
		
		return companyResponse;
	}

	@Override
	public CompanyResponse updateCompany(CompanyRequest companyRequest, MultipartFile brochureFile) throws Exception {
		CompanyResponse companyResponse = new CompanyResponse();
		CompanyEntity existedCompanyEntity = companyRepository.findByCompanyId(companyRequest.getCompanyId());
		
		if (BaseUtility.isObjectNotNull(existedCompanyEntity)) {
			existedCompanyEntity.setCompanyName(companyRequest.getCompanyName());
			existedCompanyEntity.setCompanyAddress(companyRequest.getCompanyAddress());
			existedCompanyEntity.setCompanyPhone(companyRequest.getCompanyPhone());
			existedCompanyEntity.setCompanyEmail(companyRequest.getCompanyEmail());
			existedCompanyEntity.setCompanyWebsite(companyRequest.getCompanyWebsite());
			if (brochureFile != null) {
				existedCompanyEntity.setCompanyBrochure(brochureFile.getBytes());
				existedCompanyEntity.setCompanyBrochureFileName(brochureFile.getOriginalFilename());
			}
			existedCompanyEntity.setCompanyHrName(companyRequest.getCompanyHrName());
			existedCompanyEntity.setCompanyHrPhone(companyRequest.getCompanyHrPhone());
			existedCompanyEntity.setCompanyHrEmail(companyRequest.getCompanyHrEmail());
			existedCompanyEntity.setCompanyHrGender(companyRequest.getCompanyHrGender());
			
			CompanyEntity updatedCompanyEntity = companyRepository.save(existedCompanyEntity);
			
			if (BaseUtility.isObjectNotNull(updatedCompanyEntity)) {
				companyResponse.setCompanyId(updatedCompanyEntity.getCompanyId());
				companyResponse.setCompanyName(updatedCompanyEntity.getCompanyName());
				companyResponse.setCompanyAddress(updatedCompanyEntity.getCompanyAddress());
				companyResponse.setCompanyPhone(updatedCompanyEntity.getCompanyPhone());
				companyResponse.setCompanyEmail(updatedCompanyEntity.getCompanyEmail());
				companyResponse.setCompanyWebsite(updatedCompanyEntity.getCompanyWebsite());
				companyResponse.setCompanyHrName(updatedCompanyEntity.getCompanyHrName());
				companyResponse.setCompanyHrPhone(updatedCompanyEntity.getCompanyHrPhone());
				companyResponse.setCompanyHrEmail(updatedCompanyEntity.getCompanyHrEmail());
				companyResponse.setCompanyHrGender(updatedCompanyEntity.getCompanyHrGender());
			} else {
				throw new Exception();
			}
			
			return companyResponse;
		} else {
			return null;
		}
	}

	@Transactional
	public Boolean deleteCompanyByCompanyId(String companyId) {
		Integer totalCompanyDeleted = 0;
		
		try {
			totalCompanyDeleted = companyRepository.deleteByCompanyId(companyId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalCompanyDeleted > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<QuestionResponse> getQuestions() {
		List<QuestionResponse> questionResponses = new ArrayList<QuestionResponse>();
		List<QuestionEntity> questionEntities = questionRepository.findAll();
		
		for (QuestionEntity questionEntity : questionEntities) {
			QuestionResponse questionResponse = new QuestionResponse();
			
			questionResponse.setQuestionId(questionEntity.getQuestionId());
			questionResponse.setQuestionNumber(questionEntity.getQuestionNumber());
			questionResponse.setQuestionDesc(questionEntity.getQuestionDesc());
			questionResponse.setQuestionCategoryCode(questionEntity.getQuestionCategoryCode());
			questionResponse.setQuestionCategoryDesc(questionEntity.getQuestionCategoryDesc());
			questionResponse.setQuestionWeightage(questionEntity.getQuestionWeightage());
			questionResponse.setQuestionTotalMark(questionEntity.getQuestionTotalMark());
			questionResponse.setCriteriaId(questionEntity.getCriteriaId());
			
			questionResponses.add(questionResponse);
		}
		
		return questionResponses;
	}

	@Override
	public List<QuestionResponse> filterQuestions(QuestionRequest questionRequest) {
		List<QuestionResponse> questionResponses = new ArrayList<QuestionResponse>();
		List<QuestionEntity> questionEntities = questionRepository.findAll();
		
		for (QuestionEntity questionEntity : questionEntities) {
			QuestionResponse questionResponse = new QuestionResponse();
			
			questionResponse.setQuestionId(questionEntity.getQuestionId());
			questionResponse.setQuestionNumber(questionEntity.getQuestionNumber());
			questionResponse.setQuestionDesc(questionEntity.getQuestionDesc());
			questionResponse.setQuestionCategoryCode(questionEntity.getQuestionCategoryCode());
			questionResponse.setQuestionCategoryDesc(questionEntity.getQuestionCategoryDesc());
			questionResponse.setQuestionWeightage(questionEntity.getQuestionWeightage());
			questionResponse.setQuestionTotalMark(questionEntity.getQuestionTotalMark());
			questionResponse.setCriteriaId(questionEntity.getCriteriaId());
			
			Boolean addRow = true;
			
			if (BaseUtility.isNotBlank(questionRequest.getCriteriaId()) && !questionRequest.getCriteriaId().equals(questionEntity.getCriteriaId())) {
				addRow = false;
			}
			
			if (addRow) {
				questionResponses.add(questionResponse);
			}
		}
		
		return questionResponses;
	}

	@Override
	public QuestionResponse getQuestionByQuestionId(String questionId) {
		QuestionResponse questionResponse = new QuestionResponse();
		QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);
		
		if (BaseUtility.isObjectNotNull(questionEntity)) {
			questionResponse.setQuestionId(questionEntity.getQuestionId());
			questionResponse.setQuestionNumber(questionEntity.getQuestionNumber());
			questionResponse.setQuestionDesc(questionEntity.getQuestionDesc());
			questionResponse.setQuestionCategoryCode(questionEntity.getQuestionCategoryCode());
			questionResponse.setQuestionCategoryDesc(questionEntity.getQuestionCategoryDesc());
			questionResponse.setQuestionWeightage(questionEntity.getQuestionWeightage());
			questionResponse.setQuestionTotalMark(questionEntity.getQuestionTotalMark());
			questionResponse.setCriteriaId(questionEntity.getCriteriaId());
			
			return questionResponse;
		} else {
			return null;
		}
	}

	@Override
	public QuestionResponse insertQuestion(QuestionRequest questionRequest) throws Exception {
		QuestionResponse questionResponse = new QuestionResponse();
		QuestionEntity newQuestionEntity = new QuestionEntity();
		
		newQuestionEntity.setQuestionId(BaseUtility.generateId());
		newQuestionEntity.setQuestionNumber(questionRequest.getQuestionNumber());
		newQuestionEntity.setQuestionDesc(questionRequest.getQuestionDesc());
		newQuestionEntity.setQuestionCategoryCode(questionRequest.getQuestionCategoryCode());
		newQuestionEntity.setQuestionCategoryDesc(questionRequest.getQuestionCategoryDesc());
		newQuestionEntity.setQuestionWeightage(questionRequest.getQuestionWeightage());
		newQuestionEntity.setQuestionTotalMark(questionRequest.getQuestionTotalMark());
		newQuestionEntity.setCriteriaId(questionRequest.getCriteriaId());
		
		QuestionEntity insertedQuestionEntity = questionRepository.save(newQuestionEntity);
		
		if (BaseUtility.isObjectNotNull(insertedQuestionEntity)) {
			questionResponse.setQuestionId(insertedQuestionEntity.getQuestionId());
			questionResponse.setQuestionNumber(insertedQuestionEntity.getQuestionNumber());
			questionResponse.setQuestionDesc(insertedQuestionEntity.getQuestionDesc());
			questionResponse.setQuestionCategoryCode(insertedQuestionEntity.getQuestionCategoryCode());
			questionResponse.setQuestionCategoryDesc(insertedQuestionEntity.getQuestionCategoryDesc());
			questionResponse.setQuestionWeightage(insertedQuestionEntity.getQuestionWeightage());
			questionResponse.setQuestionTotalMark(insertedQuestionEntity.getQuestionTotalMark());
			questionResponse.setCriteriaId(insertedQuestionEntity.getCriteriaId());
		} else {
			throw new Exception();
		}
		
		return questionResponse;
	}

	@Override
	public QuestionResponse updateQuestion(QuestionRequest questionRequest) throws Exception {
		QuestionResponse questionResponse = new QuestionResponse();
		QuestionEntity existedQuestionEntity = questionRepository.findByQuestionId(questionRequest.getQuestionId());
		
		if (BaseUtility.isObjectNotNull(existedQuestionEntity)) {
			existedQuestionEntity.setQuestionNumber(questionRequest.getQuestionNumber());
			existedQuestionEntity.setQuestionDesc(questionRequest.getQuestionDesc());
			existedQuestionEntity.setQuestionCategoryCode(questionRequest.getQuestionCategoryCode());
			existedQuestionEntity.setQuestionCategoryDesc(questionRequest.getQuestionCategoryDesc());
			existedQuestionEntity.setQuestionWeightage(questionRequest.getQuestionWeightage());
			existedQuestionEntity.setQuestionTotalMark(questionRequest.getQuestionTotalMark());
			existedQuestionEntity.setCriteriaId(questionRequest.getCriteriaId());
			
			QuestionEntity updatedQuestionEntity = questionRepository.save(existedQuestionEntity);
			
			if (BaseUtility.isObjectNotNull(updatedQuestionEntity)) {
				questionResponse.setQuestionId(updatedQuestionEntity.getQuestionId());
				questionResponse.setQuestionNumber(updatedQuestionEntity.getQuestionNumber());
				questionResponse.setQuestionDesc(updatedQuestionEntity.getQuestionDesc());
				questionResponse.setQuestionCategoryCode(updatedQuestionEntity.getQuestionCategoryCode());
				questionResponse.setQuestionCategoryDesc(updatedQuestionEntity.getQuestionCategoryDesc());
				questionResponse.setQuestionWeightage(updatedQuestionEntity.getQuestionWeightage());
				questionResponse.setQuestionTotalMark(updatedQuestionEntity.getQuestionTotalMark());
				questionResponse.setCriteriaId(updatedQuestionEntity.getCriteriaId());
			} else {
				throw new Exception();
			}
			
			return questionResponse;
		} else {
			return null;
		}
	}

	@Transactional
	public Boolean deleteQuestionByQuestionId(String questionId) {
		Integer totalQuestionDeleted = 0;
		
		try {
			totalQuestionDeleted = questionRepository.deleteByQuestionId(questionId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalQuestionDeleted > 0) {
			return true;
		}
		
		return false;
	}
}
