package androidapp.com.sapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import androidapp.com.sapplication.Fragment.Profile;
import androidapp.com.sapplication.R;

public class SPHome extends AppCompatActivity {
    CardView card_manageservice,card_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sphome);
        card_manageservice=(CardView)findViewById(R.id.card_manageservice);
        card_profile=(CardView)findViewById(R.id.card_profile);


        card_manageservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(SPHome.this,Categories.class);
                Intent intent=new Intent(SPHome.this,Manageservice.class);
              //  intent.putExtra("PAGE","service_home");
                startActivity(intent);
            }
        });

        card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SPHome.this,SPProfile.class);
               // intent.putExtra("PAGE","service_home");
                startActivity(intent);
            }
        });
    }
}
