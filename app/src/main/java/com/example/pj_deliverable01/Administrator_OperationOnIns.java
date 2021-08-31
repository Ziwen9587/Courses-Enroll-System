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

public class Administrator_OperationOnIns extends AppCompatActivity {
    private TextInputLayout textInputInsIDDelete;
    private TextInputEditText InstructorIDDelete;
    private Button btnDelete,btnShowInstructorList,btnBack;
    private TextView messageDelete;
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_operation_on_ins);

        textInputInsIDDelete = findViewById(R.id.textInput_InsIDDelete);
        InstructorIDDelete = findViewById(R.id.Instructor_ID_Delete);
        btnDelete = findViewById(R.id.btnDeleteIns);
        btnShowInstructorList = findViewById(R.id.btnShowInstructorList);
        btnBack = findViewById(R.id.btnBack_AdmOpIns);
        messageDelete = findViewById(R.id.message_DeleteIns);
        listView1 = findViewById(R.id.listview2);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                deleteInstructor(v);
            }
        });

        btnShowInstructorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                closeKeyboard();
                showInstructorList(v);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                backToMainActivity();
            }
        });

    }

    public void deleteInstructor(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);

        String instructorID = textInputInsIDDelete.getEditText().getText().toString().trim();

        if(instructorID.equals("")){
            messageDelete.setText("Instructor ID Can Not Be Empty");
            return;
        }

        boolean deleteResult = dbHandler.deleteInstructor(instructorID);  //Check if ID already existed

        if(deleteResult == true){
            messageDelete.setText( instructorID + " Has Been Deleted Successfully");
            InstructorIDDelete.setText("");
        } else {
            messageDelete.setText( instructorID + " Does Not Exist");
        }
    }

    public void showInstructorList(View v){
        MyDBHandler dbHandler = new MyDBHandler(this);

        ArrayList<Instructor> instructorList = dbHandler.findAllInstructor();

        ArrayList<String> arrayList = new ArrayList<String>();
        for(Instructor i : instructorList){
            arrayList.add(i.getID() + "                               " + i.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView1.setAdapter(arrayAdapter);
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