package com.example.permisosswitch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int PERMISSION_CORE = 1;

    Switch[] switches = new Switch[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//MI COMIT
        switches[0] = findViewById(R.id.almacenamientoExterno);
        switches[1] = findViewById(R.id.leerContactos);
        switches[2] = findViewById(R.id.Calendario);
        switches[3] = findViewById(R.id.Camera);
        switches[4] = findViewById(R.id.msjes);
        switches[0].setClickable(false);
        switches[1].setClickable(false);
        switches[2].setClickable(false);
        switches[3].setClickable(false);
        switches[4].setClickable(false);

        findViewById(R.id.btn_exit).setOnClickListener(this);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_SMS
        },PERMISSION_CORE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==PERMISSION_CORE)
        {
            for(int i = 0; i<permissions.length;i++) {
                String permission_info= permissions[i];
                if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    permission_info += "PERMISO ACTIVADO";
                    Toast.makeText(getBaseContext(),permission_info,Toast.LENGTH_LONG).show();
                    switches[i].setChecked(true);
                } else {
                    permission_info += "PERMISO DENEGADO";
                    switches[i].setChecked(false);
                    Toast.makeText(getBaseContext(),permission_info,Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_exit:
            {
                finishAffinity();
            }break;
        }
    }
}
