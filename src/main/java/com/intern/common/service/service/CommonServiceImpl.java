package com.intern.common.service.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intern.common.service.dao.CompanyRepository;
import com.intern.common.service.dao.QuestionRepository;
import com.intern.common.service.dao.SemesterRepository;
import com.intern.common.service.dao.StudentSemesterRepository;
import com.intern.common.service.dto.CompanyRequest;
import com.intern.common.service.dto.CompanyResponse;
import com.intern.common.service.dto.QuestionRequest;
import com.intern.common.service.dto.QuestionResponse;
import com.intern.common.service.dto.SemesterRequest;
import com.intern.common.service.dto.SemesterResponse;
import com.intern.common.service.dto.StudentSemesterResponse;
import com.intern.common.service.entity.CompanyEntity;
import com.intern.common.service.entity.QuestionEntity;
import com.intern.common.service.entity.SemesterEntity;
import com.intern.common.service.entity.StudentSemesterEntity;
import com.intern.common.service.utility.BaseUtility;
import com.intern.common.service.utility.DateUtility;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	SemesterRepository semesterRepository;
	
	@Autowired
	StudentSemesterRepository studentSemesterRepository;
	
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

	@Override
	public List<SemesterResponse> getSemesters() {
		List<SemesterResponse> semesterResponses = new ArrayList<SemesterResponse>();
		List<SemesterEntity> semesterEntities = semesterRepository.findAll();
		
		for (SemesterEntity semesterEntity : semesterEntities) {
			SemesterResponse semesterResponse = new SemesterResponse();

			semesterResponse.setSemesterId(semesterEntity.getSemesterId());
			semesterResponse.setSemesterCode(semesterEntity.getSemesterCode());
			semesterResponse.setSemesterPart(semesterEntity.getSemesterPart());
			semesterResponse.setSemesterStatus(semesterEntity.getSemesterStatus());
//			semesterResponse.setSemesterStartDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterStartDate()));
//			semesterResponse.setSemesterEndDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterEndDate()));
			semesterResponse.setSemesterStartEvaluateDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterStartEvaluateDate()));
			semesterResponse.setSemesterEndEvaluateDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterEndEvaluateDate()));
			
			semesterResponses.add(semesterResponse);
		}
		
		return semesterResponses;
	}

	@Override
	public List<SemesterResponse> filterSemesters(SemesterRequest semesterRequest) {
		List<SemesterResponse> semesterResponses = new ArrayList<SemesterResponse>();
		List<SemesterEntity> semesterEntities = semesterRepository.findAll();

		for (SemesterEntity semesterEntity : semesterEntities) {
			SemesterResponse semesterResponse = new SemesterResponse();

			semesterResponse.setSemesterId(semesterEntity.getSemesterId());
			semesterResponse.setSemesterCode(semesterEntity.getSemesterCode());
			semesterResponse.setSemesterPart(semesterEntity.getSemesterPart());
			semesterResponse.setSemesterStatus(semesterEntity.getSemesterStatus());
//			semesterResponse.setSemesterStartDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterStartDate()));
//			semesterResponse.setSemesterEndDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterEndDate()));
			semesterResponse.setSemesterStartEvaluateDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterStartEvaluateDate()));
			semesterResponse.setSemesterEndEvaluateDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterEndEvaluateDate()));
			
			Boolean addRow = true;
			
			if (BaseUtility.isNotBlank(semesterRequest.getSemesterPart()) && !semesterRequest.getSemesterPart().equals(semesterEntity.getSemesterPart())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(semesterRequest.getSemesterStatus()) && !semesterRequest.getSemesterStatus().equals(semesterEntity.getSemesterStatus())) {
				addRow = false;
			}
			
			if (addRow) {
				semesterResponses.add(semesterResponse);
			}
		}
		
		return semesterResponses;
	}

	@Override
	public SemesterResponse getSemesterBySemesterId(String semesterId) {
		SemesterResponse semesterResponse = new SemesterResponse();
		SemesterEntity semesterEntity = semesterRepository.findBySemesterId(semesterId);
		
		if (BaseUtility.isObjectNotNull(semesterEntity)) {
			semesterResponse.setSemesterId(semesterEntity.getSemesterId());
			semesterResponse.setSemesterCode(semesterEntity.getSemesterCode());
			semesterResponse.setSemesterPart(semesterEntity.getSemesterPart());
			semesterResponse.setSemesterStatus(semesterEntity.getSemesterStatus());
//			semesterResponse.setSemesterStartDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterStartDate()));
//			semesterResponse.setSemesterEndDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterEndDate()));
			semesterResponse.setSemesterStartEvaluateDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterStartEvaluateDate()));
			semesterResponse.setSemesterEndEvaluateDate(DateUtility.convertToLocalDateTime(semesterEntity.getSemesterEndEvaluateDate()));
			
			return semesterResponse;
		} else {
			return null;
		}
	}

	@Override
	public SemesterResponse insertSemester(SemesterRequest semesterRequest) throws Exception {
		SemesterResponse semesterResponse = new SemesterResponse();
		SemesterEntity newSemesterEntity = new SemesterEntity();
		
		newSemesterEntity.setSemesterId(BaseUtility.generateId());
		newSemesterEntity.setSemesterCode(semesterRequest.getSemesterCode());
		newSemesterEntity.setSemesterPart(semesterRequest.getSemesterPart());
		newSemesterEntity.setSemesterStatus("ACT");
//		newSemesterEntity.setSemesterStartDate(DateUtility.asTimeStamp(semesterRequest.getSemesterStartDate()));
//		newSemesterEntity.setSemesterEndDate(DateUtility.asTimeStamp(semesterRequest.getSemesterEndDate()));
		newSemesterEntity.setSemesterStartEvaluateDate(DateUtility.asTimeStamp(semesterRequest.getSemesterStartEvaluateDate()));
		newSemesterEntity.setSemesterEndEvaluateDate(DateUtility.asTimeStamp(semesterRequest.getSemesterEndEvaluateDate()));
		
		SemesterEntity insertedSemesterEntity = semesterRepository.save(newSemesterEntity);
		
		if (BaseUtility.isObjectNotNull(insertedSemesterEntity)) {
			semesterResponse.setSemesterId(insertedSemesterEntity.getSemesterId());
			semesterResponse.setSemesterCode(insertedSemesterEntity.getSemesterCode());
			semesterResponse.setSemesterPart(insertedSemesterEntity.getSemesterPart());
			semesterResponse.setSemesterStatus(insertedSemesterEntity.getSemesterStatus());
//			semesterResponse.setSemesterStartDate(DateUtility.convertToLocalDateTime(insertedSemesterEntity.getSemesterStartDate()));
//			semesterResponse.setSemesterEndDate(DateUtility.convertToLocalDateTime(insertedSemesterEntity.getSemesterEndDate()));
			semesterResponse.setSemesterStartEvaluateDate(DateUtility.convertToLocalDateTime(insertedSemesterEntity.getSemesterStartEvaluateDate()));
			semesterResponse.setSemesterEndEvaluateDate(DateUtility.convertToLocalDateTime(insertedSemesterEntity.getSemesterEndEvaluateDate()));
		} else {
			throw new Exception();
		}
		
		return semesterResponse;
	}

	@Override
	public SemesterResponse updateSemester(SemesterRequest semesterRequest) throws Exception {
		SemesterResponse semesterResponse = new SemesterResponse();
		SemesterEntity existedSemesterEntity = semesterRepository.findBySemesterId(semesterRequest.getSemesterId());
		
		if (BaseUtility.isObjectNotNull(existedSemesterEntity)) {
			existedSemesterEntity.setSemesterCode(semesterRequest.getSemesterCode());
			existedSemesterEntity.setSemesterPart(semesterRequest.getSemesterPart());
//			existedSemesterEntity.setSemesterStatus(semesterRequest.getSemesterStatus());
//			existedSemesterEntity.setSemesterStartDate(DateUtility.asTimeStamp(semesterRequest.getSemesterStartDate()));
//			existedSemesterEntity.setSemesterEndDate(DateUtility.asTimeStamp(semesterRequest.getSemesterEndDate()));
			existedSemesterEntity.setSemesterStartEvaluateDate(DateUtility.asTimeStamp(semesterRequest.getSemesterStartEvaluateDate()));
			existedSemesterEntity.setSemesterEndEvaluateDate(DateUtility.asTimeStamp(semesterRequest.getSemesterEndEvaluateDate()));
			
			SemesterEntity updatedSemesterEntity = semesterRepository.save(existedSemesterEntity);
			
			if (BaseUtility.isObjectNotNull(updatedSemesterEntity)) {
				semesterResponse.setSemesterId(updatedSemesterEntity.getSemesterId());
				semesterResponse.setSemesterCode(updatedSemesterEntity.getSemesterCode());
				semesterResponse.setSemesterPart(updatedSemesterEntity.getSemesterPart());
				semesterResponse.setSemesterStatus(updatedSemesterEntity.getSemesterStatus());
//				semesterResponse.setSemesterStartDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterStartDate()));
//				semesterResponse.setSemesterEndDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterEndDate()));
				semesterResponse.setSemesterStartEvaluateDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterStartEvaluateDate()));
				semesterResponse.setSemesterEndEvaluateDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterEndEvaluateDate()));
			} else {
				throw new Exception();
			}
			
			return semesterResponse;
		} else {
			return null;
		}
	}

	@Override
	public List<SemesterResponse> updateSemesterStatusList(List<SemesterRequest> semesterRequests) throws Exception {
		List<SemesterResponse> semesterResponses = new ArrayList<SemesterResponse>();
		
		for (SemesterRequest semesterRequest : semesterRequests) {
			SemesterEntity existedSemesterEntity = semesterRepository.findBySemesterId(semesterRequest.getSemesterId());
			
			if (BaseUtility.isObjectNotNull(existedSemesterEntity)) {
				existedSemesterEntity.setSemesterStatus(semesterRequest.getSemesterStatus());
				
				SemesterEntity updatedSemesterEntity = semesterRepository.save(existedSemesterEntity);
				
				if (BaseUtility.isObjectNotNull(updatedSemesterEntity)) {
					SemesterResponse semesterResponse = new SemesterResponse();
					
					semesterResponse.setSemesterId(updatedSemesterEntity.getSemesterId());
					semesterResponse.setSemesterCode(updatedSemesterEntity.getSemesterCode());
					semesterResponse.setSemesterPart(updatedSemesterEntity.getSemesterPart());
					semesterResponse.setSemesterStatus(updatedSemesterEntity.getSemesterStatus());
//					semesterResponse.setSemesterStartDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterStartDate()));
//					semesterResponse.setSemesterEndDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterEndDate()));
					semesterResponse.setSemesterStartEvaluateDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterStartEvaluateDate()));
					semesterResponse.setSemesterEndEvaluateDate(DateUtility.convertToLocalDateTime(updatedSemesterEntity.getSemesterEndEvaluateDate()));
					
					semesterResponses.add(semesterResponse);
				} else {
					throw new Exception();
				}
			}
		}
		
		return semesterResponses;
	}

	@Transactional
	public Boolean deleteSemesterBySemesterId(String semesterId) {
		Integer totalSemesterDeleted = 0;
		
		try {
			List<StudentSemesterEntity> existedStudentSemesterEntities = studentSemesterRepository.findBySemesterId(semesterId);
			
			if (existedStudentSemesterEntities.size() > 0) {
				throw new Exception("Error! semester is being used.");
			} else {
				totalSemesterDeleted = semesterRepository.deleteBySemesterId(semesterId);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalSemesterDeleted > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<StudentSemesterResponse> insertStudentSemesters(List<String> semesterIdList, String studentMatricNum) {
		List<StudentSemesterResponse> studentSemesterResponses = new ArrayList<StudentSemesterResponse>();
		List<StudentSemesterEntity> existedStudentSemesterEntities = studentSemesterRepository.findByStudentMatricNum(studentMatricNum);
		
		for (int i = 0; i < semesterIdList.size(); i++) {
			StudentSemesterEntity studentSemesterEntity = new StudentSemesterEntity();
			
			if (!BaseUtility.isListNull(existedStudentSemesterEntities)) {
				studentSemesterEntity.setStudentSemesterId(existedStudentSemesterEntities.get(i).getStudentSemesterId());
				studentSemesterEntity.setStudentMatricNum(existedStudentSemesterEntities.get(i).getStudentMatricNum());
				studentSemesterEntity.setSemesterId(semesterIdList.get(i));
			} else {
				studentSemesterEntity.setStudentSemesterId(BaseUtility.generateId());
				studentSemesterEntity.setStudentMatricNum(studentMatricNum);
				studentSemesterEntity.setSemesterId(semesterIdList.get(i));
			}

			StudentSemesterEntity insertedStudentSemesterEntity = studentSemesterRepository.save(studentSemesterEntity);
			
			if (BaseUtility.isObjectNotNull(insertedStudentSemesterEntity)) {
				StudentSemesterResponse studentSemesterResponse = new StudentSemesterResponse();
				
				studentSemesterResponse.setStudentSemesterId(insertedStudentSemesterEntity.getStudentSemesterId());
				studentSemesterResponse.setStudentMatricNum(insertedStudentSemesterEntity.getStudentMatricNum());
				studentSemesterResponse.setSemesterId(insertedStudentSemesterEntity.getSemesterId());
				
				if (BaseUtility.isNotBlank(insertedStudentSemesterEntity.getSemesterId())) {
					SemesterEntity existedSemesterEntity = semesterRepository.findBySemesterId(insertedStudentSemesterEntity.getSemesterId());
					
					if (BaseUtility.isObjectNotNull(existedSemesterEntity)) {
						SemesterResponse semesterResponse = new SemesterResponse();
						
						semesterResponse.setSemesterId(existedSemesterEntity.getSemesterId());
						semesterResponse.setSemesterCode(existedSemesterEntity.getSemesterCode());
						semesterResponse.setSemesterPart(existedSemesterEntity.getSemesterPart());
						semesterResponse.setSemesterStatus(existedSemesterEntity.getSemesterStatus());
//						semesterResponse.setSemesterStartDate(DateUtility.convertToLocalDateTime(existedSemesterEntity.getSemesterStartDate()));
//						semesterResponse.setSemesterEndDate(DateUtility.convertToLocalDateTime(existedSemesterEntity.getSemesterEndDate()));
						semesterResponse.setSemesterStartEvaluateDate(DateUtility.convertToLocalDateTime(existedSemesterEntity.getSemesterStartEvaluateDate()));
						semesterResponse.setSemesterEndEvaluateDate(DateUtility.convertToLocalDateTime(existedSemesterEntity.getSemesterEndEvaluateDate()));
						
						studentSemesterResponse.setSemester(semesterResponse);
					}
				}
				
				studentSemesterResponses.add(studentSemesterResponse);
			}
		}

		return studentSemesterResponses;
	}
}
