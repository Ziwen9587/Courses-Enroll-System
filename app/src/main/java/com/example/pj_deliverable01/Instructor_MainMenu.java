package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

import android.os.Bundle;

public class Instructor_MainMenu extends AppCompatActivity {
    private TextView tvWelcomeText;
    private String instructor_name;
    private String instructor_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main_menu);

        tvWelcomeText = findViewById(R.id.tvInsWelcomeText);
        instructor_name = getIntent().getStringExtra("instructorName");
        instructor_ID = getIntent().getStringExtra("instructorID");
        tvWelcomeText.setText("Welcome Instructor " + instructor_name + "!");
    }

    public void ManageCoursePage(View view){
        Intent intent = new Intent(this,Instructor_ManageCourse.class);
        intent.putExtra("InstructorID",getIntent().getStringExtra("instructorID"));
        startActivity(intent);
    }

    public void assignUnassgin (View view){
        Intent intent = new Intent(this,Instructor_coursePageActivity.class);
        intent.putExtra("InstructorID",getIntent().getStringExtra("instructorID"));
        startActivity(intent);
    }

    public void CheckStudentList(View view){
        Intent intent = new Intent(this,Instructor_CheckStudentList.class);
        intent.putExtra("instructorName",instructor_name);
        intent.putExtra("instructorID",instructor_ID);
//        intent.putExtra("InstructorID",getIntent().getStringExtra("instructorID"));
//        intent.putExtra("Instructor_Name",getIntent().getStringExtra("instructorID"));

        startActivity(intent);
    }

}