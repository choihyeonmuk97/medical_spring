package com.java.controller;
import java.net.HttpURLConnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.OAuthTokenDto;
import com.java.dto.KakaoDto;


@Controller
public class FController {
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	
	//로그인창
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	
	//카카오정보 
	@RequestMapping("/kakao/oauth")
	@ResponseBody
	public String oauth(String code) {
		
		
		// 카카오 로그인
		//##### 1차 : code #####
		
		
		System.out.println("oauth code :"+code);
		String grant_type = "authorization_code";
		String client_id="46aebf8f45d2156ebb99479830ce2694";
		String redirect_uri = "http://localhost:8181/kakao/oauth";
		
		
		//post방식으로 전송 - daum 토큰기를 요청함
		RestTemplate rt = new RestTemplate();
		// header오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//위 데이터를 1개로 묶음 처리
		MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		
		//headers오브젝트 여기에 묶어서 보낸다..
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		String oauthUrl = "https://kauth.kakao.com/oauth/token";
		
		//rt.exchange는 브라우저에 ip를 넣었다방식
		ResponseEntity<String> response = rt.exchange(oauthUrl,
				HttpMethod.POST,kakaoTokenRequest,String.class);
		//kakaoTokenRequest 담아서 보낸다..
		
		System.out.println("response"+response);
		
		//##### 2차 : token키 완료 #####
		
		
		//카카오 response 데이터 받기
		//json데이터를 자바오브젝트 파일로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthTokenDto oAuthTokenDto = null;
		
		try {
			oAuthTokenDto = objectMapper.readValue(response.getBody(),OAuthTokenDto.class);
		} catch (Exception e) {e.printStackTrace();	}
		
		System.out.println("oAuthTokenDto Access_token : "+ oAuthTokenDto.getAccess_token());
		
		
		
		
		//###### 3차 : 사용자 정보 요청 ##########
		
		
		
		//post방식으로 전송 - daum 토큰기를 요청함
				RestTemplate rt2 = new RestTemplate();
				// header오브젝트 생성
				HttpHeaders headers2 = new HttpHeaders();
				
				headers.add("Authorization","Bearer "+oAuthTokenDto.getAccess_token());
				headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
				
				//파라미터 필요없음
				//headers 오브젝트 여기에 묶어서 보낸다..
				HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 = 
						new HttpEntity<>(headers);
				
				//사용자 정보 http 요청
				String oauthUrl2 = "https://kapi.kakao.com/v2/user/me";
				
				ResponseEntity<String> response2 = rt.exchange(oauthUrl2,
						HttpMethod.POST,kakaoTokenRequest2,String.class);
				//kakaoTokenRequest 담아서 보낸다..
		
		
				//###### 끝!!!!!!!!!! ##########
				// ###### json파서 시작 ######
		
				ObjectMapper objectMapper2 = new ObjectMapper();
				objectMapper2.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				KakaoDto kakaoDto = null;
				
				try {
					kakaoDto =  objectMapper2.readValue(response2.getBody(), KakaoDto.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(kakaoDto.getProperties().getNickname());
				return "카카오 개인정보 응답 : " + response2;
	}
	
	
	
	
	
}
