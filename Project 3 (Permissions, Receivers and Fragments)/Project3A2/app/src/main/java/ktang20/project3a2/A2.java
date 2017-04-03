package ktang20.project3a2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class A2 extends BroadcastReceiver {
    public A2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // permissionCheck = ContextCompat.checkSelfPermission(context, "edu.uic.cs478.project3");

   //     if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
   //         Toast.makeText(context, "ACCESS DENIED", Toast.LENGTH_SHORT).show();
      //  }
     //  else {
           // Toast.makeText(context, "ACCESS GRANTED", Toast.LENGTH_SHORT).show();
            //    int result = context.checkCallingPermission("edu.uic.cs478.project3");
            //   if (PackageManager.PERMISSION_GRANTED == result) {
            if (intent.getAction() == "p3_basketball")
                Toast.makeText(context, "A2: Basketball Selected", Toast.LENGTH_SHORT).show();

            if (intent.getAction() == "p3_baseball")
                Toast.makeText(context, "A2: Baseball Selected", Toast.LENGTH_SHORT).show();
            //   }
        //}

    }
}
