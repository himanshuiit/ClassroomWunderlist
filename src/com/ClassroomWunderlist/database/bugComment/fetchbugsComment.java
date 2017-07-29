package com.ClassroomWunderlist.database.bugComment;

import com.ClassroomWunderlist.database.utils.DBUtils;
import com.ClassroomWunderlist.main.template.comments;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchbugsComment {

    public static VBox fetchbugsComment(String companyName, String listName, String bugName, String currentUserMailId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        VBox commentList = new VBox();

        String query = DBUtils.prepareSelectQuery(" * ",
                "classroomwunderlist.bugcomment", "( companyName = '"+companyName+"' AND listName = '"+listName+"' AND bugName = '"+bugName+"')",
                "ORDER BY timestamp asc" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                int count = 1;
                while (rs.next()){
                    String timestamp = rs.getString("timestamp");
                    String commentemailId = rs.getString("emailId");
                    String comment = rs.getString("comment");

                    if (commentemailId.equals(currentUserMailId))
                        commentList.getChildren().add(comments.rightformatmessage(timestamp, comment));
                    else
                        commentList.getChildren().add(comments.leftformatmessage(timestamp, commentemailId, comment));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return commentList;
        }
    }


}
