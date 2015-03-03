package com.english.search;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.english.database.DbHelper;
import com.english.entity.Word;
import com.english.word.WordDetail;
import com.example.english.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyuxuan-s on 2015/2/26.
 */
public class SearchResultActivity extends ListActivity {
    ListView lv_list;
    private List<Map<String, Object>> list;
    private DbHelper dbHelper;
    private Map<String, Object> map;
    private String search_word;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        search_word = intent.getStringExtra("search_word");
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.search_word_item, new String[]{"body", "body_zh"}, new int[]{R.id.tv_body, R.id.tv_body_zh});
        setListAdapter(adapter);

        //点击列表，页面跳转到单词详情页，附带参数wordid
        lv_list = getListView();
        if (lv_list != null) {
            lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //找到点击处的单词id
                    HashMap<String, String> map = (HashMap<String, String>) lv_list.getItemAtPosition(position);

                    String word_body = map.get("body");
                    Log.e("SearchResultActivity word_body", word_body);
                    Intent intent = new Intent();
                    intent.putExtra("word_body", word_body);
                    intent.setClass(SearchResultActivity.this, WordDetail.class);
                    SearchResultActivity.this.startActivity(intent);
                }
            });
        }

    }

    private List<Map<String, Object>> getData() {
        list = new ArrayList<Map<String, Object>>();
        //数据库操作
        dbHelper = new DbHelper(this.getApplicationContext());
        List<Word> words = dbHelper.search_blur(search_word);


        for (int i = 0; i < words.size(); i++) {
            map = new HashMap<String, Object>();

            map.put("body", words.get(i).getBody());
            map.put("body_zh", words.get(i).getBody_zh());

            list.add(map);
        }


        return list;
    }
}