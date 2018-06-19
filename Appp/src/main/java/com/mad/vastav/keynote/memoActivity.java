package com.mad.vastav.keynote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;

/**
 * Created by VastaV on 10/29/2017.
 */

public class memoActivity extends AppCompatActivity {

    private Firebase mRootRef;
    private Firebase mRootRef1;
    private ListView mListView;
    private ArrayList<String> memos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        setTitle("Memo");

        mRootRef1 = new Firebase("https://keynote-d282c.firebaseio.com");
        mRootRef =mRootRef1.child("memo");

        mListView = (ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.mylist,memos);


        mListView.setAdapter(arrayAdapter);

        mRootRef.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                memos.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {
                String val = (String) dataSnapshot.getValue();
                int index= memos.indexOf(val);
                memos.remove(index);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mListView.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String checklistData = memos.get(position);
                Firebase ref = mRootRef.child(checklistData);
                ref.removeValue();
                return false;
            }
        });
    }

    public void onClick(View v)
    {
        Intent intent = new Intent(memoActivity.this,EditMemoActivity.class);
        startActivity(intent);
    }
}