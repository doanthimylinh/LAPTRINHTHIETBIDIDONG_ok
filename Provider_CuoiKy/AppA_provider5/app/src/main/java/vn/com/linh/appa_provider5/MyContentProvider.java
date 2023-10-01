package vn.com.linh.appa_provider5;

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
import java.util.logging.Handler;

public class MyContentProvider extends ContentProvider {

    public static String AUTHORITY="vn.com.linh.appa_provider5";

    public static String TABLE_LOAISANPHAM="LoaiSanPhams";
    public static String TABLE_SANPHAM="SanPhams";

    public static String URL_LOAISANPHAM="content://"+AUTHORITY+"/"+TABLE_LOAISANPHAM;
    public static String URL_SANPHAM="content://"+AUTHORITY+"/"+TABLE_SANPHAM;

    public static final Uri CONTENT_URI_LOAISANPHAM=Uri.parse(URL_LOAISANPHAM);
    public static final Uri CONTENT_URI_SANPHAM=Uri.parse(URL_SANPHAM);

    public static UriMatcher uriMatcher;

    public static final int LOAISANPHAM_ONE=1;
    public static final int LOAISANPHAM_ALL=2;

    public static final int SANPHAM_ONE=3;
    public static final int SANPHAM_ALL=4;

    static {
        uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, TABLE_LOAISANPHAM, LOAISANPHAM_ALL);
        uriMatcher.addURI(AUTHORITY, TABLE_LOAISANPHAM+"#/", LOAISANPHAM_ONE);

        uriMatcher.addURI(AUTHORITY, TABLE_SANPHAM, SANPHAM_ALL);
        uriMatcher.addURI(AUTHORITY, TABLE_SANPHAM+"#/", SANPHAM_ONE);

    }
    private SQLiteDatabase db;
    private  static HashMap<String, String> PROJECTION_MAP;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count=0;
        int uri_matcher=uriMatcher.match(uri);
        switch (uri_matcher){
            case LOAISANPHAM_ALL:
                count=db.delete(TABLE_LOAISANPHAM, selection, selectionArgs);
                break;
            case LOAISANPHAM_ONE:
                String id_LoaiSanPham=uri.getPathSegments().get(1);
                count=db.delete(TABLE_LOAISANPHAM, "id= "+id_LoaiSanPham, selectionArgs);
                break;
            case SANPHAM_ALL:
                count=db.delete(TABLE_SANPHAM,  selection, selectionArgs);
                break;
            case SANPHAM_ONE:
                String id_SanPham=uri.getPathSegments().get(1);
                count=db.delete(TABLE_SANPHAM, "id= "+id_SanPham, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //LoaiSanPham
        long row=db.insert(TABLE_LOAISANPHAM, null, values);
        if (row>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI_LOAISANPHAM, row);
            getContext().getContentResolver().notifyChange(uri1, null) ;
            return uri1;
        }
        //SanPham
        long row2=db.insert(TABLE_SANPHAM, null, values);
        if (row2>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI_SANPHAM, row2);
            getContext().getContentResolver().notifyChange(uri1, null) ;
            return uri1;
        }
        throw  new SQLException("them that bai");
    }

    @Override
    public boolean onCreate() {
        Context context=getContext();
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        if (db==null)
            return false;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder=null;
        int uri_matcher= uriMatcher.match(uri);
        switch (uri_matcher){
            case LOAISANPHAM_ALL:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_LOAISANPHAM);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case LOAISANPHAM_ONE:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_LOAISANPHAM);
                sqLiteQueryBuilder.appendWhere("id= "+uri.getPathSegments().get(1));
                break;
            case SANPHAM_ALL:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_SANPHAM);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case SANPHAM_ONE:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_SANPHAM);
                sqLiteQueryBuilder.appendWhere("id= "+uri.getPathSegments().get(1));
                break;
        }
        if (sortOrder==null||sortOrder==""){
            sortOrder="name";
        }
        Cursor cursor=sqLiteQueryBuilder.query(db, projection,selection,selectionArgs,null, null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count=0;
        int uri_matcher=uriMatcher.match(uri);
        switch (uri_matcher){
            case LOAISANPHAM_ALL:
                count= db.update(TABLE_LOAISANPHAM,values,selection,selectionArgs);
                break;
            case LOAISANPHAM_ONE:
                String id_lopHoc=uri.getPathSegments().get(1);
                count=db.update(TABLE_LOAISANPHAM, values,"id= "+id_lopHoc,selectionArgs);
                break;
            case SANPHAM_ALL:
                count= db.update(TABLE_SANPHAM,values,selection,selectionArgs);
                break;
            case SANPHAM_ONE:
                String id_sinhVien=uri.getPathSegments().get(1);
                count=db.update(TABLE_SANPHAM, values,"id= "+id_sinhVien,selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}