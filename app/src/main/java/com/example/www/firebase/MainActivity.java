package com.example.www.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView txtCond;
    Button btnSunny,btnFoggy;

    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef=mRootRef.child("condition");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCond=(TextView)findViewById(R.id.txtCond);
        btnSunny=(Button)findViewById(R.id.btnSunny);
        btnFoggy=(Button)findViewById(R.id.btnFoggy);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text=dataSnapshot.getValue(String.class);
                txtCond.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConditionRef.setValue("Foggy");
            }
        });

        btnSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConditionRef.setValue("Sunny");
            }
        });
    }
}
