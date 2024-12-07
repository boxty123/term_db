package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.NoticeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticeDAO {

    public void saveNotice(NoticeModel noticeModel) {
        String checkQuery = "SELECT 1 FROM _Join WHERE SN = ? AND CN = ?";
        String insertQuery = "INSERT INTO Notice (title, content, date, SN, CN) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {

            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, noticeModel.getSN());
                checkStmt.setString(2, noticeModel.getCN());

                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        throw new RuntimeException("You are not member in the Club");
                    }
                }
            }
            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                insertStmt.setString(1, noticeModel.getTitle());
                insertStmt.setString(2, noticeModel.getContent());
                insertStmt.setString(3, noticeModel.getDate());
                insertStmt.setInt(4, noticeModel.getSN());
                insertStmt.setString(5, noticeModel.getCN());

                insertStmt.executeUpdate();
                System.out.println("Save new Notice successfully");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Notice", e);
        }
    }
    public void deleteByTitleAndCN(String Title,String CN) {
        String query = "DELETE FROM Notice WHERE title = ? and CN=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, Title);
            stmt.setString(2, CN);

            stmt.executeUpdate();

            System.out.println("delete successfully");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete club and fund by name", e);
        }
    }


    public void findAllByTitle(String title) {
        String query = "SELECT * FROM Notice WHERE title LIKE ? ";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, "%" + title + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String foundTitle = rs.getString("title");
                    String foundCN=rs.getString("CN");
                    System.out.println("Title: " + foundTitle + ", ClubName: "+foundCN);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find notices by title", e);
        }
    }
    public void findAllByCN(String CN) {
        String query = "SELECT title, COUNT(*) AS count FROM Notice WHERE CN=? GROUP BY title";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, CN);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String foundTitle = rs.getString("title");
                    int count = rs.getInt("count");

                    System.out.println("Title: " + foundTitle + ", Count: " + count);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find notices by title", e);
        }
    }


    public void updateByTitle(String old_title, String new_title) {

        String sql = "UPDATE Notice SET title = ? WHERE title = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, new_title);
            stmt.setString(2, old_title);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
