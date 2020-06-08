package cn.shineiot.viewframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.shineiot.viewframe.views.TitleRelativelayout;

/**
 * @author wangs
 * 该项目主要收集自定义视图/动画
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadTitleRelativelayout();
    }

    /**
     * 加载titleRelativeLayout
     */
    private void loadTitleRelativelayout() {
        TitleRelativelayout titleRelativelayout = findViewById(R.id.titlerl);
        TitleRelativelayout titleRelativelayout1 = findViewById(R.id.titlerl1);
        titleRelativelayout.setVisibility(View.VISIBLE);
//        titleRelativelayout.setImg_left(R.mipmap.ic_launcher);
//        titleRelativelayout.setImg_right(R.drawable.icon_right_jiantou);
        titleRelativelayout.setTv_title("title");
        titleRelativelayout.setTv_content("content");

        titleRelativelayout.setLayout_Onclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tag", "点击了amTitleRelativelayoutda");
            }
        });

        titleRelativelayout1.setLayout_Onclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tag", "点击了--amTitleRelativelayoutda");
            }
        });
    }
}
