package com.itheima.android5.helper;

import android.support.v4.util.Pair;

import com.itheima.android5.R;
import com.itheima.android5.WidgetActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by QinBin on 2015/5/25.
 */
public class MockDataGeneration {

    static int[] lineRes = new int[]{
            R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4, R.drawable.g5,
            R.drawable.g6, R.drawable.g7, R.drawable.g8, R.drawable.g9, R.drawable.g10,
            R.drawable.g11, R.drawable.g12, R.drawable.g13, R.drawable.g14, R.drawable.g15,
            R.drawable.g16, R.drawable.g17, R.drawable.g18, R.drawable.g19, R.drawable.g20,
            R.drawable.g21, R.drawable.g22, R.drawable.g23, R.drawable.g24, R.drawable.g25,
            R.drawable.g26, R.drawable.g27, R.drawable.g28, R.drawable.g29};

    static int[] vRes = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
            R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9,
            R.drawable.p10, R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14,
            R.drawable.p15, R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19,
            R.drawable.p20, R.drawable.p21, R.drawable.p22, R.drawable.p23, R.drawable.p24,
            R.drawable.p25, R.drawable.p26, R.drawable.p27, R.drawable.p28, R.drawable.p29,
            R.drawable.p30, R.drawable.p31, R.drawable.p32, R.drawable.p33, R.drawable.p34,
            R.drawable.p35, R.drawable.p36, R.drawable.p37, R.drawable.p38, R.drawable.p39,
            R.drawable.p40, R.drawable.p41, R.drawable.p42, R.drawable.p43};

    static int[] hRes = new int[]{R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4,
            R.drawable.h5, R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9,
            R.drawable.h10, R.drawable.h11, R.drawable.h12, R.drawable.h13, R.drawable.h14,
            R.drawable.h15, R.drawable.h16, R.drawable.h17, R.drawable.h18, R.drawable.h19,
            R.drawable.h20, R.drawable.h21, R.drawable.h22, R.drawable.h23, R.drawable.h24,
            R.drawable.h25, R.drawable.h26, R.drawable.h27, R.drawable.h28, R.drawable.h29,
            R.drawable.h30, R.drawable.h31, R.drawable.h32, R.drawable.h33, R.drawable.h34,
            R.drawable.h35, R.drawable.h36, R.drawable.h37, R.drawable.h38, R.drawable.h39,
            R.drawable.h40, R.drawable.h41, R.drawable.h42, R.drawable.h43};

    static String[] names = {
            "Cloud", "Movie", "Laptop", "Loop", "Menu",
            "Mood", "Palette", "Search", "Time", "Work",
            "Home"};


  public static List<Pair<String, Integer>> getModkData(int type, int orientation) {
        List<Pair<String, Integer>> datas = new ArrayList<Pair<String, Integer>>();
        if (type == WidgetActivity.TYPE_LINE || type == WidgetActivity.TYPE_GRID) {
            int count = lineRes.length * names.length;
            datas = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                datas.add(Pair.create(names[i % names.length], lineRes[i % lineRes.length]));
            }
            return datas;
        } else if (type == WidgetActivity.TYPE_S) {

            if (orientation == WidgetActivity.ORIENTATION_HORIZONTAL) {
                int count = hRes.length * names.length;
                datas = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    datas.add(Pair.create(names[i % names.length], hRes[i % hRes.length]));
                }
                return datas;
            } else {
                int count = vRes.length * names.length;
                datas = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    datas.add(Pair.create(names[i % names.length], vRes[i % vRes.length]));
                }
                return datas;
            }
        }
        return null;
    }

}
