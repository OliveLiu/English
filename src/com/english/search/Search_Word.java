package com.english.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.english.R;

/**
 * Created by liuyuxuan-s on 2015/3/2.
 */
public class Search_Word extends Activity {
    private Button btn_search;
    private EditText et_search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item);
        findview();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_search.getText().toString();
                Log.e("name", name);

                Intent intent = new Intent(Search_Word.this, SearchResultActivity.class);
                intent.putExtra("search_word", name);
                startActivity(intent);

            }
        });

    }

    public void findview() {
        btn_search = (Button) findViewById(R.id.btn_search);
        et_search = (EditText) findViewById(R.id.et_search);

        Log.e("Button", btn_search.getText().toString());
    }
}