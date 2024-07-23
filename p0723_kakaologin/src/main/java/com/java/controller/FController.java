package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.KakaoDto;
import com.java.dto.LogoutDto;
import com.java.dto.OAuthTokenDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	
	@Autowired HttpSession session;
	// 토큰 키 객체
	OAuthTokenDto oAuthTokenDto = null;
	
	@Value("${kakao.admin_id}")
	private String id;
	
	@RequestMapping({"/","/index","/main"})
	public String index() {
		System.out.println("application id : "+id);
		return "index";
	}
	
	// logout
	@RequestMapping("/kakao/logout")
	public String logout() {
		
		// 카카오 세션 종료
		String oauthLogoutUrl = "https://kapi.kakao.com/v1/user/unlink";
		String authorization = "Bearer "+oAuthTokenDto.getAccess_token();
		
		// 1-1. header 오브젝트 생성
		HttpHeaders headers_logout = new HttpHeaders();
		headers_logout.add("Authorization", authorization);
		
		// 1-2. body 없음
		
		// 1-3. header, body 를 1개로 묶음
		HttpEntity<MultiValueMap<String, String>> kakaoTokenLogoutRequest = new HttpEntity<>(headers_logout);
		
		// 1-4. http 요청 오브젝트 생성 후 전송
		RestTemplate rt_logout = new RestTemplate();
		ResponseEntity<String> response = rt_logout.exchange(oauthLogoutUrl, HttpMethod.POST, kakaoTokenLogoutRequest, String.class);
		
		System.out.println("1. kakaoTokenLogout response Json type : "+response);
		
		// 1-5. Json 타입을 java object 타입으로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		LogoutDto logoutDto = null; 
		
		try {
			// json -> java class
			logoutDto = objectMapper.readValue(response.getBody(), LogoutDto.class);
			
		} catch (Exception e) {	e.printStackTrace();}
		
		System.out.println("1. logout class : "+logoutDto.getId());
		System.out.println("로그아웃 완료");
		
		// 로컬 서버에서 세션을 종료
		session.invalidate();
		return "redirect:/";
	}
	
	// kakao login
	@RequestMapping("/kakao/oauth")
//	@ResponseBody
	public String oauth(@RequestParam(value = "code") String code) {
		// 1. code 값을 받는 주소
		System.out.println("1. code : "+code);
		
		// 2. token key 요청
		String oauthUrl = "https://kauth.kakao.com/oauth/token";
		String content_type = "application/x-www-form-urlencoded;charset=utf-8";
		String grant_type ="authorization_code";
		String client_id  = "0eaa4e7d89428e4883521e1d0ce6f078";
		String redirect_uri  = "http://localhost:8181/kakao/oauth";
		// code = code;
		
		// 2-1. header 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", content_type);
		
		// 2-2. body 오브젝트 그룹(map) 생성
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", grant_type);
		map.add("client_id", client_id);
		map.add("redirect_uri", redirect_uri);
		map.add("code", code);
		
		// 2-3. header, body 를 1개로 묶음
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(map,headers);
		
		// 2-4. http 요청 오브젝트 생성 후 전송
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.exchange(oauthUrl, HttpMethod.POST, kakaoTokenRequest, String.class);
		
		System.out.println("2. kakaoToken response Json type : "+response);
		
		// 2-5. Json 타입을 java object 타입으로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		try {
			// json -> java class
			oAuthTokenDto = objectMapper.readValue(response.getBody(), OAuthTokenDto.class);
			
		} catch (Exception e) {	e.printStackTrace();}
		
		System.out.println("2-2. kakaoToken class : "+oAuthTokenDto.getAccess_token());

		// 3. 사용자 정보 가져오기
		String oauthUserUrl = "https://kapi.kakao.com/v2/user/me";
		String authorization = "Bearer "+oAuthTokenDto.getAccess_token();
		content_type = "application/x-www-form-urlencoded;charset=utf-8";
		
		
		// 3-1. header 오브젝트 생성
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Authorization", authorization);
		headers_user.add("Content-type", content_type);
		
		// 3-2. body 없음
		
		// 3-3. HttpEntity를 이용 header, body 를 1개로 묶음
		HttpEntity<MultiValueMap<String, String>> kakaoTokenUserRequest = new HttpEntity<>(headers_user);
		
		// 3-4. http 요청 오브젝트 생성 후 전송
		RestTemplate rt_user = new RestTemplate();
		ResponseEntity<String> response_user = rt_user.exchange(oauthUserUrl, HttpMethod.POST, kakaoTokenUserRequest, String.class);
		
		System.out.println("3. kakaoToken user response Json type : "+response_user);
		
		// 3-5. Json 타입을 java object 타입으로 변경
		ObjectMapper objectMapper_user = new ObjectMapper();
		KakaoDto kakaoDto = null;
		
		try {
			// json -> java class
			kakaoDto = objectMapper_user.readValue(response_user.getBody(), KakaoDto.class);
			
		} catch (Exception e) {	e.printStackTrace();}
		
		System.out.println("3-2. kakaoUserToken class Id : "+kakaoDto.getId());
		System.out.println("3-3. kakaoUserToken class Nickname : "+kakaoDto.getProperties().getNickname());
		
		// 로그인의 세션 생성 후 로그인 완료
		session.setAttribute("session_id", kakaoDto.getId());
		session.setAttribute("session_nickName", kakaoDto.getProperties().getNickname());
		
		// return "1. code : "+code;
		// return "2. response : "+response;
		// return "2-2. kakaoToken class : "+oAuthTokenDto.getAccess_token();
		// return "3. kakaoToken class id : "+kakaoDto.getId();
		return "redirect:/";
	}
}