package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Admin_coursePageActivity extends AppCompatActivity {
    private RecyclerView CourseList;
    private EditText CourseName;
    private EditText CourseCode;
    private Button ViewAll;
    private Button btnBack;
    private Button Create;
    private Button Remove;
    private Button btnHideKeyboard;
    private Button UpgradeCourseName;
    private Button UpgradeCourseCode;
    private MyDBHandler dbHandler;
    private CourseAdapter courseAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_course_page_activity);

        CourseList = findViewById(R.id.CoursenameList);
        CourseName = findViewById(R.id.CourseName);
        CourseCode = findViewById(R.id.CourseCode);
        ViewAll = findViewById(R.id.ViewAll);
        btnBack = findViewById(R.id.btnBack);
        Create = findViewById(R.id.Create);
        Remove = findViewById(R.id.Remove);
        btnHideKeyboard = findViewById(R.id.btnHideKeyboard_AdmCourse);
        UpgradeCourseCode = findViewById(R.id.UpgradeCourseCode);
        UpgradeCourseName = findViewById(R.id.UpgradeCourseName);
        dbHandler = new MyDBHandler(Admin_coursePageActivity.this);
        courseAdapter = new CourseAdapter(Admin_coursePageActivity.this, dbHandler.viewAllCourse());
        CourseList.setAdapter(courseAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                backToMainActivity();
            }
        });

        btnHideKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                closeKeyboard();
            }
        });


        Create.setOnClickListener(v -> {
            dbHandler = new MyDBHandler(Admin_coursePageActivity.this);

            String courseName = CourseName.getText().toString();
            String courseCode = CourseCode.getText().toString();
            if (courseName.equals("") || courseCode.equals("")) {
                Toast.makeText(Admin_coursePageActivity.this, "You can't create a course without a name and a code", Toast.LENGTH_SHORT).show();
            } else {
                Course course = new Course(courseCode, courseName);
                dbHandler.createCourse(course);
                Toast.makeText(Admin_coursePageActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();


            }
            CourseName.setText("");
            CourseCode.setText("");
            courseAdapter = new CourseAdapter(Admin_coursePageActivity.this, dbHandler.viewAllCourse());
            CourseList.setAdapter(courseAdapter);

        });

        Remove.setOnClickListener(v -> {
            dbHandler = new MyDBHandler(Admin_coursePageActivity.this);

            boolean result = dbHandler.deleteCourse(CourseCode.getText().toString());

            if (result) {
                CourseCode.setText("");
                CourseName.setText("");
                Toast.makeText(Admin_coursePageActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

            } else {
                courseAdapter = new CourseAdapter(Admin_coursePageActivity.this, dbHandler.viewAllCourse());
                CourseList.setAdapter(courseAdapter);
                Toast.makeText(Admin_coursePageActivity.this, "No match found", Toast.LENGTH_SHORT).show();


            }

        });

        ViewAll.setOnClickListener(v -> {
            dbHandler = new MyDBHandler(Admin_coursePageActivity.this);

            List<Course> AllCourse = dbHandler.viewAllCourse();
            LinearLayoutManager linearLayoutManger = new LinearLayoutManager(Admin_coursePageActivity.this);
            linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
            CourseList.setLayoutManager(linearLayoutManger);
            courseAdapter = new CourseAdapter(Admin_coursePageActivity.this, AllCourse);
            CourseList.setAdapter(courseAdapter);


        });

        UpgradeCourseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler = new MyDBHandler(Admin_coursePageActivity.this);
                String courseCode = CourseCode.getText().toString();
                String courseName = CourseName.getText().toString();
                if (courseName.equals("") || courseCode.equals("")) {
                    Toast.makeText(Admin_coursePageActivity.this, "You can't upgrade a course without nothing", Toast.LENGTH_SHORT).show();
                } else {
                    Course course = new Course(courseCode, courseName);
                    dbHandler.UpgradeName(course);
                    Toast.makeText(Admin_coursePageActivity.this, "Upgrade Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        UpgradeCourseCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler = new MyDBHandler(Admin_coursePageActivity.this);
                String courseCode = CourseCode.getText().toString();
                String courseName = CourseName.getText().toString();
                if (courseName.equals("") || courseCode.equals("")) {
                    Toast.makeText(Admin_coursePageActivity.this, "You can't upgrade a course without nothing", Toast.LENGTH_SHORT).show();
                } else {
                    Course course = new Course(courseCode, courseName);
                    dbHandler.UpgradeCode(course);
                    Toast.makeText(Admin_coursePageActivity.this, "Upgrade Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void backToMainActivity(){
        Intent intent = new Intent(this,Administrator_Main_Page.class);
        startActivity(intent);
    }
}
