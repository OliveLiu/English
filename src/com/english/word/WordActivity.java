package com.english.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.english.database.DbHelper;
import com.english.entity.Word;
import com.example.english.R;
import com.example.english.R.layout;
import com.example.english.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class WordActivity extends ListActivity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.word_item, new String[]{"body","soundmark","body_zh"}, new int[]{R.id.tv_body,R.id.tv_soundmark,R.id.tv_body_zh} );
		setListAdapter(adapter);
		
	}

	 private List<Map<String,Object>> getData(){

         

	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

	        
	        
	        //数据库操作
	        DbHelper dbHelper = new DbHelper(this.getApplicationContext());
	        Word word[] = dbHelper.select_20_words(1187261);
	        
	        //20个赋值
	        for(int i = 0 ; i < 20 ; i++){
	        	Map<String, Object> map = new HashMap<String, Object>();
	        	
	        	map.put("body", word[i].getBody());

	 	        map.put("soundmark", word[i].getSoundmark());

	 	        map.put("body_zh", word[i].getBody_zh());
	 	        
	 	        list.add(map);
	 	        
	 	        Log.e("赋值list", list.toString());
	        }

	       

	        
	        return list;

	    }

}
