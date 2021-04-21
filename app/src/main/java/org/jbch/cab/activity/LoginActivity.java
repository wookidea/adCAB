package org.jbch.cab.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.jbch.cab.R;
import org.jbch.cab.model.LoggedInUserView;
import org.jbch.cab.model.LoginFormState;
import org.jbch.cab.model.LoginResult;
import org.jbch.cab.viewmodel.LoginViewModel;
import org.jbch.cab.viewmodel.LoginViewModelFactory;
import org.jbch.cab.viewmodel.RequestHttpURLConnection;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Switch autoLogin_switch = (Switch) findViewById(R.id.autoLogin_Switch);
        String url = getResources().getString(R.string.login_url);
        String protocol = getResources().getString(R.string.protocol);


        //스위치의 체크 이벤트를 위한 리스너 등록
        autoLogin_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            // TODO Auto-generated method stub
            Toast.makeText(getApplicationContext(), "체크상태 = " + isChecked, Toast.LENGTH_SHORT).show();
        });


        loginViewModel.getLoginFormState().

                observe(this, new Observer<LoginFormState>() {
                    @Override
                    public void onChanged(@Nullable LoginFormState loginFormState) {
                        if (loginFormState == null) {
                            return;
                        }
                        loginButton.setEnabled(loginFormState.isDataValid());
                        if (loginFormState.getUsernameError() != null) {
                            usernameEditText.setError(getString(loginFormState.getUsernameError()));
                        }
                        if (loginFormState.getPasswordError() != null) {
                            passwordEditText.setError(getString(loginFormState.getPasswordError()));
                        }
                    }
                });

        loginViewModel.getLoginResult().

                observe(this, new Observer<LoginResult>() {
                    @Override
                    public void onChanged(@Nullable LoginResult loginResult) {
                        if (loginResult == null) {
                            return;
                        }
                        loadingProgressBar.setVisibility(View.GONE);
                        if (loginResult.getError() != null) {
                            showLoginFailed(loginResult.getError());
                        }
                        if (loginResult
                                .getSuccess() != null) {
                            updateUiWithUser(loginResult.getSuccess());
                        }
                        setResult(Activity.RESULT_OK);

                        //Complete and destroy login activity once successful
                        finish();
                    }
                });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                loginViewModel.login(
//                        usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());

                ContentValues values = new ContentValues();

                values.put("protocol", protocol);
                values.put("name", usernameEditText.getText().toString());
                values.put("pwd", passwordEditText.getText().toString());
                values.put("mobile", "010-6332-9179");

                NetworkTask networkTask = new NetworkTask(url,values);
                networkTask.execute();
            }
            return false;
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(
//                        usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());

                ContentValues values = new ContentValues();

                values.put("protocol", protocol);
                values.put("name", usernameEditText.getText().toString());
                values.put("pwd", passwordEditText.getText().toString());
                values.put("mobile", "010-6332-9179");


                NetworkTask networkTask = new NetworkTask(url,values);
                networkTask.execute();
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public class NetworkTask extends AsyncTask<Void,Void,String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values){
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);
            return result;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            System.out.println(s);
        }
    }
}