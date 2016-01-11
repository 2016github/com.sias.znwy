package com.sias.znwy.activity;

import java.io.Serializable;

import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitle;
import com.sias.znwy.demo.GZJHBean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class GZJHDetailActivity extends Activity implements OnClickListener {
	private ActivityTitle detailTitle;
	private GZJHBean gzjhBean;
	private TextView taskName, managermentName, jobName;
	private EditText zhuangHao, miShu, cheDaoHao, gzMemo, gzMeasure;
	private Spinner drivingDirection, diseaseOne, diseaseTwo, diseaseThree, weather, Find;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gzjhdetail);
		detailTitle = (ActivityTitle) findViewById(R.id.gzjhDetailActivityTitle);
		gzjhBean = (GZJHBean) getIntent().getSerializableExtra("data");
		detailTitle.setItemName("工作计划浏览");
		detailTitle.setRightNameVisiable(View.VISIBLE);
		detailTitle.setItemRightName("上报");
		detailTitle.titleRightText.setOnClickListener(this);
		initView();
		setDate();
	}

	/**
	 * 初始化绑定控件
	 * 
	 * 
	 * @author lkx Created at 2016-1-9 下午3:36:05
	 */
	private void initView() {
		taskName = (TextView) findViewById(R.id.gzjhDetailTaskName);
		managermentName = (TextView) findViewById(R.id.gzjhDetailCompanyName);
		jobName = (TextView) findViewById(R.id.gzjhDetailJob);
		zhuangHao = (EditText) findViewById(R.id.pileNumber);
		miShu = (EditText) findViewById(R.id.gzjhDetailMiNumber);
		cheDaoHao = (EditText) findViewById(R.id.cartLaneMiNumber);
		gzMemo = (EditText) findViewById(R.id.gzjhDetailMemo);
		gzMeasure = (EditText) findViewById(R.id.gzjhDetailMeasurement);
		drivingDirection = (Spinner) findViewById(R.id.drivingDirection);
		diseaseOne = (Spinner) findViewById(R.id.conditionParent);
		diseaseTwo = (Spinner) findViewById(R.id.conditionSecond);
		diseaseThree = (Spinner) findViewById(R.id.conditionThird);
		weather = (Spinner) findViewById(R.id.gzjhWeatherSpinner);
		Find = (Spinner) findViewById(R.id.gzjhWingSpinner);

	}

	/**
	 * 初始化数据
	 * 
	 * 
	 * @author lkx Created at 2016-1-9 上午10:48:08
	 */
	private void setDate() {
		taskName.setText(gzjhBean.getRwmc());
		managermentName.setText(gzjhBean.getDwmc());
		jobName.setText(gzjhBean.getGw());
		zhuangHao.setText(gzjhBean.getZh());
		miShu.setText(gzjhBean.getMs());
		cheDaoHao.setText(gzjhBean.getCdh());
		gzMemo.setText(gzjhBean.getGzgy());
		gzMeasure.setText(gzjhBean.getGcgl());

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.activity_title_rightText:
			commitData();
			break;

		}

	}

	/**
	 * 提交数据
	 * 
	 * 
	 * @author lkx Created at 2016-1-9 上午10:46:39
	 */
	private void commitData() {
		// TODO Auto-generated method stub

	}
}
