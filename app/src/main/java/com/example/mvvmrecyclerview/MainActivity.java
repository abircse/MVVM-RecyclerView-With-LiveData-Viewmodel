package com.example.mvvmrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmrecyclerview.adapter.UserAdapter;
import com.example.mvvmrecyclerview.model.User;
import com.example.mvvmrecyclerview.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mainActivityViewModel.getAllUserData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                userAdapter = new UserAdapter(users);
                recyclerView.setAdapter(userAdapter);

                // get specific data
                Toast.makeText(MainActivity.this, ""+users.get(0).getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
