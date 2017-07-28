package com.ClassroomWunderlist.database.lists;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addNew {

    public static String add(String timeStamp, String companyName, String listName){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomwunderlist.lists", "timestamp, companyName, listName", "?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, companyName);
            stmt.setString(3, listName);
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
