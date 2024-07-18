package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
	private int bno, bgroup, bstep, bindent, bhit;
	private String id, btitle, bcontent, bfile;
	private Timestamp bdate;
}
