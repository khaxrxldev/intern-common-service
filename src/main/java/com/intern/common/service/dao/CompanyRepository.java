package com.intern.common.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.common.service.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

	CompanyEntity findByCompanyId(String companyId);
	
	Integer deleteByCompanyId(String companyId);
}
