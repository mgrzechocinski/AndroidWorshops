package net.grzechocinski.android.stopwatch.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 14/10/2011
 */
public class ResultsMetadata {

	public static final String AUTHORITY = "net.grzechocinski.android.stopwatch.provider.ResultsProvider";

	public static final String DB_NAME = "results.db";

	public static final int DB_VERSION = 1;

	private ResultsMetadata() {
	}

	public static final class ResultsTableMetaData implements BaseColumns {

		private ResultsTableMetaData() {
		}

		public static final String TABLE_NAME = "results";

		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/results");

		public static final String COLUMN_RESULT = "result";

		public static final String COLUMN_CREATED_DATE = "created";

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.stopwatch.results";
	}

}
