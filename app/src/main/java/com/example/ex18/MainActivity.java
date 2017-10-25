package com.example.ex18;

import java.io.File;
import java.util.Random;
 
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	String[] files = new String[]{
			"https://www.dropbox.com/s/m3sphvzt85virph/Boston%20City%20Flow.jpg?dl=1",
			"https://www.dropbox.com/s/bkvpgr2e1tctjkc/Costa%20Rican%20Frog.jpg?dl=1",
			"https://www.dropbox.com/s/knl90p2l15frns1/Pensive%20Parakeet.jpg?dl=1"};
	
	String outputFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		outputFile = getFilesDir().getPath() + "/image.jpg";
		Button btn = (Button)findViewById(R.id.btnChange);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Random rnd = new Random();
				String inputFile =  files[rnd.nextInt(files.length)];
				Intent intent = new Intent(MainActivity.this,Downloader.class);
				intent.putExtra("source", inputFile);
				intent.putExtra("destination", outputFile);
				startService(intent);
			}
		});
		
		if (new File(outputFile).exists()){
			ImageView img = (ImageView)findViewById(R.id.img);
			img.setImageBitmap(BitmapFactory.decodeFile(outputFile));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
