package com.itheima.android5.fragment;

import android.animation.Animator;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.android5.R;
import com.itheima.android5.TransitionStartActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by liukun on 2016/2/20.
 */
public class AnimationFragment extends Fragment {
    @InjectView(R.id.animaiton_show_reveal)
    Button animaitonShowReveal;
    @InjectView(R.id.animaiton_reveal_target)
    View animaitonRevealTarget;
    @InjectView(R.id.animation_go_transition)
    Button animationGoTransition;
/*
揭示动画：VieAnimationUtils
 */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);
        ButterKnife.inject(this, rootView);
        animaitonShowReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //指定动画的圆心
                //指定动画的开始结束半径
                float startRaduis = 0;
                float endRaduis = (float) Math.hypot(animaitonRevealTarget.getWidth(), animaitonRevealTarget.getHeight());
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(animaitonRevealTarget, 0, animaitonRevealTarget.getHeight(), startRaduis, endRaduis);
                circularReveal.setDuration(2000);
                circularReveal.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        animaitonRevealTarget.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                circularReveal.start();
            }
        });

        animationGoTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransitionStartActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
