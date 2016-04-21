package com.itheima.android5;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by liukun on 2016/2/20.
 */
public class TransitionTargetActivity extends Activity {
    @InjectView(R.id.target_share_element)
    ImageView targetShareElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_target);
        ButterKnife.inject(this);
        String transitionClassName = getIntent().getStringExtra("Transition");

        if (transitionClassName != null) {
            Transition transition = new ChangeBounds();
            transition.setDuration(1200);
            getWindow().setSharedElementEnterTransition(transition);
            getWindow().setSharedElementExitTransition(transition);
            targetShareElement.setTransitionName("SE");
        } else {
            try {
                Class<?> transitionClass = Class.forName(transitionClassName);
                Transition transition = (Transition) transitionClass.newInstance();
                transition.setDuration(1200);
                getWindow().setEnterTransition(transition);
                getWindow().setExitTransition(transition);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishAfterTransition();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
