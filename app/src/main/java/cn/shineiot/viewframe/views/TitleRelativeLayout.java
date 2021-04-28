package cn.shineiot.viewframe.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import cn.shineiot.viewframe.R;

/**
 * @author wangs
 * 封装一个简单布局，左边字段右边内容,还可以设置左边图片和右边箭头
 */
public class TitleRelativeLayout extends ConstraintLayout {
    private ImageView img_left;
    private ImageView img_right;
    private TextView tv_title;
    private TextView tv_content;
    private RelativeLayout mRelativeLayout;
    private int imgLeft;
    private int imgRight;

    private String title;
    private String contentText;

    public TitleRelativeLayout(Context context) {
        this(context, null);
    }

    public TitleRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View layout = LayoutInflater.from(context).inflate(R.layout.relativelayout_title, this);
        mRelativeLayout = layout.findViewById(R.id.tr_rl);
        img_left = layout.findViewById(R.id.tr_img_left);
        img_right = layout.findViewById(R.id.tr_img_right);
        tv_title = layout.findViewById(R.id.tr_tv_title);
        tv_content = layout.findViewById(R.id.tr_tv_content);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleRelativeLayout, defStyleAttr, 0);
        title = typedArray.getString(R.styleable.TitleRelativeLayout_title);
        contentText = typedArray.getString(R.styleable.TitleRelativeLayout_contentText);
        imgLeft = typedArray.getResourceId(R.styleable.TitleRelativeLayout_imgLeft, 0);
        imgRight = typedArray.getResourceId(R.styleable.TitleRelativeLayout_imgRight, 0);
        typedArray.recycle();

        if(imgLeft != 0) {
            img_left.setBackgroundResource(imgLeft);
        }
        if(imgRight != 0) {
            img_right.setBackgroundResource(imgRight);
        }
        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        if(!TextUtils.isEmpty(contentText)){
            tv_content.setText(contentText);
        }
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

}
