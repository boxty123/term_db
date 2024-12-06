package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.ClubModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubDAO {


    public void saveClub(ClubModel clubModel) {
        String query = "INSERT INTO Club (ClubName, Professor, NumberOfMember,INTRO) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, clubModel.getClubname());
            stmt.setString(2, clubModel.getProfessor());
            stmt.setInt(3,clubModel.getNumberofmember());
            stmt.setString(4, clubModel.getIntro());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save club", e);
        }
    }

    // 클럽 삭제
    public void deleteByName(String clubName) {
        String query = "DELETE FROM Club WHERE ClubName = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, clubName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete club by name", e);
        }
    }

    public void findByName(String clubName) {
        String sql = "SELECT ClubName, NumberofMember, INTRO, Amount FROM Club, Fund WHERE Club.ClubName = ? AND Fund.CN = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, clubName);
            stmt.setString(2, clubName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Here is Club Information:");
                    System.out.printf("ClubName: %s, Number of Members: %d, Intro: %s, Fund: %d%n",
                            rs.getString("ClubName"),
                            rs.getInt("NumberofMember"),
                            rs.getString("INTRO"),
                            rs.getInt("Amount"));
                } else {
                    System.out.println("No such club found.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while finding club by name: " + e.getMessage(), e);
        }
    }




    public void updateClub(String clubName, String newClubName) {
        String sql = "UPDATE Club SET ClubName=? WHERE ClubName = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, newClubName);
            stmt.setString(2, clubName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update Name", e);
        }
    }
}
