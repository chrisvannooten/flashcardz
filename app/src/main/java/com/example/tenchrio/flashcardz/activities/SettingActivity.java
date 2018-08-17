package com.example.tenchrio.flashcardz.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tenchrio.flashcardz.database.CourseDAO;
import com.example.tenchrio.flashcardz.database.FlashCardDBHelper;

import com.example.tenchrio.flashcardz.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(getString(R.string.action_settings));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void resetDB(View view){
        if (view.getId() == R.id.btnEmpty) {
            /*getApplicationContext().deleteDatabase("flaschardz.db");
            getApplicationContext();*/
            Toast.makeText(this, "Database Cleared!", Toast.LENGTH_SHORT).show();
        }
    }
}
