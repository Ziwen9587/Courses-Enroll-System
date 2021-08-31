package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Administrator_Main_Page extends AppCompatActivity {

    private Button btnOpCourse,btnOpStudent,btnOpInstructor,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_main_page);

        btnOpCourse = (Button) findViewById(R.id.btnCourseOp);
        btnOpStudent = (Button) findViewById(R.id.btnStuOp);
        btnOpInstructor = (Button) findViewById(R.id.btnInsOp);
        btnBack = (Button) findViewById(R.id.btnBack_AdmMian);

        btnOpCourse.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAdministrator_OperationOnCourse();
            }
        });

        btnOpStudent.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAdministrator_OperationOnStu();
            }
        });

        btnOpInstructor.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAdministrator_OperationOnIns();
            }
        });

        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                backToMainActivity();
            }
        });


    }


    public void openAdministrator_OperationOnStu(){
        Intent intent = new Intent(this,Administrator_OperationOnStu.class);
        startActivity(intent);
    }
    public void openAdministrator_OperationOnIns(){
        Intent intent = new Intent(this,Administrator_OperationOnIns.class);
        startActivity(intent);
    }
    public void openAdministrator_OperationOnCourse(){
        Intent intent = new Intent(this,Admin_coursePageActivity.class);
        startActivity(intent);
    }

    private void backToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}