package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.adapters.ViewPagerAdapter;
import com.example.ui.NonSwipeableViewPager;

public class FullScreenViewPager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.full_screen_view_pager);

		ArrayList<String> screenshotsList = getIntent().getStringArrayListExtra("Arraylist");
		int position = getIntent().getIntExtra("position", 0);
		NonSwipeableViewPager screenShots = (NonSwipeableViewPager) findViewById(R.id.screenshotviewpager);
		ViewPagerAdapter adapter = new ViewPagerAdapter(this, screenshotsList);
		screenShots.setAdapter(adapter);
		screenShots.setCurrentItem(position);
	}
}
