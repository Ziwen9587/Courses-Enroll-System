package com.example.pj_deliverable01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Instructor_ManageCourse extends AppCompatActivity {
    private ListView CourseCodeList;
    private ListView CourseNameList;
    private ArrayAdapter courseCodeAdapter;
    private ArrayAdapter courseNameAdapter;
    private List<Course> CourseList;
    private ArrayList<String> CourseCodeArrayList;
    private ArrayList<String> CourseNameArrayList;
    private EditText ChosenCourse;
    private Button Choose;
    private Intent intent;
    private String CourseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_managecourse);
        CourseCodeList = findViewById(R.id.CourseIDList);
        CourseNameList = findViewById(R.id.CoursenameList);
        MyDBHandler dbHandler = new MyDBHandler(this);
        CourseList = dbHandler.viewAllCourse();
        CourseNameArrayList = new ArrayList<>();
        CourseCodeArrayList = new ArrayList<>();
        String instructorID = getIntent().getStringExtra("InstructorID");
        for(Course c:CourseList) {
            if ((c != null)&&((c.get_InstructorID()!=null)&&((c.get_InstructorID()).equals(instructorID)))) {
                CourseCodeArrayList.add(c.get_CourseCode());
                CourseNameArrayList.add(c.get_CourseName());
            }
        }
        courseCodeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,CourseCodeArrayList);
        courseNameAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,CourseNameArrayList);
        CourseCodeList.setAdapter(courseCodeAdapter);
        CourseNameList.setAdapter(courseNameAdapter);

    }

    public void Choose(View view){
        ChosenCourse = findViewById(R.id.ChosenCourse);
        CourseCode = ChosenCourse.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        Course course = new Course();
        if(CourseCode == ""){
            Toast.makeText(Instructor_ManageCourse.this,"Invalid input, input can not be empty",Toast.LENGTH_SHORT).show();
        }else{
            course = dbHandler.findCourse(CourseCode);
            if(course!=null){
                String insID = course.get_InstructorID();
                if((insID != null)&&(insID.equals(getIntent().getStringExtra("InstructorID")))){
                intent = new Intent(this,Instructor_ModifyCourse.class);
                intent.putExtra("CourseCode",CourseCode);
                startActivity(intent);}
                else{
                    Toast.makeText(Instructor_ManageCourse.this,"This is not your course, you can not edit the course information",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(Instructor_ManageCourse.this,"Invalid input, please enter the right course code",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
