package com.example.mydb_term.Service;

import com.example.mydb_term.DAO.ClubDAO;
import com.example.mydb_term.DAO.FundDAO;
import com.example.mydb_term.DTO.Club_FundDTO;
import com.example.mydb_term.Model.ClubModel;
import com.example.mydb_term.Model.FundModel;
import java.time.LocalDate;
import java.util.Scanner;

public class ClubService {

        private final ClubDAO clubDAO;
        private final FundDAO fundDAO;

        public ClubService() {
            this.clubDAO = new ClubDAO();
            this.fundDAO = new FundDAO();
        }

        public void createClub() {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Enter Club Name:");
                String clubName = scanner.nextLine();
                if (clubName.isEmpty()) {
                    throw new IllegalArgumentException("Club name cannot be empty!");
                }

                System.out.println("Enter Club Professor:");
                String clubProfessor = scanner.nextLine();

                System.out.println("Enter Club Introduction:");
                String clubIntro = scanner.nextLine();

                ClubModel clubModel = new ClubModel();
                clubModel.setClubname(clubName);
                clubModel.setProfessor(clubProfessor);
                clubModel.setIntro(clubIntro);
                clubModel.setNumberofmember(0);
                clubDAO.saveClub(clubModel);


                FundModel fundModel = new FundModel();
                fundModel.setCN(clubName);
                fundModel.setSpend(0);
                fundModel.setAmount(0);
                fundModel.setDate(LocalDate.now().toString());

                fundDAO.saveFund(fundModel);

                System.out.println("Club information saved successfully!");

            } catch (Exception e) {
                System.err.println("error club information: " + e.getMessage());
            }
        }

    public void deleteByName(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Club Name:");
        String clubName = scanner.nextLine();
        clubDAO.deleteByName(clubName);

        System.out.println("Club deleted successfully!");
    }
    public void findByName(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Club Name:");
        String clubName = scanner.nextLine();
        System.out.println("Here is Club Information");

        clubDAO.findByName(clubName);

    }

    public void updateClub() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Club Name:");
        String clubName = scanner.nextLine();

        System.out.println("Change Club Name:");
        String new_clubName = scanner.nextLine();
        clubDAO.updateClub(clubName,new_clubName);
    }
}
