package com.example.adapters;

import java.util.ArrayList;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.test.R;
import com.example.test.R.id;
import com.example.test.R.layout;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class Images_Adapter extends BaseAdapter {

	private LayoutInflater mInflater;
	int columnIndex;
	DisplayImageOptions defaultOptions;
	Context mcon;
	ArrayList<String> mArr;

	public Images_Adapter(Context context, ArrayList<String> list) {

		mcon = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
		defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true)
				.cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY)
				.resetViewBeforeLoading(true)
				.displayer(new FadeInBitmapDisplayer(300)).build();
		this.mArr = list;
	}

	private class ViewHolder {

		ImageView icon;

	}

	@Override
	public int getCount() {
		return mArr.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.grid_row, arg2, false);

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		/*
		 * int imageID = cursor.getInt(columnIndex); Uri u = Uri
		 * .withAppendedPath( MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
		 * "" + imageID);
		 */
		String path = mArr.get(arg0);
		ImageLoader.getInstance().displayImage(path, holder.icon,
				defaultOptions);
		return convertView;
	}
}
