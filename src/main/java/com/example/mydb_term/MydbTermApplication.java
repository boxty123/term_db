package com.example.mydb_term;

import com.example.mydb_term.Service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MydbTermApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydbTermApplication.class, args);


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

        System.out.println("1.Insert");
        System.out.println("2.Delete");
        System.out.println("3.Find");
        System.out.println("4.Update");

        Scanner menu = new Scanner(System.in);
        Scanner table;

        switch (menu.nextInt()) {
            case 1:
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
                        noticeService.saveNotice();break;
                    case 5:break;
                    case 6: scheduleService.saveSchedule();break;
                    case 7:
                        System.out.println("You can't create only Fund");
                        break;
                }
                break;
            case 2:
                printMenu();
                table = new Scanner(System.in);
                switch (table.nextInt()) {
                    case 1:
                        clubService.deleteByName();
                        break;
                    case 2:
                        break;
                    case 3:
                        joinService.withdraw();
                        break;
                }
                break;
            case 3:
                printMenu();
                table = new Scanner(System.in);
                switch (table.nextInt()) {
                    //case 1:clubService.findByName();break;
                    case 2:
                        break;
                    case 3:
                        joinService.findSNByCN();
                        break;
                    case 5:
                        noticeService.findAllByTitle();break;
                    case 7:
                        fundService.findSpendByCNameDate();
                        break;
                }
                break;
            case 4:
                printMenu();
                table = new Scanner(System.in);
                switch (table.nextInt()) {
                    case 1:
                        clubService.updateClub();
                        break;
                    case 3:
                        joinService.updateJoin();break;
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

}
