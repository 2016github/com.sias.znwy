package com.sias.znwy.activity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.adapter.AbstractBaseAdapter;
import com.sias.znwy.adapter.YHRWAdapter;
import com.sias.znwy.demo.YHRWBean;
import com.sias.znwy.web.util.WebParam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

/**
 * 养护任务
 * 
 * @author Administrator
 * 
 */
public class YHRWActivity extends BaseActivity {

	@Override
	public WebParam getParam() {
		// jklx:"yhrwdcxqb",yhdh:"sj",sjbs:"ABCD",aqyz:""
		return WebParam.create().addParam("jklx", "yhrwdcxqb").addParam("yhdh", UserInfo.getYhdh())
				.addParam("sjbs", UserInfo.getDeviceId()).addParam("aqyz", "aqyz");
	}

	@Override
	public void setName() {
		title.setItemName("养护任务通知");

	}

	@Override
	public void setRightTextVisiable() {
		quaryLayout.setVisibility(View.GONE);

	}

	@Override
	public void setRightText() {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractBaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return new YHRWAdapter(YHRWActivity.this);
	}

	@Override
	public void rightTextclick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processDatas(JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("result");
		for (int i = 0; i < jsonArray.size(); i++) {
			datas.add(new YHRWBean(jsonArray.getJSONObject(i)));
		}
	}

	@Override
	public void itemClick(int position) {
		Intent intent = new Intent(YHRWActivity.this, YHTZDetailActivity.class);
		intent.putExtra("data", (YHRWBean) datas.get(position));
		startActivity(intent);
	}
}
