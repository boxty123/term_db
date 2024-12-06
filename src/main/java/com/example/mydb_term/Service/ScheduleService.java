package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.ScheduleDAO;
import com.example.mydb_term.Model.ScheduleModel;

import java.time.LocalTime;
import java.util.Scanner;

public class ScheduleService {

    private final ScheduleDAO ScheduleDAO;

    public ScheduleService() {
        this.ScheduleDAO = new ScheduleDAO();
    }

    public void saveSchedule() {

        ScheduleModel ScheduleModel=new ScheduleModel();

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
        
        ScheduleDAO.saveSchedule(ScheduleModel);
    }

    public void findAllByDate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the date:");
        String date = scanner.nextLine();

        System.out.println("Enter the ClubName:");
        String clubname = scanner.nextLine();

        ScheduleDAO.findAllByDateandCN(date,clubname);

    }
}
