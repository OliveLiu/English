package com.english.recite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.english.R;

/**
 * Created by liuyuxuan-s on 2015/3/3.
 */
public class Recite_word extends Activity {
    private Button btn_recite_showzh;
    private Button btn_recite_prev;
    private Button btn_recite_next;
    private RelativeLayout rl_explain;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recite_word);
        findView();

        //显示释义按钮的监听事件
        btn_recite_showzh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_explain.setVisibility(View.VISIBLE);
            }
        });

    }

    protected void findView() {
        btn_recite_showzh = (Button) findViewById(R.id.btn_recite_showzh);
        btn_recite_prev = (Button) findViewById(R.id.btn_recite_prev);
        btn_recite_next = (Button) findViewById(R.id.btn_recite_next);
        rl_explain = (RelativeLayout) findViewById(R.id.rl_explain);
    }

}