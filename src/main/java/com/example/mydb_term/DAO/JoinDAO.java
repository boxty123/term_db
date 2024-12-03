package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.JoinModel;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinDAO {
    ClubDAO clubDao=new ClubDAO();

    public void joinClub(JoinModel joinModel) {
        String query = "INSERT INTO _Join (SN, CN) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmt = con.prepareStatement(query)) {


                stmt.setInt(1, joinModel.getSN());
                stmt.setString(2, joinModel.getCN());
               // stmt.setInt(3, joinModel.isRole() ? 1 : 0);
                stmt.executeUpdate();

                clubDao.incrementMemberCount(joinModel.getCN());

                con.commit();
                System.out.println("Successfully joined the club!");

            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException("Failed to join the club and update member count", e);
            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    public void withdrawClub(JoinModel joinModel) {
        String query = "DELETE FROM _Join WHERE SN = ? AND CN = ?";

        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, joinModel.getSN());
                stmt.setString(2,joinModel.getCN());
                stmt.executeUpdate();

                clubDao.decrementMemberCount(joinModel.getCN());

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException("Failed to withdraw from the club and update member count", e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }
    public List<Integer> findAllByClubName(String clubname) {
        List<Integer> studentList = new ArrayList<>();
        String query = "SELECT SN FROM _Join WHERE CN = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, clubname);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int studentNumber = rs.getInt("SN");
                    studentList.add(studentNumber);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find students", e);
        }
        return studentList;
    }


}
