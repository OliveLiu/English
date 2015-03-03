package com.english.word;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.english.database.DbHelper;
import com.english.entity.Word;
import com.example.english.R;

import java.util.List;

/**
 * Created by liuyuxuan-s on 2015/2/26.
 */
public class WordDetail extends Activity {
    Word word;
    TextView tv_body;
    TextView tv_body_zh;
    TextView tv_usage_en;
    TextView tv_usage_zh;
    List<Word> words;
    private DbHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail);

        Intent intent = getIntent();
        String word_body = "hello";
        word_body = intent.getStringExtra("word_body");
        Log.e("Word_body_2", word_body);

        //根据传出body查找word
        dbHelper = new DbHelper(this);
        word = dbHelper.select_by_body(word_body);

        //尝试解决取值取不到
        words = dbHelper.select_by_bodys(word_body);

        Log.e("words size", Integer.toString(words.size()));

        //找出控件并settext
        findView();
        tv_body.setText(word.getBody());
        tv_body_zh.setText(word.getBody_zh());
        tv_usage_en.setText(word.getUsage_en());
        tv_usage_zh.setText(word.getUsage_zh());


    }

    protected void findView() {
        tv_body = (TextView) findViewById(R.id.tv_detail_body);
        tv_body_zh = (TextView) findViewById(R.id.tv_detail_body_zh);
        tv_usage_en = (TextView) findViewById(R.id.tv_detail_usage_en);
        tv_usage_zh = (TextView) findViewById(R.id.tv_detail_usage_zh);
    }

}
