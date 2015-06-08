package diogo.com.battleship_game;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Diogo on 08/06/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public GamePanel (Context context){
        super(context);

        //in order to interrupt events with the touch action
        getHolder().addCallback(this);
    }
}
