package vn.com.ngoc.myapplication;

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

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {
    static  final String AUTHORITY="vn.com.ngoc.myapplication";
    static  final String PRODUCT_TABLE="Products";
    static  final String URL="content://"+AUTHORITY+"/"+PRODUCT_TABLE;
    static  final Uri CONTENT_URI=Uri.parse(URL);

    static final int ONE=1;
    static final int ALL=2;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
        // content://vn.com.ngoc.myapplication/Products
        uriMatcher.addURI(AUTHORITY,PRODUCT_TABLE, ALL);
        uriMatcher.addURI(AUTHORITY,PRODUCT_TABLE+"/#",ONE);
    }
    private SQLiteDatabase db;
    private  static HashMap<String, String> PROJECTION_MAP;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long number_row= db.insert(PRODUCT_TABLE,"",values);
        if(number_row>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI,number_row);
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        throw new SQLException("File to add a record into"+ uri);
    }

    @Override
    public boolean onCreate() {
        Context context= getContext();
        DBHelper dbHelper= new DBHelper(context);
        db= dbHelper.getWritableDatabase();
        if(db== null)
            return true;
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder= new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(PRODUCT_TABLE);
        switch (uriMatcher.match(uri)){
            case ALL:
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case ONE:
                sqLiteQueryBuilder.appendWhere("id"+"="+uri.getPathSegments().get(0));

        }
        if(sortOrder== null || sortOrder=="")
            sortOrder="name";
        Cursor cursor = sqLiteQueryBuilder.query(db,projection,selection, selectionArgs,null, null, sortOrder );
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}