package com.example.tenchrio.flashcardz.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.tenchrio.flashcardz.R;
import com.example.tenchrio.flashcardz.database.CourseDAO;
import com.example.tenchrio.flashcardz.domain.Course;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CourseDAO myCourseDAO;
    private ListView myLstCourse;
    private ArrayList<Course> myCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCourseDAO = new CourseDAO(this);
        myLstCourse = findViewById(R.id.lstCourses);

        myCourses = (ArrayList<Course>)myCourseDAO.getAllCourses();
        initAdapter();

        final Context test = this;
        myLstCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Course course = myCourses.get(i);
                String myTxt = "Course is " + course.getId();
                Toast.makeText(test,myTxt,Toast.LENGTH_SHORT).show();
                /*
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                //add the artist id to the intent
                intent.putExtra(getString(R.string.extra_artist_id), artist.getId());
                startActivity(intent);*/
            }
        });

    }

    private void initAdapter() {
        myLstCourse.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myCourses));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_nothing){
            Toast.makeText(this,"Why did you feel the need to press that?",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        myCourses = (ArrayList<Course>)myCourseDAO.getAllCourses();
        initAdapter();
    }

    public void addCourse(View v) {
        if (v.getId() == R.id.btnNew) {
            Intent intent = new Intent(MainActivity.this, addCourseActivity.class);
            startActivity(intent);
        }
    }
}
