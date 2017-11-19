package androidapp.com.sapplication.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import androidapp.com.sapplication.R;
import androidapp.com.sapplication.SplashScreen;

/**
 * Created by Amaresh on 11/1/17.
 */

public class Constants {
    public static String ONLINEURL="http://applicationworld.net/beautician/";
    public static String CATEGORYLIST="categories/index.json";
    public static String SUB_CATEGORYLIST="sub-categories/index.json";
    public static String USER_REGISTRATION="users/add.json";
    public static String SHOP_REGISTRATION="shops/add.json";
    public static String LOGIN_USER="users/loginCheck.json";
    public static String LOGIN_SHOP="shops/loginCheck.json";
    public static String SUBCATEGORY_PRICE="ShopDetails/shopPriceList";
    public static String PRICE_LIST="ShopDetails/shopPriceListNew.json";
    public static String PRICE_SET="ShopDetails/add.json";
    public static String SERVICE_REQUEST="service-requests/add.json";


    public static final String SHAREDPREFERENCE_KEY = "beautician" ;
    public static final String USER_NAME = "username" ;
    public static final String USER_ID = "userid" ;
    public static final String USER_TYPE = "user_type" ;


    public static void noInternetDialouge(Context _context,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setTitle("Oops !");
        builder.setMessage(Message);
        ImageView showImage = new ImageView(_context);
        Resources res = _context.getResources();
        Drawable drawable = res.getDrawable(R.mipmap.ic_warning_black_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, _context.getResources().getColor(R.color.colorPrimaryDark));
        showImage.setImageDrawable(drawable);
        builder.setView(showImage);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
                //finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
