package com.example.ktang.funcenter;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import com.example.ktang.funcommon.FunGenerator;

public class MyService extends Service {

    private MyImpl impl = new MyImpl();

    @Override
    public IBinder onBind(Intent intent) {
        return impl;
    }

    //Music Clips
    private MediaPlayer mPlayer;
    private int mStartID;
    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.clip1);

        if (null != mPlayer) {
            mPlayer.setLooping(false);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSelf(mStartID);
                }
            });
        }

        // Create a notification area notification so the user
        // can get back to the MusicServiceClient

        final Intent notificationIntent = new Intent(getApplicationContext(),
                MyService.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        final Notification notification = new Notification.Builder(
                getApplicationContext())
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("Music Playing")
                .setContentText("Click to Access Music Player")
                .setContentIntent(pendingIntent).build();

        // Put this Service in a foreground state, so it won't
        // readily be killed by the system
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }

        if (intent.getIntExtra("clipNum", 0) == 1) {
            mPlayer = MediaPlayer.create(this, R.raw.clip1);
        }
        else if (intent.getIntExtra("clipNum", 0) == 2) {
            mPlayer = MediaPlayer.create(this, R.raw.clip2);
        }
        else if (intent.getIntExtra("clipNum", 0) == 3) {
            mPlayer = MediaPlayer.create(this, R.raw.clip3);
        }
        else if (intent.getIntExtra("clipNum", 0) == 3) {
            mPlayer = MediaPlayer.create(this, R.raw.clip3);
        }

        if (null != mPlayer) {
            mStartID = startid;

            if (mPlayer.isPlaying()) {
                mPlayer.seekTo(0);
            }
            else {
                mPlayer.start();
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (null != mPlayer) {
            mPlayer.stop();
            mPlayer.release();
        }
    }
    //End Music Clips section

    public class MyImpl extends FunGenerator.Stub {

        //Return bitmap
        @Override
        public Bitmap image(int imgNum) throws RemoteException {
            if (imgNum == 1)
                return BitmapFactory.decodeResource(getResources(), R.drawable.image1);
            else if (imgNum == 2)
                return BitmapFactory.decodeResource(getResources(), R.drawable.image2);
            else if (imgNum == 3)
                return BitmapFactory.decodeResource(getResources(), R.drawable.image3);
            else
                return null;
        }

        //final Intent musicServiceIntent = new Intent(getApplicationContext(), MyService.class);

        @Override
        public void playClip(int clipNum) throws RemoteException {
            final Intent musicServiceIntent = new Intent(getApplicationContext(), MyService.class);
            musicServiceIntent.putExtra("clipNum", clipNum);
            startService(musicServiceIntent);
        }

        int length = 0;

        @Override
        public void pause() throws RemoteException {
            if (null != mPlayer) {
                if(mPlayer.isPlaying()) {
                    length = mPlayer.getCurrentPosition();
                    mPlayer.pause();
                }
            }
        }

        @Override
        public void resume() throws RemoteException {
            if (null != mPlayer) {
                if(!mPlayer.isPlaying() && length > 0) {
                    mPlayer.seekTo(length);
                    mPlayer.start();
                }
            }
        }

        @Override
        public void stop() throws RemoteException {
            if (null != mPlayer) {
                mPlayer.stop();
            }
        }
    }
}
