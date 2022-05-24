package com.homefix.service;

import java.util.List;

import com.homefix.domain.Company;
import com.homefix.domain.CompanyInfo;
import com.homefix.domain.CompanyReport;
import com.homefix.domain.Member;
import com.homefix.domain.MemberReport;
import com.homefix.domain.Payment;

public interface AdminService {
	
	// 고객 목록 불러오기
	public List<Member> getMemberList();
	
	// 고객 아이디로 정보 불러오기
	public Member getMember(String id);
	
	// 고객 정보 수정
	public void updateMember(Member member);
	
	// 고객 블랙리스트 지정/해제
	public void enableBlackMember(String id, Boolean enabled);
	
	// 고객 신고 목록 불러오기
	public List<MemberReport> getMemberReportList();
	
	// 고객 신고 삭제
	public void deleteMemberReport(String rid);
	
	// 고객 오늘의 신고 개수
	public Long countTodayMemberReport();
	
	// 업체 목록 불러오기
	public List<Company> getCompanyList();
	
	// 업체 아이디로 정보 불러오기
	public Company getCompany(String cid);
	
	// 업체 정보 수정
	public void updateCompany(Company company);
	
	// 업체 블랙리스트 지정/해제
	public void enableBlacklist(String cid, Boolean enabled);
	
	// 업체 상세정보 불러오기
	public CompanyInfo getCompanyDetail(String cid);
	
	// 업체 결제정보 불러오기
	public List<Payment> getPaymentList(String cid);
	
	// 업체 신고 목록 불러오기
	public List<CompanyReport> getCompanyReportList();
	
	// 업체 신고 삭제
	public void deleteCompanyReport(String rid);
	
}
