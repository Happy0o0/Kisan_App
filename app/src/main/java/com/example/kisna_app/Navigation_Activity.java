package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Navigation_Activity extends AppCompatActivity {
    private ConstraintLayout logout,buy;
    private TextView name;
    private ImageView prof_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        logout = findViewById(R.id.nav_logout);
        name = findViewById(R.id.nav_username);
        prof_img = findViewById(R.id.nav_profile_img);
        buy = findViewById(R.id.nav_buy_seeds);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        DatabaseReference df = FirebaseDatabase.getInstance().getReference();
        df.child("User").child(id).child("User_Details").child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nm = snapshot.getValue(String.class);
                name.setText(nm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Navigation_Activity.this, "Logged Out Syccessfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Navigation_Activity.this,User_LoginActivity.class));
                finish();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigation_Activity.this,BuySeedsActivity.class));
            }
        });
        prof_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigation_Activity.this,user_profile_farmer.class));
            }
        });

    }

    public void onClick_goto_buy_dashboard(View view)
    {
        Intent lgn = new Intent(Navigation_Activity.this,BuySeedsActivity.class);
        startActivity(lgn);
    }

    public void onClick_goto_sell_dashboard(View view)
    {
        Intent lgn1 = new Intent(Navigation_Activity.this,DashboardSellActivity.class);
        startActivity(lgn1);
    }

    public void onClick_goto_my_orders(View view)
    {
        Intent lgn2 = new Intent(Navigation_Activity.this,OrderStatusActivity.class);
        startActivity(lgn2);
    }


}