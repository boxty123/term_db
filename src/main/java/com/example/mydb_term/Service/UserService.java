package com.example.mydb_term.Service;


import com.example.mydb_term.DAO.UserDAO;
import com.example.mydb_term.Model.UserModel;

import java.util.Scanner;

public class UserService {


    UserDAO userDAO;
    public  UserService(){
        this.userDAO=new UserDAO();
    }

    public void saveUser(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Student Number:");
        int SN = scanner.nextInt();


        System.out.println("Enter User Dept:");
        String Dept = scanner.nextLine();

        UserModel UserModel = new UserModel();
        UserModel.setSN(SN);
        UserModel.setDept(Dept);

        userDAO.saveUser(UserModel);

        System.out.println("User saved successfully!");
        
    }
}
