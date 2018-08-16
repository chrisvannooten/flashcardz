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
        // Gets the data repository in read mode
        SQLiteDatabase db = fcDbHelper.getReadableDatabase();

        // Create SQL query
        String selectQuery = "SELECT * FROM " +
                DatabaseContract.CourseEntry.TABLE_NAME;

        // Execute query, this will return a cursor that can be used to iterate results
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




}
