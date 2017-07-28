package com.ClassroomWunderlist.database.lists;

import com.ClassroomWunderlist.database.utils.DBUtils;

import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchLatest {

    public static String[][] fetchlatest(String companyName) {

        VBox noticeList = new VBox();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String[][] response = new String[1][1];

        String query = DBUtils.prepareSelectQuery(" * ", "classroomwunderlist.lists", "companyName = '"+companyName+"'","ORDER BY timestamp asc" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            response = new String[size+1][2];
            rs.beforeFirst();

            if (size<1)
                response[0][0] = "SUCCESS";

            int count = 1;
            while (rs.next()){
                response[count][0] = rs.getString("timestamp");
                response[count++][0] = rs.getString("listName");
            }

        } catch (Exception e) {
            response[0][0] = "SUCCESS";
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return response;
        }
    }

}
