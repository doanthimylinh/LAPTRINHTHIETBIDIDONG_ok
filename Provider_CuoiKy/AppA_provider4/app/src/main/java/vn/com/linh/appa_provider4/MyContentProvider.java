package vn.com.linh.appa_provider4;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {

    public static String AUTHORIRY="vn.com.linh.appa_provider4";

    public static String TABLE_LOPHOC="LopHocs";
    public static String TABLE_SINHVIEN="SinhViens";

    public static String URL_LOPHOC="content://"+AUTHORIRY+"/"+TABLE_LOPHOC;
    public static String URL_SINHVIEN="content://"+AUTHORIRY+"/"+TABLE_SINHVIEN;

    public static final Uri CONTENT_URI_LOPHOC=Uri.parse(URL_LOPHOC);
    public static final Uri CONTENT_URI_SINHVIEN= Uri.parse(URL_SINHVIEN);

    public static UriMatcher uriMatcher;

    public static final int LOPHOC_ONE=1;
    public static final int LOPHOC_ALL=2;

    public static final int SINHVIEN_ONE=3;
    public static final int SINHVIEN_ALL=4;

    static {
        uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORIRY, TABLE_LOPHOC, LOPHOC_ALL);
        uriMatcher.addURI(AUTHORIRY, TABLE_LOPHOC +"/#", LOPHOC_ONE);

        uriMatcher.addURI(AUTHORIRY, TABLE_SINHVIEN, SINHVIEN_ALL);
        uriMatcher.addURI(AUTHORIRY, TABLE_SINHVIEN +"/#", SINHVIEN_ONE);
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
           case LOPHOC_ALL:
               count= db.delete(TABLE_LOPHOC,selection,selectionArgs);
               break;
           case LOPHOC_ONE:
               String id_lopHoc=uri.getPathSegments().get(1);
               count=db.delete(TABLE_LOPHOC, "id= "+id_lopHoc,selectionArgs);
               break;
           case SINHVIEN_ALL:
               count= db.delete(TABLE_SINHVIEN,selection,selectionArgs);
               break;
           case SINHVIEN_ONE:
               String id_sinhVien=uri.getPathSegments().get(1);
               count=db.delete(TABLE_SINHVIEN, "id= "+id_sinhVien,selectionArgs);
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

        //LopHoc
        long row=db.insert(TABLE_LOPHOC, null, values);
        if (row>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI_LOPHOC, row);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }
        //SinhVien
        long row2=db.insert(TABLE_SINHVIEN, null, values);
        if (row2>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI_SINHVIEN, row2);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }
        throw new SQLException("Them khong thanh cong");
    }

    @Override
    public boolean onCreate() {
        Context context=getContext();
        DBHelper dbHelper= new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        if (db==null)
            return false;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder=null;
        int uri_matcher=uriMatcher.match(uri);

        switch (uri_matcher){
            case LOPHOC_ALL:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_LOPHOC);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case LOPHOC_ONE:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_LOPHOC);
                sqLiteQueryBuilder.appendWhere("id= "+uri.getPathSegments().get(1));
                break;
            case SINHVIEN_ALL:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_SINHVIEN);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case SINHVIEN_ONE:
                sqLiteQueryBuilder= new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_SINHVIEN);
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
            case LOPHOC_ALL:
                count= db.update(TABLE_LOPHOC,values,selection,selectionArgs);
                break;
            case LOPHOC_ONE:
                String id_lopHoc=uri.getPathSegments().get(1);
                count=db.update(TABLE_LOPHOC, values,"id= "+id_lopHoc,selectionArgs);
                break;
            case SINHVIEN_ALL:
                count= db.update(TABLE_SINHVIEN,values,selection,selectionArgs);
                break;
            case SINHVIEN_ONE:
                String id_sinhVien=uri.getPathSegments().get(1);
                count=db.update(TABLE_SINHVIEN, values,"id= "+id_sinhVien,selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;

    }
}