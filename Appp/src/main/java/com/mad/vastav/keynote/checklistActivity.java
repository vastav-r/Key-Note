package com.mad.vastav.keynote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by VastaV on 10/29/2017.
 */

public class checklistActivity  extends AppCompatActivity {

    private Firebase mRootRef;
    private Firebase mRootRef1;
    private ListView mListView;
    private ArrayList<String> memos = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        setTitle("Checklist");
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.mylist,memos);
        mRootRef1 = new Firebase("https://keynote-d282c.firebaseio.com/");
        mRootRef = mRootRef1.child("Checklist");

        mListView = (ListView)findViewById(R.id.listView);

        mListView.setAdapter(arrayAdapter);

        mRootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                memos.add(value);
                notifier();
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String val = (String) dataSnapshot.getValue();
                int index= memos.indexOf(val);
                memos.remove(index);
                notifier();
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {

                String checklistData = memos.get(position);
                Firebase ref = mRootRef.child(checklistData);
                ref.removeValue();
            }
        });
    }

    public void notifier(){
        arrayAdapter.notifyDataSetChanged();
    }

    public void onClick(View v)
    {
        Intent intent = new Intent(checklistActivity.this,EditChecklistActivity.class);
        startActivity(intent);
    }

    public void setContentView() {
    }
}
