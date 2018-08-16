package com.example.tenchrio.flashcardz.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.tenchrio.flashcardz.R;
import com.example.tenchrio.flashcardz.database.CourseDAO;
import com.example.tenchrio.flashcardz.domain.Course;

public class addCourseActivity extends AppCompatActivity {

    private CourseDAO myCourseDAO;
    private EditText myTxtName;
    private EditText myTxtLocation;
    private Course myCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        myTxtName = (EditText) findViewById(R.id.txt_Name);
        myTxtLocation = (EditText) findViewById(R.id.txt_Location);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addCourse(View view){
        if (view.getId() == R.id.btn_Add_Course)
        {
            String cName = myTxtName.getText().toString();
            String cLocation = myTxtLocation.getText().toString();

            if (cName.isEmpty()) {
                myTxtName.setError(getString(R.string.error_no_name));
            } else if (cLocation.isEmpty()) {
                myTxtLocation.setError(getString(R.string.error_no_location));
            } else {
                myCourse = new Course(cName,cLocation);
                myCourse.setCreator("local");
                myCourseDAO = new CourseDAO(this);
                myCourseDAO.addCourse(myCourse);
                finish();
            }
        }
    }
}
