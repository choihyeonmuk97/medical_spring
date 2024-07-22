package com.java.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.OAuthTokenDto;

@Controller
public class FController {

	@RequestMapping("/kakao/oauth")
	@ResponseBody
	public String oauth(@RequestParam(value="code") String code) {
		// 카카오에서 코드값을 전달해줌
		System.out.println("1. code : "+code);
		
		
		// -------- 토큰 요청 : 인터넷 url 으로 전송 (post) ---------- //
		String Content_type = "application/x-www-form-urlencoded;charset=utf-8";
		String grant_type = "authorization_code";
		String client_id = "2ffd9ba41b8e2e0a4c7d62374f1f991d";
		String redirect_uri = "http://localhost:8181/kakao/oauth";
		// code = code;
		
		// http 전송
		RestTemplate rt = new RestTemplate();
		
		// HttpHeaders 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", Content_type);
		
		// HttpBody 생성 - 여러개를 입력 받을 경우 Map 사용
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		
		// HttpEntity : HttpHeaders, HttpBody를 1개의 오브젝트로 생성
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
		
		// http 전송 - HttpEntity
		String oauthUrl = "https://kauth.kakao.com/oauth/token";
		ResponseEntity<String> response = rt.exchange(oauthUrl, HttpMethod.POST,kakaoTokenRequest,String.class);
		
		System.out.println("2. 카카오 토큰 요청에 관한 응답 : "+response);
		
		// json 파일을 java 파일로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthTokenDto oAuthTokenDto = null;
		
		try {
			oAuthTokenDto = objectMapper.readValue(response.getBody(), OAuthTokenDto.class);
		} catch (Exception e) { e.printStackTrace();} 
		
		System.out.println("3. Access_token : "+oAuthTokenDto.getAccess_token());
		
		return "카카오 토큰 요청에 대한 응답 : "+oAuthTokenDto.getAccess_token();
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	
}
