package androidapp.com.sapplication.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidapp.com.sapplication.R;

import static androidapp.com.sapplication.Utils.Constants.hasPermissions;

public class Login_Activity extends AppCompatActivity {
    TextView tv_signup,tv_forgotpassword;
    RadioGroup radio_user_type;
    RadioGroup user_type;
    LinearLayout lin_signin;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_signup=(TextView)findViewById(R.id.tv_signup);
        tv_forgotpassword=(TextView)findViewById(R.id.tv_forgotpassword);
        radio_user_type=(RadioGroup)findViewById(R.id.radio_user_type);
        lin_signin=(LinearLayout)findViewById(R.id.lin_signin);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // here it is checking whether the permission is granted previously or not
            if (!hasPermissions(this, PERMISSIONS)) {
                //Permission is granted
                ActivityCompat.requestPermissions(this, PERMISSIONS, 1);

            }
        }

/*
*
* btnDisplay.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {

		        // get selected radio button from radioGroup
			int selectedId = radioSexGroup.getCheckedRadioButtonId();

			// find the radiobutton by returned id
		        radioSexButton = (RadioButton) findViewById(selectedId);

			Toast.makeText(MyAndroidAppActivity.this,
				radioSexButton.getText(), Toast.LENGTH_SHORT).show();

		}

	});
*
* */
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        lin_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        tv_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,SPHome.class);
                startActivity(intent);
            }
        });
    }
}
