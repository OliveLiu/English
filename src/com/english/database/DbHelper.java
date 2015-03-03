package com.english.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.english.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {
	private SQLiteDatabase db;

	
	
	public DbHelper(Context context){
		//初始化，只调用一次
        AssetsDatabaseManager.initManager(context);
        //获取管理对象，因为数据库需要通过管理对象才能够获取
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        //通过管理对象获取数据库
        this.db = mg.getDatabase("offline_1001.db");
	}

    /*
    select_wordtable根据id查找单词
     */
    public Word select_by_id(int id) {
        Word word = new Word();
        Cursor c = db.query("PARAPHRASE_INFO_1001", null, "ID=?", new String[]{Integer.toString(id)}, null, null, null);
        word = evalue_by_cursor(c);
        Log.e("word select_by_id", "going");
        return word;
    }


    /*
    search_blur 模糊查找——从前往后查找——有问题
     */

    public List<Word> search_blur(String s) {
        List<Word> words = new ArrayList<Word>();
        Cursor c = db.query("PARAPHRASE_INFO_1001", null, "BODY like ?", new String[]{s + "%"}, null, null, null);
        words = evalue_list_by_cursor(c);

        return words;
    }

    /*
        select by body 根据body查找单词
     */
    public Word select_by_body(String body) {
        Word word = new Word();
        Cursor c = db.query("PARAPHRASE_INFO_1001", null, "BODY=?", new String[]{body}, null, null, null);

        word = evalue_by_cursor(c);
        if (word != null) {
            Log.e("select_by_body", "word is not null");
        }
        return word;
    }

    /*
       select by bodys 返回list尝试解决返回值为空
     */
    public List<Word> select_by_bodys(String body) {
        List<Word> words = new ArrayList<Word>();
        Cursor c = db.query("PARAPHRASE_INFO_1001", null, "BODY=?", new String[]{body}, null, null, null);
        words = evalue_list_by_cursor(c);
        return words;
    }

    /*
    根据cursor赋值给word
     */
    protected List<Word> evalue_list_by_cursor(Cursor c) {
        List<Word> words = new ArrayList<Word>();
        Log.e("state", "evalue_list_by_cursor");

        int index;
        if (c != null) {
            //c.moveToFirst();
            while (c.moveToNext()) {
                Word word = new Word();
                index = c.getColumnIndex("ID");
                word.setId(Integer.parseInt(c.getString(index++)));
                word.setWord_id(Integer.parseInt(c.getString(index++)));
                word.setBody(c.getString(index++));
                word.setBody_zh(c.getString(index++));
                word.setBody_en(c.getString(index++));
                word.setUsage_zh(c.getString(index++));
                word.setUsage_en(c.getString(index++));
                word.setRank(c.getString(index++));
                word.setCreat_user_id(Integer.parseInt(c.getString(index++)));
                word.setModify_user_id(Integer.parseInt(c.getString(index++)));
                word.setSoundmark(c.getString(index++));
                word.setCreat_time(c.getString(index++));
                word.setModify_time(c.getString(index++));
                word.setBook_id(Integer.parseInt(c.getString(index++)));
                word.setUnit_number(Integer.parseInt(c.getString(index++)));
                word.setPolyphone(Integer.parseInt(c.getString(index++)));
                word.setSoundmark2(c.getString(index++));
                word.setOther_body(c.getString(index++));
                word.setSound_ex(c.getString(index++));

                words.add(word);
            }
        }

        return words;
    }

    /*
    根据cursor赋值给word
    绝对不能添加 c.movetofirst 造成返回值为空
     */
    protected Word evalue_by_cursor(Cursor c) {
        com.english.entity.Word word = new Word();

        int index;
        if (c != null) {
            Log.e("Word evalue_by_cursor", "cursor is not null");
            while (c.moveToNext()) {
                index = c.getColumnIndex("ID");
                word.setId(Integer.parseInt(c.getString(index++)));
                word.setWord_id(Integer.parseInt(c.getString(index++)));
                word.setBody(c.getString(index++));
                word.setBody_zh(c.getString(index++));
                word.setBody_en(c.getString(index++));
                word.setUsage_zh(c.getString(index++));
                word.setUsage_en(c.getString(index++));
                word.setRank(c.getString(index++));
                word.setCreat_user_id(Integer.parseInt(c.getString(index++)));
                word.setModify_user_id(Integer.parseInt(c.getString(index++)));
                word.setSoundmark(c.getString(index++));
                word.setCreat_time(c.getString(index++));
                word.setModify_time(c.getString(index++));
                word.setBook_id(Integer.parseInt(c.getString(index++)));
                word.setUnit_number(Integer.parseInt(c.getString(index++)));
                word.setPolyphone(Integer.parseInt(c.getString(index++)));
                word.setSoundmark2(c.getString(index++));
                word.setOther_body(c.getString(index++));
                word.setSound_ex(c.getString(index++));

            }
            c.close();
        }

        return word;
    }

    /*
    select_20_words 从id往下依次查找20个单词
     */
    public List<Word> select_20_words(int id) {
        List<Word> words = new ArrayList<Word>();

        int count = id;//起始id
        int array;//负责计数
        Word w_refer = new Word();
        for (array = 0; array < 20; array++) {
            w_refer = this.select_by_id(count + array);
            //Log.e("w_refer",w_refer.getBody().toString());
            if (w_refer.getBody() != null) {
                words.add(w_refer);
            }
            Log.e("w_refer", w_refer.toString());
        }
        return words;
    }


}
