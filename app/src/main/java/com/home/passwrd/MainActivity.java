package com.home.passwrd;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int LAYOUT = R.layout.activity_main;
    private int passwordLenght = 1;
    private TextView tvPassword;
    private Button  btnBuffer;
    private Button btnGener;
    private CheckBox cbNumbers;
    private CheckBox cbSimbols;
    private CheckBox cbLetters;
    public EditText edNumbers;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private String saved;
    public StringBuilder thisispass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(LAYOUT);
        findViews();
        setupListeners();
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        saved = savedInstanceState.getString("saved");
        tvPassword.setText(saved);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("saved", saved);
    }

    void findViews() {
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        cbNumbers = (CheckBox) findViewById(R.id.cbNumbers);
        cbSimbols = (CheckBox) findViewById(R.id.cbSimbols);
        cbLetters = (CheckBox) findViewById(R.id.cbLetters);
        btnGener = (Button) findViewById(R.id.btnGener);
        edNumbers = (EditText) findViewById(R.id.edNumbers);
        btnBuffer = (Button) findViewById(R.id.btnBuffer);
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }


    void setupListeners() {
        btnGener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePassword();
            }
        });

        btnBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyToClipboard();
            }
        });
    }

    void generatePassword() {

        passwordLenght = Integer.parseInt(edNumbers.getText().toString());
        PasswordGenerator passwordGenerated = new PasswordGenerator();
        passwordGenerated.setIsNumberEnabled(cbNumbers.isChecked());
        passwordGenerated.setIsSimbolsEnabled(cbSimbols.isChecked());
        passwordGenerated.setIsLettersEnabled(cbLetters.isChecked());
        passwordGenerated.CheckNumbersBox(true);
        passwordGenerated.PassLenghtCheck(passwordLenght);
        passwordGenerated.buildPassword();
        thisispass = passwordGenerated.buildPassword();
        tvPassword.setText(thisispass);
    }

    void copyToClipboard(){
        String text;
        text = tvPassword.getText().toString();
        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
        Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
    }
}