package com.sias.znwy.activity;

import com.sias.znwy.R;
import com.sias.znwy.demo.YHRWBean;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class YHTZDetailActivity extends Activity {
	private YHRWBean bean;
	private TextView beginStation, endStation, taskNumber, taskType, taskName;
	private EditText DbName, unitName, personName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yhtz_detail);
		bean = (YHRWBean) getIntent().getSerializableExtra("data");
		beginStation = (TextView) findViewById(R.id.beginStation);
		endStation = (TextView) findViewById(R.id.endStation);
		taskNumber = (TextView) findViewById(R.id.MTaskNumber);
		taskType = (TextView) findViewById(R.id.mTaskType);
		taskName = (TextView) findViewById(R.id.mTaskName);
		DbName = (EditText) findViewById(R.id.businessName);
		unitName = (EditText) findViewById(R.id.supervisionUnitName);
		personName = (EditText) findViewById(R.id.SuperviseStaffName);
		setData();
	}

	/**
	 * 设置数据
	 * 
	 * 
	 * @author lkx Created at 2015-12-26 上午9:43:00
	 */
	private void setData() {
		beginStation.setText(bean.getKszh());
		endStation.setText(bean.getJszh());
		taskNumber.setText(bean.getRwbh());
		int type = Integer.parseInt(bean.getYwlx());
		switch (type) {
		case 1:
			taskType.setText("上路巡查");
			break;

		case 2:
			taskType.setText("日常养护");
			break;
		case 3:
			taskType.setText("维修计划");
			break;
		}

		taskName.setText(bean.getRwmc());
		DbName.setText(bean.getFzrxm());
		unitName.setText(bean.getSgdwmc());
		personName.setText(bean.getSprxm());
	}
}
