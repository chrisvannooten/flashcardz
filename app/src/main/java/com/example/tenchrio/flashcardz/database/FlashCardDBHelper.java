package com.example.tenchrio.flashcardz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tenchrio.flashcardz.database.DatabaseContract.FlashCardEntry;
import com.example.tenchrio.flashcardz.database.DatabaseContract.ChapterEntry;
import com.example.tenchrio.flashcardz.database.DatabaseContract.CourseEntry;


public class FlashCardDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "flaschardz.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INTEGER = " INTEGER";

    private static final String SQL_CREATE_FLASHCARDS =
            "CREATE TABLE " + FlashCardEntry.TABLE_NAME + " (" +
                    FlashCardEntry._ID + TYPE_INTEGER + " PRIMARY KEY," +
                    FlashCardEntry.COLUMN_NAME_QUESTION+ TYPE_TEXT + "," +
                    FlashCardEntry.COLUMN_NAME_ANSWER+ TYPE_TEXT + "," +
                    FlashCardEntry.COLUMN_NAME_PHOTOURL+ TYPE_TEXT + "," +
                    FlashCardEntry.COLUMN_NAME_CHAPTERID + TYPE_INTEGER + "," +
                    "FOREIGN KEY(" + FlashCardEntry.COLUMN_NAME_CHAPTERID + ") " +
                    "REFERENCES " + ChapterEntry.TABLE_NAME + "(" + ChapterEntry._ID + ")" +
                    ");";

    private static final String SQL_CREATE_CHAPTERS =
            "CREATE TABLE " + ChapterEntry.TABLE_NAME + " (" +
                    ChapterEntry._ID + TYPE_INTEGER + " PRIMARY KEY," +
                    ChapterEntry.COLUMN_NAME_NAME + TYPE_TEXT + "," +
                    ChapterEntry.COLUMN_NAME_COURSEID + TYPE_INTEGER + "," +
                    "FOREIGN KEY(" + ChapterEntry.COLUMN_NAME_COURSEID + ") " +
                    "REFERENCES " + CourseEntry.TABLE_NAME + "(" + CourseEntry._ID + ")" +
                    ");";

    private static final String SQL_CREATE_COURSES =
            "CREATE TABLE " + CourseEntry.TABLE_NAME + " (" +
                    CourseEntry._ID + TYPE_INTEGER + " PRIMARY KEY," +
                    CourseEntry.COLUMN_NAME_NAME + TYPE_TEXT + "," +
                    CourseEntry.COLUMN_NAME_LOCATION + TYPE_TEXT + "," +
                    CourseEntry.COLUMN_NAME_CREATOR + TYPE_TEXT +
                    ");";

    private static final String SQL_DELETE_FLASHCARDS=
            "DROP TABLE IF EXISTS " + FlashCardEntry.TABLE_NAME;
    private static final String SQL_DELETE_CHAPTERS =
            "DROP TABLE IF EXISTS " + ChapterEntry.TABLE_NAME;
    private static final String SQL_DELETE_COURSES =
            "DROP TABLE IF EXISTS " + CourseEntry.TABLE_NAME;

    private static FlashCardDBHelper sInstance;

    private FlashCardDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static synchronized FlashCardDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new FlashCardDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COURSES);
        db.execSQL(SQL_CREATE_CHAPTERS);
        db.execSQL(SQL_CREATE_FLASHCARDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_COURSES);
        db.execSQL(SQL_DELETE_CHAPTERS);
        db.execSQL(SQL_DELETE_FLASHCARDS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
