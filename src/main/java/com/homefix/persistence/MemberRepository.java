package com.homefix.persistence;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.homefix.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String>{
	//이메일 중복체크
	public List<Member> findByEmail(String email);
	//아이디 중복체크
	public long countById(String id);
	//닉네임 중복체크
	public List<Member> findByNickname(String nickname);
	//회원가입
	public List<Member> findAll();
	
	//로그인
	public List<Member> findByIdAndPassword(String id, String password);
	
	//임시비밀번호 발급-------------------------	
	public Member findMemberByEmail(String email);
	
	//아이디 찾기 ----------------------------
	public Member findByEmailAndTel(String email, String tel);
	
	//회원정보 수정
	public Optional<Member> findById(String id);

	// 특정일 이후 가입자수
	public long countBySubdateGreaterThan(Date date);
	
	// 카카오로 로그인
	public Member findByKakao(String kakaoId);
	

}
