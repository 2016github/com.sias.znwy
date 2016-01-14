package com.sias.znwy.adapter;

import com.sias.znwy.R;
import com.sias.znwy.demo.WriteWorkPlanBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WriteWorkPlanAdapter extends AbstractBaseAdapter {

	public WriteWorkPlanAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WriteWorkPlanBean object = (WriteWorkPlanBean) datas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.adapter_write_work_plan, null);
			holder.planType = (TextView) convertView.findViewById(R.id.planType);
			holder.planMemo = (TextView) convertView.findViewById(R.id.plantMemo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (object.getType() == 0) {
			holder.planType.setText("非指令计划");
		} else {
			holder.planType.setText("指令计划");
			holder.planType.setTextColor(mContext.getResources().getColor(R.color.plantColor));
		}
		holder.planMemo.setText(object.getMemo());
		return convertView;
	}

	class ViewHolder {
		TextView planType;
		TextView planMemo;
	}
}
