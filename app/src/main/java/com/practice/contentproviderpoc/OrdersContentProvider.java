package com.practice.contentproviderpoc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by jinal on 10/28/2016.
 */

public class OrdersContentProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.practice.contentproviderpoc";
    static final String URL = "content://" + PROVIDER_NAME + "/orders";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final int ORDERS = 1;
    static final int ORDER = 2;
    static final int RESTAURANT_TABLES = 3;
    static final int RESTAURANT_TABLE = 4;
    static final int MENU_ITEMS = 5;
    static final int MENU_ITEM = 6;

    SQLiteDatabase db;
    TableOrderManager dbHelper;

    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, TableOrderManager.TABLE_NAME, ORDER);

    }

    @Override
    public boolean onCreate() {
        dbHelper = new TableOrderManager(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
