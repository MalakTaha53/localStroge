package com.example.localstroge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
  private EditText edtname;
  private EditText edtTeam;
  private boolean save=false;
  public static final String NAME="NAME";
  public static final String TEAM="TEAM";
  private SharedPreferences prefs;//read
    private SharedPreferences.Editor editor;//write
  private Button btnsave;
    private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupShareadprefs();
        checkdata();
    }

    private void checkdata() {
        boolean f =prefs.getBoolean("FLAG",false);
        if(f) {
            String name = prefs.getString(NAME, "");
            String team = prefs.getString(TEAM, "");
            edtname.setText(name);
            edtTeam.setText(team);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!save){
            String name= edtname.getText().toString().trim();
            String team= edtTeam.getText().toString();
            editor.putString(NAME,name);
            editor.putString(TEAM,team);
            editor.putBoolean("FLAG",flag);
            editor.commit();


        }
    }



    private void setupShareadprefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);//file
        editor=prefs.edit();
    }

    private void setupViews() {
        edtname=findViewById(R.id.name);
        edtTeam=findViewById(R.id.winingTeam);
        btnsave=findViewById(R.id.save);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               save=true;
            }
        });
    }
}