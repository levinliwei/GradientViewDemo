package com.waynelee.wayneleetestproject.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.labels.LabelsView;
import com.waynelee.wayneleetestproject.R;
import com.waynelee.wayneleetestproject.glide.BlurTransformation;
import com.waynelee.wayneleetestproject.glide.GlideRoundTransform;
import com.waynelee.wayneleetestproject.model.TestBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * @author levin
 * @Summary MainActivity
 * @email levin.li@teamar.cn
 * @data 2018/7/12
 * @org Aurora Team
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_image_iv)
    ImageView mImg;
    @BindView(R.id.main_image_iv2)
    ImageView mImg2;
    @BindView(R.id.main_image_iv3)
    ImageView mImg3;
    @BindView(R.id.show_detail_tv)
    TextView mShowDetailTv;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mJZVideoPlayer;
    @BindView(R.id.labels)
    LabelsView labelsView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.get_detail_btn)
    void getDetailBtn() {
        startActivity(new Intent(this, DetailActivity.class));
    }

    @SuppressLint("CheckResult")
    private void initView() {
        //圆角
        Glide.with(this).load(getResources().getDrawable(R.drawable.lei))
                .apply(new RequestOptions().bitmapTransform(new GlideRoundTransform(this, 20)))
                .into(mImg);
        //高斯模糊
        Glide.with(this)
                .load(getResources().getDrawable(R.drawable.lei))
                .apply(new RequestOptions().bitmapTransform(new BlurTransformation(this, 20)))
                .into(mImg2);
        //圆形
        Glide.with(this)
                .load(getResources().getDrawable(R.drawable.lei))
                .apply(new RequestOptions().circleCrop())
                .into(mImg3);

        mJZVideoPlayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                "饺子闭眼睛");
//        mJZVideoPlayer.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));

        mJZVideoPlayer.startButton.performClick();

        //LabelsView可以设置任何类型的数据
        ArrayList<TestBean> testList = new ArrayList<>();
        testList.add(new TestBean("Android", 1));
        testList.add(new TestBean("IOS", 2));
        testList.add(new TestBean("前端", 3));
        testList.add(new TestBean("后台", 4));
        testList.add(new TestBean("微信开发", 5));
        testList.add(new TestBean("游戏开发", 6));
        labelsView.setLabels(testList, new LabelsView.LabelTextProvider<TestBean>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, TestBean data) {
                //根据data和position返回label需要显示的数据。
                return data.getName();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mJZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mJZVideoPlayer.releaseAllVideos();
    }
}
