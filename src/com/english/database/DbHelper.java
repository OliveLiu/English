package com.english.database;



import com.english.entity.Word;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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


    public Word select_wordtable(int id){
    Word word = new Word();
    Cursor c = db.query("PARAPHRASE_INFO_1001", null, "ID=?", new String[]{Integer.toString(id)}, null, null, "BODY");

    int index;
    if(c!=null){
        String[] columns= c.getColumnNames();
        int word_index,explain_index;
        while (c.moveToNext()){
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
        //c.close();
        //db.close();         
    }
    
    return word;
}

 
	public Word[] select_20_words(int id){
	Word word[] = new Word[20];
	//count代表起始id index限制20个 arry_id代表实际id值
	int count = id;
	int index = 0 ;
	int array_id;
	Word w_refer = new Word();
	
	for(index=0 ; index<20 ; index++ ){
		array_id = count + index;
		w_refer = this.select_wordtable(array_id);
		if(w_refer.getBody() == null || w_refer.getBody().equals("")){
			array_id ++;
		}
		word[index]=this.select_wordtable(array_id);
	}
	
	
	return word;
}
	

}
