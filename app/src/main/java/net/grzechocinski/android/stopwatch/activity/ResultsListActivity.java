package net.grzechocinski.android.stopwatch.activity;

import static net.grzechocinski.android.stopwatch.provider.ResultsMetadata.ResultsTableMetaData.COLUMN_CREATED_DATE;
import static net.grzechocinski.android.stopwatch.provider.ResultsMetadata.ResultsTableMetaData.COLUMN_RESULT;
import static net.grzechocinski.android.stopwatch.provider.ResultsMetadata.ResultsTableMetaData.CONTENT_URI;

import android.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import net.grzechocinski.android.stopwatch.util.Formatter;


/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class ResultsListActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Cursor cursor = managedQuery(CONTENT_URI, null, null, null, null);

		String[] from = {COLUMN_RESULT, COLUMN_CREATED_DATE};
		int[] displayViews = new int[]{android.R.id.text1, android.R.id.text2};

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.simple_list_item_2, cursor, from, displayViews);
		adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			@Override
			public boolean setViewValue(View view, Cursor cursor, int column) {
				if (column == 1) {
					TextView tv = (TextView) view;
					Long resultValueInMillis = cursor.getLong(cursor.getColumnIndex(COLUMN_RESULT));
					tv.setText(Formatter.formatElapsed(resultValueInMillis));
					return true;
				} else if (column == 2) {
					TextView tv = (TextView) view;
					Long createdDate = cursor.getLong(cursor.getColumnIndex(COLUMN_CREATED_DATE));
					tv.setText(Formatter.formatDateTime(createdDate));
					return true;
				}
				return false;
			}
		});
		setListAdapter(adapter);
	}
}