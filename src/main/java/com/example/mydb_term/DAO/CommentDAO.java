package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.CommentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public void saveComment(CommentModel commentModel) {
        String checkQuery = "SELECT 1 FROM Notice WHERE NID = ?";
        String query = "INSERT INTO Comment (comment, date, NID) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt2 = con.prepareStatement(checkQuery);
             PreparedStatement stmt1 = con.prepareStatement(query)) {

            stmt2.setInt(1, commentModel.getNID());
            try (ResultSet rs = stmt2.executeQuery()) {
                if (rs.next()) {
                    stmt1.setString(1, commentModel.getComment());
                    stmt1.setString(2, commentModel.getDate());
                    stmt1.setInt(3, commentModel.getNID());
                    stmt1.executeUpdate();
                    System.out.println("Comment saved successfully.");
                } else {
                    System.out.println("Notice with NID " + commentModel.getNID() + " does not exist.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Comment", e);
        }
    }

    public void deleteByID(int id) {

        String sql="DELETE FROM Comment WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CommentModel> findAllComment() {
        String sql = "SELECT * FROM Comment";
        List<CommentModel> commentModelList = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CommentModel commentModel = new CommentModel();
                commentModel.setCID(rs.getInt("id"));
                commentModel.setNID(rs.getInt("SN"));
                commentModel.setComment(rs.getString("comment"));
                commentModel.setDate(rs.getString("date"));
                commentModel.setNID(rs.getInt("NID"));

                commentModelList.add(commentModel);
            }

            return commentModelList;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching comments", e);
        }
    }

    public void updateById(int id,String content) {
        String sql = "UPDATE Commnet SET content = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, content);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
