package org.example.Services;


import org.example.Database.DAO;
import org.example.Model.UserCredentials;

import java.util.List;

public class Authentication {

    private static DAO dao = new DAO();

    public static boolean authenticate(UserCredentials userCredentials) {
        // Returning whether the user is one of the academic staff members.
        List<UserCredentials> academicStaff = dao.allAcademicStaff();
        for (UserCredentials currentCredentials : academicStaff) {
            if (currentCredentials.getEmail().equals(userCredentials.getEmail()) &&
                    currentCredentials.getPassword().equals(userCredentials.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
