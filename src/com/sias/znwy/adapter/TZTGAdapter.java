package com.sias.znwy.adapter;

import com.sias.znwy.R;
import com.sias.znwy.demo.TZTGBean;
import com.sias.znwy.demo.TZzhuBiaoBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TZTGAdapter extends AbstractBaseAdapter {
	private Context context;

	public TZTGAdapter(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		TZTGBean tztgBean = (TZTGBean) datas.get(position);
		TZzhuBiaoBean shhGsl = tztgBean.getShhGsl();
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.adapter_tztg_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.itemTztgTitle);
			holder.memo = (TextView) convertView.findViewById(R.id.itemTztgMemo);
			holder.whetherSignIn = (TextView) convertView.findViewById(R.id.itemWheaherSighIn);
			holder.publishTime = (TextView) convertView.findViewById(R.id.itemTztgPublishTime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(shhGsl.getBt());
		holder.memo.setText(shhGsl.getNr());
		holder.publishTime.setText(tztgBean.getFbsj());
		String qsbj = tztgBean.getQsbj();
		if (qsbj.equals("0")) {
			holder.whetherSignIn.setBackgroundResource(R.drawable.znwy_tzrg_item_unsign_bg);
			holder.whetherSignIn.setTextColor(context.getResources().getColor(R.color.tztgItemUnSignColoe));
			holder.whetherSignIn.setText("¥˝«© ’");
		} else {
			holder.whetherSignIn.setBackgroundResource(R.drawable.znwy_tzrg_item_sign_bg);
			holder.whetherSignIn.setTextColor(context.getResources().getColor(R.color.tztgItemSignColoe));
			holder.whetherSignIn.setText("“—«© ’");
		}
		return convertView;
	}

	class ViewHolder {
		TextView title;
		TextView memo;
		TextView whetherSignIn;
		TextView publishTime;

	}
}
