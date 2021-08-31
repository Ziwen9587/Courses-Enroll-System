package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Student_MainMenu extends AppCompatActivity {

    private TextView tvWelcomeText;
    private String student_name;
    private Button EnrollAndCheck;
    private Button UnEnrollAndCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_menu);

        tvWelcomeText = findViewById(R.id.tvStuWelcomeText);
        EnrollAndCheck=findViewById(R.id.CourseEnrollAndCheck);
        UnEnrollAndCheck=findViewById(R.id.CourseUnenrollAndCheck);

        student_name = getIntent().getStringExtra("studentName");
        tvWelcomeText.setText("Welcome Student " + student_name + "!");


        EnrollAndCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student_MainMenu.this,Student_Activity1.class);
                intent.putExtra("StudentID",getIntent().getStringExtra("studentID"));
                intent.putExtra("StudentName",getIntent().getStringExtra("studentName"));
                startActivity(intent);
            }
        });

        UnEnrollAndCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student_MainMenu.this,Student_Activity2_Unenroll.class);
                intent.putExtra("StudentID",getIntent().getStringExtra("studentID"));
                intent.putExtra("StudentName",getIntent().getStringExtra("studentName"));
                startActivity(intent);
            }
        });
    }
}