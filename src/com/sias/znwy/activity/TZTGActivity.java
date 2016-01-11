package com.sias.znwy.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.DialogUtil;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.WebUtil.QueryTZ;
import com.sias.znwy.adapter.TZTGAdapter;
import com.sias.znwy.demo.TZTGBean;
import com.sias.znwy.web.util.OnResultListener;

/**
 * 通知公告
 * 
 * @author Administrator
 * 
 */
public class TZTGActivity extends Activity implements OnClickListener {
	private TextView text_return;
	private EditText edit_data;
	private Button btn_query;
	private ListView listView;
	private TZTGAdapter adapter;
	private List<TZTGBean> datas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tztg);
		initView();
	}

	public void initView() {
		text_return = (TextView) findViewById(R.id.text_return);
		edit_data = (EditText) findViewById(R.id.edit_data);
		btn_query = (Button) findViewById(R.id.btn_query);
		listView = (ListView) findViewById(R.id.dataListView);
		listView.setAdapter(adapter);
		text_return.setOnClickListener(this);
		btn_query.setOnClickListener(this);
		QueryTZ();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(TZTGActivity.this, TZTGDetailActivity.class);
				intent.putExtra("data", datas.get(position));
				startActivity(intent);

			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_return:
			finish();
			break;
		case R.id.btn_query:
			QueryTZ();
			break;
		default:
			break;
		}
	}

	/**
	 * 根据日期查询通知通告
	 */
	private void QueryTZ() {
		new QueryTZ().QueryTz("tztgcx", UserInfo.getYhdh(), UserInfo.getDeviceId(), "aqyz", edit_data.getText().toString(),
				new OnResultListener() {
					@Override
					public void onResult(boolean isSuccess, int errorCode, Object obj) {
						if (isSuccess) {
							// 成功
							JSONObject json = (JSONObject) obj;
							JSONArray dataArray = json.getJSONArray("result");
							if (dataArray != null && dataArray.size() > 0) {
								for (int i = 0; i < dataArray.size(); i++) {
									datas.add(new TZTGBean(dataArray.getJSONObject(i)));

								}
								adapter.setDatas(datas);
							}

						} else {
							new DialogUtil(TZTGActivity.this, obj);
						}
					}
				});
	}
}