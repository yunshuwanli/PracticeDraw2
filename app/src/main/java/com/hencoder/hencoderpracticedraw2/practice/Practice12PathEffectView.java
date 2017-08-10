package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    //两个色彩优化：
    // paint.setDither(true); 开启抖动
    // paint.setFilterBitmap(true);  开启双线性过滤
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(new CornerPathEffect(20));//拐角设置成圆角
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(new DiscretePathEffect(30, 10));//随机的偏离 划线颤抖
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{2f, 5f, 10f}, 0); //虚线 数组必须是偶数个
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        Path path2 = new Path();
        path2.lineTo(0, 50);
        path2.lineTo(50, 50);
        path2.close();
        paint.setPathEffect(new PathDashPathEffect(path2, 40, 0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        DashPathEffect dashPathEffect1 = new DashPathEffect(new float[]{20, 2, 7, 4}, 0);
        DiscretePathEffect discretePathEffect = new DiscretePathEffect(20, 10);
        SumPathEffect sumPathEffect = new SumPathEffect(dashPathEffect1, discretePathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        DashPathEffect dashPathEffect12 = new DashPathEffect(new float[]{20, 2, 7, 4}, 0);
        DiscretePathEffect discretePathEffect2 = new DiscretePathEffect(20, 10);
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect12, discretePathEffect2);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
