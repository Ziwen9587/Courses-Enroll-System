package com.example.pj_deliverable01;

public class Course {
    private String _CourseCode;
    private String _CourseName;
    private int _ID;
    private String _CourseDay;
    private Integer _StartHour;
    private Integer _EndHour;
    private String _Description;
    private Integer _Capacity;
    private String _InstructorID;
    private String _InstructorName;
    private String _StudentName;
    private String _StudentID;

    public Course(){
    }
    public Course(String CourseCode, String CourseName){
        _CourseCode = CourseCode;
        _CourseName = CourseName;

    }
    public Course(int ID, String CourseCode,String CourseName){
        _ID = ID;
        _CourseCode=CourseCode;
        _CourseCode=CourseName;
    }

    public Course(String CourseCode,String CourseName,String InstructorID) {
        _CourseCode = CourseCode;
        _CourseName = CourseName;
        _InstructorID = InstructorID;
    }
    public Course(String CourseCode,String CourseName,String InstructorName, String InstructorID) {
        _CourseCode = CourseCode;
        _CourseName = CourseName;
        _InstructorID = InstructorID;
        _InstructorName = InstructorName;
    }
    public Course(String CourseCode,String CourseName,String StudentName,String StudentID,String CourseDay){
        _CourseCode = CourseCode;
        _CourseName = CourseName;
        _StudentID = StudentID;
        _StudentName=StudentName;
        _CourseDay = CourseDay;

    }


    public  void  set_ID(int ID){_ID= ID;}
    public  int get_ID(){return _ID;}
    public void set_CourseCode(String CourseCode){
        _CourseCode= CourseCode;
    }
    public String get_CourseCode(){
        return _CourseCode;
    }
    public void  set_CourseName(String CourseName){
        _CourseName=CourseName;
    }
    public String get_CourseName(){
        return _CourseName;
    }
    public void set_CourseDay(String CourseDay){
        _CourseDay = CourseDay;}
    public String get_CourseDay(){
        return _CourseDay;}
    public void set_StartHour(int StartHour){
        _StartHour = StartHour;}
    public Integer get_StartHour(){
        return _StartHour;
    }
    public void set_EndHour(int EndHour){
        _EndHour = EndHour;
    }
    public Integer get_EndHour(){
        return _EndHour;
    }
    public void set_CourseDescription(String Description){
        _Description = Description;
    }

    public String get_CourseDescription(){
        return  _Description;
    }

    public  void set_CourseCapacity(int Capacity){
        _Capacity = Capacity;
    }

    public Integer get_CourseCapacity(){
        return _Capacity;
    }
    public void set_InstructorID(String InstructorID) { _InstructorID=InstructorID; }
    public String get_InstructorID() { return _InstructorID; }
    public void set_InstructorName(String InstructorName) { _InstructorName=InstructorName; }
    public String get_InstructorName() { return _InstructorName; }
    public void set_StudentName(String StudentName){
        _StudentName=StudentName;
    }
    public String get_StudentName(){
        return _StudentName;
    }
    public void set_StudentID(String StudentID){
        _StudentID =StudentID;
    }
    public String get_StudentID(){
        return _StudentID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "_CourseCode='" + _CourseCode + '\'' +
                ", _CourseName='" + _CourseName + '\'' +
                ", _ID=" + _ID +
                ", _CourseDay='" + _CourseDay + '\'' +
                ", _StartHour=" + _StartHour +
                ", _EndHour=" + _EndHour +
                ", _Description='" + _Description + '\'' +
                ", _Capacity=" + _Capacity +
                ", _InstructorID='" + _InstructorID + '\'' +
                ", _InstructorName='" + _InstructorName + '\'' +
                ", _StudentName='" + _StudentName + '\'' +
                ", _StudentID='" + _StudentID + '\'' +
                '}';
    }
}
