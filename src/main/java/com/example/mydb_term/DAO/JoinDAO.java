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

    public void joinClub(JoinModel joinModel) {
        String query = "INSERT INTO _Join (SN, CN) VALUES (?, ?)";
        String updateUserQuery = "UPDATE User SET CN = ? WHERE SN = ?";
        String updateClubQuery = "UPDATE Club SET NumberofMember = NumberofMember + 1 WHERE ClubName = ?";

        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);

            try (
                    PreparedStatement stmt = con.prepareStatement(query);
                    PreparedStatement stmt2 = con.prepareStatement(updateUserQuery);
                    PreparedStatement stmt3 = con.prepareStatement(updateClubQuery)
            ) {
                // _Join 테이블 삽입
                stmt.setInt(1, joinModel.getSN());
                stmt.setString(2, joinModel.getCN());
                stmt.executeUpdate();

                // User 테이블 업데이트
                stmt2.setString(1, joinModel.getCN());
                stmt2.setInt(2, joinModel.getSN());
                stmt2.executeUpdate();

                // Club 테이블 업데이트
                stmt3.setString(1, joinModel.getCN());
                stmt3.executeUpdate();

                con.commit();

                System.out.println("Join Club Successfully");
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException("Error occurred while joining the club: " + e.getMessage(), e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error: " + e.getMessage(), e);
        }
    }



    public void withdrawClub(JoinModel joinModel) {
        String query = "DELETE FROM _Join WHERE SN = ? AND CN = ?";
        String updateClubQuery = "UPDATE Club SET NumberofMember = NumberofMember - 1 WHERE ClubName = ?";


        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmt = con.prepareStatement(query);
                PreparedStatement stmt2=con.prepareStatement(updateClubQuery)) {

                stmt.setInt(1, joinModel.getSN());
                stmt.setString(2,joinModel.getCN());
                stmt.executeUpdate();

                stmt2.setString(1, joinModel.getCN());
                stmt2.executeUpdate();


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
                    System.out.println(studentNumber);
                    studentList.add(studentNumber);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find students", e);
        }
        return studentList;
    }

    public void updateJoin(String oldCN, String newCN, int SN) {
        String checkQuery = "SELECT 1 FROM _Join WHERE CN = ? AND SN = ?";
        String updateJoinQuery = "UPDATE _Join SET CN = ? WHERE CN = ? AND SN = ?";
        String decrementOldClubQuery = "UPDATE Club SET NumberofMember = NumberofMember - 1 WHERE ClubName = ?";
        String incrementNewClubQuery = "UPDATE Club SET NumberofMember = NumberofMember + 1 WHERE ClubName = ?";

        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setString(1, oldCN);
                checkStmt.setInt(2, SN);

                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        try (PreparedStatement updateStmt = con.prepareStatement(updateJoinQuery)) {
                            updateStmt.setString(1, newCN);
                            updateStmt.setString(2, oldCN);
                            updateStmt.setInt(3, SN);
                            updateStmt.executeUpdate();
                        }

                        // decrement
                        try (PreparedStatement decrementStmt = con.prepareStatement(decrementOldClubQuery)) {
                            decrementStmt.setString(1, oldCN);
                            decrementStmt.executeUpdate();
                        }

                        // increment
                        try (PreparedStatement incrementStmt = con.prepareStatement(incrementNewClubQuery)) {
                            incrementStmt.setString(1, newCN);
                            incrementStmt.executeUpdate();
                        }

                        con.commit();
                        System.out.println("Membership updated successfully.");
                    } else {
                        System.out.println("No matching record found for the old club and student ID.");
                    }
                }
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException("Failed to update membership: " + e.getMessage(), e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error: " + e.getMessage(), e);
        }
    }


}
