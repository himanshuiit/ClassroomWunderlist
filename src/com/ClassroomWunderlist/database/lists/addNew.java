package com.ClassroomWunderlist.database.lists;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addNew {

    public static String add(String timeStamp, String emailId, String message){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomdbms.speakouts", "timestamp, emailId, message", "?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, emailId);
            stmt.setString(3, message);
            stmt.executeUpdate();
            status ="success";
        }
        catch(Exception e){
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
