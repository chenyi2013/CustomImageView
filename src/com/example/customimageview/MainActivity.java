package com.example.customimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private CustomImageView mImageView;
	private Button mScaleUp;
	private Button mScaleDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (CustomImageView) findViewById(R.id.img);
		mScaleUp = (Button) findViewById(R.id.scale_up);
		mScaleUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mImageView.scaleUp();

			}
		});
		mScaleDown = (Button) findViewById(R.id.scale_down);
		mScaleDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mImageView.scaleDown();

			}
		});
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
