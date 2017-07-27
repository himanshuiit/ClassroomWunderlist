package com.ClassroomWunderlist.database.logIn;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbLoginCheck {

    public static String dbLoginCheck(String companyName, String emailId, String password) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomwunderlist.company", " companyName = ? AND employeeEmailId = ? AND password = ?");

        String updateCurrentUserQuery = DBUtils.prepareInsertQuery("classroomwunderlist.currentuser", "companyName, employeeEmailId", "?,?");

        String status = "ongoing";

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, companyName);
            stmt.setString(2, emailId);
            stmt.setString(3, password);
            rs = stmt.executeQuery();
            rs.next();
            status="success";

            stmt = con.prepareStatement(updateCurrentUserQuery);
            stmt.setString(1, companyName);
            stmt.setString(2, emailId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            status = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return status;
        }
    }
}
