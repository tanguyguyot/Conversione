package com.example.conversione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class infos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        ImageButton menu = findViewById(R.id.menuIcon);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allerMenu();
            }
        });


    }

    public void allerMenu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}