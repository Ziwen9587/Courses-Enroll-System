package com.example.pj_deliverable01;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseInstructor extends RecyclerView.Adapter <CourseInstructor.MyViewHolder>{
    private final Context context;
    private final List<Course> CourseList;

    public CourseInstructor(Context context, List<Course> instructor_course_list) {
        this.context = context;
        CourseList = instructor_course_list;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.instructor_course_list,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        //holder.tv1.setText(CourseList.get(position).get_CourseCode());
        holder.tv1.setText(CourseList.get(position).get_CourseName());
        holder.tv2.setText(CourseList.get(position).get_InstructorName());


    }

    @Override
    public int getItemCount() {
        return CourseList== null ? 0: CourseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //private final TextView tv1;
        private final TextView tv1;
        private final TextView tv2;
        public MyViewHolder(@NonNull  View itemView) {

            super(itemView);
            //tv1 = itemView.findViewById(R.id.CourseCodeDisplay);
            tv1 = itemView.findViewById(R.id.CourseNameDisplay);
            tv2 = itemView.findViewById(R.id.InstructorNameDisplay);

        }
    }
}
