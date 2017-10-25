package com.example.ex18;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.RecoverySystem;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
	// TODO: Rename actions, choose action names that describe tasks that this
	// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
	private static final String ACTION_RUN_CLOCK = "com.example.service.action.RUN_CLOCK";
	private static final String ACTION_BAZ = "com.example.service.action.BAZ";

	// TODO: Rename parameters
	private static final String EXTRA_PARAM1 = "com.example.service.extra.PARAM1";
	private static final String EXTRA_PARAM2 = "com.example.service.extra.PARAM2";
	
	private static Intent myIntent=null; 
	private static boolean goClock = false;

	/**
	 * Starts this service to perform action Foo with the given parameters. If
	 * the service is already performing a task this action will be queued.
	 *
	 * @see IntentService
	 */
	// TODO: Customize helper method
	public static void startMyClock(Context context, int howMany) {
		myIntent = new Intent(context, MyIntentService.class);
		myIntent.setAction(ACTION_RUN_CLOCK);
		myIntent.putExtra(EXTRA_PARAM1, howMany);
		goClock = true;
		context.startService(myIntent);
	}

	public static void stopMyClock(Context context) {
		if (myIntent != null){
			boolean stopped = context.stopService(myIntent);
			myIntent = null;
//			MyNotification.stop(MyNotification.NOTIFICATION_ID1);
		}
	}

	 @Override
	    public void onDestroy() { //this override is a result of a "bug" in stopService of IntentService
		 	goClock = false;
	        super.onDestroy();
	    }
	

	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if (intent != null) {
			final String action = intent.getAction();
			if (ACTION_RUN_CLOCK.equals(action)) {
				final int howMany = intent.getIntExtra(EXTRA_PARAM1,10);
				handleRunClock(howMany);
			} else if (ACTION_BAZ.equals(action)) {
				final String param1 = intent.getStringExtra(EXTRA_PARAM1);
				final String param2 = intent.getStringExtra(EXTRA_PARAM2);
				handleActionBaz(param1, param2);
			}
		}
	}

	/**
	 * Handle action Foo in the provided background thread with the provided
	 * parameters.
	 */
	private void handleRunClock(int howMany) {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i=0;goClock && i<howMany;i++){
			Date date = new Date();
//			MyNotification.update(MyNotification.NOTIFICATION_ID1, fmt.format(date));		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}
        Log.d("MyService", "MyService command completed");
		Toast.makeText(getApplicationContext(), "MyService command completed", Toast.LENGTH_LONG).show();

	}

	/**
	 * Handle action Baz in the provided background thread with the provided
	 * parameters.
	 */
	private void handleActionBaz(String param1, String param2) {
		// TODO: Handle action Baz
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static void commonTask(URI uri, RecoverySystem.ProgressListener backend){
		backend.onProgress(0);
	}
}
