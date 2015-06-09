package diogo.com.battleship_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Diogo on 09/06/2015.
 */
public class GridView extends View{

    private int numCols, numRows;
    private int cellWidth, cellHeight;
    private Paint paint = new Paint();
    private boolean[][] cellChecked;

    public GridView(Context context)
    {
        this(context, null);
    }

    public GridView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setNumCols(int numCols)
    {
        this.numCols = numCols;
        calculateDimensions();
    }

    public int getNumCols()
    {
        return numCols;
    }

    public void setNumRows(int numRows)
    {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows()
    {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions()
    {
        if (numCols == 0 || numRows == 0)
            return;

        cellWidth = getWidth() / numCols;
        cellHeight = getHeight() / numRows;

        cellChecked = new boolean[numCols][numRows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        if (numCols == 0 || numRows == 0)
            return;

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numCols; i++)
        {
            for (int j = 0; j < numRows; j++)
            {
                if (cellChecked[i][j])
                {
                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                }
            }
        }

        for (int i = 1; i < numCols; i++)
        {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, paint);
        }

        for (int i = 1; i < numRows; i++)
        {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return true;

        int column = (int)(event.getX() / cellWidth);
        int row = (int)(event.getY() / cellHeight);

        cellChecked[column][row] = !cellChecked[column][row];
        invalidate();

        return true;
    }
}
