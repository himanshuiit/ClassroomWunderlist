package com.ClassroomWunderlist.database.signIn;

import com.ClassroomWunderlist.database.utils.DBUtils;

public class deleteAccount {

    public static void deleteAccount(String emailId) {
        DBUtils.performAction("DELETE FROM `classroomdbms`.`userdetail` WHERE `emailID`='"+emailId+"';");
        DBUtils.performAction("DELETE FROM `classroomdbms`.`currentuser` WHERE `emailID`='"+emailId+"';");
    }
}
