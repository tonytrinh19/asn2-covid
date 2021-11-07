package com.bcit.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class HomePageActivity extends AppCompatActivity {

    int maleCount;
    int femaleCount;
    int unknown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button monthYear = findViewById(R.id.mon_year_but);
        Button healthAuthority = findViewById(R.id.health_auth_button);
        Button gender = findViewById(R.id.gender_button);
        Button ageGroup = findViewById(R.id.age_group_button);

        monthYear.setOnClickListener(myv -> {
            Intent monthIntent = new Intent(this, CasesMonthYear.class);
//            monthIntent.putStringArrayListExtra("db_result")
            startActivity(monthIntent);
        });

        healthAuthority.setOnClickListener(hav -> {
            Intent healthAuthIntent = new Intent(this, CasesHealthAuthority.class);
//            monthIntent.putStringArrayListExtra("db_result")
            startActivity(healthAuthIntent);
        });

        gender.setOnClickListener(myv -> {
            Intent genderIntent = new Intent(this, CasesGender.class);
//            monthIntent.putStringArrayListExtra("db_result")
            startActivity(genderIntent);
        });

        ageGroup.setOnClickListener(myv -> {
            Intent ageIntent = new Intent(this, CasesAgeGroup.class);
//            monthIntent.putStringArrayListExtra("db_result")
            startActivity(ageIntent);
        });

        Query q = FirebaseDatabase.getInstance().getReference();
        q.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    if (Objects.equals(snapshot.child("Sex").getValue(String.class), "M")) {
                        maleCount++;
                    } else if (Objects.equals(snapshot.child("Sex").getValue(String.class), "F")) {
                        femaleCount++;
                    } else {
                        unknown++;
                    }

//                    snapshot.child("Age_Group").toString();
//                    snapshot.child("Classification_Reported").toString();
//                    snapshot.child("HA").toString();
//                    snapshot.child("Reported_Date").toString();
//                    snapshot.child("Sex").toString();
//                    patientList.add(p);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}