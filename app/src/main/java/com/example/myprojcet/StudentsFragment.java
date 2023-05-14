package com.example.myprojcet;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.myprojcet.databinding.FragmentStudentsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsFragment extends Fragment implements View.OnClickListener {

    FragmentStudentsBinding binding;
    AddStudentsFragment addStudentsFragment = new AddStudentsFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStudentsBinding.inflate(getLayoutInflater());

        FloatingActionButton addButton = binding.addStudentsButton;

        addButton.setOnClickListener(this);

        return binding.getRoot();
    }

    private void setNewFragment(Fragment fragment) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        ft.replace(R.id.frameStudentsLayout, fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        setNewFragment(addStudentsFragment);
    }
}