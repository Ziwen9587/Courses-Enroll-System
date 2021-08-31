package com.example.pj_deliverable01;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentCourseAdapter extends RecyclerView.Adapter<StudentCourseAdapter.MyViewHolder> {
    private final Context context;
    private final List<Course> CourseList;

    public StudentCourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        CourseList = courseList;
    }
    @NonNull
    @Override
    public StudentCourseAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.student_course_list,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv1.setText(CourseList.get(position).get_CourseCode());
        holder.tv2.setText(CourseList.get(position).get_CourseName());
        holder.tv3.setText(CourseList.get(position).get_CourseDay());
//        holder.tv4.setText(CourseList.get(position).get_StudentName());
        if ( CourseList.get(position).get_StartHour() !=null & CourseList.get(position).get_EndHour()!= null ){
            holder.tv4.setText("" + CourseList.get(position).get_StartHour() + ":00");
            holder.tv5.setText("" + CourseList.get(position).get_EndHour() + ":00");
        }


    }


    @Override
    public int getItemCount() {
        return CourseList== null ? 0: CourseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv1;
        private final TextView tv2;
        private final TextView tv3;
        private final TextView tv4;
        private final TextView tv5;


        public MyViewHolder(@NonNull  View itemView) {

            super(itemView);
            tv1 = itemView.findViewById(R.id.Student_CourseCodeDisplay);
            tv2 = itemView.findViewById(R.id.Student_CourseNameDisplay);
            tv3 = itemView.findViewById(R.id.CourseDayDisplay);
            tv4 = itemView.findViewById(R.id.StartTimeDisplay);
            tv5 = itemView.findViewById(R.id.EndTimeDisplay);

        }
    }
}
