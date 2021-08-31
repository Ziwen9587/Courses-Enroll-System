package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button btnLogIn,btnSignUP;
    private TextView message;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextInputLayout textInputUser;
    private TextInputLayout textInputPassWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnSignUP = (Button) findViewById(R.id.btnSignUP);
        radioGroup = findViewById(R.id.rGroup);
        message = findViewById(R.id.message);
        textInputUser = findViewById(R.id.textInputUser);
        textInputPassWord = findViewById(R.id.textInputPassWord);

        btnLogIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
//                System.out.println("This is check:::::::::::::::::::::");
                if(radioButton.getText().equals("Administrator")){
                    if( validateAdministrator() ) {
                        openAdministrator_Main_Page();
                    }
                } else if (radioButton.getText().equals("Student")){
                    if ( validateStudent() ) {
                        openStudent_MainMenu();
                    }
                } else{
                    if ( validateInstructor() ) {
                        openInstructor_MainMenu();
                    }
                    System.out.println("TBD");
                }
                closeKeyboard();
            }
        });

        btnSignUP.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                if(radioButton.getText().equals("Student")){
                    openStudent_SignUP();
                }
                if(radioButton.getText().equals("Instructor")){
                    openInstructor_SignUP();
                }
            }
        });

    }

    public boolean validateAdministrator(){
        String userAccount = textInputUser.getEditText().getText().toString().trim();
        String userPassWord = textInputPassWord.getEditText().getText().toString().trim();
        boolean accountBoolean = userAccount.equals("admin");
        boolean passWordBoolean = userPassWord.equals("admin123");

        if(accountBoolean && passWordBoolean){
            return true;
        }  else {
            return false;
        }
    }

    public boolean validateStudent(){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String studentID = textInputUser.getEditText().getText().toString().trim();
        String studentPassWord = textInputPassWord.getEditText().getText().toString().trim();

        Student studentInData = dbHandler.findStudent(studentID);

        if (studentInData == null){
            message.setText("Student ID Not Exists");
            return false;
        } else if (!studentInData.getPassWord().equals(studentPassWord)) {
            message.setText("The PassWord Is Incorrect");
            return false;
        } else {
            return true;
        }
    }

    public boolean validateInstructor(){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String instructorID = textInputUser.getEditText().getText().toString().trim();
        String instructorPassWord = textInputPassWord.getEditText().getText().toString().trim();

        Instructor instructorInData = dbHandler.findInstructor(instructorID);

        if (instructorInData == null){
            message.setText("Instructor ID Not Exists");
            return false;
        } else if (!instructorInData.getPassWord().equals(instructorPassWord)) {
            message.setText("The PassWord Is Incorrect");
            return false;
        } else {
            return true;
        }
    }



    public void openAdministrator_Main_Page(){
        Intent intent = new Intent(this,Administrator_Main_Page.class);
        startActivity(intent);
    }
    public void openStudent_MainMenu(){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String studentID = textInputUser.getEditText().getText().toString().trim();
        Student student = dbHandler.findStudent(studentID);
        String studentName = student.getName();

        Intent intent = new Intent(this,Student_MainMenu.class);
        intent.putExtra("studentName",studentName);
        intent.putExtra("studentID",studentID);
        startActivity(intent);
    }
    public void openInstructor_MainMenu(){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String instructorID = textInputUser.getEditText().getText().toString().trim();
        Instructor instructor = dbHandler.findInstructor(instructorID);
        String instructorName = instructor.getName();

        Intent intent = new Intent(this,Instructor_MainMenu.class);
        intent.putExtra("instructorName",instructorName);
        intent.putExtra("instructorID",instructorID);
        startActivity(intent);
    }


    public void openStudent_SignUP(){
        Intent intent = new Intent(this,Student_SignUP.class);
        startActivity(intent);
    }
    public void openInstructor_SignUP(){
        Intent intent = new Intent(this,Instructor_SignUP.class);
        startActivity(intent);
    }



    public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}























