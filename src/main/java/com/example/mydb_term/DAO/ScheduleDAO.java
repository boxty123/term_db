package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.ScheduleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    public void saveSchedule(ScheduleModel ScheduleModel) {
        String query = "INSERT INTO Schedule (todo, date,CN) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, ScheduleModel.getTodo());
            stmt.setString(2,ScheduleModel.getDate());
            stmt.setString(3, ScheduleModel.getCN());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Schedule", e);
        }
    }

    public List<String> findAllByDateandCN(String date, String clubname) {
        String sql = "SELECT todo FROM Schedule WHERE CN = ? AND date = ?";
        List<String> todolist = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, clubname);
            stmt.setString(2, date);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Here are todos for the date: " + date);
                while (rs.next()) {
                    todolist.add(rs.getString("todo"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred " + e.getMessage(), e);
        }

        if (todolist.isEmpty()) {
            System.out.println("No todos founds.");
        }
        return todolist;
    }
    public void updateByDate(String content, String date) {
        String sql = "UPDATE Schedule SET content = ? WHERE date = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {


            stmt.setString(1,content );
            stmt.setString(2, date);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByDate(String date) {
        String query = "DELETE FROM Schedule WHERE date = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, date);

            stmt.executeUpdate();

            System.out.println("delete successfully");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete schedule by name", e);
        }
    }
}
