package com.ClassroomWunderlist.database.bugs.list;

import com.ClassroomWunderlist.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchbugFromList {


    public static String[][] fetchbugFromList(String companyName, String listName) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String[][] response = new String[1][1];

        String query = DBUtils.prepareSelectQuery(" * ", "classroomwunderlist.bugsinlist", "( companyName = '"+companyName+"' AND listName = '"+listName+"')","ORDER BY timestamp asc" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            response = new String[size+1][5];
            rs.beforeFirst();

            if (size>0){
                response[0][0] = "SUCCESS";

                int count = 1;
                while (rs.next()){
                    response[count][0] = rs.getString("bugName");
                    response[count][1] = rs.getString("assigneeEmailId");
                    response[count][2] = rs.getString("deadline");
                    response[count][3] = rs.getString("priority");
                    response[count++][4] = rs.getString("checked");
                }
            }
            else
                response[0][0] = "NO Results found";

        } catch (Exception e) {
            e.printStackTrace();
            response[0][0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return response;
        }
    }

}
