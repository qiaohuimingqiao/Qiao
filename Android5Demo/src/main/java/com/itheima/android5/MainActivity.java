package com.itheima.android5;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.android5.fragment.AnimationFragment;
import com.itheima.android5.fragment.DrawableFragment;
import com.itheima.android5.fragment.ShadowFragment;
import com.itheima.android5.fragment.ThemeFragment;
import com.itheima.android5.fragment.WidgetFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

    @InjectView(R.id.main_fragment_container)
    FrameLayout mainFragmentContainer;
    @InjectView(R.id.menu)
    LinearLayout menu;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.menu_list_view)
    ListView menuListView;
    private ActionBarDrawerToggle toggle;
    ArrayList<Pair<String, Fragment>> menuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(toggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        menuItems.add(new Pair<String, Fragment>("新控件", new WidgetFragment()));
        menuItems.add(new Pair<String, Fragment>("动画", new AnimationFragment()));
        menuItems.add(new Pair<String, Fragment>("图片与颜色", new DrawableFragment()));
        menuItems.add(new Pair<String, Fragment>("阴影与裁剪", new ShadowFragment()));
        menuItems.add(new Pair<String, Fragment>("主题与样式", new ThemeFragment()));
        menuListView.setAdapter(menuAdapter);
        menuListView.setOnItemClickListener(oicl);

        //加载第一个fragment作为默认的
        getFragmentManager().beginTransaction().add(R.id.main_fragment_container, menuItems.get(0).second).commit();
    }

    AdapterView.OnItemClickListener oicl = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, menuItems.get(position).second);
            fragmentTransaction.commit();
            drawerLayout.closeDrawers();
        }
    };

    BaseAdapter menuAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return menuItems == null?0:menuItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(parent.getContext());
            textView.setTextSize(24);
            textView.setPadding(4,5,4,5);
            textView.setText(menuItems.get(position).first);
            return textView;
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        toggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
}

