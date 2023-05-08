package com.example.myprojcet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.myprojcet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding binding;
    ColorStateList def;
    TextView groups_tab, students_tab, select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        groups_tab = findViewById(R.id.groups_tab);
        students_tab = findViewById(R.id.students_tab);

        groups_tab.setOnClickListener(this);
        students_tab.setOnClickListener(this);

        select = findViewById(R.id.select);
        def = students_tab.getTextColors();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.groups_tab){
            select.animate().x(0).setDuration(100);
            groups_tab.setTextColor(Color.WHITE);
            students_tab.setTextColor(def);
        } else if (view.getId() == R.id.students_tab){
            groups_tab.setTextColor(def);
            students_tab.setTextColor(Color.WHITE);
            int size = students_tab.getWidth();
            select.animate().x(size).setDuration(100);
        }
    }
}