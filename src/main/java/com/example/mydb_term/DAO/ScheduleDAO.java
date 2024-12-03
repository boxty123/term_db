package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.ScheduleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
