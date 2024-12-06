package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.JoinDAO;
import com.example.mydb_term.Model.JoinModel;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class JoinService {

    JoinDAO joinDAO;

    public JoinService(){
        this.joinDAO=new JoinDAO();
    }

    public void join() {
        JoinModel joinModel = new JoinModel();
        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Enter Club Name:");
            String clubName = scanner.nextLine();
            if (clubName.isEmpty()) {
                throw new IllegalArgumentException("Club name cannot be empty.");
            }

            System.out.println("Enter Student Number:");
            int SN = scanner.nextInt();

            System.out.println("Are you admin? (true or false):");
            boolean role;
            try {
                role = scanner.nextBoolean();
            } catch (InputMismatchException e) {
                throw new IllegalArgumentException("Invalid role. Enter true or false.");
            }

            joinModel.setSN(SN);
            joinModel.setCN(clubName);
            joinModel.setRole(role);

            joinDAO.joinClub(joinModel);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void withdraw(){
        JoinModel joinModel=new JoinModel();

        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter Club Name:");
        String clubName = scanner.nextLine();
        System.out.println("Enter Student Number:");
        int SN=scanner.nextInt();

        joinModel.setSN(SN);
        joinModel.setCN(clubName);

        joinDAO.withdrawClub(joinModel);
    }

    public void findSNByCN(){

        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter Club Name:");

        String clubname=scanner.nextLine();

        joinDAO.findAllByClubName(clubname);

    }

    public void updateJoin(){
        System.out.println("Enter Your StudentNumber:");
        Scanner scanner=new Scanner(System.in);
        int SN = parseInt(scanner.nextLine());
        System.out.println("Check Your Current Club:");
        String oldCN=scanner.nextLine();
        System.out.println("Enter the new club:");
        String newCN=scanner.nextLine();

        joinDAO.updateJoin(oldCN,newCN,SN);

    }
}
