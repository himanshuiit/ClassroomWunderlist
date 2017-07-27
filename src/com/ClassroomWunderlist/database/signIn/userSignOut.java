package com.ClassroomWunderlist.database.signIn;

import com.ClassroomWunderlist.database.utils.DBUtils;

public class userSignOut {

    public static void userSignOut(String emailId) {
        DBUtils.performAction("DELETE FROM `classroomdbms`.`currentuser` WHERE `emailID`='"+emailId+"';");
    }

}