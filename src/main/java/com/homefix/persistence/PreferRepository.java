package com.homefix.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.homefix.domain.Brag;
import com.homefix.domain.Member;
import com.homefix.domain.Prefer;

public interface PreferRepository extends CrudRepository<Prefer, Integer> {

	long countByBrag(Brag brag);

	List<Prefer> findByBragAndMember(Brag brag, Member member);

	void deleteByBragAndMember(Brag brag, Member member);

	// 개인 마이페이지에 하트찍은 글 가져오기
	List<Prefer> findByMember(Member mem);

	// 개인 마이페이지에 하트찍은 글 페이징
	Page<Prefer> findByMember(Member mem, Pageable pageable);

	// 개인 마이페이지에 하트찍은 글 페이징 2
	int countByMember(Member member);
	

}
