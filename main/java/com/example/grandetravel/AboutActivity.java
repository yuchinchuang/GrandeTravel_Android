package com.example.grandetravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_VIEW = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void packs(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivityForResult(mainIntent,REQUEST_CODE_VIEW);
    }
}
