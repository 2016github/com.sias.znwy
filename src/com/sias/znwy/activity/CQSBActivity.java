package com.sias.znwy.activity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.adapter.AbstractBaseAdapter;
import com.sias.znwy.adapter.CQSHAdapter;
import com.sias.znwy.demo.CQSBBean;
import com.sias.znwy.demo.YHRWBean;
import com.sias.znwy.web.util.WebParam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

/**
 * �����ϱ�
 * 
 * @author Administrator
 * 
 */
public class CQSBActivity extends BaseActivity {

	@Override
	public void setName() {
		title.setItemName("�����ϱ�");

	}

	@Override
	public void setRightTextVisiable() {
		title.setRightNameVisiable(View.VISIBLE);
		quaryLayout.setVisibility(View.GONE);

	}

	@Override
	public void setRightText() {
		title.setItemRightName("�ύ");

	}

	@Override
	public AbstractBaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return new CQSHAdapter(CQSBActivity.this);
	}

	@Override
	public WebParam getParam() {
		// jklx:"cqqkcx",yhdh:"sj",sjbs:"ABCD",aqyz:"",cxrq:"��ѯ����"

		return WebParam.create().addParam("jklx", "cqqkcx").addParam("yhdh", UserInfo.getYhdh())
				.addParam("sjbs", AppKit.getDeviceId(CQSBActivity.this)).addParam("aqyz", "aqyz")
				.addParam("cxrq", searChDate.getText().toString());
	}

	@Override
	public void rightTextclick() {
		// �ύ����

	}

	@Override
	public void processDatas(JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("result");
		if (jsonArray != null || jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				datas.add(new CQSBBean(jsonArray.getJSONObject(i)));
			}
		}

	}

	@Override
	public void itemClick(int position) {
		// TODO Auto-generated method stub

	}
}
