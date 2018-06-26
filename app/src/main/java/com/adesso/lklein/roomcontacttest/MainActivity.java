package com.adesso.lklein.roomcontacttest;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.Update;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adesso.lklein.roomcontacttest.db.AppDatabase;
import com.adesso.lklein.roomcontacttest.db.RoomDAO;
import com.adesso.lklein.roomcontacttest.models.Projekt;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int CREATE_PROJEKT = 1;
    private static final int UPDATE_PROJEKT = 2;


    private RoomDAO mRoomDAO;
    private RecyclerView ProjekteRecyclerview;
    private ProjektRecyclerAdapter mProjektRecyclerAdapter;
    private FloatingActionButton mAddProjektFloatingActionButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoomDAO = Room.databaseBuilder(this, AppDatabase.class, "db-projekte")
                .allowMainThreadQueries()
                .build()
                .getRoomDAO();

        ProjekteRecyclerview = findViewById(R.id.ProjektRecyclerView);
        ProjekteRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAddProjektFloatingActionButton = findViewById(R.id.addProjektFloatingActionButton);

        int colors [] = {ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, android.R.color.holo_red_light),
                ContextCompat.getColor(this, android.R.color.holo_orange_light),
                ContextCompat.getColor(this, android.R.color.holo_green_light),
                ContextCompat.getColor(this, android.R.color.holo_blue_dark),
                ContextCompat.getColor(this, android.R.color.holo_purple)};

        mProjektRecyclerAdapter = new ProjektRecyclerAdapter(this, new ArrayList<Projekt>(), colors);
        mProjektRecyclerAdapter.addActionCallback(new ProjektRecyclerAdapter.ActionCallback() {
            @Override
            public void onLongClickListener(Projekt projekt) {
                Intent intent = new Intent (MainActivity.this, UpdateProjektActivity.class);
                /*intent.putExtra(UpdateProjektActivity.EXTRA_PROJEKT_ID, projekt*/
                startActivityForResult(intent, UPDATE_PROJEKT);
            }
        });

        ProjekteRecyclerview.setAdapter(mProjektRecyclerAdapter);

        mAddProjektFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateProjektActivity.class);
                startActivityForResult(intent, CREATE_PROJEKT);
            }
        });

        //methode loadprojekte noch nicht vorhanden
    }


    private void loadProjekte(){
        mProjektRecyclerAdapter.updateData(mRoomDAO.getProjektname());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE_PROJEKT && resultCode == RESULT_OK){
            loadProjekte();
        } else if (requestCode == UPDATE_PROJEKT && resultCode == RESULT_OK){
            loadProjekte();
        }
    }
}
