package com.justcan.library.dialog.indicator;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

/**
 * Created by Jack on 2015/10/19.
 */
public class BallScaleIndicator extends BaseIndicatorController {

    float scale=1;
    int alpha=255;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing=4;
        paint.setAlpha(alpha);
        canvas.scale(scale,scale,getWidth()/2,getHeight()/2);
        paint.setAlpha(alpha);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-circleSpacing,paint);
    }

    @Override
    public void createAnimation() {
        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0,1);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        scaleAnim.start();

        ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 0);
        alphaAnim.setInterpolator(new LinearInterpolator());
        alphaAnim.setDuration(1000);
        alphaAnim.setRepeatCount(-1);
        alphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alpha = (Integer) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        alphaAnim.start();
    }


}
