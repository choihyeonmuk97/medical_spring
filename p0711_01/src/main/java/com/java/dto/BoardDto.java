package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
    private int bno;
    private String id;
    private String btitle;
    private String bcontent;
    private Timestamp bdate;
    private int bgroup;
    private int bstep;
    private int bindent;
    private int bhit;
    private String bfile;

}
