package utils;

/**
 * Created by Jenya on 21.11.2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "itemsDB.db";
    private static final String TABLE_ITEMS= "items"; // name of the table
    // name of the columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_CATEGORYNAME = "categoryName";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FAVCOLOR = "favColor";

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " +
                TABLE_ITEMS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_VALUE + " INTEGER,"
                + COLUMN_CATEGORYNAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FAVCOLOR + " TEXT"
                + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public void addCategoryItem(categoryItem item) {

        ContentValues values = new ContentValues();

        values.put(COLUMN_VALUE, item.getValue());
        values.put(COLUMN_CATEGORYNAME, item.getCategoryName());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_FAVCOLOR, item.getFavColor());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public categoryItemList findAllItems()
    {
        String query = "Select * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        categoryItemList itemsList = new categoryItemList(); // create a list to hold all the items

        while(cursor.moveToNext())
        {
            categoryItem item=new categoryItem(); // create a free object

            item.setId(Integer.parseInt(cursor.getString(0))); // retrieve data
            item.setValue(Integer.parseInt(cursor.getString(1)));
            item.setCategoryName(cursor.getString(2));
            item.setDescription(cursor.getString(3));
            item.setFavColor(cursor.getString(4));

            itemsList.add(item); // add to list
        }

        cursor.close();
        db.close();
        return itemsList;
    }

    public boolean deleteCategoryItem(String categoryItemName) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_CATEGORYNAME + " =  \"" + categoryItemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //categoryItem product = new categoryItem();

        if (cursor.moveToFirst())
        {
            //product.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ITEMS, COLUMN_CATEGORYNAME + " = ?", new String[]{ String.valueOf(categoryItemName)});
            cursor.close();
            result = true;
        }

        db.close();
        return result; // return boolean if success
    }



}