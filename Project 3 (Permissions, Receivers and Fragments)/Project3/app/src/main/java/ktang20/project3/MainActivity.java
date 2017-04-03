package ktang20.project3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //Granted

                    Toast.makeText(this, "A1: ACCESS GRANTED", Toast.LENGTH_SHORT).show();
                    Intent basketball = new Intent();
                    basketball.setAction("p3_basketball");
                    basketball.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    int permissionCheck = ContextCompat.checkSelfPermission(this,
                            "edu.uic.cs478.project3");
                    sendOrderedBroadcast(basketball, null);

                } else { //Denied
                    Toast.makeText(this, "A1: ACCESS DENIED", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 2: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //Granted

                    Toast.makeText(this, "A1: ACCESS GRANTED", Toast.LENGTH_SHORT).show();
                    Intent baseball = new Intent();
                    baseball.setAction("p3_baseball");
                    baseball.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    int permissionCheck = ContextCompat.checkSelfPermission(this,
                            "edu.uic.cs478.project3");
                    sendOrderedBroadcast(baseball, null);

                } else { //Denied
                    Toast.makeText(this, "A1: ACCESS DENIED", Toast.LENGTH_SHORT).show();
                }
                return;
            }


            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public Button button1;
    public Button button2;

   // public void permissionRequest() {
   //     if (ActivityCompat.checkSelfPermission(this, "edu.uic.cs478.project3") != PackageManager.PERMISSION_GRANTED)
   //     ActivityCompat.requestPermissions(this, new String[]{permissions}, PackageManager.PERMISSION_GRANTED);
 //   }
    //Initialize Button 1 (Basketball)
    public void but1_init() {
        button1 = (Button)findViewById(R.id.button1); //Assign button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent basketball = new Intent();
                basketball.setAction("p3_basketball");
                basketball.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                int permissionCheck = ContextCompat.checkSelfPermission(v.getContext(),
                        "edu.uic.cs478.project3");
                int response = 1;
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{"edu.uic.cs478.project3"}, response);
                }
                //sendOrderedBroadcast(basketball, "edu.uic.cs478.project3");
                else
                    sendOrderedBroadcast(basketball, null);
            }
        });
    }

    //Initialize Button 2 (Baseball)
    public void but2_init() {
        button2 = (Button)findViewById(R.id.button2); //Assign button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent baseball = new Intent();
                baseball.setAction("p3_baseball");
                baseball.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                int permissionCheck = ContextCompat.checkSelfPermission(v.getContext(),
                        "edu.uic.cs478.project3");
                int response = 2;
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{"edu.uic.cs478.project3"}, response);
                }
                else
                    sendOrderedBroadcast(baseball, null);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but1_init();
        but2_init();
    }
}
