package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
	private int bno,bgroup,bstep,bindent,bhit;
	private String id,btitle,bcontent,bfile;
	private Timestamp bdate;
}
