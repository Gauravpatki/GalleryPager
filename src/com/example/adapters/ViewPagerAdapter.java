package com.example.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;

import com.example.ui.TouchImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ViewPagerAdapter extends PagerAdapter {

	Activity activity;

	ArrayList<String> imageArray;

	DisplayImageOptions defaultOptions;

	public ViewPagerAdapter(Activity act, ArrayList<String> screenshotsList) {

		imageArray = screenshotsList;
		activity = act;

		defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true)
				.cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY)
				.resetViewBeforeLoading(true)
				.displayer(new FadeInBitmapDisplayer(300)).build();
	}

	public int getCount() {

		return imageArray.size();
	}

	public Object instantiateItem(View collection, int position) {

		TouchImageView view = new TouchImageView(activity);

		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		view.setScaleType(ScaleType.FIT_XY);
		view.setAdjustViewBounds(true);

		ImageLoader.getInstance().displayImage(imageArray.get(position), view,
				defaultOptions);
		view.setPadding(1, 1, 1, 1);

		((ViewPager) collection).addView(view, 0);

		return view;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {

		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {

		return null;
	}

}
