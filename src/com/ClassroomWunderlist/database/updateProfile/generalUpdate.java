package com.ClassroomWunderlist.database.updateProfile;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class generalUpdate {

    public static String generalUpdate(String primaryKey, String fullName, String emailId, String phoneNumber, String college) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        String queryUserDetail = DBUtils.prepareUpdateQuery("classroomdbms.userdetail", "fullName = ? , emailId = ? , phoneNumber = ? , college = ? ", "emailId = ?");

        String queryCurrentuser = DBUtils.prepareUpdateQuery("classroomdbms.currentuser", "fullName = ? , emailId = ? , phoneNumber = ? , college = ? ", "emailId = ?");

        String status = "";

        try {
            con = DBUtils.getConnection();

            stmt = con.prepareStatement(queryUserDetail);
            stmt.setString(1, fullName);
            stmt.setString(2, emailId);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, college);
            stmt.setString(5, primaryKey);
            int q1 = stmt.executeUpdate();

            stmt = con.prepareStatement(queryCurrentuser);
            stmt.setString(1, fullName);
            stmt.setString(2, emailId);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, college);
            stmt.setString(5, primaryKey);
            int q2 = stmt.executeUpdate();

            if (q1!=-1 && q2!=-2)
                status="Success";
            else
                status="Failed";
        } catch (Exception e) {
            e.printStackTrace();
            status = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return status;
        }

    }
}
