package com.itheima.android5.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.android5.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by liukun on 2016/2/20.
 */
public class DrawableFragment extends Fragment {
    @InjectView(R.id.drawable_iv)
    ImageView drawableIv;
    @InjectView(R.id.drawable_color_1)
    View drawableColor1;
    @InjectView(R.id.drawable_color_2)
    View drawableColor2;
    @InjectView(R.id.drawable_color_3)
    View drawableColor3;
    @InjectView(R.id.drawable_color_4)
    View drawableColor4;
    @InjectView(R.id.drawable_color_5)
    View drawableColor5;
    @InjectView(R.id.drawable_color_6)
    View drawableColor6;
    /*
    Palette可以获取图片上的颜色
    不能new出来，是通过builder去获取的。builder需要一个bitmap对象
    获取的时候需要传入一个默认颜色，在获取失败的时候返回。
    */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawable, container, false);
        ButterKnife.inject(this, rootView);
        Bitmap bitmap = ((BitmapDrawable) drawableIv.getDrawable()).getBitmap();
        Palette.Builder builder = Palette.from(bitmap);
        Palette palette = builder.generate();
        drawableColor1.setBackgroundColor(palette.getMutedColor(0xff0094ff));
        drawableColor2.setBackgroundColor(palette.getDarkMutedColor(0xff0094ff));
        drawableColor3.setBackgroundColor(palette.getVibrantColor(0xff0094ff));
        drawableColor4.setBackgroundColor(palette.getDarkVibrantColor(0xff0094ff));
        drawableColor5.setBackgroundColor(palette.getLightVibrantColor(0xff0094ff));
        drawableColor6.setBackgroundColor(palette.getLightMutedColor(0xff0094ff));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
