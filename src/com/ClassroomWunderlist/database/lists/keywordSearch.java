package com.ClassroomWunderlist.database.lists;

import com.ClassroomWunderlist.database.utils.DBUtils;

import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class keywordSearch {

    public static VBox keywordSearch(String currentUserMailId, String keyword) {

        VBox noticeList = new VBox();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.speakouts", "( message LIKE '%"+keyword+"%' OR emailId LIKE '%"+keyword+"%' )","ORDER BY timestamp asc" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
//            if (size==0)
//                noticeList.getChildren().add(message.errorformatmessage());

            rs.beforeFirst();

            while (rs.next()){
                String timestamp = rs.getString("timestamp");
                String emailId = rs.getString("emailId");
                String notice = rs.getString("message");

//                if (emailId.equals(currentUserMailId))
//                    noticeList.getChildren().add(message.rightformatmessage(timestamp, notice));
//                else
//                    noticeList.getChildren().add(message.leftformatmessage(timestamp, emailId, notice));
            }

        } catch (Exception e) {
//            noticeList.getChildren().add(message.errorformatmessage());
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return noticeList;
        }
    }


}
