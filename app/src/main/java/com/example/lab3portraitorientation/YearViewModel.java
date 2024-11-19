package com.example.lab3portraitorientation;

import androidx.lifecycle.ViewModel;

public class YearViewModel extends ViewModel {
    private String yearText = "";
    private String resultText = "";

    public String getYearText() {
        return yearText;
    }

    public void setYearText(String yearText) {
        this.yearText = yearText;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }
}
