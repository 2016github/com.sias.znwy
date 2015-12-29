package com.sias.znwy.Util;

import com.sias.znwy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActivityTitle extends RelativeLayout {

	public TextView simpleItem;
	public TextView titleRightText;

	public ActivityTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr);

	}

	public ActivityTitle(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ActivityTitle(final Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) {
			return;
		}
		// TypedArray a = context.obtainStyledAttributes(attrs,
		// R.styleable.ActivityTitleView);
		// String itemName =
		// a.getString(R.styleable.ActivityTitleView_ActivityTitleViewName);
		// String itemRightName =
		// a.getString(R.styleable.ActivityTitleView_ActivityTitleViewRightText);
		// int visible = a.getInt(R.styleable.ActivityTitleView_Visiable, 0);
		// if (visible == 1) {
		// titleRightText.setVisibility(View.VISIBLE);
		// } else {
		// titleRightText.setVisibility(View.GONE);
		// }
		// setItemName(itemName);
		// setItemRightName(itemRightName);
		View view = View.inflate(context, R.layout.util_activity_title, this);
		simpleItem = (TextView) view.findViewById(R.id.activity_title);
		titleRightText = (TextView) view.findViewById(R.id.activity_title_rightText);
		view.findViewById(R.id.text_return).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				((Activity) context).finish();

			}
		});

	}

	public ActivityTitle(Context context) {
		super(context);
		if (isInEditMode()) {
			return;
		}
	}

	public void setItemName(String itemName) {
		simpleItem.setText(itemName);

	}

	public void setRightNameVisiable(int visibility) {
		titleRightText.setVisibility(visibility);

	}

	public void setItemRightName(String itemName) {
		titleRightText.setText(itemName);

	}

	public void setViewVersible(View... views) {
		for (View view : views) {
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setViewGone(View... views) {
		for (View view : views) {
			if (view != null) {
				view.setVisibility(View.GONE);
			}
		}
	}

	// public void refreshstart() {
	//
	// titleRefresh.setAnimation(animation);
	// animation.start();
	//
	// }
	//
	// public void refreshstop() {
	// animation.cancel();
	//
	// }

}
