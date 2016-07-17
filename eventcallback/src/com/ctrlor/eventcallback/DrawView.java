package com.ctrlor.eventcallback;

import android.content.Context;
import android.view.View;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Color;

public class DrawView extends View {
    
    public float currentX = 40;
    public float currentY = 50;

    Paint p = new Paint();

    public DrawView(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p.setColor(Color.RED);
        canvas.drawCircle(currentX, currentY, 15, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.currentX = event.getX();
        this.currentY = event.getY();

        this.invalidate();
        return true;
    }
}
