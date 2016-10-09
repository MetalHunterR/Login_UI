package com.dreambigstudios.login_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    private Toolbar toolbar;
    protected TextView welcomeText;
    private Button logOut;
    protected String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = getIntent().getStringExtra(LoginActivity.LOGGED_IN_USER);

        welcomeText = (TextView) findViewById(R.id.welcome);
        welcomeText.setText(getString(R.string.welcome_back) + userName);

        logOut = (Button) findViewById(R.id.LogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}