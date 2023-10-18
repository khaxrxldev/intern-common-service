package com.intern.common.service.dto;

public class StudentSemesterResponse {

	private String studentSemesterId;

	private String studentMatricNum;

	private String semesterId;
	
	private SemesterResponse semester;

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

	public SemesterResponse getSemester() {
		return semester;
	}

	public void setSemester(SemesterResponse semester) {
		this.semester = semester;
	}
}
