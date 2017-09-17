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

    private int passwordLenght = 1;
    public TextView tvPassword;
    public Button btnGener, btnBuffer;
    public CheckBox cbNumbers,cbSimbols,cbLetters;
    public EditText edNumbers;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private String saved;
    public StringBuilder thisispass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findeView();
        setupListner();

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        saved = savedInstanceState.getString("saved");
        tvPassword.setText(saved);
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("saved" , saved);

    }

    void findeView(){
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        cbNumbers = (CheckBox) findViewById(R.id.cbNumbers);
        cbSimbols = (CheckBox) findViewById(R.id.cbSimbols);
        cbLetters = (CheckBox) findViewById(R.id.cbLetters);
        btnGener = (Button) findViewById(R.id.btnGener);
        edNumbers = (EditText) findViewById(R.id.edNumbers);
        btnBuffer = (Button) findViewById(R.id.btnBuffer);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
    }


    void setupListner(){
        btnGener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        btnBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                text = tvPassword.getText().toString();

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });

}
}