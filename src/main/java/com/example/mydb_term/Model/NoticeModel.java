package com.example.mydb_term.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeModel {
    private int NID;
    private String CN;
    private int SN;
    private String title;
    private String content;
    private String date;

}
