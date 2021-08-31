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

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Student_Activity2_Unenroll extends AppCompatActivity {
    private TextView tvStudentUnenroll;
    private String studentName;
    private String studentID;
    private Button btnUnenroll,btnShowUpdateCourseList;
    private TextInputLayout textInputUnenrollCourseID;
    private ListView lvEnrolledCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_activity2_unenroll);

        studentName = getIntent().getStringExtra("StudentName");
        studentID = getIntent().getStringExtra("StudentID");
        tvStudentUnenroll = findViewById(R.id.tvStudentUnenroll);
        tvStudentUnenroll.setText("Welcome Student: " + studentID + studentName);

        btnUnenroll = findViewById(R.id.btnUnenroll);
        btnShowUpdateCourseList = findViewById(R.id.btnShowUpdateList);
        textInputUnenrollCourseID = findViewById(R.id.textInput_UnenrollCourseID);
        lvEnrolledCourse = findViewById(R.id.lvEnrolledCourse);

        btnShowUpdateCourseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                closeKeyboard();
                showEnrolledCourseList(v);
            }
        });

        btnUnenroll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                closeKeyboard();
                unenrollCourse(v);
            }
        });

    }

    public void showEnrolledCourseList(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);

        ArrayList<Course> enrolledCourses = dbHandler.findEnrolledCourse(studentID);

        ArrayList<String> arrayList = new ArrayList<String>();
        for(Course c : enrolledCourses){
            arrayList.add(c.get_CourseCode() + "             " + c.get_CourseName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        lvEnrolledCourse.setAdapter(arrayAdapter);

    }

    public void unenrollCourse(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);
        String courseCode = textInputUnenrollCourseID.getEditText().getText().toString().trim();

        if(courseCode.equals("")){
            Toast.makeText(this, "Please Enter The Course Code", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean unenrollResult = dbHandler.removeStudentIDFromCourse(courseCode);
        if(unenrollResult){
            Toast.makeText(this, "The Course Has Been Removed Successfully", Toast.LENGTH_SHORT).show();
            return;
        } else{
            Toast.makeText(this, "The Course Does Not Exist, Please Enter A Valid Course Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}