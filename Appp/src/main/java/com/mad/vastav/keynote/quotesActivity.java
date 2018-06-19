package com.mad.vastav.keynote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by VastaV on 10/29/2017.
 */

public class quotesActivity  extends AppCompatActivity {

    private Firebase mRootRef;
    private ListView mListView;
    private Button mAddQuote;
    private ArrayList<String> memos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        setTitle("Quotes");

        mRootRef = new Firebase("https://keynote-d282c.firebaseio.com/Quotes");

        mAddQuote = (Button)findViewById(R.id.Quotes);
        mListView = (ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.mylist,memos);

        mListView.setAdapter(arrayAdapter);

        mRootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                memos.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void onClick(View v)
    {
        Intent intent = new Intent(quotesActivity.this,EditQuotesActivity.class);
        startActivity(intent);
    }
}
