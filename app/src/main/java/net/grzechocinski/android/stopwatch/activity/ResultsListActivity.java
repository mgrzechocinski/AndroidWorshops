package net.grzechocinski.android.stopwatch.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import java.util.Arrays;


/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class ResultsListActivity extends ListActivity {

	private ListAdapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				Arrays.asList("aaa", "bbbb", "ccc", "dddd", "aaa", "bbbb", "ccc", "dddd", "aaa", "bbbb", "ccc", "dddd"));
		setListAdapter(adapter);
	}
}