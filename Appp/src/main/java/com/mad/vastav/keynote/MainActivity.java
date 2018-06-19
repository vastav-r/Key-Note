package com.mad.vastav.keynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button memo, camera, scribble, checklist, mquotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memo = (Button)findViewById(R.id.Memo);
        camera = (Button)findViewById(R.id.Camera);
        mquotes = (Button)findViewById(R.id.Quotes);
        scribble = (Button)findViewById(R.id.Scribble);
        checklist = (Button)findViewById(R.id.Checklist);

        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,memoActivity.class);
                startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,cameraActivity.class);
                startActivity(intent);
            }
        });
       scribble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,scribbleActivity.class);
                startActivity(intent);
            }
        });
        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,checklistActivity.class);
                startActivity(intent);
            }
        });
        mquotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,quotesActivity.class);
                startActivity(intent);
            }
        });



    }
}
