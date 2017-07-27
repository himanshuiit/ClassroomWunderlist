package com.ClassroomWunderlist.database.logIn;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userLoggedIn {

    public static String[] userLoggedIn() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.currentuser", "" );

        String[] status = new String[6];

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            rs.next();
            status[0]="success";
            status[1]=rs.getString("fullName");
            status[2]=rs.getString("emailId");
            status[3]=rs.getString("phoneNumber");
            status[4]=rs.getString("gender");
            status[5]=rs.getString("college");
        } catch (Exception e) {
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return status;
        }
    }
}
