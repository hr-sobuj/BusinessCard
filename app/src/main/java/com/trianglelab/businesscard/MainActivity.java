package com.trianglelab.businesscard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView myname,myweb,myphn,mymail;
    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        myname=findViewById(R.id.name);
        myweb=findViewById(R.id.web);
        myphn=findViewById(R.id.call);
        mymail=findViewById(R.id.mail);

        myname.setOnClickListener(this);
        myweb.setOnClickListener(this);
        myphn.setOnClickListener(this);
        mymail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==myname){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://facebook.com/habiburrahman.sobuj.75"));
            startActivity(intent);
        }
        if(v==myweb){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://sobujhstu.github.io/sobuj"));
            startActivity(intent);
        }
        if(v==myphn){
            makePhoneCall();
        }
        if(v==mymail){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","sobujhd@gmail.com", null));
            intent.putExtra(Intent.EXTRA_SUBJECT, "App Email");
            intent.putExtra(Intent.EXTRA_TEXT, "Hey!");
            startActivity(Intent.createChooser(intent, "Send email"));
        }




    }


    private void makePhoneCall() {
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

        }
        else{
            String dial = "tel:01797972527";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}