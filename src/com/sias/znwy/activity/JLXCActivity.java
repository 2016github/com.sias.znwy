package com.sias.znwy.activity;

import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.adapter.AbstractBaseAdapter;
import com.sias.znwy.web.util.WebParam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

/**
 * ����Ѳ��
 * 
 * @author Administrator
 * 
 */
public class JLXCActivity extends BaseActivity {

	@Override
	public WebParam getParam() {
		// jklx:"cqqkcx",yhdh:"sj",sjbs:"ABCD",aqyz:"",cxrq:"��ѯ����"
		return WebParam.create().addParam("jklx", "jlxccx").addParam("yhdh", UserInfo.getYhdh())
				.addParam("sjbs", AppKit.getDeviceId(JLXCActivity.this)).addParam("aqyz", "aqyz")
				.addParam("cxrq", searChDate.getText().toString());
	}

	@Override
	public void setName() {
		title.setItemName("����Ѳ��");

	}

	@Override
	public void setRightTextVisiable() {
		title.setRightNameVisiable(View.VISIBLE);

	}

	@Override
	public void setRightText() {
		title.setItemRightName("�½�");

	}

	@Override
	public AbstractBaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rightTextclick() {
		Intent intent = new Intent(JLXCActivity.this, JLXCShangBaoActivity.class);
		startActivity(intent);

	}

	@Override
	public void processDatas(JSONObject json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemClick(int position) {

	}

}
