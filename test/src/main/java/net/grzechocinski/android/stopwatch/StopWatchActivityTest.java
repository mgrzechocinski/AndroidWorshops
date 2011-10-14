package net.grzechocinski.android.stopwatch;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import net.grzechocinski.android.stopwatch.activity.StopWatchActivity;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 15/10/2011
 */
public class StopWatchActivityTest extends ActivityInstrumentationTestCase2<StopWatchActivity> {

	private StopWatchActivity activity;

	private Button startButton;

	private String resourceString;

	public StopWatchActivityTest() {
		super("net.grzechocinski.android.stopwatch", StopWatchActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = this.getActivity();
		startButton = (Button) activity.findViewById(R.id.button_start);
		resourceString = activity.getString(R.string.button_start);
	}

	public void testPreconditions() {
		assertNotNull(startButton);
	}

	public void testText() {
		assertEquals(resourceString, (String) startButton.getText());
	}
}
