package com.example.tenchrio.flashcardz.database;


import android.provider.BaseColumns;

import com.example.tenchrio.flashcardz.domain.FlashCard;

final class DatabaseContract {

    private DatabaseContract() {}

    static class FlashCardEntry implements BaseColumns
    {
        static final String TABLE_NAME = "flashcards";
        static final String COLUMN_NAME_QUESTION = "question";
        static final String COLUMN_NAME_ANSWER = "answer";
        static final String COLUMN_NAME_PHOTOURL = "photourl";
        static final String COLUMN_NAME_CHAPTERID = "chapterid";
        private FlashCardEntry() {}
    }

    static class ChapterEntry implements BaseColumns
    {
        static final String TABLE_NAME = "chapters";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_COURSEID = "courseid";
        private ChapterEntry() {}
    }

    static class CourseEntry implements BaseColumns
    {
        static final String TABLE_NAME = "course";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_LOCATION = "location";
        static final String COLUMN_NAME_CREATOR = "creator";
        private CourseEntry() {}
    }

}
