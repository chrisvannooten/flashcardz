package com.example.tenchrio.flashcardz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tenchrio.flashcardz.domain.Chapter;
import com.example.tenchrio.flashcardz.domain.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class FlashCardDAO {
    private final FlashCardDBHelper fcDbHelper;

    public FlashCardDAO(Context context)
    {
        fcDbHelper = FlashCardDBHelper.getInstance(context);
    }

    public FlashCard addFlashCard(FlashCard newFlashCard, long chapterId) {
        SQLiteDatabase db = fcDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FlashCardEntry.COLUMN_NAME_QUESTION, newFlashCard.getQuestion());
        values.put(DatabaseContract.FlashCardEntry.COLUMN_NAME_ANSWER, newFlashCard.getAnswer());
        if (newFlashCard.getPhotoUrl() != null) values.put(DatabaseContract.FlashCardEntry.COLUMN_NAME_PHOTOURL, newFlashCard.getPhotoUrl());
        values.put(DatabaseContract.FlashCardEntry.COLUMN_NAME_CHAPTERID, chapterId);

        long newRowId = db.insert(DatabaseContract.FlashCardEntry.TABLE_NAME, null, values);

        newFlashCard.setId(newRowId);
        return newFlashCard;
    }

    public List<FlashCard> getAllFlashCardsForChapter(long chapterID)
    {
        SQLiteDatabase db = fcDbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM " +
                DatabaseContract.FlashCardEntry.TABLE_NAME +
                " WHERE " + DatabaseContract.FlashCardEntry.COLUMN_NAME_CHAPTERID + " = " + chapterID;

        Cursor c = db.rawQuery(selectQuery, null);
        ArrayList<FlashCard> cards= new ArrayList<>();
        if (c != null) {
            while(c.moveToNext()){
                FlashCard addFlashCard = new FlashCard(c.getString(c.getColumnIndex(DatabaseContract.FlashCardEntry.COLUMN_NAME_QUESTION)),c.getString(c.getColumnIndex(DatabaseContract.FlashCardEntry.COLUMN_NAME_ANSWER)));
                addFlashCard.setId(c.getInt(c.getColumnIndex(DatabaseContract.FlashCardEntry._ID)));
                if(c.getString(c.getColumnIndex(DatabaseContract.FlashCardEntry.COLUMN_NAME_PHOTOURL)) == ""){}
                cards.add(addFlashCard);
            }
            c.close();
        }

        return cards;
    }

}
