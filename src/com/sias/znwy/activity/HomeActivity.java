package com.sias.znwy.activity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityJump;
import com.sias.znwy.Util.RequestTools;
import com.sias.znwy.Util.SignRule;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.demo.Tusbkey;
import com.sias.znwy.demo.WyAutoupdate;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.WebParam;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 主界面
 * 
 * @author Administrator
 * 
 */
public class HomeActivity extends Activity {
	private int icorn[];
	private String icornName[];
	private GridViewAdapter adapter;
	private GridView gridview;
	private LayoutInflater inflate;
	private Tusbkey tusbkey;
	private TextView text_username;
	private WyAutoupdate wyautoupdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}

	private void initView() {
		wyautoupdate = (WyAutoupdate) getIntent().getExtras().getSerializable("wyautoupdate");
		tusbkey = (Tusbkey) getIntent().getExtras().getSerializable("tusbkey");
		text_username = (TextView) findViewById(R.id.text_username);
		text_username.setText("你好：" + tusbkey.getPin());
	}

	/**
	 * 主页面准备资源
	 */
	private void initData() {
		inflate = LayoutInflater.from(HomeActivity.this);
		icorn = new int[] { R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4, R.drawable.home5, R.drawable.home6,
				R.drawable.home7, R.drawable.home8, R.drawable.home9 };
		icornName = new String[] { "通知公告", "考勤签到", "出勤上报", "监理巡查", "养护任务通知", "工作计划", "现场汇报", "工作日结", "系统设置" };
		gridview = (GridView) findViewById(R.id.gridview);
		adapter = new GridViewAdapter();
		gridview.setAdapter(adapter);
		getSignRule();
	}

	/**
	 * 获取签到规则
	 * 
	 */
	private void getSignRule() {
		new RequestTools().request(
				WebParam.create().addParam("jklx", "qdgz").addParam("yhbh", UserInfo.getYhdh()).addParam("sjbs", UserInfo.getDeviceId())
						.addParam("aqyz", "aqyz"), new OnResultListener() {

					@Override
					public void onResult(boolean isSuccess, int errorCode, Object obj) {
						if (isSuccess) {
							// yhdh:用户代号,sxsj:生效时间,qdkssj:签到开始时间,qdjssj:签到结束时间,jgxss:间隔小时数,yxpcfz:允许偏差分钟数}
							JSONObject json = (JSONObject) obj;
							JSONArray jsonArray = json.getJSONArray("result");
							SignRule.setJgxss(jsonArray.getJSONObject(0).getString("jgxss"));
							SignRule.setYhdh(jsonArray.getJSONObject(0).getString("yhdh"));
							SignRule.setSxsj(jsonArray.getJSONObject(0).getString("sxsj"));
							SignRule.setQdkssj(jsonArray.getJSONObject(0).getString("qdkssj"));
							SignRule.setQdjssj(jsonArray.getJSONObject(0).getString("qdjssj"));
							SignRule.setYxpcfz(jsonArray.getJSONObject(0).getString("yxpcfz"));
						}

					}
				});

	}

	class GridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return icorn.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHold hold;
			if (convertView == null) {
				hold = new ViewHold();
				convertView = inflate.inflate(R.layout.gridview_item, null);
				hold.img = (ImageView) convertView.findViewById(R.id.img);
				hold.text = (TextView) convertView.findViewById(R.id.text);
				convertView.setTag(hold);
			} else {
				hold = (ViewHold) convertView.getTag();
			}
			hold.img.setBackgroundResource(icorn[position]);
			hold.text.setText(icornName[position]);
			hold.img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					switch (position) {
					case 0:
						// 通知公告
						ActivityJump.JumpActivity(HomeActivity.this, TZTGActivity.class);
						break;
					case 1:
						// 考勤签到
						ActivityJump.JumpActivity(HomeActivity.this, QDKQActivity.class);
						break;
					case 2:
						// 出勤上报
						ActivityJump.JumpActivity(HomeActivity.this, CQSBActivity.class);
						break;
					case 3:
						// 监理巡查
						ActivityJump.JumpActivity(HomeActivity.this, JLXCActivity.class);
						break;
					case 4:
						// 养护任务通知
						ActivityJump.JumpActivity(HomeActivity.this, YHRWActivity.class);
						break;
					case 5:
						// 工作计划
						ActivityJump.JumpActivity(HomeActivity.this, GZJHActivity.class);
						break;
					case 6:
						// 现场汇报
						ActivityJump.JumpActivity(HomeActivity.this, XCHBActivity.class);
						break;
					case 7:
						// 工作日结
						ActivityJump.JumpActivity(HomeActivity.this, GZZJActivity.class);
						break;
					case 8:
						// 系统设置
						Bundle bun = new Bundle();
						bun.putString("version", wyautoupdate.getVersion());
						bun.putString("yhdh", tusbkey.getPin());
						ActivityJump.JumpActivity(HomeActivity.this, XTSZActivity.class, bun);
						break;
					default:
						break;
					}
				}
			});
			return convertView;
		}

	}

	class ViewHold {
		ImageView img;
		TextView text;
	}
}
