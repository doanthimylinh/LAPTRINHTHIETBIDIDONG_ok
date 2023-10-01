package com.mhxx307.ontapcontentprovider_27_5_2022;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {
    public static String AUTHORITY = "com.mhxx307.ontapcontentprovider_27_5_2022";

    public static String LOAI_SAN_PHAM_TABLE = "loai_san_pham";
    public static String SAN_PHAM_TABLE = "san_pham";

    public static String LOAI_SAN_PHAM_URL = "content://" + AUTHORITY + "/" + LOAI_SAN_PHAM_TABLE;
    public static String SAN_PHAM_URL = "content://" + AUTHORITY + "/" + SAN_PHAM_TABLE;

    public static Uri LOAI_SAN_PHAM_URI = Uri.parse(LOAI_SAN_PHAM_URL);
    public static Uri SAN_PHAM_URI = Uri.parse(SAN_PHAM_URL);

    public static final int LOAI_SAN_PHAM_ONE = 111;
    public static final int LOAI_SAN_PHAM_ALL = 112;
    public static final int SAN_PHAM_ONE = 113;
    public static final int SAN_PHAM_ALL = 114;

    public static UriMatcher uriMatcher;
    private SQLiteDatabase db;

    public static HashMap<String, String> PROJECTION_MAP;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, LOAI_SAN_PHAM_TABLE, LOAI_SAN_PHAM_ALL);
        uriMatcher.addURI(AUTHORITY, LOAI_SAN_PHAM_TABLE + "/#", LOAI_SAN_PHAM_ONE);

        uriMatcher.addURI(AUTHORITY, SAN_PHAM_TABLE, SAN_PHAM_ALL);
        uriMatcher.addURI(AUTHORITY, SAN_PHAM_TABLE + "/#", SAN_PHAM_ONE);
    }


    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        db = databaseHelper.getWritableDatabase();
        if (db == null) {
            return false;
        }
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = db.insert(LOAI_SAN_PHAM_TABLE, null, values);
        if (row > 0) {
            Uri uri1 = ContentUris.withAppendedId(LOAI_SAN_PHAM_URI, row);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }

        long row1 = db.insert(SAN_PHAM_TABLE, null, values);
        if (row1 > 0) {
            Uri uri1 = ContentUris.withAppendedId(SAN_PHAM_URI, row);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = null;

        switch (uriMatcher.match(uri)) {
            case LOAI_SAN_PHAM_ALL:
                sqLiteQueryBuilder = new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(LOAI_SAN_PHAM_TABLE);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case LOAI_SAN_PHAM_ONE:
                sqLiteQueryBuilder = new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(LOAI_SAN_PHAM_TABLE);
                sqLiteQueryBuilder.appendWhere("id="+uri.getPathSegments().get(1));
                break;
            case SAN_PHAM_ALL:
                sqLiteQueryBuilder = new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(SAN_PHAM_TABLE);
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case SAN_PHAM_ONE:
                sqLiteQueryBuilder = new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(SAN_PHAM_TABLE);
                sqLiteQueryBuilder.appendWhere("id="+uri.getPathSegments().get(1));
                break;
        }

        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int count = 0;

        switch (uriMatcher.match(uri)) {
            case LOAI_SAN_PHAM_ONE:
                count = db.update(LOAI_SAN_PHAM_TABLE, values, "id="+uri.getPathSegments().get(1), selectionArgs);
                break;
            case SAN_PHAM_ONE:
                count = db.update(SAN_PHAM_TABLE, values, "id="+uri.getPathSegments().get(1), selectionArgs);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case LOAI_SAN_PHAM_ONE:
                count = db.delete(LOAI_SAN_PHAM_TABLE, "id="+uri.getPathSegments().get(1), selectionArgs);
                break;
            case SAN_PHAM_ONE:
                count = db.delete(SAN_PHAM_TABLE, "id="+uri.getPathSegments().get(1), selectionArgs);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}