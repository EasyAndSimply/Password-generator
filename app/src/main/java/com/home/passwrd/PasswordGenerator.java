package com.home.passwrd;

import android.util.Log;
import java.util.Random;

/**
 * Created by kamina on 17.09.2017.
 */

public class PasswordGenerator {

    public final String MY_LOG = "My Log";
    boolean isNumberEnabled;
    boolean isSimbolEnabled;
    boolean isLetterEnablet;

    private int passwordLenght = 1;
    private String result = "";
    public StringBuilder randomPass;

    public void setIsNumberEnabled(boolean isNumberEnabled){
        this.isNumberEnabled = isNumberEnabled;
    }

    public void setIsSimbolsEnabled(boolean isSimbolEnabled){
        this.isSimbolEnabled=isSimbolEnabled;
    }

    public void setIsLettersEnabled(boolean isLetterEnabled){
        this.isLetterEnablet=isLetterEnabled;
    }

    public void CheckNumbersBox(boolean check) {
        if (check = true) {
            if ((isNumberEnabled | isSimbolEnabled | isLetterEnablet)== true) {
                if (isNumberEnabled == true) {
                    result = result + Const.NUMBERS;
                }
                if (isSimbolEnabled == true) {
                    result = result + Const.SYMBOLS;
                }
                if (isLetterEnablet == true) {
                    result = result + Const.LETTERS;
                }else{}
            }
        }
    }

    public void PassLenghtCheck(int passwordLenght){
        this.passwordLenght = passwordLenght;
    }

    public StringBuilder buildPassword() {
            randomPass = new StringBuilder();
            int index;
        Log.e(MY_LOG , "ТУТ"+result);
            for (int i = 0; i < passwordLenght; i++) {
                index = new Random().nextInt(result.length());
                randomPass.append(result.charAt(index));
            }
            return randomPass;
    }
}
