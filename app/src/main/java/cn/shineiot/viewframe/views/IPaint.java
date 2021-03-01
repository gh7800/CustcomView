package cn.shineiot.viewframe.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wangshuai
 */
public class IPaint extends View {
	private Paint mPaint;

	public IPaint(Context context) {
		this(context,null);
	}

	public IPaint(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public IPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

//		线性渐变
//		Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
//				Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);


//		辐射渐变
		@SuppressLint("DrawAllocation")
		Shader shader = new RadialGradient(300,300,200, Color.parseColor("#FFE91E63"),
				Color.parseColor("#FF2196F3"), Shader.TileMode.CLAMP);

//		扫描渐变
//		Shader shader = new SweepGradient(300,300, Color.parseColor("#E91E63"),
//				Color.parseColor("#2196F3"));



		mPaint.setShader(shader);
		canvas.drawCircle(300,300,200,mPaint);
	}
}
