package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.NoticeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoticeDAO {

    public void saveNotice(NoticeModel NoticeModel) {
        String query = "INSERT INTO Notice (title, content, date) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, NoticeModel.getTitle());
            stmt.setString(2, NoticeModel.getContent());
            stmt.setString(3,NoticeModel.getDate());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Notice", e);
        }
    }
}
