package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Instructor_CheckStudentList extends AppCompatActivity {

    private String instructorID;
    private String instructorName;

    private TextView tvTest;
    private Button btnShowStudentList;
    private ListView lvStudentList;
    private TextInputLayout textInputCourseID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_check_student_list);
        tvTest = findViewById(R.id.tvInsCheckStudentList);
        btnShowStudentList = findViewById(R.id.btnShowStudentListByCourseID);
        lvStudentList = findViewById(R.id.lvStudentList);
        textInputCourseID = findViewById(R.id.textinput_InsCheckStuByCourseID);

        instructorID = getIntent().getStringExtra("instructorID");
        instructorName = getIntent().getStringExtra("instructorName");

        tvTest.setText("Welcome Instructor " + instructorID + " " + instructorName + "!");

        btnShowStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                closeKeyboard();
                showStudentList(v);
            }
        });
    }

    public void showStudentList(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);
        String courseCode = textInputCourseID.getEditText().getText().toString().trim();


        ArrayList<Student> enrolledStudent = dbHandler.findEnrolledStudent(courseCode,instructorID);
//
        ArrayList<String> arrayList = new ArrayList<String>();
        for(Student s : enrolledStudent){
            arrayList.add(s.getID() + "             " + s.getName());
        }
//
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        lvStudentList.setAdapter(arrayAdapter);

    }


    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}