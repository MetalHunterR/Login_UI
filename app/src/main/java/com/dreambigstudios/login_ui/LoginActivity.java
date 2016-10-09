package com.dreambigstudios.login_ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGGED_IN_USER = "loggedInUser";

    private Toolbar toolbar;
    private TextInputEditText userName, password;
    private TextInputLayout userInput, passwordInput;
    private Button login;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = (TextInputEditText) findViewById(R.id.userName);
        password = (TextInputEditText) findViewById(R.id.password);

        userInput = (TextInputLayout) findViewById(R.id.userInput);
        passwordInput = (TextInputLayout) findViewById(R.id.passwordInput);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    protected void attemptLogin() {
        userInput.setError(null);
        passwordInput.setError(null);

        String user = userName.getText().toString();
        String pass = password.getText().toString();

        boolean cancel = false;
        View viewFocus = null;

        if (TextUtils.isEmpty(pass) || !isValidPassword(pass)) {
            passwordInput.setError("Wrong or invalid password");
            viewFocus = password;
            cancel = true;
        }

        if (TextUtils.isEmpty(user)) {
            userInput.setError("Empty user name");
            viewFocus = userName;
            cancel = true;
        } else if (!isValidUser(user)) {
            userInput.setError("Invalid user name");
            viewFocus = userName;
            cancel = true;
        }

        if (cancel) {
            viewFocus.requestFocus();
        } else {
            Intent loggedIn = new Intent(this, LoggedInActivity.class);

            loggedIn.putExtra(LOGGED_IN_USER, user);
            startActivity(loggedIn);
        }
    }

    protected boolean isValidUser(String user) {
        return user.length() >= 4;
    }

    protected boolean isValidPassword(String pass) {
        return pass.length() >= 4;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}