package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder 			// 부분 생성자
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor  // 기본 생성자
@Data 				// getter, setter
public class Member {
	
	private String id;
	private String pw;
	private String name;
	private String phone;
	
}
