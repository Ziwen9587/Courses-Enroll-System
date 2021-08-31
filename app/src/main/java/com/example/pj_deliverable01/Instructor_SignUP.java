package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Instructor_SignUP extends AppCompatActivity {
    private TextInputLayout textInputInstructorID;
    private TextInputLayout textInputInstructorName;
    private TextInputLayout textInputInstructorPassWord;
    private TextInputEditText instructorIDInput;
    private TextInputEditText instructorNameInput;
    private TextInputEditText instructorPassWordInput;
    private TextView tvMessageSignUp_Ins;
    private Button btnSignUpConfirm,btnBack,btnHideKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up);

        btnSignUpConfirm = (Button) findViewById(R.id.btnConfirm_InsSignUP);
        btnHideKeyboard = (Button) findViewById(R.id.btnHideKeyboad_Ins);
        btnBack = (Button) findViewById(R.id.btnBack_InsSignUP);
        tvMessageSignUp_Ins = findViewById(R.id.message_InsSignUp);
        textInputInstructorID = findViewById(R.id.textInput_InsID);
        textInputInstructorName = findViewById(R.id.textInput_InsName);
        textInputInstructorPassWord = findViewById(R.id.textInput_InsPassWord);
        instructorIDInput = findViewById(R.id.Instructor_ID_Input);
        instructorNameInput = findViewById(R.id.Instructor_Name_Input);
        instructorPassWordInput = findViewById(R.id.Instructor_PW_Input);

        btnSignUpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                newInstructor(v);
            }
        });

        btnHideKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                closeKeyboard();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                backToMainActivity();
            }
        });

    }

    public void newInstructor(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String instructorID = textInputInstructorID.getEditText().getText().toString().trim();
        String instructorName = textInputInstructorName.getEditText().getText().toString().trim();
        String instructorPassWord = textInputInstructorPassWord.getEditText().getText().toString().trim();
        Instructor newInstructor = new Instructor(instructorID, instructorName, instructorPassWord);

        Instructor oldInstructor = dbHandler.findInstructor(instructorID);

        if(instructorID.equals("")){
            tvMessageSignUp_Ins.setText("ID Can Not Be Empty");
            return;
        }
        if(instructorName.equals("")){
            tvMessageSignUp_Ins.setText("Name Can Not Be Empty");
            return;
        }
        if(instructorPassWord.equals("")){
            tvMessageSignUp_Ins.setText("PassWord Can Not Be Empty");
            return;
        }

        if(oldInstructor==null){
            dbHandler.addInstructor(newInstructor);
            instructorIDInput.setText("");
            instructorNameInput.setText("");
            instructorPassWordInput.setText("");
            tvMessageSignUp_Ins.setText("SignUP Successes");
        } else {
            tvMessageSignUp_Ins.setText("ID Already Exists");
        }

    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void backToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}