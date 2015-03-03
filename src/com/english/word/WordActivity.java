package com.english.word;

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
import com.example.english.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordActivity extends ListActivity {

	private Map<String, Object> map;
	private int word_id = 1187261;
	private ListView word_listview;
	private List<Map<String, Object>> list;
	private DbHelper dbHelper;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//获取传入的参数——若其他界面需要，需加入防错处理
		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 1187261);
		word_id += position * 20;
		
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.word_item, new String[]{"body","soundmark","body_zh"}, new int[]{R.id.tv_body,R.id.tv_soundmark,R.id.tv_body_zh} );
		setListAdapter(adapter);

		//点击列表，页面跳转到单词详情页，附带参数wordid
		word_listview = getListView();
		if (word_listview != null) {
			word_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					//找到点击处的单词id
					HashMap<String, String> map = (HashMap<String, String>) word_listview.getItemAtPosition(position);

					String word_body = map.get("body");
					Log.e("word_body", map.toString());

					Intent intent = new Intent();
					intent.putExtra("word_body", word_body);
					intent.setClass(WordActivity.this, WordDetail.class);
					WordActivity.this.startActivity(intent);
				}
			});
		}
		
	}

	 private List<Map<String,Object>> getData(){

		 list = new ArrayList<Map<String, Object>>();

	        //数据库操作
		 dbHelper = new DbHelper(this.getApplicationContext());
		 List<Word> words = dbHelper.select_20_words(word_id);

		 //20个赋值
		 for (int i = 0; i < words.size(); i++) {
			 map = new HashMap<String, Object>();

			 map.put("body", words.get(i).getBody());
			 map.put("soundmark", words.get(i).getSoundmark());
			 map.put("body_zh", words.get(i).getBody_zh());

	 	        list.add(map);
			 Log.e("List", list.toString());

	        }

	        return list;

	    }

}
