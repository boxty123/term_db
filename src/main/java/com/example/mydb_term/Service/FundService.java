package com.example.mydb_term.Service;
import com.example.mydb_term.DAO.FundDAO;
import java.util.Scanner;

public class FundService {
    private final FundDAO fundDAO;

    public FundService() {
        this.fundDAO = new FundDAO();
    }

    public void updateFund(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Club Name:");
        String clubName = scanner.nextLine();
        System.out.println("Enter spend:");
        int spend = scanner.nextInt();
        fundDAO.updateFund(clubName,spend);
    }


    public void findSpendByCNameDate(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Club Name:");
        String clubName = scanner.nextLine();
        System.out.println("Enter date:");
        String date=scanner.nextLine();
        fundDAO.findSpendByNameAndDate(clubName,date);
    }
}
