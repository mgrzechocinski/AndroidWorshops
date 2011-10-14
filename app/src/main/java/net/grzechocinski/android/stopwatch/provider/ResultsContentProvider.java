package net.grzechocinski.android.stopwatch.provider;

import static net.grzechocinski.android.stopwatch.provider.ResultsMetadata.ResultsTableMetaData.*;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 14/10/2011
 */
public class ResultsContentProvider extends ContentProvider {

	private DatabaseHelper databaseHelper;

	@Override
	public boolean onCreate() {
		databaseHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(TABLE_NAME);
		SQLiteDatabase readableDatabase = databaseHelper.getReadableDatabase();
		return qb.query(readableDatabase, null, null, null, null, null, _ID + " DESC");
	}

	@Override
	public Uri insert(Uri uri, ContentValues contentValues) {

		SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
		long rowId = writableDatabase.insert(TABLE_NAME, null, contentValues);
		if(rowId <= 0){
			throw new IllegalStateException("Exception while storing result");
		}

		Uri insertedUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
		getContext().getContentResolver().notifyChange(insertedUri, null);
		return insertedUri;
	}

	@Override
	public int delete(Uri uri, String s, String[] strings) {
		throw new UnsupportedOperationException("Not yet implemented!");
	}

	@Override
	public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
		throw new UnsupportedOperationException("Not yet implemented!");
	}

	@Override
	public String getType(Uri uri) {
		return CONTENT_TYPE;
	}
}
