package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Students {
	
	private int stuNo, kor, eng, math, total, rank;
	private String name, gender;
	private double avg;
	private String hobby;
	private String[] hobbys;
	
	
	public Students(int stuNo, String name, int kor, int eng, int math) {
		this.stuNo = stuNo;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor+eng+math;
		this.avg = total/3.0;
	}
	
	
}
