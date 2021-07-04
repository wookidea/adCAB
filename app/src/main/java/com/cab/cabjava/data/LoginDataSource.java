package com.cab.cabjava.data;

import com.cab.cabjava.data.model.Christian;
import com.cab.cabjava.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            Christian fakeUser =
                    new Christian(
                            "정상욱",
                            "youngAdult",
                            "2",
                            "22" ,
                            "01063329179");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}