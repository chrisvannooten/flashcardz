package com.example.tenchrio.flashcardz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tenchrio.flashcardz.R;
import com.example.tenchrio.flashcardz.database.ChapterDAO;
import com.example.tenchrio.flashcardz.database.CourseDAO;
import com.example.tenchrio.flashcardz.domain.Chapter;
import com.example.tenchrio.flashcardz.domain.Course;

import java.util.ArrayList;

public class ManageCourseActivity extends AppCompatActivity {

    private ChapterDAO myChapterDAO;
    private ArrayList<Chapter> myChapters;
    private ListView myLstChapter;
    private long courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        myChapterDAO = new ChapterDAO(this);
        myLstChapter = findViewById(R.id.lstChapter);

        Intent intent = getIntent();
        courseId = intent.getLongExtra("course_id",-1);

        if (courseId != -1){
            setTitle(new CourseDAO(this).getCourseByID(courseId).getNaam());
            myChapters = (ArrayList<Chapter>) myChapterDAO.getAllChaptersForCourse(courseId);
        }
        initAdapter();

        myLstChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Chapter chapter = myChapters.get(i);
                Intent intent = new Intent(ManageCourseActivity.this, CardActivity.class);
                intent.putExtra(getString(R.string.extra_chapter_id), chapter.getId());
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        myLstChapter.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myChapters));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        myChapters = (ArrayList<Chapter>) myChapterDAO.getAllChaptersForCourse(courseId);
        initAdapter();
    }


    public void addCourse(View v) {
        if (v.getId() == R.id.btnChapter) {
            Intent intent = new Intent(this, AddChapterActivity.class);
            intent.putExtra(getString(R.string.extra_course_id), courseId);
            startActivity(intent);
        }
    }
}
