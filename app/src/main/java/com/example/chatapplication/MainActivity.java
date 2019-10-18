package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = FirebaseDatabase.getInstance().getReference("Message");

        final TextView mytext = findViewById(R.id.text1);

        myDatabase.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                mytext.setText(dataSnapshot.getValue().toString());
            }

            public void onCancelled(DatabaseErrorHandler databaseError){
                mytext.setText("CANCELLED");
            }
        });
    }

    public void sendMessage(View view){
        EditText myEditText = findViewById(R.id.editText2);

        myDatabase.setValue(myEditText.getText());
        myEditText.setText("");
    }
}
