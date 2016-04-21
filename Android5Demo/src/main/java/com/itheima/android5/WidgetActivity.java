package com.itheima.android5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.android5.helper.MockDataGeneration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WidgetActivity extends Activity {
    /*
    RecyclerView会替代ListView
    使用步骤:
    1,在xml中和listview一样定义
    2,给recyclearview设置layoutmanager
    3,设置adapter
    4,创建ViewHolder
     */


    public static final int TYPE_LINE = 0;
    public static final int TYPE_GRID = 1;
    public static final int TYPE_S = 2;
    public static final int ORIENTATION_VERTICAL = LinearLayoutManager.VERTICAL;
    public static final int ORIENTATION_HORIZONTAL = LinearLayoutManager.HORIZONTAL;

    int type;
    int orientation;
    @InjectView(R.id.widget_recycler)
    RecyclerView widgetRecycler;
    @InjectView(R.id.widget_srl)
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Pair<String, Integer>> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        ButterKnife.inject(this);
        type = getIntent().getIntExtra("TYPE", 0);
        orientation = getIntent().getIntExtra("ORIENTATION", 0);
        RecyclerView.LayoutManager layoutManager = null;
        switch (type) {
            case TYPE_LINE:
                layoutManager = new LinearLayoutManager(this, orientation, false);
                break;
            case TYPE_GRID:
                layoutManager = new GridLayoutManager(this, 3, orientation, false);
                break;
            case TYPE_S:
                layoutManager = new StaggeredGridLayoutManager(2, orientation);
                break;
        }

        data = MockDataGeneration.getModkData(type, orientation);
        widgetRecycler.setLayoutManager(layoutManager);
        widgetRecycler.setAdapter(adapter);
        //RecyclerView并没有提供条目监听，而是提供了更为底层的触摸监听
        //点击监听需要自己实现

        //指定圆圈的颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(0xFF0094FF);
        //指定箭头的颜色（可指定多个，依次渐变，最后一个会回到第一个）
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        //刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟耗时
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //关闭下拉刷新
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
    }

    static Handler handler = new Handler();

    public static Intent getStartIntent(Context context, int type, int orientation) {
        Intent intent = new Intent(context, WidgetActivity.class);
        intent.putExtra("TYPE", type);
        intent.putExtra("ORIENTATION", orientation);
        return intent;
    }


    private RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        /*
        把getView方法拆成两个,
        1创建viewholder
        2绑定数据到viewholder上
        其中viewType是getItemViewType方法的返回值
        */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int itemRes = 0;
            if (type == TYPE_S) {
                itemRes = R.layout.item_card;
            } else {
                itemRes = R.layout.item_simple;
            }
            View itemView = View.inflate(parent.getContext(), itemRes, null);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Pair<String, Integer> itemData = data.get(position);
            myViewHolder.iv.setImageResource(itemData.second);
            myViewHolder.tv.setText(itemData.first);

            myViewHolder.itemView.setTag(myViewHolder);
            myViewHolder.itemView.setOnClickListener(onItemClickListener);
        }

        //等同于BaseAdapter的getCunt方法
        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    };

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //通过被点击的View获取tag
            MyViewHolder mvh = (MyViewHolder) v.getTag();
            //Toast.makeText(v.getContext(),"" + mvh.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            //Listview如何删除一个条目呢，首先从集合中删除，然后刷新adapter
            data.remove(mvh.getAdapterPosition());
            adapter.notifyItemRemoved(mvh.getAdapterPosition());
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_simple_iv)
        ImageView iv;

        @InjectView(R.id.item_simple_tv)
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
