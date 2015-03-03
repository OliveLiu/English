package com.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.english.WordsBook.WordsBook;
import com.english.recite.Recite_word;
import com.english.search.Search_Word;
import com.english.word.WordListActivity;
import com.example.english.R;

public class MainActivity extends Activity {

	private Button btn_view;
	private Button btn_recite;
	private Button btn_search;
	private Button btn_wordsbook;
	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);

		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.search_item);
		findview();
		btn_view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this, WordListActivity.class);
				startActivity(intent);
				
			}
		});
		btn_search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this, Search_Word.class);
				startActivity(intent);

			}
		});
		btn_recite.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this, Recite_word.class);
				startActivity(intent);

			}
		});
		btn_wordsbook.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this, WordsBook.class);
				startActivity(intent);

			}
		});

	}

	public void findview() {
		btn_view = (Button) findViewById(R.id.button1);
		btn_search = (Button) findViewById(R.id.button2);
		btn_recite = (Button) findViewById(R.id.button3);
		btn_wordsbook = (Button) findViewById(R.id.button4);
	}

	

}
