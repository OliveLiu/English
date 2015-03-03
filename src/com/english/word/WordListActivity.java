package com.english.word;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordListActivity extends ListActivity {
    List<Map<String, Object>> list;
    private ListView myListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), com.example.english.R.layout.word_list_item, new String[]{"list_x"}, new int[]{com.example.english.R.id.tv_list_item});
        setListAdapter(adapter);

        //点击列表，跳转到单词列表页
        myListview = getListView();
        if (myListview != null) {
            //Log.e("Listview", Integer.toString(myListview.getId()));
            myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("position", position);
                    intent.setClass(WordListActivity.this, WordActivity.class);
                    WordListActivity.this.startActivity(intent);
                }
            });
        } else {
            Log.e("Listview", "is null");
        }

    }

    private List<Map<String, Object>> getData() {

        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 50; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list_x", Integer.toString(i));
            list.add(map);
        }
        return list;

    }

}
