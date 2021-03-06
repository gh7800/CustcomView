package cn.shineiot.viewframe.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.shineiot.viewframe.R;

/**
 * @author wangs
 * 封装一个简单布局，左边字段右边内容,还可以设置左边图片和右边箭头
 */
public class TitleRelativelayout extends RelativeLayout {
    private ImageView img_left;
    private ImageView img_right;
    private TextView tv_title;
    private TextView tv_content;
    private RelativeLayout mRelativeLayout;
    private int imgLeft;
    private int imgRight;

    private String title;
    private String contentText;

    public TitleRelativelayout(Context context) {
        this(context, null);
    }

    public TitleRelativelayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View layout = LayoutInflater.from(context).inflate(R.layout.relativelayout_title, this);
        mRelativeLayout = layout.findViewById(R.id.tr_rl);
        img_left = layout.findViewById(R.id.tr_img_left);
        img_right = layout.findViewById(R.id.tr_img_right);
        tv_title = layout.findViewById(R.id.tr_tv_title);
        tv_content = layout.findViewById(R.id.tr_tv_content);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleRelativelayout, defStyleAttr, 0);
        title = typedArray.getString(R.styleable.TitleRelativelayout_title);
        contentText = typedArray.getString(R.styleable.TitleRelativelayout_contentText);
        imgLeft = typedArray.getResourceId(R.styleable.TitleRelativelayout_imgLeft, 0);
        imgRight = typedArray.getResourceId(R.styleable.TitleRelativelayout_imgRight, 0);
        typedArray.recycle();

        img_left.setBackgroundResource(imgLeft);
        img_right.setBackgroundResource(imgRight);
    }


    /**
     * 设置左边的图片
     */
    public void setImg_left(int res) {
        img_left.setBackgroundResource(res);
    }

    /**
     * 设置右边的图片
     */
    public void setImg_right(int res) {
        img_right.setBackgroundResource(res);
    }

    /**
     * 设置title
     */
    public void setTv_title(String title) {
        tv_title.setText(title);
    }

    /**
     * 设置content
     */
    public void setTv_content(String content) {
        tv_content.setText(content);
    }

    /**
     * 给layout添加了监听事件
     */
    public void setLayout_Onclick(OnClickListener listener) {
        mRelativeLayout.setOnClickListener(listener);
    }

}
