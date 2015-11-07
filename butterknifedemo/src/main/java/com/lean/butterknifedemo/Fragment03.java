package com.lean.butterknifedemo;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by fang on 2015/11/7.
 */
public class Fragment03 extends Fragment {

    @Nullable
    @Bind(R.id.lv_listview)
    ListView lvListview;


    private static String[] name = {"xiaofang", "xiaoming", "xiaobai"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_fragment03, null);
        ButterKnife.bind(this, v);
        lvListview.setAdapter(new MyAdapter(this));
        return v;
    }

    @OnItemClick(R.id.lv_listview)
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "select item" + position, Toast.LENGTH_SHORT).show();
    }


    public static class MyAdapter extends BaseAdapter {
        Fragment f;
        @Override
        public int getCount() {
            return name.length;
        }

        public MyAdapter(Fragment f) {
            this.f = f;
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
            View v;
            ViewHolder vh;
            if (convertView == null) {
                v = View.inflate(f.getActivity(), R.layout.layout_listview_item, null);
                vh = new ViewHolder(v);
                v.setTag(vh);
            } else {
                v = convertView;
                vh = (ViewHolder) v.getTag();
            }

            vh.ivHead.setImageResource(R.drawable.head);
            vh.tvHead.setText("LexiMee" + position);
            vh.ivTail.setImageResource(R.drawable.tail);

            return v;
        }

        /**
         * This class contains all butterknife-injected Views & Layouts from layout file 'layout_listview_item.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
         */
        static class ViewHolder {
            @Bind(R.id.iv_head)
            ImageView ivHead;
            @Bind(R.id.tv_head)
            TextView tvHead;
            @Bind(R.id.iv_tail)
            ImageView ivTail;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
