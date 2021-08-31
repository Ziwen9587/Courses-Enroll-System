package com.example.pj_deliverable01;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PJ_InstrumentedTest {
    private MyDBHandler db;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = new MyDBHandler(context);
    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void test1_DatabaseExistence() {
        assertNotNull(db);
    }

    @Test
    public void test2_AddStudent() {
        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.example.pj_deliverable01", appContext.getPackageName());
//        MyDBHandler db = new MyDBHandler(appContext);
        String studentID = "001";
        String studentName = "James";
        String studentPassWord = "111222";
        Student student = new Student(studentID,studentName,studentPassWord);

        db.addStudent(student);
        Student findStudent = db.findStudent(studentID);

        boolean result = true;
        if (findStudent == null){
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void test3_findStudent() {

        String notExistStudentID = "9999999";
        Student findStudent = db.findStudent(notExistStudentID);

        boolean result = true;
        if (findStudent == null){
            result = false;
        }
        assertFalse(result);
    }

    @Test
    public void test4_addInstructor() {

        String instructorID = "001";
        String instructorName = "Patrick";
        String instructorPassWord = "333444";
        Instructor instructor = new Instructor(instructorID,instructorName,instructorPassWord);

        db.addInstructor(instructor);
        Instructor findInstructor = db.findInstructor(instructorID);

        boolean result = true;
        if (findInstructor == null){
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void test5_findInstructor() {

        String notExistStudentID = "00000000";
        Instructor findInstructor = db.findInstructor(notExistStudentID);

        boolean result = true;
        if (findInstructor == null){
            result = false;
        }
        assertFalse(result);
    }

    @Test
    public void test6_deleteStudent() {

        String studentID = "002";
        String studentName = "Helen";
        String studentPassWord = "111222";
        Student student = new Student(studentID,studentName,studentPassWord);
        db.addStudent(student);
        Student findResult = db.findStudent(studentID);
        assertNotNull(findResult); //First assert

        db.deleteStudent(studentID);
        Student deleteResult = db.findStudent(studentID);
        boolean result = false;
        if (deleteResult == null){
            result = true;
        }
        assertTrue(result);     //Last assert
    }

    @Test
    public void test7_deleteInstructor() {

        String instructorID = "002";
        String instructorName = "Patrick";
        String instructorPassWord = "111222";
        Instructor instructor = new Instructor(instructorID,instructorName,instructorPassWord);
        db.addInstructor(instructor);
        Instructor findResult = db.findInstructor(instructorID);
        assertNotNull(findResult); //First asset

        db.deleteInstructor(instructorID);
        Instructor deleteResult = db.findInstructor(instructorID);
        boolean result = false;
        if (deleteResult == null){
            result = true;
        }
        assertTrue(result);     //Last asset
    }

//test for deliverable 3 start


    @Test
    public void test8_enrollStudent(){
        String CourseCode = "TC1";
        String CourseName = "TestCourse1";
        Course course = new Course(CourseCode,CourseName);
        db.createCourse(course);
        Student student = new Student("4","st","111");
        db.addStudent(student);
        course.set_StudentName(student.getName());
        db.enrollStudent(course);
        Course newcourse = db.findCourse(CourseCode);
        assertEquals(newcourse.get_StudentName(),"st",newcourse.get_StudentName());
    }

    @Test
    public void test9_searchByCode(){
        Course fcourse = new Course("TC1","TestCourse1");
        fcourse.set_StudentName("st");
        Course scourse = new Course("TC1","TestCourse11");
        scourse.set_StudentName("st");
        Course tcourse = new Course("TC3","TestCourse3");
        db.createCourse(fcourse);
        db.createCourse(scourse);
        db.createCourse(tcourse);
        List<Course> dbList = db.SearchByCode("TC1");
        boolean flag = true;
        for(Course c:dbList){
            if(c!=null) {
                if (!(c.get_CourseCode()).equals("TC1")) {
                    flag = false;
                    System.out.println(c.get_CourseCode());
                }
            }
        }
        assertTrue(flag);
    }

    @Test
    public void test10_searchByName(){
        Course fcourse = new Course("TC1","TestCourse1");
        fcourse.set_StudentName("st");
        Course scourse = new Course("TC2","TestCourse1");
        scourse.set_StudentName("st");
        Course tcourse = new Course("TC3","TestCourse3");
        db.createCourse(fcourse);
        db.createCourse(scourse);
        db.createCourse(tcourse);
        List<Course> dbList = db.SearchByName("TestCourse1");
        boolean flag = true;
        for(Course c:dbList){
            if(c!=null) {
                if (!(c.get_CourseName()).equals("TestCourse1")) {
                    flag = false;
                }
            }
        }
        assertTrue(flag);
    }

    @Test
    public void test11_searchByDay(){
        Course fcourse = new Course("TC1","TestCourse1");
        fcourse.set_CourseDay("Monday");
        Course scourse = new Course("TC2","TestCourse2");
        scourse.set_CourseDay("Monday");
        Course tcourse = new Course("TC3","TestCourse3");
        tcourse.set_CourseDay("Sunday");
        Course lcourse = new Course("TC4","TestCourse4");
        db.createCourse(fcourse);
        db.createCourse(scourse);
        db.createCourse(tcourse);
        db.createCourse(lcourse);
        List<Course> dbList = db.SearchByDay("Monday");
        boolean flag = true;
        for(Course c:dbList){
            if(c!=null) {
                if (!(c.get_CourseDay()).equals("Monday")) {
                    flag = false;
                }
            }
        }
        assertTrue(flag);
    }

    public void test12_findEnrolledCourse(){
        Course fcourse = new Course("TC1","TestCourse1");
        fcourse.set_CourseDay("Monday");
        Course scourse = new Course("TC2","TestCourse2");
        scourse.set_CourseDay("Monday");
        Course tcourse = new Course("TC3","TestCourse3");
        tcourse.set_CourseDay("Sunday");
        Course lcourse = new Course("TC4","TestCourse4");
        db.createCourse(fcourse);
        db.createCourse(scourse);
        db.createCourse(tcourse);
        db.createCourse(lcourse);
        Student student = new Student("4","st","111");
        db.addStudent(student);
        fcourse.set_StudentName("st");
        fcourse.set_StudentID("4");
        tcourse.set_StudentName("st");
        tcourse.set_StudentID("4");
        db.enrollStudent(fcourse);
        db.enrollStudent(tcourse);
        ArrayList<Course> dbList = db.findEnrolledCourse("4");
        String enrolledfCourse = (dbList.get(0)).get_CourseCode();
        String enrolledsCourse = (dbList.get(1)).get_CourseCode();
        assertEquals("TC1",enrolledfCourse);
        assertEquals("TC3",enrolledsCourse);

    }
}