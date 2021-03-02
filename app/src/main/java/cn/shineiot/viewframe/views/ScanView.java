package cn.shineiot.viewframe.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author GF63
 */
public class ScanView extends View {

    private Paint mCirclePaint;
    private Paint mSweepPaint;
    private int mSize = 800;
    private int mWidth;
    private int mHeight;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mRadius;
    private Matrix mMatrix;
    private int mStart = 0;
    private ScanThread mScanThread;
    private Shader mShader;

    public ScanView(Context context) {
        super(context);
        init();
    }

    public ScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);

        //暗绿色的画笔
        mSweepPaint = new Paint();
        mSweepPaint.setColor(0x9D00ff00);
        mSweepPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        //获取最短的边作为直径
        mSize = (mWidth >= mHeight) ? mHeight : mWidth;
        mRadius = mSize / 2;
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;

        mShader = new SweepGradient(mSize / 2, mSize / 2, Color.TRANSPARENT, Color.RED);
        mSweepPaint.setShader(mShader);
        Log.e("jielong","mSize ="+mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        mCirclePaint.setColor(0xffB8DCFC);
        canvas.drawCircle(mSize/2, mSize/2, mRadius-20, mCirclePaint);
        //画圆
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mSize/2, mSize/2, mRadius/3+40 , mCirclePaint);
        canvas.drawCircle(mSize/2, mSize/2, mRadius/2+80, mCirclePaint);
        canvas.drawCircle(mSize/2, mSize/2, mRadius-20, mCirclePaint);
        //画十字线
        //竖线
        canvas.drawLine(mSize/2, 20, mSize/2, mSize-20, mCirclePaint);
        //横线
        canvas.drawLine(20, mSize/2, mSize-20, mSize/2, mCirclePaint);

        canvas.concat(mMatrix);
        canvas.drawCircle(mSize / 2, mSize / 2, mSize/2-20, mSweepPaint);
        super.onDraw(canvas);

    }

    public void start() {
        mScanThread = new ScanThread(this);
        mScanThread.start();
    }

    protected class ScanThread extends Thread {

        private ScanView mView;

        public ScanThread(ScanView view) {
            this.mView = view;
        }

        @Override
        public void run() {
            while (true) {
                mView.post(new Runnable() {
                    @Override
                    public void run() {
                        mStart ++;
                        mMatrix = new Matrix();
                        mMatrix.preRotate(mStart, mSize/2, mSize/2);
                        mView.invalidate();
                    }
                });
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
