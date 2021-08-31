package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Student_Activity1 extends AppCompatActivity {
    private EditText editText_CourseCode;
    private EditText editText_CourseName;
    private EditText editText_CourseDay;
    private Button btn_Search;
    private Button btn_ViewAll;
    private Button btn_Enroll;
    private RecyclerView Student_Course_list;
    private StudentCourseAdapter studentCourseAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student1);

        editText_CourseCode = findViewById(R.id.AS1_CourseCode);
        editText_CourseName = findViewById(R.id.AS1_CourseName);
        editText_CourseDay = findViewById(R.id.AS1_DayOfWeek);
        btn_Search = findViewById(R.id.Search);
        btn_ViewAll = findViewById(R.id.ViewAll);
        btn_Enroll = findViewById(R.id.enroll);
        Student_Course_list = findViewById(R.id.Student_ListOfCourses);

        btn_Enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler db = new MyDBHandler(Student_Activity1.this);
                String courseCode = editText_CourseCode.getText().toString();
                String courseName = editText_CourseName.getText().toString();
                String courseDay = editText_CourseDay.getText().toString();
                String studentID = getIntent().getStringExtra("StudentID");
                String studentName = getIntent().getStringExtra("StudentName");

                if(courseCode.equals("") || courseName.equals("") || courseDay.equals("")){
                    Toast.makeText(Student_Activity1.this, "You can't enroll with empty element", Toast.LENGTH_SHORT).show();
                }
                else if (db.checkCourseName(courseName).equals("NULL") && db.checkCourseCode(courseCode).equals("NULL")){
                    Toast.makeText(Student_Activity1.this, "Course doesn't exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    Course course = new Course(courseCode,courseName,studentName,studentID,courseDay);
                    if(db.checkCourseDay(courseDay).equals("exist")&&db.checkStudentID(courseDay,studentID)){
                        if (db.checkCourseDay(courseDay,courseCode).equals("exist")){

                            Toast.makeText(Student_Activity1.this, "Conflict with other courses ", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            db.enrollStudent(course);
                            Toast.makeText(Student_Activity1.this, "Enroll Successfully ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        db.enrollStudent(course);
                        Toast.makeText(Student_Activity1.this, "Enroll Successfully ", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler dbHandler = new MyDBHandler(Student_Activity1.this);
                List<Course> AllCourse = dbHandler.viewAll();
                LinearLayoutManager linearLayoutManger = new LinearLayoutManager(Student_Activity1.this);
                linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
                Student_Course_list.setLayoutManager(linearLayoutManger);
                studentCourseAdapter = new StudentCourseAdapter(Student_Activity1.this, AllCourse);
                Student_Course_list.setAdapter(studentCourseAdapter);
            }
        });
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler dbHandler = new MyDBHandler(Student_Activity1.this);
                String courseCode =editText_CourseCode.getText().toString();
                String courseName = editText_CourseName.getText().toString();
                String courseDay = editText_CourseDay.getText().toString();
                if(!courseCode.equals("")&&dbHandler.checkCourseCode(courseCode)!="NULL"){
                    List<Course> AllCourse = dbHandler.SearchByCode(courseCode);
                    LinearLayoutManager linearLayoutManger = new LinearLayoutManager(Student_Activity1.this);
                    linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
                    Student_Course_list.setLayoutManager(linearLayoutManger);
                    studentCourseAdapter = new StudentCourseAdapter(Student_Activity1.this, AllCourse);
                    Student_Course_list.setAdapter(studentCourseAdapter);
                }
                else if(!courseName.equals("")&&dbHandler.checkCourseName(courseName)!="NULL"){
                    List<Course> AllCourse = dbHandler.SearchByName(courseName);
                    LinearLayoutManager linearLayoutManger = new LinearLayoutManager(Student_Activity1.this);
                    linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
                    Student_Course_list.setLayoutManager(linearLayoutManger);
                    studentCourseAdapter = new StudentCourseAdapter(Student_Activity1.this, AllCourse);
                    Student_Course_list.setAdapter(studentCourseAdapter);

                }else if (!courseDay.equals("")&&dbHandler.checkCourseDay(courseDay)!="NULL"){
                    List<Course> AllCourse = dbHandler.SearchByDay(courseDay);
                    LinearLayoutManager linearLayoutManger = new LinearLayoutManager(Student_Activity1.this);
                    linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
                    Student_Course_list.setLayoutManager(linearLayoutManger);
                    studentCourseAdapter = new StudentCourseAdapter(Student_Activity1.this, AllCourse);
                    Student_Course_list.setAdapter(studentCourseAdapter);

                }

            }
        });

    }
}