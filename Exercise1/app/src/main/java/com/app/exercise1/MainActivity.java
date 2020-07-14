package com.app.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" + //it nhất một số
                    "(?=.*[a-z])" + // ít nhất một kí tự thường
                    "(?=.*[A-Z])" + // ít nhất một kí tự hoa
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9]{9}$");


    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputPhone;

    private TextInputEditText editPassword;
    private TextInputEditText editEmail;
    private TextInputEditText editPhone;

    private Spinner spinner;
    private String arr[] = {"+84", "+85", "+86"};
    private TextView selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_scrollview);


        textInputEmail = findViewById(R.id.textInputEmail);
        textInputPassword = findViewById(R.id.textInputPassword);
        textInputPhone = findViewById(R.id.textInputPhone);

        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);


        spinner = (Spinner) findViewById(R.id.spinner_phone);
        selection = (TextView) findViewById(R.id.selection);
        // Gán data vào Adapter
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arr
        );

        // lệnh hiển thị danh sách cho spinner
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // thiết lập adapter cho spinner
        spinner.setAdapter(adapter);

        // thiết lập sự kiện chọn phần tử cho spinner
        spinner.setOnItemSelectedListener(new MyProcessEvent());





        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePassword()) return;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateEmail()) return;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePhone()) return;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (validatePassword()) return;
        if (validateEmail()) return;
        if (validatePhone()) return;
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            textInputPassword.setError("Password too weak");
            return false;
        } else if(passwordInput.length() < 4 ||passwordInput.length() > 16 ) {
            textInputPassword.setError("Password has 4-16 characters");
            return false;
        } else {
            textInputPassword.setError(null);
        }

        return true;
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!EMAIL_PATTERN.matcher(emailInput).matches()) {
            textInputEmail.setError("x Your email is wrong");
        } else {
            textInputEmail.setError(null);
        }

        return true;
    }

    private boolean validatePhone() {
        String phoneInput = textInputPhone.getEditText().getText().toString().trim();
        if (phoneInput.isEmpty()) {
            textInputPhone.setError("Field can't be empty");
        } else if (!PHONE_PATTERN.matcher(phoneInput).matches()) {
            textInputPhone.setError("Phone has 9 number characters");
            return false;
        } else {
            textInputPhone.setError(null);
        }

        return true;
    }

    // class tạo sự kiện
    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {

        // khi lựa chọn
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selection.setText(arr[position]);
        }

        // khi không chọn gì cả
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            selection.setText("");
        }
    }

}
