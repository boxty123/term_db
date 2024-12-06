package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.NoticeDAO;
import com.example.mydb_term.Model.NoticeModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NoticeService {

    private final NoticeDAO noticeDAO;

    public NoticeService() {
        this.noticeDAO = new NoticeDAO();
    }

    public void saveNotice() {

        NoticeModel noticeModel = new NoticeModel();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title:");
        String title = scanner.nextLine();

        System.out.println("Enter the content:");
        String comment = scanner.nextLine();

        System.out.println("Enter the StudentNumber:");
        int SN = scanner.nextInt();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(formatter);

        noticeModel.setTitle(title);
        noticeModel.setContent(comment);
        noticeModel.setDate(date);
        noticeModel.setSN(SN);

        noticeDAO.saveNotice(noticeModel);
    }

    public void findAllByTitle(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title:");
        String title = scanner.nextLine();

        noticeDAO.findAllByTitle(title);

    }

    public void updateNotice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the old title:");
        String oldtitle = scanner.nextLine();
        System.out.println("Enter the new title:");
        String newtitle = scanner.nextLine();
        noticeDAO.updateByTitle(oldtitle, newtitle);
    }
}
