package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.JoinModel;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class JoinDAO {
    ClubDAO clubDao;

    public void joinClub(JoinModel joinModel) {
        String query = "INSERT INTO _Join (SN, CN, Role) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement stmt = con.prepareStatement(query)) {

                // Join 테이블에 데이터 삽입
                stmt.setInt(1, joinModel.getSN());
                stmt.setString(2, joinModel.getCN());
                stmt.setBoolean(3, joinModel.isRole());
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

}
