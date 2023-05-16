package com.example.myprojcet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myprojcet.databinding.FragmentAddStudentsBinding;

public class AddStudentsFragment extends Fragment implements View.OnClickListener {

    FragmentAddStudentsBinding binding;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddStudentsBinding.inflate(getLayoutInflater());

        Button addButton = binding.addStudents;
        Button cancelButton = binding.cancelStudents;

        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        dbHelper = new DBHelper(getActivity());

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        boolean flag = true;
        if (v.getId() == R.id.addStudents){
            String name = binding.addName.getText().toString();
            String last_name = binding.addLastName.getText().toString();
            String surname = binding.addSurname.getText().toString();
            String date = binding.addDate.getText().toString();
            String group = binding.addGroup.getText().toString();

            if (name.isEmpty() || last_name.isEmpty() ||
                    surname.isEmpty() || date.isEmpty() || group.isEmpty()){
                flag = false;
                Toast.makeText(getActivity().getApplicationContext(),
                        "You haven't entered one or more fields. Check them and try again.",
                        Toast.LENGTH_LONG).show();
            } else {
                Student student = new Student(name, last_name, surname, date, Integer.parseInt(group));
                dbHelper.addStudent(student);
            }
        }
        if (flag) {
            clearAll();
            getFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    private void clearAll(){
        binding.addName.setText("");
        binding.addLastName.setText("");
        binding.addSurname.setText("");
        binding.addDate.setText("");
        binding.addGroup.setText("");
    }
}