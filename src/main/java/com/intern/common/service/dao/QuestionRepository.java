package com.intern.common.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.common.service.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {

	QuestionEntity findByQuestionId(String questionId);
	
	Integer deleteByQuestionId(String questionId);
}
