package vn.com.linh.appa_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MyProvider extends ContentProvider {

    static  final String AUTHORITY ="vn.com.linh.appa_provider";
    static final String CONTEXT_PROVIDER="contextprovider";
    static final String URL="content://"+AUTHORITY+"/"+CONTEXT_PROVIDER;
    static final Uri CONTENT_URI=Uri.parse(URL);
    static final String PRODUCT_TABLE="Products";
    static final int ONE=1;
    static final int ALL=2;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,PRODUCT_TABLE,ONE);
        uriMatcher.addURI(AUTHORITY,PRODUCT_TABLE+"/#",ALL);
    }
    private SQLiteDatabase db;
    private static HashMap<String,String> PROJECTION_MAP;

    MyProvider(){}

    @Override
    public boolean onCreate() {
        Context context=getContext();
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        if (db==null)
            return false;
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqlite_Builder= new SQLiteQueryBuilder();
        sqlite_Builder.setTables(PRODUCT_TABLE);
        switch (uriMatcher.match(uri)){
            case ALL:
                sqlite_Builder.setProjectionMap(PROJECTION_MAP);
                break;
            case ONE:
                sqlite_Builder.appendWhere("id"+"="+uri.getPathSegments().get(0));
        }
        if (sortOrder==null|| sortOrder=="")
            sortOrder="name";
        Cursor cursor=sqlite_Builder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long number_row =db.insert(PRODUCT_TABLE,"",contentValues);
        if (number_row>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI,number_row);
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        throw new SQLException("Filed to add a record into"+uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    ;
}
