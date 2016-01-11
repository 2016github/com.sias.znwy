package com.sias.znwy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitle;
import com.sias.znwy.Util.RequestTools;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.demo.TZTGBean;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.WebParam;

public class TZTGDetailActivity extends Activity {
	private TextView itemName, tztgDetailResource, tztgDetailPublishTime, tztgDetailMemo, tztgDetailPersonCharge, tztgDetailPhone,
			tztgDetailSignPerson, tztgDetailSignTime;
	private ActivityTitle title;
	private RelativeLayout signLayout, finishSignLayout;
	private TZTGBean bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tztg_detail);
		title.setItemName("通知通告浏览");
		title.setRightNameVisiable(View.GONE);
		bean = (TZTGBean) getIntent().getSerializableExtra("data");
		initView();
	}

	/**
	 * 初始化绑定数据
	 * 
	 * 
	 * @author lkx Created at 2016-1-11 下午4:21:09
	 */
	private void initView() {
		itemName = (TextView) findViewById(R.id.tztgDetailName);
		tztgDetailResource = (TextView) findViewById(R.id.tztgDetailResource);
		tztgDetailPublishTime = (TextView) findViewById(R.id.tztgDetailPublishTime);
		tztgDetailMemo = (TextView) findViewById(R.id.tztgDetailMemo);
		tztgDetailPersonCharge = (TextView) findViewById(R.id.tztgDetailPersonCharge);
		tztgDetailPhone = (TextView) findViewById(R.id.tztgDetailPhone);
		tztgDetailSignPerson = (TextView) findViewById(R.id.tztgDetailSignPerson);
		tztgDetailSignTime = (TextView) findViewById(R.id.tztgDetailSignTime);
		signLayout = (RelativeLayout) findViewById(R.id.noSignLauout);
		finishSignLayout = (RelativeLayout) findViewById(R.id.finishSignlayout);
		findViewById(R.id.tztgDetailBtnSign).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				commitData();

			}

		});
		setData(bean);

	}

	/**
	 * 通知通告签收
	 * 
	 * 
	 * @author lkx Created at 2016-1-11 下午4:51:32
	 */
	private void commitData() {
		new RequestTools().request(
				WebParam.create().addParam("jklx", "sjqdcx").addParam("yhdh", UserInfo.getYhdh()).addParam("sjbs", UserInfo.getDeviceId())
						.addParam("id", bean.getId()), new OnResultListener() {

					@Override
					public void onResult(boolean isSuccess, int errorCode, Object obj) {
						if (isSuccess) {

						} else {
							Toast.makeText(TZTGDetailActivity.this, obj.toString(), Toast.LENGTH_SHORT).show();
						}

					}
				});

	}

	/**
	 * 初始化数据
	 * 
	 * @param bean2
	 * 
	 * @author lkx Created at 2016-1-11 下午4:43:27
	 */
	private void setData(TZTGBean bean2) {

		itemName.setText(bean2.getShhGsl().getBt());
		tztgDetailResource.setText(bean2.getShhGsl().getFbr());
		tztgDetailPublishTime.setText(bean2.getShhGsl().getFbsj());
		tztgDetailPersonCharge.setText(bean2.getShhGsl().getFzr());
		tztgDetailPhone.setText(bean2.getShhGsl().getFzrlxdh());
		if (bean2.getQsbj().equals("0")) {
			// 待签收状态
			signLayout.setVisibility(View.VISIBLE);
			finishSignLayout.setVisibility(View.GONE);
		} else {
			// 已经签收
			signLayout.setVisibility(View.GONE);
			finishSignLayout.setVisibility(View.VISIBLE);
			tztgDetailSignPerson.setText(bean2.getQsr());

			tztgDetailSignTime.setText(bean2.getQssj());
		}

	}
}
