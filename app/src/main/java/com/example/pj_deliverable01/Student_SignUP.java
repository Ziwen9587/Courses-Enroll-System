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


public class Student_SignUP extends AppCompatActivity {
    private TextInputLayout textInputStudentID;
    private TextInputLayout textInputStudentName;
    private TextInputLayout textInputStudentPassWord;
    private TextInputEditText studentIDInput;
    private TextInputEditText studentNameInput;
    private TextInputEditText studentPassWordInput;
    private TextView tvMessageSignUp_Stu;
    private Button btnSignUpConfirm,btnBack,btnHideKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        btnSignUpConfirm = (Button) findViewById(R.id.btnConfirm_StuSignUP);
        btnHideKeyboard = (Button) findViewById(R.id.btnHideKeyboad_Stu);
        btnBack = (Button) findViewById(R.id.btnBack_StuSignUP);
        tvMessageSignUp_Stu = findViewById(R.id.message_StuSignUp);
        textInputStudentID = findViewById(R.id.textInput_StuID);
        textInputStudentName = findViewById(R.id.textInput_StuName);
        textInputStudentPassWord = findViewById(R.id.textInput_StuPassWord);
        studentIDInput = findViewById(R.id.Student_ID_Input);
        studentNameInput = findViewById(R.id.Student_Name_Input);
        studentPassWordInput = findViewById(R.id.Student_PW_Input);

        btnSignUpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                newStudent(v);
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

    public void newStudent(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String studentID = textInputStudentID.getEditText().getText().toString().trim();
        String studentName = textInputStudentName.getEditText().getText().toString().trim();
        String studentPassWord = textInputStudentPassWord.getEditText().getText().toString().trim();
        Student newStudent = new Student(studentID, studentName, studentPassWord);

        Student oldStudent = dbHandler.findStudent(studentID);  //Check if ID already existed

        if(studentID.equals("")){
            tvMessageSignUp_Stu.setText("ID Can Not Be Empty");
            return;
        }
        if(studentName.equals("")){
            tvMessageSignUp_Stu.setText("Name Can Not Be Empty");
            return;
        }
        if(studentPassWord.equals("")){
            tvMessageSignUp_Stu.setText("PassWord Can Not Be Empty");
            return;
        }

        if(oldStudent==null){
            dbHandler.addStudent(newStudent);
            studentIDInput.setText("");
            studentNameInput.setText("");
            studentPassWordInput.setText("");
            tvMessageSignUp_Stu.setText("SignUP Successes");
        } else {
            tvMessageSignUp_Stu.setText("ID Already Exists");
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