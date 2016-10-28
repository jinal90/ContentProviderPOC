package com.practice.contentproviderpoc;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER = 0x01;
    TableOrderManager dbHelper;
    ListView list;
    SimpleCursorAdapter adapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TableOrderManager(this);
        db = dbHelper.getWritableDatabase();
        list = (ListView)findViewById(R.id.lvData);

        String[] from = {TableOrderManager.COLUMN_ID, TableOrderManager.COLUMN_TABLE_ID, TableOrderManager.COLUMN_FOOD_ITEM_ID};
        int[] to = {R.id.tvRowID, R.id.tvTableName, R.id.tvOrderName};

        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_layout, null, from,
                to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        list.setAdapter(adapter);

        getSupportLoaderManager().restartLoader(LOADER, null, this);

        Button btnInsert = (Button)findViewById(R.id.btnAdd);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnFetch = (Button)findViewById(R.id.btnFetch);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(TableOrderManager.COLUMN_ID, 1);
                cv.put(TableOrderManager.COLUMN_TABLE_ID, 1);
                cv.put(TableOrderManager.COLUMN_FOOD_ITEM_ID, 1);

                ContentResolver content = getContentResolver();
                content.insert(OrdersContentProvider.CONTENT_URI, cv);
            }
        });

    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] columns = {TableOrderManager.COLUMN_ID, TableOrderManager.COLUMN_TABLE_ID,
                TableOrderManager.COLUMN_FOOD_ITEM_ID};
        CursorLoader cursorLoader = new CursorLoader(this, OrdersContentProvider.CONTENT_URI,
                columns, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
