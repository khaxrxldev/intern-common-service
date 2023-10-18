package com.intern.common.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_student_semester")
public class StudentSemesterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "student_semester_id")
	private String studentSemesterId;

	@Column(name = "student_matric_num")
	private String studentMatricNum;

	@Column(name = "semester_id")
	private String semesterId;

	public String getStudentSemesterId() {
		return studentSemesterId;
	}

	public void setStudentSemesterId(String studentSemesterId) {
		this.studentSemesterId = studentSemesterId;
	}

	public String getStudentMatricNum() {
		return studentMatricNum;
	}

	public void setStudentMatricNum(String studentMatricNum) {
		this.studentMatricNum = studentMatricNum;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
}
