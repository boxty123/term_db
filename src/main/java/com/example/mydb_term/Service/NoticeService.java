package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.NoticeDAO;
import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.NoticeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


        System.out.println("Enter the ClubName:");
        String CN = scanner.nextLine();

        System.out.println("Enter the StudentNumber:");
        int SN = scanner.nextInt();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(formatter);

        noticeModel.setTitle(title);
        noticeModel.setContent(comment);
        noticeModel.setDate(date);
        noticeModel.setSN(SN);
        noticeModel.setCN(CN);

        noticeDAO.saveNotice(noticeModel);
    }

    public void findAllByTitle(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title:");
        String title = scanner.nextLine();
        System.out.println("Enter the ClubName:");
        String CN = scanner.nextLine();
        noticeDAO.findAllByTitle(title,CN);

    }
    public void findAllByCN(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ClubName:");
        String CN = scanner.nextLine();

        noticeDAO.findAllByCN(CN);

    }

    public void updateNotice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the old title:");
        String oldtitle = scanner.nextLine();
        System.out.println("Enter the new title:");
        String newtitle = scanner.nextLine();
        noticeDAO.updateByTitle(oldtitle, newtitle);
    }

    public void deleteByTitleAndCN() {


        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the title:");
        String title = scanner.nextLine();
        System.out.println("Enter the ClubName:");
        String CN = scanner.nextLine();

        noticeDAO.deleteByTitleAndCN(title,CN);
    }
}
