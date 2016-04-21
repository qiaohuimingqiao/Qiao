package com.itheima.android5;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by liukun on 2016/2/20.
 */
public class TransitionStartActivity extends Activity {
    @InjectView(R.id.start_explode)
    Button startExplode;
    @InjectView(R.id.start_slide)
    Button startSlide;
    @InjectView(R.id.start_fade)
    Button startFade;
    @InjectView(R.id.start_share_element)
    Button startShareElement;
    @InjectView(R.id.start_element)
    ImageView startElement;
    @InjectView(R.id.start_element2)
    ImageView startElement2;

    /*
        转场动画的步骤：
        1，开启功能(在setContentView之前完成)getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        2，指定动画效果getWindow().setExitTransition(new Explode());
        3，开启目标Activity,传入bundle，startActivity(this, bundle);bundle是由ActivityOptions创建出来的。
        4，在目标Activity中也开启功能。
        可选操作
        在目标Activity中尽早调用Window.setAllowEnterTransitionOverlap()
        在目标Activity结束时候调用finishAfterTransition()替代finish


        共享元素功转场动画的步骤:
        1,同上
        2,修改方法为getWindow.setSharedElementExitTransition();
        3,传入bundle时，添加共享的元素
        4,同上
        5,同上,需要指定共享元素的名称
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_transition_start);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.start_share_element})
    public void goSETargetActivity(View view) {
        Transition transition = new ChangeBounds();
        transition.setDuration(1200);
        getWindow().setSharedElementEnterTransition(transition);
        getWindow().setSharedElementExitTransition(transition);
        //开启目标Activity
        Intent intent = new Intent(this, TransitionTargetActivity.class);

        intent.putExtra("Transition", transition.getClass().getName());
        //指定一个共享元素
        //ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, startElement, "SE");
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create((View)startElement, "SE"),
                Pair.create((View)startElement2, "SE2"));
        Bundle bundle = activityOptions.toBundle();
        startActivity(intent, bundle);
    }

    @OnClick({R.id.start_explode, R.id.start_slide, R.id.start_fade})
    public void goTargetActivity(View view) {
        Transition transition = null;
        switch (view.getId()) {
            case R.id.start_explode:
                transition = new Explode();
                break;
            case R.id.start_fade:
                transition = new Fade();
                break;
            case R.id.start_slide:
                transition = new Slide();
                break;
        }
        transition.setDuration(1200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);

        Intent intent = new Intent(this, TransitionTargetActivity.class);

        intent.putExtra("Transition", transition.getClass().getName());
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }
}
