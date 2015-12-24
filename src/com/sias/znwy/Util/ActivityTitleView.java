package com.sias.znwy.Util;

import com.sias.znwy.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActivityTitleView extends RelativeLayout {
	private TextView simpleItem;
	private TextView titleRightText;

	public ActivityTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ActivityTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActivityTitleView);
		String itemName = a.getString(R.styleable.ActivityTitleView_ActivityTitleViewName);
		String itemRightName = a.getString(R.styleable.ActivityTitleView_ActivityTitleViewRightText);
		int visible = a.getInt(R.styleable.ActivityTitleView_Visiable, 0);
		if (visible == 1) {
			titleRightText.setVisibility(View.VISIBLE);
		} else {
			titleRightText.setVisibility(View.GONE);
		}
		setItemName(itemName);
		setItemRightName(itemRightName);
		a.recycle();
	}

	private void setItemName(String itemName) {
		simpleItem.setText(itemName);

	}

	private void setItemRightName(String itemName) {
		titleRightText.setText(itemName);

	}

	public ActivityTitleView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(final Context context) {
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
}
