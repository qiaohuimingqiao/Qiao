package com.itheima.android5.fragment;

import android.app.Fragment;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.itheima.android5.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by liukun on 2016/2/20.
 */
public class ShadowFragment extends Fragment {
    @InjectView(R.id.shadow_raduis_rect)
    TextView shadowRaduisRect;
    @InjectView(R.id.shadow_trangle)
    TextView shadowTrangle;
    @InjectView(R.id.shadow_small_rect)
    TextView shadowSmallRect;
    @InjectView(R.id.shadow_raduis_rect2)
    TextView shadowRaduisRect2;
    @InjectView(R.id.shadow_trangle2)
    TextView shadowTrangle2;
    @InjectView(R.id.shadow_small_rect2)
    TextView shadowSmallRect2;

    /*
    带有透明通道的背景资源，如果指定 android:outlineProvider="background" 不会显示阴影
    只能通过代码处理
    */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shadow, container, false);
        ButterKnife.inject(this, rootView);
        ViewOutlineProvider provider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                switch (view.getId()) {
                    case R.id.shadow_raduis_rect:
                        outline.setRoundRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), 15);
                        break;
                    case R.id.shadow_trangle:
                        Path path = new Path();
                        path.moveTo(0, view.getHeight());
                        path.lineTo(view.getWidth() / 2, 0);
                        path.lineTo(view.getWidth(), view.getHeight());
                        path.close();
                        outline.setConvexPath(path);
                        break;
                    case R.id.shadow_small_rect:
                        outline.setRect(view.getWidth() / 4, view.getHeight() / 4, view.getWidth() * 3 / 4, view.getHeight() * 3 / 4);
                        break;
                }
            }
        };
        shadowRaduisRect.setOutlineProvider(provider);
        shadowTrangle.setOutlineProvider(provider);
        shadowSmallRect.setOutlineProvider(provider);

        ViewOutlineProvider provider2 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                switch (view.getId()) {
                    case R.id.shadow_raduis_rect2:
                        outline.setRoundRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), 15);
                        break;
                    case R.id.shadow_trangle2:
                        Path path = new Path();
                        path.moveTo(0, view.getHeight());
                        path.lineTo(view.getWidth() / 2, 0);
                        path.lineTo(view.getWidth(), view.getHeight());
                        path.close();
                        outline.setConvexPath(path);
                        break;
                    case R.id.shadow_small_rect2:
                        outline.setRect(view.getWidth() / 4, view.getHeight() / 4, view.getWidth() * 3 / 4, view.getHeight() * 3 / 4);
                        break;
                }
                Log.d(((TextView)view).getText().toString(), outline.canClip() + "");
            }
        };

        shadowRaduisRect2.setOutlineProvider(provider2);
        shadowTrangle2.setOutlineProvider(provider2);
        shadowSmallRect2.setOutlineProvider(provider2);
        //把控件裁剪成轮廓提供者的形状
        shadowSmallRect2.setClipToOutline(true);
        shadowTrangle2.setClipToOutline(true);
        shadowSmallRect2.setClipToOutline(true);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
