package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.CommentDAO;
import com.example.mydb_term.Model.CommentModel;

import java.util.Scanner;


public class CommentService {

    CommentDAO commentDAO=new CommentDAO();
    public void saveComment() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the Notice ID:");
        int NID = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Reply:");
        String content = scanner.nextLine();

        CommentModel commentModel = new CommentModel();
        commentModel.setNID(NID);
        commentModel.setContent(content);

        commentDAO.saveComment(commentModel);
    }


    public void findAllByNID(){

        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the Comment ID:");
        int ID = scanner.nextInt();

        commentDAO.deleteByID(ID);

    }

    public void findAllComment(){

        System.out.println("Find All Comment");
        commentDAO.findAllComment();


    }

    public void updateComment() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Comment ID");
        int ID = scanner.nextInt();

        System.out.println("Rewrite content");
        String content = scanner.nextLine();

        commentDAO.updateById(ID,content);
    }

    public void deleteById() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID");
        int ID = scanner.nextInt();

        commentDAO.deleteByID(ID);
    }
}
