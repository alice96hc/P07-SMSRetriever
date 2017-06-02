package sg.edu.rp.c347.p07_smsretriever;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.fragment;
import static sg.edu.rp.c347.p07_smsretriever.R.id.btnRetrieve;
import static sg.edu.rp.c347.p07_smsretriever.R.id.textViewSMS;
import static sg.edu.rp.c347.p07_smsretriever.R.id.tvSMS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        setContentView(R.layout.activity_main);

        Fragment f1 = new FragmentFirst();
        ft.replace(R.id.frame1, f1);

        Fragment f2 = new FragmentSecond();
        ft.replace(R.id.frame2, f2);

        ft.commit();

        int permissionCheck = PermissionChecker.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS);
        if (permissionCheck != PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, 0);
            //stop the action from proceeding further as permission not granted yet
            return;
        }
    }
}
