package cn.shineiot.viewframe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cn.shineiot.viewframe.views.ScanView;
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

        ScanView scanView = findViewById(R.id.scanView);
        scanView.start();

        loadTitleRelativelayout();
    }

    /**
     * 加载titleRelativeLayout
     */
    private void loadTitleRelativelayout() {
        TitleRelativelayout titleRelativelayout = findViewById(R.id.titlerl);
        titleRelativelayout.setVisibility(View.VISIBLE);

        titleRelativelayout.setTv_title("title");
        titleRelativelayout.setTv_content("content");

        titleRelativelayout.setLayout_Onclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tag", "点击了amTitleRelativelayoutda");
            }
        });

    }
}
