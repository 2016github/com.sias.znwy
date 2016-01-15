package com.sias.znwy.activity;

import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 写日结界面
 * 
 */
public class XRJActivity extends Activity implements OnClickListener {
	private ActivityTitle title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xrj);
		intiView();
	}

	private void intiView() {
		title = (ActivityTitle) findViewById(R.id.xrjActivityTitle);
		title.setItemName("填写工作日结");
		title.setRightNameVisiable(View.VISIBLE);
		title.setItemRightName("提交");
		title.titleRightText.setOnClickListener(this);
		findViewById(R.id.locationBtn).setOnClickListener(this);
		findViewById(R.id.addRecord).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_title_rightText:
			// 提交日结
			break;

		case R.id.locationBtn:
			// 重新定位
			break;
		case R.id.addRecord:
			// 添加记录
			break;

		}

	}
}
