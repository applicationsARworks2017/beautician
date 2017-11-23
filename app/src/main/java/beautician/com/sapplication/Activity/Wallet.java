package beautician.com.sapplication.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import beautician.com.sapplication.R;

public class Wallet extends AppCompatActivity {
    TextView tv_addMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        tv_addMoney=(TextView)findViewById(R.id.tv_addMoney);
        tv_addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Wallet.this);
                dialog.setContentView(R.layout.dialog_add_money);
                ImageView imageView=(ImageView)dialog.findViewById(R.id.close);
                Button add_money=(Button)dialog.findViewById(R.id.add_money);
                final EditText et_add_money=(EditText) dialog.findViewById(R.id.et_add_money);
                dialog.show();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });
    }
}
