package org.jbch.cab.viewmodel;

import org.jbch.cab.activity.LoginActivity;
import org.jbch.cab.model.LoggedInUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends LoginActivity {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            "정상욱",
                            "youngAdult"
                           );
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    public String HttpPostData(String Url, String protocol, String username, String password, String mobile) {
        try {
            URL url = new URL(Url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Charset", "UTF-8");
            con.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();
            buffer.append("protocol").append("=").append(protocol).append("&");
            buffer.append("name").append("=").append(username).append("&");
            buffer.append("pwd").append("=").append(password).append("&");
            if(mobile!=null) {
                buffer.append("mobile").append("=").append(mobile);
            }

            OutputStream os = con.getOutputStream();
            os.write(buffer.toString().getBytes("UTF-8"));
            os.flush();
            os.close();

            if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
                return "ResponseCode is not OK";

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str + "\n");
            }
            String myResult = builder.toString();
            return myResult;
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        return null;
    }
}