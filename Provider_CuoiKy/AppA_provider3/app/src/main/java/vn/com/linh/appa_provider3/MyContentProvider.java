package vn.com.linh.appa_provider3;

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

    public static String AUTHORITY ="vn.com.linh.appa_provider3";

    public static String TABLE_PHONGBAN="PhongBans";
    public static String TABLE_NHANVIEN="NhanViens";

    public static String URL_PHONGBAN="content://"+AUTHORITY+"/"+TABLE_PHONGBAN;
    public static String URL_NHANVIEN="content://"+AUTHORITY+"/"+TABLE_NHANVIEN;

    public static final Uri CONTENT_URI_PHONGBAN=Uri.parse(URL_PHONGBAN);
    public static final Uri CONTENT_URI_NHANVIEN=Uri.parse(URL_NHANVIEN);

    public static UriMatcher uriMatcher;

    public static final int PHONGBAN_ONE=1;
    public static final int PHONGBAN_ALL=2;

    public static final int NHANVIEN_ONE=3;
    public static final int NHANVIEN_ALL=4;

    static {
        uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, TABLE_PHONGBAN, PHONGBAN_ALL);
        uriMatcher.addURI(AUTHORITY, TABLE_PHONGBAN+"/#", PHONGBAN_ONE);

        uriMatcher.addURI(AUTHORITY, TABLE_NHANVIEN, NHANVIEN_ALL);
        uriMatcher.addURI(AUTHORITY, TABLE_NHANVIEN+"/#", NHANVIEN_ONE);
    }
    private SQLiteDatabase db;
    private static HashMap<String,String> PROJECTION_MAP;
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count=0;
        int uri_matcher=uriMatcher.match(uri);
        switch (uri_matcher){
            case PHONGBAN_ALL:
                count=db.delete(TABLE_PHONGBAN, selection,selectionArgs);
                break;
            case PHONGBAN_ONE:
                String id_phongBan=uri.getPathSegments().get(1);
                count=db.delete(TABLE_PHONGBAN,"id= "+id_phongBan,selectionArgs);
                break;
            case NHANVIEN_ALL:
                count=db.delete(TABLE_NHANVIEN, selection,selectionArgs);
                break;
            case NHANVIEN_ONE:
                String id_nhanVien=uri.getPathSegments().get(1);
                count=db.delete(TABLE_NHANVIEN,"id= "+id_nhanVien,selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
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
        //Phong Ban
        long row=db.insert(TABLE_PHONGBAN, null, values);
        if (row>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI_PHONGBAN, row);
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        //NhanVien
        long row2=db.insert(TABLE_NHANVIEN, null, values);
        if (row2>0){
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI_NHANVIEN, row);
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        throw new SQLException("Them khong thanh cong");

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Context context= getContext();
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
            case PHONGBAN_ALL:
                sqLiteQueryBuilder=new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_PHONGBAN);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case PHONGBAN_ONE:
                sqLiteQueryBuilder=new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_PHONGBAN);
                sqLiteQueryBuilder.appendWhere("id= "+uri.getPathSegments().get(1));
                break;
            case NHANVIEN_ALL:
                sqLiteQueryBuilder=new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_NHANVIEN);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case NHANVIEN_ONE:
                sqLiteQueryBuilder=new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(TABLE_NHANVIEN);
                sqLiteQueryBuilder.appendWhere("id= "+uri.getPathSegments().get(1));
                break;
        }
        if (sortOrder==null || sortOrder==""){
            sortOrder="name";
        }
        Cursor cursor=sqLiteQueryBuilder.query(db, projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count=0;
        int uri_matcher=uriMatcher.match(uri);
        switch (uri_matcher){
            case PHONGBAN_ALL:
                count=db.update(TABLE_PHONGBAN, values,selection,selectionArgs);
                break;
            case PHONGBAN_ONE:
                String id_phongBan=uri.getPathSegments().get(1);
                count=db.update(TABLE_PHONGBAN,values, "id= "+id_phongBan, selectionArgs);
                break;
            case NHANVIEN_ALL:
                count=db.update(TABLE_NHANVIEN, values,selection,selectionArgs);
                break;
            case NHANVIEN_ONE:
                String id_nhanVien=uri.getPathSegments().get(1);
                count=db.update(TABLE_NHANVIEN,values,"id= "+id_nhanVien,selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}