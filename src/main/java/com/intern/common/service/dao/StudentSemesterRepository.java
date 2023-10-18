package com.intern.common.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.common.service.entity.StudentSemesterEntity;

public interface StudentSemesterRepository extends JpaRepository<StudentSemesterEntity, String> {

	StudentSemesterEntity findByStudentSemesterId(String studentSemesterId);

	List<StudentSemesterEntity> findByStudentMatricNum(String studentMatricNum);

	List<StudentSemesterEntity> findBySemesterId(String semesterId);
}
