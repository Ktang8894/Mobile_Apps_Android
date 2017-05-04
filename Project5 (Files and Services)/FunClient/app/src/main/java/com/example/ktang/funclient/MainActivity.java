//REFERENCES
//http://stackoverflow.com/questions/22984696/storing-array-list-object-in-sharedpreferences

package com.example.ktang.funclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ktang.funcommon.FunGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //FunGenerator instance
    private FunGenerator gen;

    //Service Connection
    private ServiceConnection serviceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            gen = FunGenerator.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public ArrayList<String> requests = new ArrayList<String>();

    //Request ArrayAdapter/ListView

    public void listUpdate(){
        //Save Shared Preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(requests);

        editor.putString("Requests", json);
        editor.commit();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, requests);
        ListView lv =(ListView)findViewById(R.id.requests);
        lv.setAdapter(arrayAdapter);
    }

    //Load Shared Preferences
    public void loadSP() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Requests", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        requests = gson.fromJson(json, type);
        listUpdate();
    }

    //Request
    public void requests(String request) {
        ListView lv =(ListView)findViewById(R.id.requests);
        requests.add(0, request);//Add to top
        listUpdate();
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requests.clear();
                listUpdate();
            }
        });
    }

    public void imgButtons() {

        //Button 1 (Image 1)
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ImageView img = (ImageView) findViewById(R.id.imageView);
                    img.setImageBitmap(gen.image(1));
                    requests("Image 1");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        //Button 2 (Image 2)
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ImageView img = (ImageView) findViewById(R.id.imageView);
                    img.setImageBitmap(gen.image(2));
                    requests("Image 2");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        //Button 3 (Image 3)
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ImageView img = (ImageView) findViewById(R.id.imageView);
                    img.setImageBitmap(gen.image(3));
                    requests("Image 3");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void clipButtons() {
        //Button 4 (Clip 1)
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Flag to play clip1
                    gen.playClip(1);
                    requests("Clip 1");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        //Button 5 (Clip 2)
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gen.playClip(2);
                    requests("Clip 2");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        //Button 6 (Clip 3)
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gen.playClip(3);
                    requests("Clip 3");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        //stopButton
        findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    requests("Stop");
                    gen.stop();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        //resumeButton
        findViewById(R.id.resumeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    requests("Resume");
                    gen.resume();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        //pauseButton
        findViewById(R.id.pauseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    requests("Resume");
                    gen.pause();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Explicit Intent only!!
        Intent intent = new Intent("com.example.ktang.service.AIDL");
        bindService(createExplicitFromImplicitIntent(this, intent), serviceCon, BIND_AUTO_CREATE);

        imgButtons();
        clipButtons();
        loadSP();
    }


    //Lazy way to get Explicit Intent from Implicit Intent - [Reference]
    public Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
