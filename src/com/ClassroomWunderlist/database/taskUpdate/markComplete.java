package com.ClassroomWunderlist.database.taskUpdate;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class markComplete {

    public static String update(String companyName, String listName, String bugName,String checked){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareUpdateQuery("classroomwunderlist.bugsinlist", "checked= ? ","( companyName = ? AND listName = ? AND bugName= ? )");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, checked);
            stmt.setString(2, companyName);
            stmt.setString(3, listName);
            stmt.setString(4, bugName);
            stmt.executeUpdate();
            status = "success";
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
