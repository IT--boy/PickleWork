package com.justcan.library.dialog.indicator;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Jack on 2015/10/16.
 */
public class BallClipRotateIndicator extends BaseIndicatorController {

    float scaleFloat=1,degrees;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        float circleSpacing=12;
        float x = (getWidth()) / 2;
        float y=(getHeight()) / 2;
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);
        RectF rectF=new RectF(-x+circleSpacing,-y+circleSpacing,0+x-circleSpacing,0+y-circleSpacing);
        canvas.drawArc(rectF, -45, 270, false, paint);
    }

    @Override
    public void createAnimation() {
        ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.6f,0.5f,1);
        scaleAnim.setDuration(750);
        scaleAnim.setRepeatCount(-1);
        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloat = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        scaleAnim.start();

        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360);
        rotateAnim.setDuration(750);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        rotateAnim.start();
    }

}
