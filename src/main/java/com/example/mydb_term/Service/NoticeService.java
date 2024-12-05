package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.NoticeDAO;
import com.example.mydb_term.Model.NoticeModel;

import java.time.LocalTime;
import java.util.Scanner;

public class NoticeService {

    private final NoticeDAO noticeDAO;

    public NoticeService() {
        this.noticeDAO = new NoticeDAO();
    }

    public void saveNotice() {

        NoticeModel noticeModel=new NoticeModel();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title:");
        String title = scanner.nextLine();


        System.out.println("Enter the content:");
        String comment = scanner.nextLine();

        LocalTime now=LocalTime.now();
        String date=now.toString();

        noticeModel.setTitle(title);
        noticeModel.setContent(comment);
        noticeModel.setDate(date);

        noticeDAO.saveNotice(noticeModel);
    }

    public void findAllByTitle(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title:");
        String title = scanner.nextLine();

        noticeDAO.findAllByTitle(title);

    }
}
