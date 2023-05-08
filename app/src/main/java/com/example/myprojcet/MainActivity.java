package com.example.myprojcet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.myprojcet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding binding;
    ColorStateList def;
    TextView groups_tab, students_tab, select;
    StudentsFragment studentsFragment = new StudentsFragment();

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
        def = groups_tab.getTextColors();

        setNewFragment(studentsFragment);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.students_tab){
            select.animate().x(0).setDuration(100);
            students_tab.setTextColor(Color.WHITE);
            groups_tab.setTextColor(def);
            setNewFragment(studentsFragment);
        } else if (view.getId() == R.id.groups_tab){
            students_tab.setTextColor(def);
            groups_tab.setTextColor(Color.WHITE);
            int size = groups_tab.getWidth();
            select.animate().x(size).setDuration(100);
            GroupsFragment groupsFragment = new GroupsFragment();
            setNewFragment(groupsFragment);
        }
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }
}