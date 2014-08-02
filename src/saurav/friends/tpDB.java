package saurav.friends;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class tpDB 
{
	public static final String KEY_LOCATION_NAME = "location_name";
	public static final String KEY_LOCATION_ADDRESS = "location_address";
	public static final String KEY_LOCATION_TIME = "location_time";
	
	public static final String KEY_NEWS = "news";
	public static final String KEY_SUBNEWS = "subnews";	
	public static final String KEY_SCIENCE_NEWS = "sc_news";
	public static final String KEY_SCIENCE_SUBNEWS = "sc_subnews";	
	public static final String KEY_ENTERTAINMENT_NEWS = "ent_news";
	public static final String KEY_ENTERTAINMENT_SUBNEWS = "ent_subnews";
	public static final String KEY_TECH_NEWS = "tech_news";
	public static final String KEY_TECH_SUBNEWS = "tech_subnews";
	public static final String KEY_WORLD_NEWS = "world_news";
	public static final String KEY_WORLD_SUBNEWS = "world_subnews";	

	
	private static final String DATABASE_NAME = "sauravFriends";

	private static final String TABLE_LOCATION = "locationTable";
	private static final String TABLE_NEWS = "newsTable";
	private static final String TABLE_SCIENCE = "scienceTable";
	private static final String TABLE_ENTERTAINMENT = "entertainmentTable";
	private static final String TABLE_TECH = "techTable";
	private static final String TABLE_WORLD = "worldTable";
	
	private static final int DATABASE_VERSION = 1;	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper
	{
		public DbHelper(Context context)
		{
			super(context,DATABASE_NAME, null, DATABASE_VERSION);			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL("CREATE TABLE " + TABLE_NEWS + " (" +
						KEY_NEWS + " VARCHAR(1000), " +
						KEY_SUBNEWS + " VARCHAR(1000));"								
					);
			db.execSQL("CREATE TABLE " + TABLE_SCIENCE + " (" +
					KEY_SCIENCE_NEWS + " VARCHAR(1000), " +
					KEY_SCIENCE_SUBNEWS + " VARCHAR(1000));"								
				);
			db.execSQL("CREATE TABLE " + TABLE_ENTERTAINMENT + " (" +
					KEY_ENTERTAINMENT_NEWS + " VARCHAR(1000), " +
					KEY_ENTERTAINMENT_SUBNEWS + " VARCHAR(1000));"								
				);
			db.execSQL("CREATE TABLE " + TABLE_TECH + " (" +
					KEY_TECH_NEWS + " VARCHAR(1000), " +
					KEY_TECH_SUBNEWS + " VARCHAR(1000));"								
				);
			db.execSQL("CREATE TABLE " + TABLE_WORLD + " (" +
					KEY_WORLD_NEWS + " VARCHAR(1000), " +
					KEY_WORLD_SUBNEWS + " VARCHAR(1000));"								
				);
			db.execSQL("CREATE TABLE " + TABLE_LOCATION + " (" +
					KEY_LOCATION_NAME + " VARCHAR(30), " +
					KEY_LOCATION_ADDRESS + " VARCHAR(500), " +
					KEY_LOCATION_TIME + " VARCHAR(150));"						
					);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCIENCE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTERTAINMENT);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECH);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORLD);
			onCreate(db);
		}
	}
	
	public tpDB (Context c)
	{
		ourContext = c;
	}

	public void insertNews(ArrayList<NewsInfo> objects)
	{
		ourDatabase.delete(TABLE_NEWS, null, null);
		ContentValues cv = new ContentValues();
		for(int index=0; index <objects.size(); index++)
		{
			String head = objects.get(index).getHead().toString();
			String detail = objects.get(index).getDetail().toString();
			cv.put(KEY_NEWS, head);
			cv.put(KEY_SUBNEWS, detail);
			ourDatabase.insert(TABLE_NEWS, null,cv);
		}
			
	}
	
	public void insertScienceNews(ArrayList<NewsInfo> objects)
	{
		ourDatabase.delete(TABLE_SCIENCE, null, null);
		ContentValues cv = new ContentValues();
		for(int index=0; index <objects.size(); index++)
		{
			String head = objects.get(index).getHead().toString();
			String detail = objects.get(index).getDetail().toString();
			cv.put(KEY_SCIENCE_NEWS, head);
			cv.put(KEY_SCIENCE_SUBNEWS, detail);
			ourDatabase.insert(TABLE_SCIENCE, null,cv);
		}			
	}
	public void insertEntertainmentNews(ArrayList<NewsInfo> objects)
	{
		ourDatabase.delete(TABLE_ENTERTAINMENT, null, null);
		ContentValues cv = new ContentValues();
		for(int index=0; index <objects.size(); index++)
		{
			String head = objects.get(index).getHead().toString();
			String detail = objects.get(index).getDetail().toString();
			cv.put(KEY_ENTERTAINMENT_NEWS, head);
			cv.put(KEY_ENTERTAINMENT_SUBNEWS, detail);
			ourDatabase.insert(TABLE_ENTERTAINMENT, null,cv);
		}			
	}
	public void insertTechNews(ArrayList<NewsInfo> objects)
	{
		ourDatabase.delete(TABLE_TECH, null, null);
		ContentValues cv = new ContentValues();
		for(int index=0; index <objects.size(); index++)
		{
			String head = objects.get(index).getHead().toString();
			String detail = objects.get(index).getDetail().toString();
			cv.put(KEY_TECH_NEWS, head);
			cv.put(KEY_TECH_SUBNEWS, detail);
			ourDatabase.insert(TABLE_TECH, null,cv);
		}			
	}
	public void insertWorldNews(ArrayList<NewsInfo> objects)
	{
		ourDatabase.delete(TABLE_WORLD, null, null);
		ContentValues cv = new ContentValues();
		for(int index=0; index <objects.size(); index++)
		{
			String head = objects.get(index).getHead().toString();
			String detail = objects.get(index).getDetail().toString();
			cv.put(KEY_WORLD_NEWS, head);
			cv.put(KEY_WORLD_SUBNEWS, detail);
			ourDatabase.insert(TABLE_WORLD, null,cv);
		}			
	}
	
	
	public void insertLocation(ArrayList<LocationInfo> objects) 
	{		
		ourDatabase.delete(TABLE_LOCATION, null, null);
		ContentValues cv = new ContentValues();
		for(int index=0; index < objects.size(); index++)
		{
			String name = objects.get(index).getName().toString();
			String address= objects.get(index).getAddress().toString();
			String time = objects.get(index).getTime().toString();
			cv.put(KEY_LOCATION_NAME, name);
			cv.put(KEY_LOCATION_ADDRESS, address);
			cv.put(KEY_LOCATION_TIME, time);
			ourDatabase.insert(TABLE_LOCATION,null, cv );
		}
	}

	public tpDB open() throws SQLException
	{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		ourHelper.close();
	}
	
	public ArrayList<NewsInfo> getNews()
	{
		String[] columns = new String[]{KEY_NEWS, KEY_SUBNEWS};
		Cursor c = ourDatabase.query(TABLE_NEWS, columns, null, null, null, null, null);
		ArrayList<NewsInfo> result = new ArrayList<NewsInfo>();
		int iNews = c.getColumnIndex(KEY_NEWS);
		int iDet = c.getColumnIndex(KEY_SUBNEWS);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{	
			NewsInfo temp = new NewsInfo(c.getString(iNews), c.getString(iDet));			
			result.add(temp);
		}
		return result;
	}
	
	public ArrayList<NewsInfo> getScienceNews()
	{
		String[] columns = new String[]{KEY_SCIENCE_NEWS, KEY_SCIENCE_SUBNEWS};
		Cursor c = ourDatabase.query(TABLE_SCIENCE, columns, null, null, null, null, null);
		ArrayList<NewsInfo> result = new ArrayList<NewsInfo>();
		int iNews = c.getColumnIndex(KEY_SCIENCE_NEWS);
		int iDet = c.getColumnIndex(KEY_SCIENCE_SUBNEWS);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{	
			NewsInfo temp = new NewsInfo(c.getString(iNews), c.getString(iDet));			
			result.add(temp);
		}
		return result;
	}
	
	public ArrayList<NewsInfo> getEntertainmentNews()
	{
		String[] columns = new String[]{KEY_ENTERTAINMENT_NEWS, KEY_ENTERTAINMENT_SUBNEWS};
		Cursor c = ourDatabase.query(TABLE_ENTERTAINMENT, columns, null, null, null, null, null);
		ArrayList<NewsInfo> result = new ArrayList<NewsInfo>();
		int iNews = c.getColumnIndex(KEY_ENTERTAINMENT_NEWS);
		int iDet = c.getColumnIndex(KEY_ENTERTAINMENT_SUBNEWS);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{	
			NewsInfo temp = new NewsInfo(c.getString(iNews), c.getString(iDet));			
			result.add(temp);
		}
		return result;
	}
	public ArrayList<NewsInfo> getTechNews()
	{
		String[] columns = new String[]{KEY_TECH_NEWS, KEY_TECH_SUBNEWS};
		Cursor c = ourDatabase.query(TABLE_TECH, columns, null, null, null, null, null);
		ArrayList<NewsInfo> result = new ArrayList<NewsInfo>();
		int iNews = c.getColumnIndex(KEY_TECH_NEWS);
		int iDet = c.getColumnIndex(KEY_TECH_SUBNEWS);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{	
			NewsInfo temp = new NewsInfo(c.getString(iNews), c.getString(iDet));			
			result.add(temp);
		}
		return result;
	}
	public ArrayList<NewsInfo> getWorldNews()
	{
		String[] columns = new String[]{KEY_WORLD_NEWS, KEY_WORLD_SUBNEWS};
		Cursor c = ourDatabase.query(TABLE_WORLD, columns, null, null, null, null, null);
		ArrayList<NewsInfo> result = new ArrayList<NewsInfo>();
		int iNews = c.getColumnIndex(KEY_WORLD_NEWS);
		int iDet = c.getColumnIndex(KEY_WORLD_SUBNEWS);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{	
			NewsInfo temp = new NewsInfo(c.getString(iNews), c.getString(iDet));			
			result.add(temp);
		}
		return result;
	}
	
	
	public ArrayList<String> getLocation()
	{
		String[] columns = new String[]{KEY_LOCATION_NAME, KEY_LOCATION_ADDRESS, KEY_LOCATION_TIME};
		Cursor c = ourDatabase.query(TABLE_LOCATION, columns, null, null, null, null, null);
		ArrayList<String> result = new ArrayList<String>();
		int iLocationName = c.getColumnIndex(KEY_LOCATION_NAME);
		int iLocationAddress = c.getColumnIndex(KEY_LOCATION_ADDRESS);
		int iLocationTime = c.getColumnIndex(KEY_LOCATION_TIME);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			result.add(c.getString(iLocationName) +": "+ c.getString(iLocationAddress) + "\n At "+ c.getString(iLocationTime));
		}
		return result;
	}
}
