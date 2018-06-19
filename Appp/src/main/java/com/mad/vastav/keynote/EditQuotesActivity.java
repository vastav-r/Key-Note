package com.mad.vastav.keynote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * Created by VastaV on 10/29/2017.
 */

public class EditQuotesActivity extends AppCompatActivity {

    private Firebase mRootRef;
    private Button save;
    private EditText edMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quotes);
        setTitle("Add Your Quotes");

        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://keynote-d282c.firebaseio.com/Quotes");

        edMemo = (EditText)findViewById(R.id.editTextMemo);
        save = (Button)findViewById(R.id.saveMemo);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = edMemo.getText().toString();
                Firebase childRef = mRootRef.child(value);
                childRef.setValue(value);
                finish();
            }
        });
    }
}
