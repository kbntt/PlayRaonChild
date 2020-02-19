package es.kr.playraonchild;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ///////////////////////////////////////

    public static final String EXTRA_PHONE_NUMBER = "es.kr.playraonchild.phone_number";
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        int permssionCamera = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE);

        if (permssionCamera!= PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_PHONE_STATE)) {
                Toast.makeText(this,"카메라 권한이 필요합니다.",Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,  new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(this,"카메라 권한이 필요합니다.",Toast.LENGTH_LONG).show();

            }
        }

        Bundle bundle = new Bundle();
        bundle.putString("phoneNumber",getPhoneNumber(this));
        fragment.setArguments(bundle);
    }

    //현재 전화 번호 찾기
    public static String getPhoneNumber(Context context) {
        String telNumber = "";
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(mTelephonyManager != null){
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(context, "전화 권한이 허용되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }else if(mTelephonyManager.getSimState() == TelephonyManager.SIM_STATE_UNKNOWN
                    || mTelephonyManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT){
                Toast.makeText(context, "유심이 없거나, 알 수 없는 유심입니다.", Toast.LENGTH_SHORT).show();
            }else{
                telNumber = mTelephonyManager.getLine1Number();
                telNumber = telNumber.replace("+82", "0");
            }



        }
        return telNumber;
    }

}
