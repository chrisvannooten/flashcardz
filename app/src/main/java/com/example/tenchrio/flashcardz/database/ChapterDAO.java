package com.example.tenchrio.flashcardz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tenchrio.flashcardz.domain.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {

    private final FlashCardDBHelper fcDbHelper;

    public ChapterDAO(Context context)
    {
        fcDbHelper = FlashCardDBHelper.getInstance(context);
    }

    public Chapter addChapter(Chapter newChapter, long courseID) {
        SQLiteDatabase db = fcDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ChapterEntry.COLUMN_NAME_NAME, newChapter.getNaam());
        values.put(DatabaseContract.ChapterEntry.COLUMN_NAME_COURSEID, courseID);

        long newRowId = db.insert(DatabaseContract.ChapterEntry.TABLE_NAME, null, values);

        newChapter.setId(newRowId);
        return newChapter;
    }

    public List<Chapter> getAllChaptersForCourse(long CourseID)
    {
        SQLiteDatabase db = fcDbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM " +
                DatabaseContract.ChapterEntry.TABLE_NAME +
                " WHERE " + DatabaseContract.ChapterEntry.COLUMN_NAME_COURSEID + " = " + CourseID;

        Cursor c = db.rawQuery(selectQuery, null);
        ArrayList<Chapter> chapters= new ArrayList<>();
        if (c != null) {
            while(c.moveToNext()){
                Chapter addChapter = new Chapter(c.getString(c.getColumnIndex(DatabaseContract.ChapterEntry.COLUMN_NAME_NAME)));
                addChapter.setId(c.getInt(c.getColumnIndex(DatabaseContract.ChapterEntry._ID)));
                chapters.add(addChapter);
            }
            c.close();
        }

        return chapters;
    }
}
