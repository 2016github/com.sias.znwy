package com.sias.znwy.activity;

import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityJump;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * œµÕ≥…Ë÷√
 * @author Administrator
 *
 */
public class XTSZActivity extends Activity implements OnClickListener{
	private RelativeLayout rl_xiug;
	private TextView text_return;
	private TextView text_version;
	private String version;
	private String yhdh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xtsz);
		initView();
	}
	private void initView() {
		version=getIntent().getExtras().getString("version");
		yhdh=getIntent().getExtras().getString("yhdh");
		rl_xiug=(RelativeLayout) findViewById(R.id.rl_xiug);
		rl_xiug.setOnClickListener(this);
		text_return=(TextView) findViewById(R.id.text_return);
		text_return.setOnClickListener(this);
		text_version=(TextView) findViewById(R.id.text_version);
		text_version.setText(version);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_return:
			finish();
			break;
		case R.id.rl_xiug:
			Bundle bun=new Bundle();
			bun.putString("yhdh", yhdh);
			ActivityJump.JumpActivity(XTSZActivity.this, XiugPasswordActivity.class,bun);
			break;

		default:
			break;
		}
	}
}
