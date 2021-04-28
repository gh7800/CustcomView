package cn.shineiot.viewframe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import cn.shineiot.viewframe.views.MdStyleProgress;
import cn.shineiot.viewframe.views.ScanView;
import cn.shineiot.viewframe.views.TitleRelativeLayout;

/**
 * @author wangs
 * 该项目主要收集自定义视图/动画
 */
public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanView scanView = findViewById(R.id.scanView);
        scanView.start();

        loadTitleRelativeLayout();

        final MdStyleProgress circleView = findViewById(R.id.circleView);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                circleView.setStatus(MdStyleProgress.Status.LoadSuccess);
            }
        },3000);
    }

    /**
     * 加载titleRelativeLayout
     */
    private void loadTitleRelativeLayout() {
        TitleRelativeLayout titleRelativelayout = findViewById(R.id.titlerl);
        titleRelativelayout.setVisibility(View.VISIBLE);

        //titleRelativelayout.setTv_title("title");
        //titleRelativelayout.setTv_content("content");

        titleRelativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tag", "点击了amTitleRelativelayoutda");
            }
        });

    }
}
