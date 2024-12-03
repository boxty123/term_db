package com.example.mydb_term.DAO;

import com.example.mydb_term.DatabaseConnection;
import com.example.mydb_term.Model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public void saveUser(UserModel userModel){
        String query="insert into User (SN,Dept) values (?,?)";
        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement stmt=con.prepareStatement(query)) {
            stmt.setInt(1,userModel.getSN());
            stmt.setString(2,userModel.getDept());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteBySn(int SN) {
        String query="delete from User where SN=?";
        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement stmt=con.prepareStatement(query)) {
            stmt.setInt(1,SN);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
