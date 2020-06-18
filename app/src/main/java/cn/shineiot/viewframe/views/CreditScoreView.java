package cn.shineiot.viewframe.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import cn.shineiot.viewframe.R;
import cn.shineiot.viewframe.utils.DpUtils;

/**
 * 芝麻信用分
 *
 * @author wangs
 * @date 2017/6/24
 */

public class CreditScoreView extends View {
	//数据个数
	private int dataCount = 5;
	//每个角的弧度
	private float radian = (float) (Math.PI * 2 / dataCount);
	//雷达图半径
	private float radius;
	//中心X坐标
	private int centerX;
	//中心Y坐标
	private int centerY;
	//各维度标题
	private String[] titles = {"履约能力", "信用历史", "人脉关系", "行为偏好", "身份特质"};
	//各维度图标
	private int[] icons = {R.drawable.ic_performance, R.drawable.ic_history, R.drawable.ic_contacts,
			R.drawable.ic_predilection, R.drawable.ic_identity};
	//各维度分值
	private float[] data = {130, 150, 160, 120, 150};
	//数据最大值
	private float maxValue = 190;
	//雷达图与标题的间距
	private int radarMargin = DpUtils.dpToPx(getContext(), 15);
	//DensityUtils.dp2px(getContext(), 15);
	//雷达区画笔
	private Paint mainPaint;
	//数据区画笔
	private Paint valuePaint;
	//分数画笔
	private Paint scorePaint;
	//标题画笔
	private Paint titlePaint;
	//图标画笔
	private Paint iconPaint;
	//分数大小
	private int scoreSize = DpUtils.dpToPx(getContext(), 28);
	//标题文字大小
	private int titleSize = DpUtils.dpToPx(getContext(), 13);

	public CreditScoreView(Context context) {
		this(context, null);
	}

	public CreditScoreView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CreditScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {

		mainPaint = new Paint();
		mainPaint.setColor(Color.WHITE);
		mainPaint.setAntiAlias(true);
		mainPaint.setStrokeWidth(0.3f);
		mainPaint.setStyle(Paint.Style.STROKE);

		valuePaint = new Paint();
		valuePaint.setAntiAlias(true);
		valuePaint.setColor(Color.WHITE);
		valuePaint.setAlpha(120);
		valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

		scorePaint = new Paint();
		scorePaint.setAntiAlias(true);
		scorePaint.setColor(Color.WHITE);
		scorePaint.setTextSize(scoreSize);
		scorePaint.setStyle(Paint.Style.FILL);
		scorePaint.setTextAlign(Paint.Align.CENTER);

		titlePaint = new Paint();
		titlePaint.setAntiAlias(true);
		titlePaint.setTextSize(titleSize);
		titlePaint.setColor(Color.WHITE);
		titlePaint.setStyle(Paint.Style.FILL);

		iconPaint = new Paint();
		iconPaint.setAntiAlias(true);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//雷达图半径(取宽或高的最小值的1/4作为半径画圆)
		radius = Math.min(w, h) / 2 * 0.5f;
		centerX = w / 2;
		centerY = h / 2;
		postInvalidate();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawPolygon(canvas);
		drawLine(canvas);
		drawRegion(canvas);//画覆盖图
		drawScore(canvas);
		drawTitle(canvas);
		drawableIcon(canvas);

	}

	/**
	 * 绘制多边形
	 */
	private void drawPolygon(Canvas canvas) {
		Path path = new Path();
		for (int i = 0; i < dataCount; i++) {
			if (i == 0) {
				path.moveTo(getPoint(i).x, getPoint(i).y);
			} else {
				path.lineTo(getPoint(i).x, getPoint(i).y);
			}
		}
		path.close();
		canvas.drawPath(path, mainPaint);
	}

	/**
	 * 绘制中心点的连接线
	 */
	private void drawLine(Canvas canvas) {
		Path path = new Path();
		for (int i = 0; i < dataCount; i++) {
			path.reset();
			path.moveTo(centerX, centerY);
			path.lineTo(getPoint(i).x, getPoint(i).y);
			canvas.drawPath(path, mainPaint);
		}
	}

	/**
	 * 绘制覆盖图
	 */
	private void drawRegion(Canvas canvas) {
		Path path = new Path();

		for (int i = 0; i < dataCount; i++) {
			//计算百分比
			float percentage = data[i] / maxValue;
			float x = getPoint(i, 0, percentage).x;
			float y = getPoint(i, 0, percentage).y;
			if (i == 0) {
				path.moveTo(x, y);
			} else {
				path.lineTo(x, y);
			}
		}
		//绘制填充区域的边界
		path.close();
		valuePaint.setStyle(Paint.Style.STROKE);
		canvas.drawPath(path, valuePaint);

		//绘制填充区域的填充物
		valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawPath(path, valuePaint);
	}

	/**
	 * 绘制分数
	 */
	private void drawScore(Canvas canvas) {
		int score = 0;
		for (int i = 0; i < data.length; i++) {
			score += data[i];
		}
		canvas.drawText(String.valueOf(score), centerX, centerY + scoreSize / 2, scorePaint);
	}

	/**
	 * 绘制title
	 */
	private void drawTitle(Canvas canvas) {
		for (int i = 0; i < dataCount; i++) {
			//获取点坐标
			float x = getPoint(i, radarMargin, 1).x;
			float y = getPoint(i, radarMargin, 1).y;

			String title = titles[i];
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), icons[i]);
			int iconHeight = bitmap.getHeight();
			float titleWidth = titlePaint.measureText(titles[i]);//测量长度

			//底下两个角的坐标需要向下移动半个图片的位置（1、2）
			if (i == 1) {
				y = y + iconHeight / 2;
			} else if (i == 2) {
				y = y + iconHeight / 2;
				x = x - titleWidth ;
			} else if (i == 3) {
				x = x - titleWidth;
			} else if (i == 4) {
				x = x - titleWidth / 2;
			}
			canvas.drawText(titles[i],x,y,titlePaint);
		}
	}
	/**
	 * 绘制图标
	 */
	private void drawableIcon(Canvas canvas){
		for (int i = 0; i < dataCount ; i++) {
			float x = getPoint(i,radarMargin,1).x;
			float y = getPoint(i,radarMargin,1).y;

			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),icons[i]);
			float iconWidth = bitmap.getWidth();
			float iconHeight = bitmap.getHeight();
			float titleHeight  = getTextHeight(titlePaint);
			float titleWidth = titlePaint.measureText(titles[i]);

			//上面获取到的x、y坐标是标题左下角的坐标
			//需要将图标移动到标题上方居中位置
			if(i == 0){
				x += (titleWidth-iconWidth)/2;
				y -= (titleHeight+iconHeight) ;
			}else if(i == 1){
				x += (titleWidth-iconWidth)/2;
				y -= titleHeight + iconHeight/2;
			}else if(i == 2){
				x -= titleWidth+ (titleWidth - iconWidth)/2;
				y -= titleHeight+iconHeight/2 ;
			}else if(i == 3){
				x -= titleWidth + (titleWidth - iconWidth)/2;
				y -= (titleHeight+iconHeight);
			}else if(i == 4){
				x -= titleWidth/2;
				y -= titleHeight + iconHeight;
			}
			canvas.drawBitmap(bitmap,x,y,iconPaint);
		}
	}

	/**
	 * 获取文本的高度
	 *
	 * @param paint 文本绘制的画笔
	 * @return 文本高度
	 */
	private int getTextHeight(Paint paint) {
		Paint.FontMetrics fontMetrics = paint.getFontMetrics();
		return (int) (fontMetrics.descent - fontMetrics.ascent);
	}

	/**
	 * 获取雷达图上各个点的坐标
	 *
	 * @param position 坐标位置（右上角为0，顺时针递增）
	 * @return 坐标
	 */
	private Point getPoint(int position) {
		return getPoint(position, 0, 1);
	}

	/**
	 * 获取雷达图上各个点的坐标（包括维度标题与图标的坐标）
	 * 需要计算正弦和余弦
	 *
	 * @param position    坐标位置
	 * @param radarMargin 雷达图与维度标题的间距
	 * @param percent     覆盖区的的百分比
	 * @return 坐标
	 */
	private Point getPoint(int position, int radarMargin, float percent) {
		int x = 0;
		int y = 0;

		if (position == 0) {
			x = (int) (centerX + (radius + radarMargin) * Math.sin(radian) * percent);
			y = (int) (centerY - (radius + radarMargin) * Math.cos(radian) * percent);

		} else if (position == 1) {
			x = (int) (centerX + (radius + radarMargin) * Math.sin(radian / 2) * percent);
			y = (int) (centerY + (radius + radarMargin) * Math.cos(radian / 2) * percent);

		} else if (position == 2) {
			x = (int) (centerX - (radius + radarMargin) * Math.sin(radian / 2) * percent);
			y = (int) (centerY + (radius + radarMargin) * Math.cos(radian / 2) * percent);

		} else if (position == 3) {
			x = (int) (centerX - (radius + radarMargin) * Math.sin(radian) * percent);
			y = (int) (centerY - (radius + radarMargin) * Math.cos(radian) * percent);

		} else if (position == 4) {
			x = centerX;
			y = (int) (centerY - (radius + radarMargin) * percent);
		}

		return new Point(x, y);
	}
}
