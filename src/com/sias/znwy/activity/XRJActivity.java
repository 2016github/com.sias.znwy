package com.sias.znwy.activity;

import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * д�ս����
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
		title.setItemName("��д�����ս�");
		title.setRightNameVisiable(View.VISIBLE);
		title.setItemRightName("�ύ");
		title.titleRightText.setOnClickListener(this);
		findViewById(R.id.locationBtn).setOnClickListener(this);
		findViewById(R.id.addRecord).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_title_rightText:
			// �ύ�ս�
			break;

		case R.id.locationBtn:
			// ���¶�λ
			break;
		case R.id.addRecord:
			// ��Ӽ�¼
			break;

		}

	}
}
