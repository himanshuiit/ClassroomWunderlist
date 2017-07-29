package com.ClassroomWunderlist.database.bugs.list;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addNewBug {

    public static String add(String timeStamp, String companyName, String listName, String bugName){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomwunderlist.bugsinlist", "timestamp, companyName, listName, bugName, checked", "?,?,?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, companyName);
            stmt.setString(3, listName);
            stmt.setString(4, bugName);
            stmt.setString(5, "false");
            stmt.executeUpdate();
            status ="success";
        }
        catch(Exception e){
            e.printStackTrace();
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
