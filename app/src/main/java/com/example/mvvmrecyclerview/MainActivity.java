package com.example.mvvmrecyclerview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmrecyclerview.adapter.UserAdapter;
import com.example.mvvmrecyclerview.model.User;
import com.example.mvvmrecyclerview.viewmodel.MainActivityViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mainActivityViewModel.getAllUserData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(final List<User> users) {

                userAdapter = new UserAdapter(users);
                recyclerView.setAdapter(userAdapter);

                // get specific data
                Toast.makeText(MainActivity.this, ""+users.get(0).getName(), Toast.LENGTH_SHORT).show();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Generate csv data
                        StringBuilder stringBuilder = new StringBuilder();
                        // below LINE add you column name using comma separation
                        stringBuilder.append("Name,Designation,Email");
                        for (int i = 0; i < users.size(); i++)
                        {
                            stringBuilder.append("\n"+users.get(i).getName()+","+users.get(i).getDesignation()+","+users.get(i).getEmail());
                        }
                        try {
                            // saving the file into device
                            FileOutputStream out = openFileOutput("administrator.csv", Context.MODE_PRIVATE);
                            out.write(stringBuilder.toString().getBytes());
                            out.close();

                            // export the file into device
                            Context context = getApplicationContext();
                            File filelocation = new File(getFilesDir(),"administrator.csv");
                            Uri path = FileProvider.getUriForFile(context,"com.example.mvvmrecyclerview.fileprovider", filelocation);
                            Intent fileintent = new Intent(Intent.ACTION_SEND);
                            fileintent.setType("text/csv");
                            fileintent.putExtra(Intent.EXTRA_SUBJECT, "University Admin Data");
                            fileintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            fileintent.putExtra(Intent.EXTRA_STREAM,path);
                            startActivity(Intent.createChooser(fileintent,"Export/Share to"));

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                });
                /////////////button end/////////////////
            }
        });
    }


}
