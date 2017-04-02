package com.example.aditya.menuview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by aditya on 30-03-2017.
 */

public final class DatabaseSqlClass {
    private DatabaseSqlClass(){}
    //SQL common column names for all tables are below, while the column names unique to each table are written in the table's class
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_IMAGE_ID = "imageID";
    public static final String COLUMN_NAME_QUANTITY_ARRAY_STRING = "quantityString";
    public static final String COLUMN_NAME_QUANTITY_ARRAY_INTEGER = "quantityInteger";
    public static final String COLUMN_NAME_PRICE = "price";

    //SQL Queries for creating and deleting a table:
    private static final String SQL_CREATE_ENTRIES_FRUITS_VEGETABLES =
            "CREATE TABLE " + FruitsVegetablesTable.TABLE_NAME_FRUITS_VEGETABLES + " (" +
                    FruitsVegetablesTable._ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_NAME + " TEXT," +
                    COLUMN_NAME_IMAGE_ID + " INTEGER,"+
                    COLUMN_NAME_QUANTITY_ARRAY_STRING + " TEXT,"+
                    COLUMN_NAME_QUANTITY_ARRAY_INTEGER + " TEXT,"+
                    COLUMN_NAME_PRICE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES_FRUITS_VEGETABLES =
            "DROP TABLE IF EXISTS " + FruitsVegetablesTable.TABLE_NAME_FRUITS_VEGETABLES;

    private static final String SQL_CREATE_ENTRIES_BREAD_DAIRY_EGGS =
            "CREATE TABLE " + BreadDairyAndEggs.TABLE_NAME_BREAD_DAIRY_EGGS + " (" +
                    FruitsVegetablesTable._ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_NAME + " TEXT," +
                    COLUMN_NAME_IMAGE_ID + " INTEGER,"+
                    COLUMN_NAME_QUANTITY_ARRAY_STRING + " TEXT,"+
                    COLUMN_NAME_QUANTITY_ARRAY_INTEGER + " TEXT,"+
                    COLUMN_NAME_PRICE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES_BREAD_DAIRY_EGGS =
            "DROP TABLE IF EXISTS " + BreadDairyAndEggs.TABLE_NAME_BREAD_DAIRY_EGGS;

    private static final String SQL_CREATE_ENTRIES_BEVERAGES=
            "CREATE TABLE " + Beverages.TABLE_NAME_BEVERAGES + " (" +
                    FruitsVegetablesTable._ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_NAME + " TEXT," +
                    COLUMN_NAME_IMAGE_ID + " INTEGER,"+
                    COLUMN_NAME_QUANTITY_ARRAY_STRING + " TEXT,"+
                    COLUMN_NAME_QUANTITY_ARRAY_INTEGER + " TEXT,"+
                    COLUMN_NAME_PRICE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES_BEVERAGES =
            "DROP TABLE IF EXISTS " + Beverages.TABLE_NAME_BEVERAGES;

    private static final String SQL_CREATE_ENTRIES_GROCERIES_STAPLES =
            "CREATE TABLE " + GroceriesAndStaples.TABLE_NAME_GROCERIES_STAPLES + " (" +
                    FruitsVegetablesTable._ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_NAME + " TEXT," +
                    COLUMN_NAME_IMAGE_ID + " INTEGER,"+
                    COLUMN_NAME_QUANTITY_ARRAY_STRING + " TEXT,"+
                    COLUMN_NAME_QUANTITY_ARRAY_INTEGER + " TEXT,"+
                    COLUMN_NAME_PRICE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES_GROCERIES_STAPLES =
            "DROP TABLE IF EXISTS " + GroceriesAndStaples.TABLE_NAME_GROCERIES_STAPLES;
    //Unique Column names/ Characteristics for each table:
    public static class FruitsVegetablesTable implements BaseColumns{
        static final String TABLE_NAME_FRUITS_VEGETABLES = "fruitsVegetablesTable";
    }
    public static class GroceriesAndStaples implements BaseColumns{
        static final String TABLE_NAME_GROCERIES_STAPLES = "groceriesAndStaples";
    }
    public static class BreadDairyAndEggs implements BaseColumns{
        static final String TABLE_NAME_BREAD_DAIRY_EGGS = "breadDairyAndEggs";
    }
    public static class Beverages implements BaseColumns{
        static final String TABLE_NAME_BEVERAGES = "beverages";
    }

    //Containing methods to create and perform actions on different tables of the databases
    static class NeedyProductDbHelper extends SQLiteOpenHelper{
        // If you change the database schema, you must increment the database version.
        static final int DATABASE_VERSION = 1;
        static final String DATABASE_NAME = "NeedyProducts";
        private Context context;

        NeedyProductDbHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES_BEVERAGES);
            db.execSQL(SQL_CREATE_ENTRIES_BREAD_DAIRY_EGGS);
            db.execSQL(SQL_CREATE_ENTRIES_GROCERIES_STAPLES);
            db.execSQL(SQL_CREATE_ENTRIES_FRUITS_VEGETABLES);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    NeedyProductDbHelper databaseHelper = new NeedyProductDbHelper(context);
                    //Fruits&Vegetables
                    databaseHelper.addItemToTable(DatabaseSqlClass.FruitsVegetablesTable.TABLE_NAME_FRUITS_VEGETABLES,"Broccoli",R.drawable.broccoli,new String[]{"1KG","2KG","3KG","10KG"},new int[]{1,2,3,10},20);
                    databaseHelper.addItemToTable(DatabaseSqlClass.FruitsVegetablesTable.TABLE_NAME_FRUITS_VEGETABLES,"Onions",R.drawable.onion,new String[]{"1KG","2KG","3KG","10KG"},new int[]{1,2,3,10},10);
                    databaseHelper.addItemToTable(DatabaseSqlClass.FruitsVegetablesTable.TABLE_NAME_FRUITS_VEGETABLES,"Orange",R.drawable.orange,new String[]{"1 Dozen","2 Dozens","3 Dozens","5 Dozens"},new int[]{1,2,3,5},40);
                    databaseHelper.addItemToTable(DatabaseSqlClass.FruitsVegetablesTable.TABLE_NAME_FRUITS_VEGETABLES,"Pineapple",R.drawable.pineapple,new String[]{"1KG","2KG","3KG","5KG"},new int[]{1,2,3,5},35);
                    //Groceries&Staples
                    databaseHelper.addItemToTable(DatabaseSqlClass.GroceriesAndStaples.TABLE_NAME_GROCERIES_STAPLES,"Rice",R.drawable.rice,new String[]{"1KG","2KG","3KG","10KG"},new int[]{1,2,3,10},20);
                    databaseHelper.addItemToTable(DatabaseSqlClass.GroceriesAndStaples.TABLE_NAME_GROCERIES_STAPLES,"Flour",R.drawable.flour,new String[]{"1KG","2KG","3KG","10KG"},new int[]{1,2,3,10},35);
                    databaseHelper.addItemToTable(DatabaseSqlClass.GroceriesAndStaples.TABLE_NAME_GROCERIES_STAPLES,"Olive Oil",R.drawable.oliveoil,new String[]{"1 Litre","2 Litres","3 Litres","5 Litres"},new int[]{1,2,3,5},75);
                    databaseHelper.addItemToTable(DatabaseSqlClass.GroceriesAndStaples.TABLE_NAME_GROCERIES_STAPLES,"Cashew Nuts",R.drawable.cashew,new String[]{"1KG","2KG","3KG","10KG"},new int[]{1,2,3,10},90);
                    //Bread&Dairy
                    databaseHelper.addItemToTable(DatabaseSqlClass.BreadDairyAndEggs.TABLE_NAME_BREAD_DAIRY_EGGS,"Bread",R.drawable.wheatbread,new String[]{"1 Packet","2 Packets","3 Packets","5 Packets"},new int[]{1,2,3,5},25);
                    databaseHelper.addItemToTable(DatabaseSqlClass.BreadDairyAndEggs.TABLE_NAME_BREAD_DAIRY_EGGS,"Pizza Base",R.drawable.pizzabase,new String[]{"1 Base","2 Pizza Bases","3 Pizza Bases","5 Pizza Bases"},new int[]{1,2,3,5},40);
                    databaseHelper.addItemToTable(DatabaseSqlClass.BreadDairyAndEggs.TABLE_NAME_BREAD_DAIRY_EGGS,"Milk",R.drawable.milk,new String[]{"1 Carton","2 Cartons","3 Cartons","5 Cartons"},new int[]{1,2,3,5},20);
                    databaseHelper.addItemToTable(DatabaseSqlClass.BreadDairyAndEggs.TABLE_NAME_BREAD_DAIRY_EGGS,"Yogurt",R.drawable.yogurt,new String[]{"1 Pack","2 Packs","3 Packs","5 Packs"},new int[]{1,2,3,5},15);
                    //Beverages
                    databaseHelper.addItemToTable(DatabaseSqlClass.Beverages.TABLE_NAME_BEVERAGES,"Pepsi",R.drawable.pepsi,new String[]{"1 Bottle","2 Bottles","3 Bottles","5 Bottles"},new int[]{1,2,3,5},20);
                    databaseHelper.addItemToTable(DatabaseSqlClass.Beverages.TABLE_NAME_BEVERAGES,"Hershey's Chocolate Shake ",R.drawable.chocolatemilk,new String[]{"1 Bottle","2 Bottles","3 Bottles","5 Bottles"},new int[]{1,2,3,5},30);
                    databaseHelper.addItemToTable(DatabaseSqlClass.Beverages.TABLE_NAME_BEVERAGES,"Tuborg",R.drawable.tuborg,new String[]{"1 Bottle","2 Bottles","3 Bottles","5 Bottles"},new int[]{1,2,3,5},60);
                    databaseHelper.addItemToTable(DatabaseSqlClass.Beverages.TABLE_NAME_BEVERAGES,"Monster",R.drawable.monster,new String[]{"1 Can","2 Cans","3 Cans","5 Cans"},new int[]{1,2,3,5},60);
                }
            });
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES_GROCERIES_STAPLES);
            db.execSQL(SQL_DELETE_ENTRIES_FRUITS_VEGETABLES);
            db.execSQL(SQL_DELETE_ENTRIES_BREAD_DAIRY_EGGS);
            db.execSQL(SQL_DELETE_ENTRIES_BEVERAGES);
            onCreate(db);
        }

        public boolean addItemToTable(String tableName, String itemName, int imageId, String[] quantityString, int[] quantityInt, int price){
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_NAME,itemName);
            contentValues.put(COLUMN_NAME_IMAGE_ID,imageId);
            contentValues.put(COLUMN_NAME_QUANTITY_ARRAY_STRING,convertArrayToString(quantityString));
            contentValues.put(COLUMN_NAME_QUANTITY_ARRAY_INTEGER,convertArrayToString(convertIntArrayToStringArray(quantityInt)));
            contentValues.put(COLUMN_NAME_PRICE,price);
            sqLiteDatabase.insert(tableName,null,contentValues);
            sqLiteDatabase.close();
            return true;
        }

        ArrayList<String> getItemNamesFromTable(String tableName){
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + tableName;
            Cursor cursor = db.rawQuery(selectQuery,null);
            ArrayList<String> itemNames = new ArrayList<>();
            while(cursor.moveToNext()) {
                String name = cursor.getString(1);
                itemNames.add(name);
            }
            cursor.close();
            return itemNames;
        }
        ArrayList<Integer> getImageIDFromTable(String tableName){
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + tableName;
            Cursor cursor = db.rawQuery(selectQuery,null);
            ArrayList<Integer> imageIDs = new ArrayList<>();
            while(cursor.moveToNext()) {
                int imageID = cursor.getInt(2);
                imageIDs.add(imageID);
            }
            cursor.close();
            return imageIDs;
        }

        ArrayList<String[]> getQuantityStringArray(String tableName){
            //example: a table has 2 elements onions and apples with 4th column being:
            //Onion: "1 KG,2 KG,3KG"
            //Apples: "1 Dozen,2 Dozen"
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + tableName;
            Cursor cursor = db.rawQuery(selectQuery,null);
            ArrayList<String> quantityString = new ArrayList<>();
            while(cursor.moveToNext()) {
                String quantityArrayString = cursor.getString(3);//String may now contain: "1 KG,2 KG,3KG","1 Dozen,2 Dozen"
                quantityString.add(quantityArrayString);
            }
            cursor.close();
            ArrayList<String []> QuantityStringArrayList= new ArrayList<>();
            for (int i = 0;i<quantityString.size();i++){
                QuantityStringArrayList.add(convertStringToArray(quantityString.get(i)));
            }
            return QuantityStringArrayList;
        }

        ArrayList<Integer[]> getQuantiyIntArray(String tableName){
            //example: a table has 2 elements onions and apples with 4th column being:
            //Onion: "1,2,3"
            //Apples: "1,2"
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + tableName;
            Cursor cursor = db.rawQuery(selectQuery,null);
            ArrayList<String> quantityString = new ArrayList<>();
            while(cursor.moveToNext()) {
                String quantityArrayString = cursor.getString(4);//String may now contain: "1,2,3"
                quantityString.add(quantityArrayString);
            }
            cursor.close();
            ArrayList<Integer []> QuantityIntArrayList= new ArrayList<>();
            for (int i = 0;i<quantityString.size();i++){
                QuantityIntArrayList.add(convertStringArrayToIntArray(convertStringToArray(quantityString.get(i))));
            }
            return QuantityIntArrayList;
        }

        ArrayList<Integer> getPriceFromTable(String tableName){
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT * FROM " + tableName;
            Cursor cursor = db.rawQuery(selectQuery,null);
            ArrayList<Integer> prices = new ArrayList<>();
            while(cursor.moveToNext()) {
                int price = cursor.getInt(5);
                prices.add(price);
            }
            cursor.close();
            return prices;
        }

        int numberOfItems(String tableName){
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + tableName;
            Cursor cursor = db.rawQuery(selectQuery,null);
            ArrayList<String> itemNames = new ArrayList<>();
            while(cursor.moveToNext()) {
                String name = cursor.getString(1);
                itemNames.add(name);
            }
            cursor.close();
            return itemNames.size();
        }
    }

    private static String strSeparator = "__,__";
    private static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    private static String[] convertIntArrayToStringArray(int[] intArray){
        int sizeOfArray = intArray.length;
        String[] stringArray = new String[sizeOfArray];
        for (int i = 0;i<sizeOfArray;i++)
            stringArray[i] = String.valueOf(intArray[i]);
        return stringArray;
    }
    private static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
    private static Integer[] convertStringArrayToIntArray(String[] stringArray){
        int sizeOfArray = stringArray.length;
        Integer[] intArray = new Integer[sizeOfArray];
        for (int i = 0;i<sizeOfArray;i++)
            intArray[i] = Integer.parseInt(stringArray[i]);
        return intArray;
    }
}
