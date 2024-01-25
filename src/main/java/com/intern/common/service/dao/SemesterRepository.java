package com.intern.common.service.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.common.service.entity.SemesterEntity;

public interface SemesterRepository extends JpaRepository<SemesterEntity, String> {

	SemesterEntity findBySemesterId(String semesterId);
	
	List<SemesterEntity> findBySemesterPartAndSemesterStartEvaluateDateAndSemesterEndEvaluateDate(String semesterPart, Timestamp semesterStartEvaluateDate, Timestamp semesterEndEvaluateDate);
	
	List<SemesterEntity> findBySemesterCode(String semesterCode);
	
	Integer deleteBySemesterId(String semesterId);
}
