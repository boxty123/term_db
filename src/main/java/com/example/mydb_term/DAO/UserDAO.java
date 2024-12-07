package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public void saveUser(UserModel userModel){
        String query="insert into User (SN,Dept) values (?,?)";
        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement stmt=con.prepareStatement(query)) {
            stmt.setInt(1,userModel.getSN());
            stmt.setString(2,userModel.getDept());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteBySN(int SN) {
        String query="delete from User where SN=?";
        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement stmt=con.prepareStatement(query)) {
            stmt.setInt(1,SN);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void findCNBySN(int sn) {

        String sql = "SELECT CN FROM User WHERE SN = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, sn);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Here are Student "+ sn + "'s clubs");
                    System.out.printf("ClubName: %s\n",
                            rs.getString("CN"));
                } else {
                    System.out.println("No club found.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while finding club by name: " + e.getMessage(), e);
        }
    }

    public void findAll() {

        String sql = "SELECT * FROM User";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("Student Number: %s ",rs.getString("SN"));
                    System.out.printf("ClubName: %s\n",
                            rs.getString("CN"));
                } else {
                    System.out.println("No club found.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while finding club by name: " + e.getMessage(), e);
        }
    }
}
