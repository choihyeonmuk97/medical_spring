package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KakaoDto {
	
	private long id;
	private String connected_at;
	private Properties properties; // class
	private Kakao_account kakao_account;
	
	
	@Data // 내부 클래스
	public class Properties{
		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}
	
	@Data
	public class Kakao_account{
		public String profile_nickname_needs_agreement;
		public String profile_image_needs_agreement;
		public Profile profile;
		
		@Data
		public class Profile{
			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public String is_default_image;
			public String is_default_nickname;
			
		}
	}
	
	
	
	// class Properties
	// 1. nickname	String	닉네임
	// 2. profile_image	String	프로필 사진 URL
	// 3. thumbnail_image
	
	
	// class Kakao_account
	
	
	// KakaoDto
	// 1. id	Long	회원번호	O
	// 2. connected_at	Datetime	서비스에 연결 완료된 시각, UTC*	X
	// 3. properties	JSON	사용자 프로퍼티(Property)
	// 4. kakao_account	KakaoAccount	카카오계정 정보	X
	
}
