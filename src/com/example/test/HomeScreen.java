package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.example.adapters.Images_Adapter;

public class HomeScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		GridView gridView = (GridView) findViewById(R.id.sdcard);
		ArrayList<String> mArrlist = getPath();
		Images_Adapter mAdapter = new Images_Adapter(this, mArrlist);
		gridView.setAdapter(mAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				Intent next = new Intent(HomeScreen.this,
						FullScreenViewPager.class);
				next.putStringArrayListExtra("Arraylist", getPath());
				next.putExtra("position", position);
				startActivity(next);

			}

		});

	}

	public ArrayList<String> getPath() {
		Cursor cursor;

		ArrayList<String> s = new ArrayList<String>();

		cursor = getContentResolver().query(
				MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, null, null,
				null, null);

		if (cursor != null) {
			for (int i = 0; i < 50 && cursor.moveToNext(); i++) {

				s.add("file://"
						+ cursor.getString(cursor
								.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA)));

			}
		}
		return s;

	}

}
