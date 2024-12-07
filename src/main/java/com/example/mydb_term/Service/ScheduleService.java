package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.ScheduleDAO;
import com.example.mydb_term.Model.ScheduleModel;

import java.time.LocalTime;
import java.util.Scanner;

public class ScheduleService {

    private final ScheduleDAO scheduleDAO;

    public ScheduleService() {
        this.scheduleDAO = new ScheduleDAO();
    }

    public void saveSchedule() {

        ScheduleModel ScheduleModel = new ScheduleModel();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter to do:");
        String todo = scanner.nextLine();

        System.out.println("Enter date:");
        String date = scanner.nextLine();

        System.out.println("Enter ClubName:");
        String CN = scanner.nextLine();

        ScheduleModel.setTodo(todo);
        ScheduleModel.setDate(date);
        ScheduleModel.setCN(CN);

        scheduleDAO.saveSchedule(ScheduleModel);
    }

    public void findAllByDate() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the ClubName:");
        String clubname = scanner.nextLine();

        System.out.println("If you want show all schedule press enter, Or Enter the date: ");
        String date = scanner.nextLine();
        if (date.isEmpty())
            scheduleDAO.findAllByCN(clubname);
        else {
            scheduleDAO.findAllByDateandCN(date, clubname);
        }
    }


    public void updateByDate() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ClubName:");
        String CN = scanner.nextLine();

        System.out.println("Enter the date:");
        String date = scanner.nextLine();

        System.out.println("Rewrite the todo:");
        String todo = scanner.nextLine();

        scheduleDAO.updateByDate(todo,date,CN);
    }

    public void deleteByDate() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Date:");
        String date = scanner.nextLine();

        scheduleDAO.deleteByDate(date);
    }
}
