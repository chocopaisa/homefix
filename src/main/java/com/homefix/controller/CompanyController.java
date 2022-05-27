package com.homefix.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.homefix.domain.Company;
import com.homefix.domain.CompanyDto;
import com.homefix.service.CompanyService;

/**
 * @author 이은혜
 *
 */

@Controller
@RequestMapping("/sign")
public class CompanyController {

	private Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	CompanyService companyService;

	// 사업자 아이디 중복 조회
	@GetMapping("/company/idCheck")
	@ResponseBody
	public String idCheck(String id) {
		return companyService.idCheck(id);
	}

	// 사업자 이메일 중복 조회
	@GetMapping("/company/emailCheck")
	@ResponseBody
	public String emailCheck(String email,String email2 ) {
		
		if(email.equals(email2)) {
			return "S";
		}else {
		
		return companyService.emailCheck(email);}
	}

	// 사업자번호 중복 조회
	@GetMapping("/company/companyNumberCheck")
	@ResponseBody
	public String companyNumberCheck(String num) {
		return companyService.companyNumberCheck(num);
	}

	// 로그인하기
	@PostMapping("/company/companyLogin")
	public String loginCheck(Company com, HttpSession session, Model model) {

		if (companyService.login(com) != null) {
			System.out.println("*******로그인 성공********");
			session.setAttribute("userId", com.getId());
			session.setAttribute("companyName", companyService.login(com));
			model.addAttribute("message", "Y");
			return "redirect:/index";
		} else {
			model.addAttribute("message", "N");
			System.out.println("*******로그인 실패*********");
			return "/sign/sign-in";
		}
	}

	@GetMapping("/company/sign-in")
	public String signIn(HttpSession session) {

		if (session.getAttribute("userId") != null) {

			return "/company/companyprofile";

		} else {
			return "/sign/sign-in";

		}

	}

	// 회원가입 동의 페이지 이동
	@GetMapping("/company/sign-agree-b")
	public void signAgree() {

	}

	// 사업자 회원가입 페이지 이동
	@GetMapping("/company/sign-up-b")
	public void signUpBusniss() {

	}

	
	
	
	
	
	// 사업자 정보수정
	@PutMapping("/company/companyUpdate")
	public String companyUpdate(Company com) {
		
	System.out.println(	"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+com.getId());
		companyService.companyUpdate(com);
		return "/company/companyprofile";
	}

	/*
	 * // 사업자 회원 탈퇴
	 * 
	 * @DeleteMapping public String companyDelete(String id) {
	 * companyService.companyDelete(id); return ""; }
	 */

	// 사업자 로그아웃
	@GetMapping("/company/logOut")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("userId") + "님 로그아웃");
		session.invalidate();
		return "redirect:/index";
	}

	// 사업자 회원가입
	@PostMapping("/company/signUpB")
	public String companyInsert(Company com) {
		com.setEnabled(true);
		String num = com.getNum();
		com.setNum(num.replaceAll("-", ""));
		companyService.companyInsert(com);
		return "redirect:/index";
	}

	/* 비밀번호 찾기 */
	/*
	 * @RequestMapping(value = "findIdPw", method = RequestMethod.GET) public void
	 * findIdPwGET() throws Exception{ }
	 * 
	 * @RequestMapping(value = "findIdPw", method = RequestMethod.POST) public void
	 * findIdPwPOST(Company com, HttpServletResponse response) throws Exception{
	 * companyService.findIdPwPOST(response, com); }
	 */

}