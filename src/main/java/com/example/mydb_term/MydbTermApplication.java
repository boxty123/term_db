package com.example.mydb_term;

import com.example.mydb_term.Service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

@SpringBootApplication
public class MydbTermApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydbTermApplication.class, args);


       // String filePath = "C:/Users/junghyun/IdeaProjects/mydb_term/src/main/resources/schema";
       // executeSQLScript(filePath);


        while(true) {
            runconsole();
        }
    }

    public static void runconsole() {
        ClubService clubService = new ClubService();
        FundService fundService = new FundService();
        UserService userService = new UserService();
        JoinService joinService = new JoinService();
        NoticeService noticeService=new NoticeService();
        ScheduleService scheduleService=new ScheduleService();
        CommentService commentService=new CommentService();

        System.out.println("1.Insert");
        System.out.println("2.Delete");
        System.out.println("3.Find");
        System.out.println("4.Update");

        Scanner menu = new Scanner(System.in);
        Scanner table;

        switch (menu.nextInt()) {
            case 1: //insert
                printMenu();
                table = new Scanner(System.in);
                switch (table.nextInt()) {
                    case 1:
                        clubService.createClub();
                        break;
                    case 2:
                        userService.saveUser();
                        break;
                    case 3:
                        joinService.join();
                        break;
                    case 4:
                        noticeService.saveNotice();
                        break;
                    case 5:
                        commentService.saveComment();
                        break;
                    case 6:
                        scheduleService.saveSchedule();
                        break;
                    case 7:
                        System.out.println("You can't create Fund only. Use Update");
                        break;
                }
                break;
            case 2: //delete
                printMenu();
                table = new Scanner(System.in);
                switch (table.nextInt()) {
                    case 1:
                        clubService.deleteByName();
                        break;
                    case 2:
                        userService.deleteBySN();
                        break;
                    case 3:
                        joinService.withdraw();
                        break;
                    case 4:
                        noticeService.deleteByTitleAndCN();
                        break;
                    case 5:
                        commentService.deleteById();
                        break;
                    case 6:
                        scheduleService.deleteByDate();
                        break;
                    case 7:
                        System.out.println("you cant delete fund only");
                }
                break;
            case 3: //find
                printMenu();
                table = new Scanner(System.in);
                switch (table.nextInt()) {
                    case 1:
                        clubService.findByName();
                        break;
                    case 2:
                        userService.findCNBySN();
                        break;
                    case 3:
                        joinService.findSNByCN();
                        break;
                    case 4:
                        noticeService.findAllByCN();
                        noticeService.findAllByTitle();
                        break;
                    case 5:
                        commentService.findAllComment();
                        commentService.findAllByNID();
                        break;
                    case 6:
                        scheduleService.findAllByDate();
                        break;
                    case 7:
                        fundService.findSpendByCNameDate();
                        break;
                }
                break;
            case 4: //update
                printMenu();
                table = new Scanner(System.in);
                switch (table.nextInt()) {
                    case 1:
                        clubService.updateClub();
                        break;
                    case 3:
                        joinService.updateJoin();
                        break;
                    case 4:
                        noticeService.updateNotice();
                        break;
                    case 5:
                        commentService.updateComment();
                        break;
                    case 6:
                        scheduleService.updateByDate();
                        break;
                    case 7:
                        fundService.updateFund();
                        break;
                }
                break;
        }
    }

    public static void printMenu () {
            System.out.println("1.Club");
            System.out.println("2.User");
            System.out.println("3.Join");
            System.out.println("4.Notice");
            System.out.println("5.Comment");
            System.out.println("6.Schedule");
            System.out.println("7.Funds");
        }
    public static void executeSQLScript(String filePath) {
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            StringBuilder sqlBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--") || line.startsWith("#")) {
                    continue;
                }
                sqlBuilder.append(line);
                if (line.endsWith(";")) {
                    stmt.execute(sqlBuilder.toString());
                    sqlBuilder.setLength(0);
                }
            }
            System.out.println("SQL script executed successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute SQL script", e);
        }
    }

}
