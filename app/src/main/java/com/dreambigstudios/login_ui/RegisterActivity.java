package com.dreambigstudios.login_ui;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputEditText userName, email, password, passwordConfirm;
    private TextInputLayout userNameInput, emailInput, passwordInput, passwordConfirmInput;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userName = (TextInputEditText) findViewById(R.id.userNameReg);
        email = (TextInputEditText) findViewById(R.id.userMail);
        password = (TextInputEditText) findViewById(R.id.passwordReg);
        passwordConfirm = (TextInputEditText) findViewById(R.id.passwordConf);

        userNameInput = (TextInputLayout) findViewById(R.id.userInputReg);
        emailInput = (TextInputLayout) findViewById(R.id.userMailInput);
        passwordInput = (TextInputLayout) findViewById(R.id.passwordInputReg);
        passwordConfirmInput = (TextInputLayout) findViewById(R.id.passwordInputConfirm);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
    }

    protected void attemptRegister() {
        userNameInput.setError(null);
        emailInput.setError(null);
        passwordInput.setError(null);
        passwordConfirmInput.setError(null);

        String user = userName.getText().toString();
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String passConf = passwordConfirm.getText().toString();

        boolean registrationFailed = false;
        View viewFocus = null;

        if(TextUtils.isEmpty(user)) {
            userNameInput.setError("Empty User Name");
            viewFocus = userName;
            registrationFailed = true;
        } else if (!isValidUser(user)){
            userNameInput.setError("Invalid User Name");
            viewFocus = userName;
            registrationFailed = true;
        }

        if(TextUtils.isEmpty(mail) || !isValidEmail(mail)) {
            emailInput.setError("Empty or Invalid E-mail");
            viewFocus = email;
            registrationFailed = true;
        }

        if(TextUtils.isEmpty(pass) || !isValidPassword(pass)) {
            passwordInput.setError("Empty or Invalid Password");
            viewFocus = password;
            registrationFailed = true;
        } else if (!isPassMatching(pass, passConf)) {
            passwordInput.setError("Password and Password Confirm not matching!");
            viewFocus = passwordConfirm;
            registrationFailed = true;
        }

        if (registrationFailed) {
            viewFocus.requestFocus();
        } else {
            finish();
        }
    }

    private boolean isValidUser(String user) {
        return user.length() >= 4;
    }

    private boolean isValidEmail(String mail) {
        return mail.contains("@");
    }

    private boolean isValidPassword(String pass) {
        return pass.length() >= 4;
    }

    private boolean isPassMatching(String pass, String passConf) {
        return pass.equals(passConf);
    }

    private void clearRegisterForm() {
        userName.setText("");
        email.setText("");
        password.setText("");
        passwordConfirm.setText("");

        userNameInput.setError(null);
        emailInput.setError(null);
        passwordInput.setError(null);
        passwordConfirmInput.setError(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.clear) {
            clearRegisterForm();
        } else if(id == R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}