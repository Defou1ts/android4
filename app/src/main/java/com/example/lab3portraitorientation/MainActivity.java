package com.example.lab3portraitorientation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String PREFS_FILE = "screenData";

    private EditText editTextYear;
    private TextView textViewResult;

    private SharedPreferences screenData;
    private YearViewModel yearViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextYear = findViewById(R.id.editTextYear);
        textViewResult = findViewById(R.id.textViewResult);

        // Получаем экземпляр YearViewModel
        yearViewModel = new ViewModelProvider(this).get(YearViewModel.class);

        // Восстанавливаем данные из ViewModel
        editTextYear.setText(yearViewModel.getYearText());
        textViewResult.setText(yearViewModel.getResultText());

        screenData = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        Log.d(TAG, "MainActivity: onCreate()");
    }

    public void checkYear(View view) {
        String inputYear = editTextYear.getText().toString();
        if (inputYear.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите год", Toast.LENGTH_SHORT).show();
            return;
        }

        int year = Integer.parseInt(inputYear);
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        String result = isLeapYear ? year + " — високосный год" : year + " — не високосный год (365 дней)";
        textViewResult.setText(result);

        // Сохраняем данные в ViewModel
        yearViewModel.setYearText(inputYear);
        yearViewModel.setResultText(result);
    }

    public void save(View view) {
        SharedPreferences.Editor prefEditor = screenData.edit();
        prefEditor.putString("editTextYearText", editTextYear.getText().toString());
        prefEditor.putString("textViewResultText", textViewResult.getText().toString());
        prefEditor.apply();
    }

    public void load(View view) {
        String year = screenData.getString("editTextYearText", "");
        String result = screenData.getString("textViewResultText", "");

        editTextYear.setText(year);
        textViewResult.setText(result);

        // Сохраняем загруженные данные в ViewModel
        yearViewModel.setYearText(year);
        yearViewModel.setResultText(result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");
    }
}
