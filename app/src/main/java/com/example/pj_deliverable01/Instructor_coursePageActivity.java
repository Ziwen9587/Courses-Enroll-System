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

public class Instructor_coursePageActivity extends AppCompatActivity{
    private RecyclerView CourseList;
    private EditText CourseName;
    private EditText CourseCode;
    private Button ViewAll;
    private Button btnBack;
    private Button Assign;
    private Button Unassign;
    private Button btnHideKeyboard;

    private MyDBHandler dbHandler;
    private CourseInstructor courseInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_course_page_activity);

        CourseList = findViewById(R.id.CourseList);
        CourseName = findViewById(R.id.CourseName);
        CourseCode = findViewById(R.id.CourseCode);
        ViewAll = findViewById(R.id.ViewAll);
        btnBack = findViewById(R.id.btnBack);
        Assign = findViewById(R.id.Assign);
        Unassign = findViewById(R.id.Unassign);
        btnHideKeyboard = findViewById(R.id.btnHideKeyboard_InsCourse);
        dbHandler = new MyDBHandler(Instructor_coursePageActivity.this);
        courseInstructor = new CourseInstructor(Instructor_coursePageActivity.this, dbHandler.viewAllCourse());
        CourseList.setAdapter(courseInstructor);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });

        btnHideKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });

        Assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler = new MyDBHandler(Instructor_coursePageActivity.this);
                String courseCode = CourseCode.getText().toString();
                String courseName = CourseName.getText().toString();
                String instructorID = getIntent().getStringExtra("InstructorID");
                System.out.println(instructorID);
                // if (courseCode.equals("") || instructorID.equals("") || courseName.equals(""))
                if (courseCode.equals("")&&courseName.equals("")) {
                    Toast.makeText(Instructor_coursePageActivity.this, "You can't Assign nothing", Toast.LENGTH_SHORT).show();
                } //else if (dbHandler.checkCourseCode(courseCode).equals("NULL")){
                   // Toast.makeText(Instructor_coursePageActivity.this, "Course Code doesn't exist", Toast.LENGTH_SHORT).show();
               // } else if(dbHandler.checkCourseName(courseName).equals("NULL")) {
                // Toast.makeText(Instructor_coursePageActivity.this, "Course Name doesn't exist", Toast.LENGTH_SHORT).show();
                else if (dbHandler.checkCourseCode(courseCode).equals("NULL")&&dbHandler.checkCourseName(courseName).equals("NULL")){
                    Toast.makeText(Instructor_coursePageActivity.this, "Course doesn't exist", Toast.LENGTH_SHORT).show();
                } else {
                    Course course = new Course(courseCode, courseName, instructorID);
                    dbHandler.AssignInstructor(course);
                    if (!dbHandler.GetInstructorID(course).equals(instructorID)) {
                        Toast.makeText(Instructor_coursePageActivity.this, "The course already has a instructor", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Instructor_coursePageActivity.this, "Assign Successfully (Don't forget to add course information)", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Unassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler = new MyDBHandler(Instructor_coursePageActivity.this);
                String courseCode = CourseCode.getText().toString();
                String courseName = CourseName.getText().toString();
                String instructorID = getIntent().getStringExtra("InstructorID");
                //add so can unassgin both from course code and course name
                if (courseCode.equals("")&&(courseName.equals(""))) {
                    Toast.makeText(Instructor_coursePageActivity.this, "You can't UnAssign a Instructor to nothing", Toast.LENGTH_SHORT).show();
                } else {
                    //add so can determine whether the course is exist and belong to the instructor or not
                    Course course = new Course();
                    course.set_CourseCode(courseCode);
                    course.set_CourseName(courseName);
                    course.set_InstructorID(instructorID);
                        boolean flag = dbHandler.UnAssignInstructor(course);
                        if(flag == true) {
                            Toast.makeText(Instructor_coursePageActivity.this, "Unassign Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Instructor_coursePageActivity.this, "Unassign NOT Successfully", Toast.LENGTH_SHORT).show();
                        }
                }

            }
        });


        ViewAll.setOnClickListener(v -> {
            //change so can see all course
            dbHandler = new MyDBHandler(Instructor_coursePageActivity.this);
            List<Course> AllCourse = dbHandler.viewAllCourse();
            for(Course c: AllCourse){
                if(c.get_InstructorID()!=null) {
                    c.set_InstructorName((dbHandler.findInstructor(c.get_InstructorID())).getName());
                }else{
                    c.set_InstructorName("No instructor");
                }
            }
            LinearLayoutManager linearLayoutManger = new LinearLayoutManager(Instructor_coursePageActivity.this);
            linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
            CourseList.setLayoutManager(linearLayoutManger);
            courseInstructor = new CourseInstructor(Instructor_coursePageActivity.this, AllCourse);
            CourseList.setAdapter(courseInstructor);
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
        MyDBHandler db = new MyDBHandler(Instructor_coursePageActivity.this);
        String InstructorID = getIntent().getStringExtra("InstructorID");
        String InstructorName = (dbHandler.findInstructor(InstructorID)).getName();
        Intent intent = new Intent(this,Instructor_MainMenu.class);
        intent.putExtra("instructorID",getIntent().getStringExtra("InstructorID"));
        intent.putExtra("instructorName",InstructorName);
        startActivity(intent);
    }

}
