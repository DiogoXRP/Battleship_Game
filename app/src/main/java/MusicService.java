import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Binder;
import android.os.IBinder;

import diogo.com.battleship_game.R;

/**
 * Created by Diogo on 08/06/2015.
 */
public class MusicService extends Service implements MediaPlayer.OnErrorListener {

    private final IBinder mBinder = new ServiceBinder();
    MediaPlayer mPlayer;
    private int length = 0;

    public MusicService {}

    public class ServiceBinder extends Binder {
        MusicService getService()
        {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent i) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mPlayer = MediaPlayer.create(this, R.raw.opening);
        mPlayer.setOnErrorListener(this);

        if (mPlayer != null) {
            mPlayer.setLooping(true);
            mPlayer.setVolume(100, 100);
        }

        mPlayer.setOnErrorListener(new OnErrorListener() {
            //@Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                onError(mPlayer, what, extra);
                return true;
            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer.start();
        return START_STICKY;
    }
}
