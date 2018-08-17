package com.example.tenchrio.flashcardz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tenchrio.flashcardz.domain.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private final FlashCardDBHelper fcDbHelper;

    public CourseDAO(Context context)
    {
        fcDbHelper = FlashCardDBHelper.getInstance(context);
    }

    public Course addCourse(Course newCourse) {
        SQLiteDatabase db = fcDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.CourseEntry.COLUMN_NAME_NAME, newCourse.getNaam());
        values.put(DatabaseContract.CourseEntry.COLUMN_NAME_LOCATION, newCourse.getLocatie());
        values.put(DatabaseContract.CourseEntry.COLUMN_NAME_CREATOR, newCourse.getCreator());

        long newRowId = db.insert(DatabaseContract.CourseEntry.TABLE_NAME, null, values);

        newCourse.setId(newRowId);
        return newCourse;
    }

    public List<Course> getAllCourses()
    {
        SQLiteDatabase db = fcDbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM " +
                DatabaseContract.CourseEntry.TABLE_NAME;

        Cursor c = db.rawQuery(selectQuery, null);
        ArrayList<Course> courses = new ArrayList<>();
        if (c != null) {
            while(c.moveToNext()){
                Course addCourse = new Course(c.getString(c.getColumnIndex(DatabaseContract.CourseEntry.COLUMN_NAME_NAME)),c.getString(c.getColumnIndex(DatabaseContract.CourseEntry.COLUMN_NAME_LOCATION)));
                addCourse.setId(c.getInt(c.getColumnIndex(DatabaseContract.CourseEntry._ID)));
                addCourse.setCreator(c.getString(c.getColumnIndex(DatabaseContract.CourseEntry.COLUMN_NAME_CREATOR)));
                courses.add(addCourse);
            }

            c.close();
        }

        return courses;
    }

    public Course getCourseByID(long courseID)
    {
        SQLiteDatabase db = fcDbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM " +
                DatabaseContract.CourseEntry.TABLE_NAME +
                " WHERE " + DatabaseContract.CourseEntry._ID + " = " + courseID;

        Cursor c = db.rawQuery(selectQuery, null);
        Course reCourse = new Course();
        if (c != null) {
            while(c.moveToNext()){
                reCourse = new Course(c.getString(c.getColumnIndex(DatabaseContract.CourseEntry.COLUMN_NAME_NAME)),c.getString(c.getColumnIndex(DatabaseContract.CourseEntry.COLUMN_NAME_LOCATION)));
                reCourse.setId(c.getInt(c.getColumnIndex(DatabaseContract.CourseEntry._ID)));
                reCourse.setCreator(c.getString(c.getColumnIndex(DatabaseContract.CourseEntry.COLUMN_NAME_CREATOR)));
            }

            c.close();
        }

        return reCourse;
    }




}
