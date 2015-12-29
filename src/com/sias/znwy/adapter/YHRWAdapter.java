package com.sias.znwy.adapter;

import com.sias.znwy.R;
import com.sias.znwy.demo.YHRWBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class YHRWAdapter extends AbstractBaseAdapter {
	private Context context;

	public YHRWAdapter(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final YHRWBean bean = (YHRWBean) datas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.adapter_yhrw, null);
			holder.taskName = (TextView) convertView.findViewById(R.id.taskName);
			holder.taskNumber = (TextView) convertView.findViewById(R.id.taskNumber);
			holder.taskType = (TextView) convertView.findViewById(R.id.taskType);
			holder.beginStation = (TextView) convertView.findViewById(R.id.beginStation);
			holder.endStation = (TextView) convertView.findViewById(R.id.endStation);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.taskName.setText(bean.getRwmc());
		holder.taskNumber.setText(bean.getRwbh());
		holder.taskType.setText(bean.getYwlx());
		holder.beginStation.setText(bean.getKszh());
		holder.endStation.setText(bean.getJszh());
		return convertView;
	}

	class ViewHolder {
		TextView taskName;
		TextView taskNumber;
		TextView taskType;
		TextView beginStation;
		TextView endStation;

	}
}
