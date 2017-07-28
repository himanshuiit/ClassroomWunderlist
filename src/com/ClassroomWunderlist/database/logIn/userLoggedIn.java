package com.ClassroomWunderlist.database.logIn;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userLoggedIn {

    public static String[] userLoggedIn(String id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomwunderlist.currentuser", "id = '"+id+"'" );

        String[] status = {"ongoing","","",""};

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()){
                status[0]="success";
                status[1]=rs.getString("companyName");
                status[2]=rs.getString("fullName");
                status[3]=rs.getString("employeeEmailId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return status;
        }
    }
}
