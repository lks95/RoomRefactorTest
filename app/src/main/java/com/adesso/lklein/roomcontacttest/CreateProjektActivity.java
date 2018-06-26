package com.adesso.lklein.roomcontacttest;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.adesso.lklein.roomcontacttest.db.AppDatabase;
import com.adesso.lklein.roomcontacttest.db.RoomDAO;

public class CreateProjektActivity extends AppCompatActivity{

    private EditText mProjektEditText;
    private EditText mArbeiterEditText;

    private Button mSaveButton;
    private RoomDAO mRoomDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_projekt);

        mRoomDAO = Room.databaseBuilder(this, AppDatabase.class, "projekt")
                .allowMainThreadQueries()
                .build()
                .getRoomDAO();

        mProjektEditText = findViewById(R.id.projektedit);
    }
}
