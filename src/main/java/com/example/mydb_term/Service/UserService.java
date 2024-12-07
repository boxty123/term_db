package com.example.mydb_term.Service;


import com.example.mydb_term.DAO.UserDAO;
import com.example.mydb_term.Model.UserModel;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UserService {


    UserDAO userDAO;
    public  UserService(){
        this.userDAO=new UserDAO();
    }

    public void saveUser(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter User Dept:");
        String Dept = scanner.nextLine();

        System.out.println("Enter Student Number:");
        int SN = scanner.nextInt();

        UserModel UserModel = new UserModel();
        UserModel.setSN(SN);
        UserModel.setDept(Dept);

        userDAO.saveUser(UserModel);

        System.out.println("User saved successfully!");
    }

    public void deleteBySN(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter User Student Number:");
        int SN = scanner.nextInt();

        userDAO.deleteBySN(SN);


    }

    public void findCNBySN() {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter Student Number:");
        String SN = scanner.nextLine();

        if(SN.isEmpty()){
            System.out.println("Find All ...");
            userDAO.findAll();
        }
        else{
            userDAO.findCNBySN(parseInt(SN));
        }
    }
}
