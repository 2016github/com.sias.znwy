package com.sias.znwy.activity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitle;
import com.sias.znwy.Util.DialogUtil;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.WebUtil.QueryXchb;
import com.sias.znwy.adapter.XCHBAdapter;
import com.sias.znwy.demo.TZTGBean;
import com.sias.znwy.demo.XCHBBean;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * 现场汇报
 * 
 * @author Administrator
 * 
 */
public class XCHBActivity extends Activity implements OnClickListener {
	private EditText edit_data;
	private Button btn_query;
	private ActivityTitle title;
	private ListView listView;
	private List<XCHBBean> datas;
	private XCHBAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xchb);
		initView();
	}

	private void initView() {
		edit_data = (EditText) findViewById(R.id.edit_data);
		btn_query = (Button) findViewById(R.id.btn_query);
		title = (ActivityTitle) findViewById(R.id.activityTitle1);
		listView = (ListView) findViewById(R.id.XCSBListView);
		datas = new ArrayList<XCHBBean>();
		adapter = new XCHBAdapter(XCHBActivity.this);
		listView.setAdapter(adapter);
		title.setItemName("现场汇报");
		title.setRightNameVisiable(View.VISIBLE);
		title.setItemRightName("写汇报");
		title.titleRightText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 进入写汇报界面

			}
		});
		btn_query.setOnClickListener(this);
		queryXchb();
	}

	/**
	 * 查询现场汇报
	 */
	private void queryXchb() {

		new QueryXchb().queryXchb("xchbcx", UserInfo.getYhdh(), UserInfo.getDeviceId(), "aqyz", edit_data.getText().toString(),
				new OnResultListener() {

					@Override
					public void onResult(boolean isSuccess, int errorCode, Object obj) {
						if (isSuccess) {
							JSONObject json = (JSONObject) obj;
							JSONArray dataArray = json.getJSONArray("result");
							if (dataArray != null && dataArray.size() > 0) {
								datas = new ArrayList<XCHBBean>();
								for (int i = 0; i < dataArray.size(); i++) {
									datas.add(new XCHBBean(dataArray.getJSONObject(i)));

								}
								adapter.setDatas(datas);
							}
						} else {
							new DialogUtil(XCHBActivity.this, obj);
						}
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_query:
			queryXchb();
			break;
		default:
			break;
		}
	}
}
