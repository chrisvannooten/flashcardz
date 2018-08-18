package com.example.tenchrio.flashcardz.activities;


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

        myLstCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Course course = myCourses.get(i);
                Intent intent = new Intent(MainActivity.this, ManageCourseActivity.class);
                intent.putExtra(getString(R.string.extra_course_id), course.getId());
                startActivity(intent);
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
        if(id == R.id.menu_settings){
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        if(id == R.id.menu_about){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
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
