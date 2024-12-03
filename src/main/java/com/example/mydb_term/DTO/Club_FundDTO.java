package com.example.mydb_term.DTO;

import com.example.mydb_term.DatabaseConnection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club_FundDTO {
    private String professor;
    private int numberOfMembers;
    private int amount;

    public Club_FundDTO findClubByName(String clubName) {
        String sql = "SELECT c.Professor, c.NumberofMember, f.Amount FROM Club c " +
                "JOIN Fund f ON c.ClubName = f.CN WHERE c.ClubName = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, clubName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String professor = rs.getString("Professor");
                    int numberOfMembers = rs.getInt("NumberofMember");
                    int amount = rs.getInt("amount");

                    return new Club_FundDTO(professor, numberOfMembers, amount);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find club by name", e);
        }
    }

}
