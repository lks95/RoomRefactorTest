package com.adesso.lklein.roomcontacttest;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.adesso.lklein.roomcontacttest.db.AppDatabase;
import com.adesso.lklein.roomcontacttest.db.RoomDAO;
import com.adesso.lklein.roomcontacttest.models.Projekt;

public class UpdateProjektActivity extends AppCompatActivity {

    public static String EXTRA_PROJEKT_ID = "projekt_id";

    private TextView mCreatedTimeTextView;
    private EditText mProjektEditText;
    private EditText mArbeiterEditText;

    private Button mUpdateButton;
    private Toolbar mToolbar;

    private RoomDAO mRoomDAO;
    private Projekt PROJEKT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_projekt);

        mRoomDAO = Room.databaseBuilder(this, AppDatabase.class, "projekt" )
                .allowMainThreadQueries()
                .build()
                .getRoomDAO();

        //set layout ressource files for TextView EditText etc.

        mCreatedTimeTextView = findViewById(R.id.createdTimeTextView);
        mProjektEditText = findViewById(R.id.ProjektEditText);
        mArbeiterEditText = findViewById(R.id.ArbeiterEditText);
        mUpdateButton = findViewById(R.id.updateButton);
        mToolbar = findViewById(R.id.toolbar);

        PROJEKT = mRoomDAO.getProjektWithId(getIntent().getStringExtra(EXTRA_PROJEKT_ID));

        initViews();

    }

    private void initViews(){
        setSupportActionBar(mToolbar);

        mCreatedTimeTextView.setText(PROJEKT.getCreatedDate().toString());
        mProjektEditText.setText(PROJEKT.getProjektname());
        mArbeiterEditText.setText(PROJEKT.getArbeitername());

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projektname = mProjektEditText.getText().toString();
                String arbeitername = mArbeiterEditText.getText().toString();

                if(projektname.length() == 0 || arbeitername.length() == 0){
                    Toast.makeText(UpdateProjektActivity.this, "Nothing typed in", Toast.LENGTH_SHORT).show();
                    return;
                }

                PROJEKT.setArbeitername(arbeitername);
                PROJEKT.setProjektname(projektname);

                //insert into database
                mRoomDAO.update(PROJEKT);
                setResult(RESULT_OK);
                finish();



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete: {
                mRoomDAO.delete(PROJEKT);
                setResult(RESULT_OK);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
