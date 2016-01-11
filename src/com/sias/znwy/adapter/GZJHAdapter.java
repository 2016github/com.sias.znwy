package com.sias.znwy.adapter;

import com.sias.znwy.R;
import com.sias.znwy.demo.GZJHBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GZJHAdapter extends AbstractBaseAdapter {
	private Context context;

	public GZJHAdapter(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GZJHBean gzjhBean = (GZJHBean) datas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.adapter_gzjh, null);
			holder.companyName = (TextView) convertView.findViewById(R.id.gzjh_itemCompanyName);
			holder.userName = (TextView) convertView.findViewById(R.id.gzjh_itemUserName);
			holder.gzjhName = (TextView) convertView.findViewById(R.id.gzjh_itemTaskName);
			holder.gzjhNumber = (TextView) convertView.findViewById(R.id.gzjh_itemTaskNumber);
			holder.gzjhType = (TextView) convertView.findViewById(R.id.gzjh_itemTaskType);
			holder.gzjhMemo = (TextView) convertView.findViewById(R.id.gzjh_itemWorkMemo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.companyName.setText(gzjhBean.getDwmc());
		holder.userName.setText(gzjhBean.getYhxm());
		holder.gzjhName.setText(gzjhBean.getRwmc());
		holder.gzjhNumber.setText(gzjhBean.getRwbh());
		holder.gzjhType.setText(gzjhBean.getRwlx());
		holder.gzjhMemo.setText(gzjhBean.getGzgy());

		return convertView;
	}

	class ViewHolder {
		TextView companyName;
		TextView userName;
		TextView gzjhName;
		TextView gzjhNumber;
		TextView gzjhType;
		TextView gzjhMemo;
	}
}
