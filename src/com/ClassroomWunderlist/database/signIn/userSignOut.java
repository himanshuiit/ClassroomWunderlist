package com.ClassroomWunderlist.database.signIn;

import com.ClassroomWunderlist.database.utils.DBUtils;
import com.ClassroomWunderlist.main.functions.getMotherboardSN;

public class userSignOut {

    public static void userSignOut(String emailId) {

        String userID = getMotherboardSN.getMotherboardSN();

        DBUtils.performAction("DELETE FROM `classroomwunderlist`.`currentuser` WHERE `id`='"+userID+"';");
    }

}