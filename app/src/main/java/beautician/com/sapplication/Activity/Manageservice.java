package beautician.com.sapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import beautician.com.sapplication.R;

public class Manageservice extends AppCompatActivity {
    LinearLayout lin_myservice,add_services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageservice);
        lin_myservice=(LinearLayout)findViewById(R.id.lin_myservice);
        add_services=(LinearLayout)findViewById(R.id.lin_addservices);

        add_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Manageservice.this,Categories.class);
                intent.putExtra("PAGE","service_home");
                startActivity(intent);

            }
        });
    }
}
