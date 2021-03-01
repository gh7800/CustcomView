package cn.shineiot.viewframe.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author GF63
 */
public class ICanvas extends View {
    private Paint paint;//圆
    private Paint paintText;//文字
    private Paint paintPoint;//点
    private Paint paintRect;//矩形
    private Paint paintArc;//扇形
    private Paint paintLine;//画线


    public ICanvas(Context context) {
        this(context,null);
    }

    public ICanvas(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ICanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(22);

        paintPoint = new Paint();
        paintPoint.setColor(Color.RED);

        paintRect = new Paint();
        paintRect.setColor(Color.BLUE);
        paintRect.setStrokeWidth(6);
        paintRect.setStyle(Paint.Style.STROKE);
        paintRect.setAntiAlias(true);

        paintArc = new Paint();
        paintArc.setColor(Color.GREEN);
        paintArc.setAntiAlias(true);
        paintArc.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(6f);
        paintLine.setColor(Color.RED);

    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(R.color.f2);

        canvas.drawCircle(200,200,100,paint);
        canvas.drawText("中心",200-22,200,paintText);
        canvas.drawPoint(200,200,paintPoint);

        RectF rect = new RectF();
        rect.set(100,100,300,300);
        //canvas.drawRect(rect,paintRect);
        canvas.drawRoundRect(rect,10,10,paintRect);//矩形圆角

        canvas.drawOval(100,150,300,250,paintRect);

        canvas.drawArc(rect,240,60,true,paintArc);
        canvas.drawArc(rect,60,120,false,paintArc);

        Path pathLine = new Path();
        pathLine.lineTo(300,300);
        //pathLine.rLineTo(100,0);
        //pathLine.arcTo(300,300,500,500,-90,90,true);
        pathLine.arcTo(300,300,500,500,-90,90,false);
        canvas.drawPath(pathLine,paintLine);

        Path pathLineClose = new Path();
        pathLineClose.moveTo(400,100);
        pathLineClose.lineTo(500,100);
        pathLineClose.lineTo(450,150);
        pathLineClose.close();
        paintLine.setStyle(Paint.Style.FILL);
        canvas.drawPath(pathLineClose,paintLine);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
