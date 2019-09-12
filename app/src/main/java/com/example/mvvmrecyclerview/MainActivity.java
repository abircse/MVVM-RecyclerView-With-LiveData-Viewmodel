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
        mainActivityViewModel.getAllUserData().observe(this, new Observer<User[]>() {
            @Override
            public void onChanged(User[] users) {

                // we go data here finally by pass repor> vieewmodel >> now here
                userAdapter = new UserAdapter(users);
                recyclerView.setAdapter(userAdapter);

                // to get specific data
                Toast.makeText(MainActivity.this, "get "+users[0].getName(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
