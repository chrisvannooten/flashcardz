package com.example.tenchrio.flashcardz.activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tenchrio.flashcardz.database.FlashCardDBHelper;

import com.example.tenchrio.flashcardz.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

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
            FlashCardDBHelper fcHelper = FlashCardDBHelper.getInstance(this);
            SQLiteDatabase db = fcHelper.getReadableDatabase(); // helper is object extends SQLiteOpenHelper
            fcHelper.onUpgrade(db, 1,1);
            Toast.makeText(this, "Database Cleared!", Toast.LENGTH_SHORT).show();
        }
    }

    public void backDB(View view){
        if (view.getId() == R.id.btnEmpty) {
            new Thread(new Runnable(){
                public void run() {
                    exportDB();
                }
            }).start();
        }
    }

    public void exportDB(){
        File direct = new File(Environment.getExternalStorageDirectory() + "/Exam Creator");

        if(!direct.exists()) {
            if (direct.mkdir()) {
                //directory is created;
            }
        }
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "com.example.tenchrio.flashcardz" + "//databases//" + "flashcardz.db";
                String backupDBPath  = "/BackupFolder/flashback.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), backupDB.toString(),Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }
}
