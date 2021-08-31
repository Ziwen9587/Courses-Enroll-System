package com.example.pj_deliverable01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Administrator_OperationOnStu extends AppCompatActivity {
    private TextInputLayout textInputStuIDDelete;
    private TextInputEditText studentIDDelete;
    private Button btnDelete,btnShowStudentList,btnBack;
    private TextView messageDelete;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_operation_on_stu);

        textInputStuIDDelete = findViewById(R.id.Unenroll_Course_ID);
        studentIDDelete = findViewById(R.id.Student_ID_Delete);
        btnDelete = findViewById(R.id.btnDeleteStu);
        btnShowStudentList = findViewById(R.id.btnShowStudentList);
        btnBack = findViewById(R.id.btnBack_AdmOpStu);
        messageDelete = findViewById(R.id.message_DeleteStu);
        listView = findViewById(R.id.listview1);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                deleteStudent(v);
            }
        });

        btnShowStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                closeKeyboard();
                showStudentList(v);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                backToMainActivity();
            }
        });


    }

    public void deleteStudent(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String studentID = textInputStuIDDelete.getEditText().getText().toString().trim();

        if(studentID.equals("")){
            messageDelete.setText("Student ID Can Not Be Empty");
            return;
        }

        boolean deleteResult = dbHandler.deleteStudent(studentID);  //Check if ID already existed

        if(deleteResult == true){
            messageDelete.setText( studentID + " Has Been Deleted Successfully");
            studentIDDelete.setText("");
        } else {
            messageDelete.setText( studentID + " Does Not Exist");
        }

    }

    public void showStudentList(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);

        ArrayList<Student> studentsList = dbHandler.findAllStudent();

        ArrayList<String> arrayList = new ArrayList<String>();
        for(Student s : studentsList){
            arrayList.add(s.getID() + "                               " + s.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

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