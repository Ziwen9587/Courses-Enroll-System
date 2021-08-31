package com.example.pj_deliverable01;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Instructor_ModifyCourse extends AppCompatActivity {
    TextView CourseCodeBox,CourseDayBox,CourseHourBox, CourseCapacityBox,CourseDescriptionBox;
    Button ChangeDay, ChangeHour, ChangeCapacity,ChangeDescription;
    String CourseCode,CourseHour,CourseDay,CourseDescription;
    Spinner CourseDayChoose;
    Course course;
    Integer StartHour, EndHour, CourseCapacity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CourseCode = getIntent().getStringExtra("CourseCode");
        MyDBHandler dbHandler = new MyDBHandler(this);
        course = dbHandler.findCourse(CourseCode);
        setContentView(R.layout.activity_instructor_modifycourse);
        CourseCodeBox = findViewById(R.id.CourseNameModifyPage);
        CourseDayBox = findViewById(R.id.CourseDay);
        CourseDescriptionBox = findViewById(R.id.CourseDescription);
        CourseCapacityBox = findViewById(R.id.CourseCapacity);
        CourseHourBox = findViewById(R.id.CourseHour);
        CourseCodeBox.setText(CourseCode);
        if (course.get_CourseDescription() != null) {
            CourseDescriptionBox.setText(course.get_CourseDescription());
        }
        if (course.get_CourseDay() != null) {
            CourseDayBox.setText(course.get_CourseDay());
        }
        if ((course.get_StartHour() != 0) && (course.get_EndHour() != 0)) {
            CourseHour = Integer.toString(course.get_StartHour()) + ":00 - " + Integer.toString(course.get_EndHour()) + ":00";
            CourseHourBox.setText(CourseHour);
        }
        if (course.get_CourseCapacity() != null) {
            CourseCapacityBox.setText(String.valueOf(course.get_CourseCapacity()));
        }
        ChangeDay = findViewById(R.id.ChangeDayButton);
        ChangeHour = findViewById(R.id.ChangeHourButton);
        ChangeCapacity = findViewById(R.id.ChangeCapacityButton);
        ChangeDescription = findViewById(R.id.ChangeDescriptionButton);
    }

    public void CourseDayChangeDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        MyDBHandler dbHandler = new MyDBHandler(this);
        builder.setTitle("Course day modify");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_courseday,null);
        builder.setView(view);
        CourseDayChoose = view.findViewById(R.id.DaySpinner);
        CourseDayChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                      @Override
                                                      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                          CourseDay = CourseDayChoose.getSelectedItem().toString();
                                                      }

                                                      @Override
                                                      public void onNothingSelected(AdapterView<?> parent) {

                                                      }
                                                  }

        );
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                course.set_CourseDay(CourseDay);
                dbHandler.UpdateDay(course);
                CourseDayBox.setText(CourseDay);
                Toast.makeText(Instructor_ModifyCourse.this, "Course Day updated!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void CourseHourChangeDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        MyDBHandler dbHandler = new MyDBHandler(this);
        builder.setTitle("Course hour modify");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_coursehour,null);
        builder.setView(view);
        EditText StartHourBox,EndHourBox;
        StartHourBox = view.findViewById(R.id.StartHourBox);
        EndHourBox = view.findViewById(R.id.EndHourBox);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if((StartHourBox.getText().toString().equals("")||(EndHourBox.getText().toString().equals("")))) {
                    Toast.makeText(Instructor_ModifyCourse.this,"input can not be empty",Toast.LENGTH_SHORT).show();
                }else {
                    StartHour = Integer.valueOf(StartHourBox.getText().toString());
                    EndHour = Integer.valueOf(EndHourBox.getText().toString());
                    if ((StartHour < 1) || (StartHour > 24)) {
                        Toast.makeText(Instructor_ModifyCourse.this, "Invalid input, Start hour should be in between 1 - 24.", Toast.LENGTH_SHORT).show();
                    } else if ((EndHour < 1) || (EndHour > 24)) {
                        Toast.makeText(Instructor_ModifyCourse.this, "Invalid input, End hour should be in between 1 - 24.", Toast.LENGTH_SHORT).show();
                    } else if (StartHour > EndHour) {
                        Toast.makeText(Instructor_ModifyCourse.this, "Start Hour can not be later than end hour", Toast.LENGTH_SHORT).show();
                    } else if (StartHour == EndHour) {
                        Toast.makeText(Instructor_ModifyCourse.this, "Start Hour and end hour can not be the same", Toast.LENGTH_SHORT).show();
                    } else {
                        course.set_StartHour(StartHour);
                        course.set_EndHour(EndHour);
                        dbHandler.UpdateStartHour(course);
                        dbHandler.UpdateEndHour(course);
                        CourseHour = Integer.toString(course.get_StartHour()) + ":00 - " + Integer.toString(course.get_EndHour()) + ":00";
                        CourseHourBox.setText(CourseHour);
                        Toast.makeText(Instructor_ModifyCourse.this,"Course hour updated",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void CapacityChangeDialog(View v){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    MyDBHandler dbHandler = new MyDBHandler(this);
    builder.setTitle("Course capacity modify");
    View view = LayoutInflater.from(this).inflate(R.layout.dialog_coursecapacity,null);
    builder.setView(view);
    EditText CourseCapacityInput;
    CourseCapacityInput = view.findViewById(R.id.CourseCapacityInput);
    builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(CourseCapacityInput.getText().toString().equals("")) {
                Toast.makeText(Instructor_ModifyCourse.this, "input can not be empty", Toast.LENGTH_SHORT).show();
            }
            else {
                CourseCapacity = Integer.valueOf(CourseCapacityInput.getText().toString());
                if(CourseCapacity == 0) {
                Toast.makeText(Instructor_ModifyCourse.this,"Course capacity can not be zero",Toast.LENGTH_SHORT).show();
                }else {
                    course.set_CourseCapacity(CourseCapacity);
                    dbHandler.UpdateCapacity(course);
                    CourseCapacityBox.setText(String.valueOf(CourseCapacity));
                    Toast.makeText(Instructor_ModifyCourse.this, "Course capacity updated", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        }
    });
    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
        });
        builder.show();
    }


    public void DescriptionChangeDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        MyDBHandler dbHandler = new MyDBHandler(this);
        builder.setTitle("Course description modify");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_coursedes,null);
        builder.setView(view);
        EditText CourseDescriptionInput;
        CourseDescriptionInput = view.findViewById(R.id.CourseDescriptionInput);
        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(CourseDescriptionInput.getText().toString().equals("")) {
                    Toast.makeText(Instructor_ModifyCourse.this, "input can not be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    CourseDescription = CourseDescriptionInput.getText().toString();
                    course.set_CourseDescription(CourseDescription);
                    dbHandler.UpdateDescription(course);
                    CourseDescriptionBox.setText(CourseDescription);
                    Toast.makeText(Instructor_ModifyCourse.this,"Course description updated",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}


