package com.gilvanstudios.redthebat;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class SmokePuff extends com.gilvanstudios.redthebat.GameObject {

    private int radius;

    public SmokePuff(int x, int y) {
        this.radius = 3;
        super.x = x;
        super.y = y;
    }

    public void update() {
        x -= 10;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(x - radius, y - radius, radius, paint);
        canvas.drawCircle(x - radius + 2, y - radius - 2, radius, paint);
        canvas.drawCircle(x - radius + 4, y - radius + 1, radius, paint);
    }

}
