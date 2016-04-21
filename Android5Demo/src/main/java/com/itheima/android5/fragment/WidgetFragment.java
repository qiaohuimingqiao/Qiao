package com.itheima.android5.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.style.LineHeightSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.android5.R;
import com.itheima.android5.WidgetActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by liukun on 2016/2/21.
 */
public class WidgetFragment extends Fragment {
    @InjectView(R.id.vertical_line)
    Button verticalLine;
    @InjectView(R.id.horizontal_line)
    Button horizontalLine;
    @InjectView(R.id.vertical_grid)
    Button verticalGrid;
    @InjectView(R.id.horizontal_grid)
    Button horizontalGrid;
    @InjectView(R.id.vertical_s)
    Button verticalS;
    @InjectView(R.id.horizontal_s)
    Button horizontalS;

    @OnClick({R.id.vertical_line,R.id.horizontal_line,R.id.vertical_grid,R.id.horizontal_grid,R.id.vertical_s,R.id.horizontal_s})
    public void startWidgetActivity(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.vertical_line:
                intent = WidgetActivity.getStartIntent(v.getContext(), WidgetActivity.TYPE_LINE , WidgetActivity.ORIENTATION_VERTICAL);
                break;
            case R.id.horizontal_line:
                intent =WidgetActivity.getStartIntent(v.getContext(), WidgetActivity.TYPE_LINE , WidgetActivity.ORIENTATION_HORIZONTAL);
                break;
           case R.id.vertical_grid:
               intent =WidgetActivity.getStartIntent(v.getContext(), WidgetActivity.TYPE_GRID , WidgetActivity.ORIENTATION_VERTICAL);
               break;
           case R.id.horizontal_grid:
               intent = WidgetActivity.getStartIntent(v.getContext(), WidgetActivity.TYPE_GRID , WidgetActivity.ORIENTATION_HORIZONTAL);
               break;
            case R.id.vertical_s:
                intent =WidgetActivity.getStartIntent(v.getContext(), WidgetActivity.TYPE_S , WidgetActivity.ORIENTATION_VERTICAL);
                break;
            case R.id.horizontal_s:
                intent =WidgetActivity.getStartIntent(v.getContext(), WidgetActivity.TYPE_S , WidgetActivity.ORIENTATION_HORIZONTAL);
                break;
        }
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_widget, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
