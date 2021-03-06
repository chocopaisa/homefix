package com.homefix.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.homefix.domain.Company;
import com.homefix.domain.Esti_request;
import com.homefix.domain.Estimation;


public interface Esti_requestRepository extends CrudRepository<Esti_request, Integer>{
	//견적 아이디로 커넥트 리스트 찾기
	public List<Esti_request> findByEstimation(Estimation estimation);
	//견적과 회사로 견적 요청 찾기
	public Esti_request findByEstimationAndCompany(Estimation estimation,Company company);
	
}
