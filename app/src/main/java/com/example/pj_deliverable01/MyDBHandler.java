package com.example.pj_deliverable01;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.ScriptIntrinsicYuvToRGB;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "SEG2105_Project.db";

    // Student //
    private static final String TABLE_STUDENT = "Student";
    private static final String STUDENT_ID = "Student_ID";
    private static final String STUDENT_NAME = "Student_Name";
    private static final String STUDENT_PASSWORD = "Student_PassWord";
    // Student //

    // Instructor //
    private static final String TABLE_INSTRUCTOR = "Instructor";
    private static final String INSTRUCTOR_ID = "Instructor_ID";
    private static final String INSTRUCTOR_NAME = "Instructor_Name";
    private static final String INSTRUCTOR_PASSWORD = "Instructor_PassWord";
    // Instructor //

    // Course //
    private static final String TABLE_COURSE="Course";
    private static final String COLUMN_ID="ID";
    private static final String COLUMN_COURSENAME="Course_Name";
    private static final String COLUMN_COURSECODE="Course_Code";
    private static final String COLUMN_COURSEDAY = "Course_Day";
    private static final String COLUMN_STARTHOUR = "Course_StartHour";
    private static final String COLUMN_ENDHOUR = "Course_EndHour";
    private static final String COLUMN_DESCRIPTION = "Course_Description";
    private static final String COLUMN_CAPACITY = "Course_Capacity";
    private static final String COLUMN_INSTRUCTOR_ID="Instructor_ID";
    private static final String COLUMN_STUDENT_ID = "Student_ID";
    private static final String COLUMN_STUDENT_NAME = " Student_Name ";

    // Course //

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String TableStudent = "CREATE TABLE " + TABLE_STUDENT + "(" +
                STUDENT_ID + " TEXT PRIMARY KEY," +
                STUDENT_NAME + " TEXT," +
                STUDENT_PASSWORD + " TEXT "+")";

        String TableInstructor = "CREATE TABLE " + TABLE_INSTRUCTOR + "(" +
                INSTRUCTOR_ID + " TEXT PRIMARY KEY," +
                INSTRUCTOR_NAME + " TEXT," +
                INSTRUCTOR_PASSWORD + " TEXT" + ")";

        String CreateCourseTable = "CREATE TABLE " + TABLE_COURSE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, "+
                COLUMN_COURSECODE +" TEXT," +
                COLUMN_COURSENAME + " TEXT," +
                COLUMN_COURSEDAY + " TEXT," +
                COLUMN_STARTHOUR + " INTEGER,"+
                COLUMN_ENDHOUR + " INTEGER,"+
                COLUMN_DESCRIPTION +" TEXT,"+
                COLUMN_CAPACITY + " INTEGER,"+
                COLUMN_INSTRUCTOR_ID + " TEXT, " + COLUMN_STUDENT_ID + " TEXT,"+ COLUMN_STUDENT_NAME+" TEXT,"+
                "FOREIGN KEY(" + COLUMN_INSTRUCTOR_ID + ") REFERENCES " +
                TABLE_INSTRUCTOR + "(" + INSTRUCTOR_ID +")," + "FOREIGN KEY(" + COLUMN_STUDENT_ID + ") REFERENCES " +
                TABLE_COURSE + "(" + COLUMN_STUDENT_ID + "))";


        db.execSQL(TableStudent);
        db.execSQL(TableInstructor);
        db.execSQL(CreateCourseTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTRUCTOR);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COURSE);
        onCreate(db);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues studentInfo = new ContentValues();
        studentInfo.put(STUDENT_ID, student.getID());
        studentInfo.put(STUDENT_NAME, student.getName());
        studentInfo.put(STUDENT_PASSWORD, student.getPassWord());
        db.insert(TABLE_STUDENT, null, studentInfo);
//        db.close();
    }


    // Student------------------------------------ //
    public Student findStudent(String studentID){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_STUDENT + " WHERE " + STUDENT_ID + " = \"" + studentID + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Student student = new Student();
        if(cursor.moveToFirst()){
            student.setID(cursor.getString(0));
            student.setName(cursor.getString(1));
            student.setPassWord(cursor.getString(2));
        } else{
            student = null;
        }
//        db.close();
        return student;
    }

    public ArrayList<Student> findAllStudent(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Student> allStudentList = new ArrayList<Student>();

        String query = "SELECT * FROM " + TABLE_STUDENT;
        Cursor cursor = db.rawQuery(query, null);


        while(cursor.moveToNext()){
            Student student = new Student();
            student.setID(cursor.getString(0));
            student.setName(cursor.getString(1));
            student.setPassWord(cursor.getString(2));
            allStudentList.add(student);
        }
//        db.close();
        return allStudentList;
    }

    public boolean deleteStudent(String studentID){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_STUDENT + " WHERE  " + STUDENT_ID + " = \""
                + studentID + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            String[] idArg = { idStr };
            db.delete(TABLE_STUDENT, STUDENT_ID + " = ?",idArg );
            cursor.close();
            result =true;
        }
        return result;
    }
    // Student------------------------------------ //

    // Instructor------------------------------------ //
    public void addInstructor(Instructor instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues instructorInfo = new ContentValues();
        instructorInfo.put(INSTRUCTOR_ID, instructor.getID());
        instructorInfo.put(INSTRUCTOR_NAME, instructor.getName());
        instructorInfo.put(INSTRUCTOR_PASSWORD, instructor.getPassWord());
        db.insert(TABLE_INSTRUCTOR, null, instructorInfo);
//        db.close();
    }

    public Instructor findInstructor(String instructorID){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_INSTRUCTOR + " WHERE " + INSTRUCTOR_ID + " = \"" + instructorID + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Instructor instructor = new Instructor();
        if(cursor.moveToFirst()){
            instructor.setID(cursor.getString(0));
            instructor.setName(cursor.getString(1));
            instructor.setPassWord(cursor.getString(2));
        } else{
            instructor = null;
        }
//        db.close();
        return instructor;
    }

    public ArrayList<Instructor>  findAllInstructor(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Instructor> allInstructorList = new ArrayList<Instructor>();

        String query = "SELECT * FROM " + TABLE_INSTRUCTOR;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            Instructor instructor = new Instructor();
            instructor.setID(cursor.getString(0));
            instructor.setName(cursor.getString(1));
            instructor.setPassWord(cursor.getString(2));
            allInstructorList.add(instructor);
        }
//        db.close();
        return allInstructorList;
    }

    public boolean deleteInstructor(String instructorID){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_INSTRUCTOR + " WHERE  " + INSTRUCTOR_ID + " = \""
                + instructorID + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            String [] idArg = { idStr };
            db.delete(TABLE_INSTRUCTOR, INSTRUCTOR_ID + " = ?",idArg);
            cursor.close();
            result =true;
        }
        return result;
    }
    // Instructor------------------------------------ //

    // Course------------------------------------ //
    public void createCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSECODE, course.get_CourseCode());
        values.put(COLUMN_COURSENAME, course.get_CourseName());

        db.insert(TABLE_COURSE,null,values);
//        db.close();
    }

    public boolean deleteCourse(String courseCode){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_COURSE +" WHERE "+ COLUMN_COURSECODE +" = \""+ courseCode + "\"";
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_COURSE,COLUMN_ID+" = "+idStr,null);
            cursor.close();
            result=true;

        }
//        db.close();
        return result;
    }

    public Course findCourse(String courseCode){
        Course course = new Course();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            course.set_CourseCode(cursor.getString(1));
            course.set_CourseName(cursor.getString(2));
            course.set_ID(cursor.getInt(0));
            if(cursor.getString(3)!=null){
                course.set_CourseDay(cursor.getString(3));
            }
            if(!cursor.isNull(cursor.getInt(4))){
                course.set_StartHour(cursor.getInt(4));
            }
            if(!cursor.isNull(cursor.getInt(5))){
                course.set_EndHour(cursor.getInt(5));
            }
            if(cursor.getString(6)!=null){
                course.set_CourseDescription(cursor.getString(6));
            }
            if(cursor.isNull(cursor.getInt(7))){
                course.set_CourseCapacity(cursor.getInt(7));
            }
            if(cursor.getString(8)!=null){
                course.set_InstructorID(cursor.getString(8));
            }
//            xx
            if(cursor.getString(9)!=null){
                course.set_StudentID(cursor.getString(9));
            }
            if(cursor.getString(10)!=null){
                course.set_StudentName(cursor.getString(10));
            }

        }else {
            course = null;
        }
        return course;
    }

    public List<Course> viewAllCourse(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_COURSE;

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString,null);

        while (cursor.moveToNext()){
            String CourseName= cursor.getString(1);
            String CourseCode  = cursor.getString(2);
            String InstructorID = cursor.getString(8);
            Course course = new Course(CourseName,CourseCode,InstructorID);
            returnList.add(course);
        }
        cursor.close();
//        db.close();
        return returnList;
    }

    public void UpgradeCode(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUMN_COURSECODE, course.get_CourseCode());
        String query = " SELECT * FROM "+ TABLE_COURSE +" WHERE "+ COLUMN_COURSENAME +" = \""+ course.get_CourseName() + "\"";
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.update(TABLE_COURSE, values,COLUMN_ID+" = "+ idStr,null);
            cursor.close();
        }
//        db.close();
    }

    public void UpgradeName(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUMN_COURSENAME,course.get_CourseName());
        String courseCode= course.get_CourseCode();
        String query = " SELECT * FROM "+ TABLE_COURSE +" WHERE "+ COLUMN_COURSECODE +" = \""+ courseCode + "\"";
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.update( TABLE_COURSE, values,COLUMN_ID +" = "+ idStr,null);
            cursor.close();
        }db.close();
    }
// Deliverable 2 Start---------------------------------------------------------------------------------->
    public boolean UpdateDay(Course course){
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSEDAY,course.get_CourseDay());
        String query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \""  + course.get_CourseCode() + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.update(TABLE_COURSE, values,COLUMN_ID + " = "  + idStr, null);
            cursor.close();
            flag = true;
        }
        db.close();
        return flag;
    }

    public boolean UpdateStartHour(Course course){
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STARTHOUR,course.get_StartHour());
        String query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \""  + course.get_CourseCode() + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.update(TABLE_COURSE, values,COLUMN_ID + " = "  + idStr, null);
            cursor.close();
            flag = true;
        }
        db.close();
        return flag;
    }

    public boolean UpdateEndHour(Course course){
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ENDHOUR,course.get_EndHour());
        String query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \""  + course.get_CourseCode() + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.update(TABLE_COURSE, values,COLUMN_ID + " = "  + idStr, null);
            cursor.close();
            flag = true;
        }
        db.close();
        return flag;
    }

    public boolean UpdateCapacity(Course course){
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAPACITY,course.get_CourseCapacity());
        String query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \""  + course.get_CourseCode() + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.update(TABLE_COURSE, values,COLUMN_ID + " = "  + idStr, null);
            cursor.close();
            flag = true;
        }
        db.close();
        return flag;
    }

    public boolean UpdateDescription(Course course){
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION,course.get_CourseDescription());
        String query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \""  + course.get_CourseCode() + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.update(TABLE_COURSE, values,COLUMN_ID + " = "  + idStr, null);
            cursor.close();
            flag = true;
        }
        db.close();
        return flag;
    }

    public List<Course> viewCourseInstructor(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> returnList = new ArrayList<>();
        String queryString = "SELECT " + TABLE_COURSE + "." + COLUMN_COURSECODE + ", " +
                TABLE_COURSE + "." + COLUMN_COURSENAME + ", " +
                TABLE_INSTRUCTOR + "." + INSTRUCTOR_NAME + ", " +
                TABLE_INSTRUCTOR + "." + INSTRUCTOR_ID +
                " FROM " + TABLE_COURSE + ", " + TABLE_INSTRUCTOR +
                " WHERE " + TABLE_COURSE + "." + COLUMN_INSTRUCTOR_ID + " = " +
                TABLE_INSTRUCTOR + "." + INSTRUCTOR_ID;

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString,null);
//change cursor next time
        while (cursor.moveToNext()){
            String CourseCode = cursor.getString(0);
            String CourseName = cursor.getString(1);
            String InstructorName = cursor.getString(2);
            String InstructorID = cursor.getString(8);
            Course course = new Course(CourseCode,CourseName,InstructorName,InstructorID);
            returnList.add(course);
        }
        cursor.close();
//        db.close();
        return returnList;
    }

    public void AssignInstructor(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUMN_INSTRUCTOR_ID,course.get_InstructorID());
        String courseCode= course.get_CourseCode();
        String query;
        if((checkCourseCode(courseCode)).equals("exist")) {
            query = " SELECT * FROM " + TABLE_COURSE +
                    " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
        }else{
            query = " SELECT * FROM " + TABLE_COURSE +
                    " WHERE " + COLUMN_COURSENAME + " = \"" + course.get_CourseName() + "\"";
        }
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(1);
            String inStr = cursor.getString(8);
            if (inStr == null) {
                db.update(TABLE_COURSE, values, COLUMN_COURSECODE + " = \"" + idStr + "\"", null);
            }
            cursor.close();
        }

    }

    public boolean UnAssignInstructor(Course course) {
        //change to boolean from void
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String courseCode= course.get_CourseCode();
        String courseName = course.get_CourseName();
        String query;
        String unassignUpdateTable = "UPDATE " + TABLE_COURSE + " SET " + COLUMN_INSTRUCTOR_ID + " = null,"
                + COLUMN_COURSEDAY + " = null,"
                + COLUMN_STARTHOUR + " = null,"
                + COLUMN_ENDHOUR + " = null,"
                + COLUMN_CAPACITY + " = null,"
                + COLUMN_DESCRIPTION + " = null";
        if((checkCourseCode(courseCode)).equals("exist")) {
            query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
            unassignUpdateTable = unassignUpdateTable  + " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
        }else{
            query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSENAME + " = \"" + courseName + "\"";
            unassignUpdateTable = unassignUpdateTable + " WHERE " + COLUMN_COURSENAME + " = \"" + courseName + "\"";
        }
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            if(cursor.getString(8)!=null){
                flag = true;
                db.execSQL(unassignUpdateTable);

            }
            cursor.close();
        }
        course.set_InstructorID("");
        return flag;
    }


    public String checkCourseCode(String courseCode){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
        Cursor cursor = db.rawQuery(query, null);

        String result = "";
        if(cursor.moveToFirst()){
            result = "exist";
        } else{
            result = "NULL";
        }
//        db.close();
        return result;
    }

    public String checkCourseName(String courseName){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSENAME + " = \"" + courseName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        String result = "";
        if(cursor.moveToFirst()){
            result = "exist";
        } else{
            result = "NULL";
        }
//        db.close();
        return result;
    }

    public String GetInstructorID(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        String instructorID = "";
        String courseCode= course.get_CourseCode();
        String courseName = course.get_CourseName();
        String query;
        if((checkCourseCode(courseCode)).equals("exist")) {
            query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
        }else{
            query = " SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSENAME + " = \"" + courseName + "\"";
        }
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            instructorID = cursor.getString(8);
        }
        return instructorID;
    }


    // Course------------------------------------ //
    // Deliverable 3 Start------------------------------------------------------------------------->
    public void enrollStudent(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUMN_STUDENT_ID,course.get_StudentID());
        values.put(COLUMN_STUDENT_NAME,course.get_StudentName());
        String courseCode= course.get_CourseCode();
        String courseName = course.get_CourseName();
        String query;
        if((checkCourseCode(courseCode)).equals("exist")) {
            query = " SELECT * FROM " + TABLE_COURSE +
                    " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\""; //+
            //" AND " + COLUMN_INSTRUCTOR_ID + " IS NULL";

        }else if(checkCourseName(courseName).equals("exist")){
            query = " SELECT * FROM " + TABLE_COURSE +
                    " WHERE " + COLUMN_COURSENAME + " = \"" + course.get_CourseName() + "\"";
        }else {
            query = " SELECT * FROM " + TABLE_COURSE +
                    " WHERE " + COLUMN_COURSEDAY + " = \"" + course.get_CourseDay() + "\"";
        }
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            String inStr = cursor.getString(9);
            if (inStr == null) {
                db.update(TABLE_COURSE, values, COLUMN_ID + " = \"" + idStr + "\"", null);
            }
            cursor.close();
        }
    }

    public List<Course> viewAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_COURSE;

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString,null);

        while (cursor.moveToNext()){
            String CourseName= cursor.getString(2);
            String CourseCode  = cursor.getString(1);
            String CourseDay = cursor.getString(3);

            String CourseStartTime = cursor.getString(4);
            String CourseEndTime = cursor.getString(5);
//            String StudentID = cursor.getString(9);
//            String StudentName = cursor.getString(10);
//            Course course = new Course(CourseName,CourseCode,StudentName,StudentID,CourseDay);
            Course course = new Course();
            course.set_CourseName(CourseName);
            course.set_CourseCode(CourseCode);
            course.set_CourseDay(CourseDay);
            if(CourseStartTime != null && CourseEndTime!= null){
                course.set_StartHour(Integer.parseInt(CourseStartTime));
                course.set_EndHour(Integer.parseInt(CourseEndTime));
            }

            returnList.add(course);
        }
        cursor.close();
//        db.close();
        return returnList;
    }

    public List<Course> SearchByCode(String courseCode){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            String CourseName= cursor.getString(1);
            String CourseCode  = cursor.getString(2);
            String StudentID = cursor.getString(9);
            String CourseDay = cursor.getString(3);
            String StudentName = cursor.getString(10);

            Course course = new Course(CourseName,CourseCode,StudentName,StudentID,CourseDay);
            returnList.add(course);
        }
        cursor.close();
//        db.close();
        return returnList;
    }

    public  List<Course> SearchByName(String courseName){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> returnList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSENAME + " = \"" + courseName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            String CourseName= cursor.getString(1);
            String CourseCode  = cursor.getString(2);
            String StudentID = cursor.getString(9);
            String CourseDay = cursor.getString(3);
            String StudentName = cursor.getString(10);

            Course course = new Course(CourseName,CourseCode,StudentName,StudentID,CourseDay);
            returnList.add(course);
        }
        cursor.close();
//        db.close();
        return returnList;
    }

    public List<Course> SearchByDay(String courseDay){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_COURSE+ " WHERE "+COLUMN_COURSEDAY + " = \"" + courseDay + "\"";;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString,null);
        while (cursor.moveToNext()){
            String CourseName= cursor.getString(1);
            String CourseCode  = cursor.getString(2);
            String StudentID = cursor.getString(9);
            String CourseDay = cursor.getString(3);
            String StudentName = cursor.getString(10);

            Course course = new Course(CourseName,CourseCode,StudentName,StudentID,CourseDay);
            returnList.add(course);
        }
        cursor.close();
//        db.close();
        return returnList;
    }

    public String checkCourseDay(String courseDay){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSEDAY + " = \"" + courseDay + "\"";
        Cursor cursor = db.rawQuery(query, null);


        String result = "";

        while (cursor.moveToNext()){
            String studentID = cursor.getString(9);
            System.out.println(studentID);
            if(studentID!=null){
                result = "exist";

            }
        }
        cursor.close();
        db.close();
        return result;
    }

    public String checkCourseDay(String courseDay,String courseCode){
        SQLiteDatabase db = this.getWritableDatabase();
        String result = "";
        String queryString ="SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSECODE + " = \"" + courseCode + "\"";
        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSEDAY + " = \"" + courseDay + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Cursor cursorCopy = db.rawQuery(queryString,null);
        cursorCopy.moveToFirst();
        String StartTime1 = cursorCopy.getString(4);
        String EndTime1  = cursorCopy.getString(5);

        int StartTime2 = Integer.parseInt(StartTime1);
        int EndTime2 = Integer.parseInt(EndTime1);
        System.out.println(StartTime2);
        System.out.println(EndTime2);

        while (cursor.moveToNext()){
            String StartTime= cursor.getString(4);
            String EndTime  = cursor.getString(5);
            String courseCode1 = cursor.getString(1);
            int StartTime3 = Integer.parseInt(StartTime);
            int EndTime3 = Integer.parseInt(EndTime);
            System.out.println(StartTime3);
            System.out.println(EndTime3);

            if(((StartTime3<=StartTime2 && StartTime2<=EndTime3) || (EndTime3>=EndTime2&&StartTime3<=EndTime2)) && !courseCode.equals(courseCode1)){
                result = "exist";
                return result;
            }else {
                result = "";
            }
        }


//        db.close();

        return result;


    }
    public boolean checkStudentID(String courseDay, String studentID){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString ="SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_COURSEDAY + " = \"" + courseDay + "\"";
        Cursor cursor = db.rawQuery(queryString,null);
        while (cursor.moveToNext()){
            String exist_studentID = cursor.getString(9);
            if(exist_studentID == null){
                return false;
            }
            if (exist_studentID.equals(studentID)){
                return true;
            }
        }
        cursor.close();
        return false;

    }


    public ArrayList<Course> findEnrolledCourse(String studentID){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Course> enrolledCourseList = new ArrayList<Course>();


        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COLUMN_STUDENT_ID + " = \"" + studentID + "\"";
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            Course course = new Course();

//            course.set_ID(Integer.parseInt(cursor.getString(0)));
            course.set_CourseCode(cursor.getString(1));
            course.set_CourseName(cursor.getString(2));
//            course.set_CourseDay(cursor.getString(3));
//            course.set_StartHour(Integer.parseInt(cursor.getString(4)));
//            course.set_EndHour(Integer.parseInt(cursor.getString(5)));
//            course.set_CourseDescription(cursor.getString(6));
//            course.set_CourseCapacity(Integer.parseInt(cursor.getString(7)));
//            course.set_InstructorID(cursor.getString(8));
//            course.set_StudentID(cursor.getString(9));
//            course.set_StudentName(cursor.getString(10));

            enrolledCourseList.add(course);

            System.out.println(course);
        }
//        db.close();
        return enrolledCourseList;
    }

//    public boolean removeStudentIDFromCourse(String courseCode){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values =new ContentValues();
//        values.put(COLUMN_STUDENT_ID, "null");
//        values.put(COLUMN_STUDENT_NAME, "null");
//
//        String query = " SELECT * FROM "+ TABLE_COURSE +" WHERE "+ COLUMN_COURSECODE +" = \""+ courseCode + "\"";
//        Cursor cursor =db.rawQuery(query,null);
//        if(cursor.moveToFirst()){
//            String idStr = cursor.getString(0);
//            db.update(TABLE_COURSE, values,COLUMN_ID +" = "+ idStr,null);
//            cursor.close();
//            return true;
//        }
////        db.close();
//        return false;
//    }

    public boolean removeStudentIDFromCourse(String courseCode){
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_COURSE + " SET " + COLUMN_STUDENT_ID + " = null, "
                + COLUMN_STUDENT_NAME + " = null" + " WHERE " + COLUMN_COURSECODE +" = \""+ courseCode + "\"";
        String selectQuery = " SELECT * FROM "+ TABLE_COURSE +" WHERE "+ COLUMN_COURSECODE +" = \""+ courseCode + "\"";
        Cursor cursor =db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            db.execSQL(updateQuery);
            return true;
        }
//        db.close();
        return false;
    }

    public ArrayList<Student> findEnrolledStudent(String CourseCode,String InstructorID){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Student> studentList = new ArrayList<Student>();


        String query = "SELECT * FROM " + TABLE_COURSE + " WHERE " +
                COLUMN_INSTRUCTOR_ID + " = \"" + InstructorID + "\"" + " and " +
                COLUMN_COURSECODE + " = \"" + CourseCode+ "\"";
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            Student student = new Student();
            student.setID(cursor.getString(9));
            student.setName(cursor.getString(10));

            studentList.add(student);

            System.out.println(student);
        }
//        db.close();
        return studentList;
    }

}




