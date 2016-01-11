package com.sias.znwy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitleView;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.adapter.AbstractBaseAdapter;
import com.sias.znwy.adapter.GZJHAdapter;
import com.sias.znwy.demo.GZJHBean;
import com.sias.znwy.demo.YHRWBean;
import com.sias.znwy.web.util.WebParam;

/**
 * 工作计划
 * 
 * @author Administrator
 * 
 */
public class GZJHActivity extends BaseActivity {
	@Override
	public WebParam getParam() {
		// jklx:"cqqkcx",yhdh:"sj",sjbs:"ABCD",aqyz:"",cxrq:"查询日期"
		return WebParam.create().addParam("jklx", "gzjhmxcx").addParam("yhdh", UserInfo.getYhdh())
				.addParam("sjbs", AppKit.getDeviceId(GZJHActivity.this)).addParam("aqyz", "aqyz")
				.addParam("rjhbh", searChDate.getText().toString());
	}

	@Override
	public void setName() {
		title.setItemName("工作计划");
	}

	@Override
	public void setRightTextVisiable() {
		title.setRightNameVisiable(View.VISIBLE);

	}

	@Override
	public void setRightText() {
		title.setItemRightName("写计划");

	}

	@Override
	public AbstractBaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return new GZJHAdapter(GZJHActivity.this);
	}

	@Override
	public void rightTextclick() {
		Intent intent = new Intent(GZJHActivity.this, XJHActivity.class);
		startActivity(intent);

	}

	@Override
	public void processDatas(JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("result");
		for (int i = 0; i < jsonArray.size(); i++) {
			datas.add(new GZJHBean(jsonArray.getJSONObject(i)));
		}
	}

	@Override
	public void itemClick(int position) {
		Intent intent = new Intent(GZJHActivity.this, GZJHDetailActivity.class);
		intent.putExtra("data", (GZJHBean) datas.get(position));
		startActivity(intent);
	}

}
