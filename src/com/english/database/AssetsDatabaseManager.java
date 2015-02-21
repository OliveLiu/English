package com.english.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class AssetsDatabaseManager {
	 private static String tag = "AssetsDatabase";//for logcat
	    private static String databasepath = "/data/data/%s/database";

	    //a mapping from assets database file to SqliteDatabase object
	    private Map<String,SQLiteDatabase> database = new HashMap<String, SQLiteDatabase>();

	    //Context of application
	    private Context context = null;

	    //Singleleton pattern
	    private  static AssetsDatabaseManager mInstance = null;

	    /**
	     * Initialize AssetsDatabaseManager
	     */
	    public  static void initManager(Context context){
	        if (mInstance == null){
	            mInstance = new AssetsDatabaseManager(context);
	        }
	    }

	    /**
	     * get an AssetsDatabaseManager object
	     */
	    public  static AssetsDatabaseManager getManager(){
	        return mInstance;
	    }

	    private AssetsDatabaseManager(Context context){
	        this.context = context;
	    }

	    /**
	     * get a assets database ,if this database is opened this method  is only return a copy
	     */
	    public  SQLiteDatabase getDatabase(String dbfile) {
	        if (database.get(dbfile) != null) {//应为databases
	            Log.i(tag, String.format("Return a database copy of %s", dbfile));
	            return (SQLiteDatabase) database.get(dbfile);
	        }
	        if (context == null) {
	            return null;
	        }

	        Log.i(tag, String.format("Create database %s", dbfile));
	        String spath = getDatabaseFilepath();
	        String sfile = getDatabaseFile(dbfile);

	        File file = new File(sfile);
	        SharedPreferences dbs = context.getSharedPreferences(AssetsDatabaseManager.class.toString(), 0);
	        boolean flag = dbs.getBoolean(dbfile, false);
	        if (!flag || !file.exists()) {
	            file = new File(spath);
	            if (!file.exists() && !file.mkdirs()) {
	                Log.i(tag, "Create \"" + spath + "\"fail!");
	                return null;
	            }
	            if (!copyAssetsToFilesystem(dbfile, sfile)) {
	                Log.i(tag, String.format("Copy %s to %s fail", dbfile, sfile));
	                return null;
	            }
	            dbs.edit().putBoolean(dbfile, true).commit();
	        }

	        SQLiteDatabase db = SQLiteDatabase.openDatabase(sfile, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
	        if (db != null) {
	            database.put(dbfile, db);
	        }
	        return db;
	    }

	    private String getDatabaseFilepath(){
	        return  String.format(databasepath,context.getApplicationInfo().packageName);
	    }
	    private String getDatabaseFile(String dbfile){
	        return getDatabaseFilepath()+"/"+dbfile;
	    }
	    private boolean copyAssetsToFilesystem(String assetsSrc,String des){
	        Log.i(tag,"Copy"+assetsSrc+"to"+des);
	        InputStream istream = null;
	        OutputStream ostream = null;
	        try{
	            AssetManager am = context.getAssets();
	            istream = am.open(assetsSrc);
	            ostream = new FileOutputStream(des);
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length=istream.read(buffer))>0){
	                ostream.write(buffer,0,length);
	            }
	            istream.close();
	            ostream.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	            try {
	                if(istream!=null)
	                    istream.close();
	                if (ostream!=null)
	                    ostream.close();
	            }
	            catch (Exception ee){
	                ee.printStackTrace();
	            }
	            return false;
	        }
	        return true;
	    }
	    /**
	     * close assets database
	     */
	    public boolean closeDatabase(String dbfile){
	        if(database.get(dbfile)!=null){
	            SQLiteDatabase db = (SQLiteDatabase)database.get(dbfile);
	            db.close();
	            database.remove(dbfile);
	            return true;
	        }
	        return false;
	    }

	    /**
	     * Close all assets database
	     */
	    static  public void closeAllDatabase(){
	        Log.i(tag,"Close all database");
	        if(mInstance != null){
	            for(int i=0;i<mInstance.database.size();++i){
	                if(mInstance.database.get(i)!=null){
	                    mInstance.database.get(i).close();
	                }
	            }
	            mInstance.database.clear();
	        }
	    }
}
