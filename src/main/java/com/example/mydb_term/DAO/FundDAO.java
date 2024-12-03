package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.FundModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FundDAO {
    public List<Integer> findSpendByNameAndDate(String clubName, String date) {
        String sql = "SELECT spend FROM Fund WHERE CN = ? and date=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, clubName);
            stmt.setString(2,date);
            List<Integer> spendList=new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    spendList.add(rs.getInt("spend"));
                }
                return spendList.isEmpty()? null:spendList;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find club by name", e);
        }
    }

    public void updateFund(String clubname, int spend) {
        String sql = "UPDATE Fund SET amount = amount + ? WHERE CN = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, spend);
            stmt.setString(2, clubname);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Fund updated successfully for club");
            } else {
                System.out.println("Failed to update Fund");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update Fund", e);
        }
    }

    public void saveFund(FundModel fundModel) {
        String query = "INSERT INTO Fund (CN, spend, amount,date) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, fundModel.getCN());
            stmt.setInt(2, fundModel.getSpend());
            stmt.setInt(3, fundModel.getAmount());
            stmt.setString(4,fundModel.getDate());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save fund", e);
        }
    }
}
