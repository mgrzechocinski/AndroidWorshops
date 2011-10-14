package net.grzechocinski.android.stopwatch.activity;

import android.R;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.SimpleCursorAdapter;


/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class ResultsListActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ContentResolver contentResolver = getContentResolver();

		Cursor cursor = managedQuery(Settings.System.CONTENT_URI, null, null, null, null);
		// Get the list view
		String[] from = {Settings.System.NAME, Settings.System.VALUE};
		int[] displayViews = new int[]{android.R.id.text1, android.R.id.text2};

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.simple_list_item_2, cursor, from, displayViews);
		setListAdapter(adapter);
	}
}