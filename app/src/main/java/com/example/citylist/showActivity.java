package com.example.citylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class showActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        Intent intent = getIntent();
        String s = intent.getStringExtra("cityname");
        TextView t = findViewById(R.id.textView);
        //Toast.makeText(showActivity.this,s,Toast.LENGTH_SHORT).show();
        t.setText(s);
        Button b = findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(showActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}