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

            if (intent.getAction() == "p3_basketball")
                Toast.makeText(context, "A2: Basketball Selected", Toast.LENGTH_SHORT).show();

            if (intent.getAction() == "p3_baseball")
                Toast.makeText(context, "A2: Baseball Selected", Toast.LENGTH_SHORT).show();

    }
}
