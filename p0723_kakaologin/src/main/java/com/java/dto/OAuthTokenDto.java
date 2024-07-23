package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OAuthTokenDto {
	
	private String access_token;
	private String token_type;
	private int expires_in;
	private String refresh_token;
	private int refresh_token_expires_in;
	private String scope;
	
//	1. token_type	String	토큰 타입, bearer로 고정	O
//	2. access_token	String	사용자 액세스 토큰 값	O
//	3. id_token	String	ID 토큰 값
//	4. expires_in	Integer	액세스 토큰과 ID 토큰의 만료 시간(초)
//	5. refresh_token	String	사용자 리프레시 토큰 값	O
//	6. refresh_token_expires_in	Integer	리프레시 토큰 만료 시간(초)	O
	
}
