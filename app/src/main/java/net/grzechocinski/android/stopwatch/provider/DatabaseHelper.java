package net.grzechocinski.android.stopwatch.provider;

import static net.grzechocinski.android.stopwatch.provider.ResultsMetadata.ResultsTableMetaData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 14/10/2011
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, ResultsMetadata.DB_NAME, null, ResultsMetadata.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + ResultsTableMetaData.TABLE_NAME + " ("
				+ ResultsTableMetaData._ID + " INTEGER PRIMARY KEY,"
				+ ResultsTableMetaData.COLUMN_RESULT + " LONG,"
				+ ResultsTableMetaData.COLUMN_CREATED_DATE + " LONG"
				+ ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int i, int i1) {
		db.execSQL("DROP TABLE IF EXISTS " + ResultsTableMetaData.TABLE_NAME);
	}
}
