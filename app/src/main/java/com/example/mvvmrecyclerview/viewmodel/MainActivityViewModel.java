package com.example.mvvmrecyclerview.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmrecyclerview.model.User;
import com.example.mvvmrecyclerview.repository.MainActivityRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MainActivityRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new MainActivityRepository(application);
    }

    // this method will be call to mainactivity
    public LiveData<List<User>> getAllUserData()
    {
        // we just call reopository method here
        return repository.getUserData();
    }
}
