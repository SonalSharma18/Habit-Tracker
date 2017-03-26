package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.habittracker.HabitTrackerContract.HabitContract;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase myDatabase;
    private HabitDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentValues values = new ContentValues();
        values.put(HabitContract.COLUMN_NAME, "Sonal");
        values.put(HabitContract.COLUMN_EXERCISE, HabitTrackerContract.HabitContract.EXERCISE_FALSE);
        values.put(HabitContract.COLUMN_SLEPT_FOR, 9); //test
        getContentResolver().insert(HabitContract.HABIT_URI, values);

        Cursor cursor = getContentResolver().query(HabitContract.HABIT_URI, null,
                null,null,null);
        TextView displayView = (TextView) findViewById(R.id.display);

        try {

            displayView.setText("The routine in table is " + cursor.getCount() + " pets.\n\n");
            displayView.append(HabitContract._ID + " - " +
                    HabitContract.COLUMN_NAME + " - " +
                    HabitContract.COLUMN_EXERCISE + " - " +
                    HabitContract.COLUMN_SLEPT_FOR + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitContract._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.COLUMN_NAME);
            int exerciseColumnIndex = cursor.getColumnIndex(HabitContract.COLUMN_EXERCISE);
            int sleepColumnIndex = cursor.getColumnIndex(HabitContract.COLUMN_SLEPT_FOR);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {


                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentExercise = cursor.getString(exerciseColumnIndex);
                int currentSleep = cursor.getInt(sleepColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentExercise + " - " +
                        currentSleep ));
            }
        }
        finally {
            cursor.close();
        }
    }

}