package com.example.tenchrio.flashcardz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tenchrio.flashcardz.R;
import com.example.tenchrio.flashcardz.database.ChapterDAO;
import com.example.tenchrio.flashcardz.database.CourseDAO;
import com.example.tenchrio.flashcardz.database.FlashCardDAO;
import com.example.tenchrio.flashcardz.domain.Chapter;
import com.example.tenchrio.flashcardz.domain.FlashCard;

import java.util.ArrayList;

public class AddChapterActivity extends AppCompatActivity {

    private EditText myTxtName;
    private EditText myTxtQuestion;
    private EditText myTxtAnswer;
    private ListView myLstFlashCards;
    private Chapter myChapter;
    private ArrayList<FlashCard> myCards;
    private long courseID;
    private ChapterDAO myChapterDAO;
    private FlashCardDAO myFlashCardDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(getString(R.string.btn_Chapter));

        myChapter = new Chapter();
        myCards = new ArrayList<FlashCard>();
        myChapterDAO = new ChapterDAO(this);
        myFlashCardDAO = new FlashCardDAO(this);

        myTxtName = (EditText) findViewById(R.id.txtChapterName);
        myTxtQuestion = (EditText) findViewById(R.id.txtQuestion);
        myTxtAnswer = (EditText) findViewById(R.id.txtAnswer);

        myLstFlashCards = findViewById(R.id.lstFlashCards);

        Intent intent = getIntent();
        courseID = intent.getLongExtra(getString(R.string.extra_course_id), -1);

        initAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initAdapter() {
        myLstFlashCards.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myCards));
    }


    public void addFlashCard(View view) {
        if (view.getId() == R.id.btnAddFlashcard)
        {
            String question = myTxtQuestion.getText().toString();
            String answer = myTxtAnswer.getText().toString();

            if(question.isEmpty()){
                myTxtQuestion.setError("Please give a valid Question");
            } else if (answer.isEmpty()){
                myTxtAnswer.setError("Please give a valid Answer");
            } else {
                FlashCard newFlash = new FlashCard(question,answer);
                myTxtQuestion.setError(null);
                myTxtAnswer.setError(null);
                myTxtQuestion.setText(null);
                myTxtAnswer.setText(null);
                myCards.add(newFlash);
                initAdapter();
            }
        }
    }

    public void addChapter(View view) {
        if (view.getId() == R.id.btnAddChapter)
        {
            String name = myTxtName.getText().toString();

            if(name.isEmpty()){
                myTxtName.setError("Please give a valid Name");
            } else if (myCards.size() < 1){
                myTxtName.setError("Need a minimum of 1 Flashcard");
            } else {
                Chapter newChapter = new Chapter(name);
                newChapter = myChapterDAO.addChapter(newChapter, courseID);
                for (FlashCard flash : myCards)
                {
                    myFlashCardDAO.addFlashCard(flash, newChapter.getId());
                }
                Toast.makeText(this,"Chapter "+ newChapter.getNaam() + " created",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
