package com.sias.znwy.adapter;

import java.io.UnsupportedEncodingException;

import com.sias.znwy.R;
import com.sias.znwy.demo.XCHBBean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class XCHBAdapter extends AbstractBaseAdapter {

	public XCHBAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		XCHBBean bean = (XCHBBean) datas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.adapter_xchb, null);
			holder.data = (TextView) convertView.findViewById(R.id.xchb_data);
			holder.diseaseDescription = (TextView) convertView.findViewById(R.id.xchb_description);
			holder.managementDepartment = (TextView) convertView.findViewById(R.id.xchb_manager);
			holder.rwImg = (ImageView) convertView.findViewById(R.id.xchb_img);
			holder.rwName = (TextView) convertView.findViewById(R.id.xchb_reName);
			holder.weather = (TextView) convertView.findViewById(R.id.xchb_weather);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.data.setText(bean.getSbsj());
		holder.diseaseDescription.setText(bean.getSbsj());
		holder.managementDepartment.setText(bean.getBmmc());
		holder.rwName.setText(bean.getRwmc());
		holder.weather.setText(bean.getTqdm());
		holder.diseaseDescription.setText(bean.getBhdl() + bean.getBhxl() + bean.getBhzl());

		holder.rwImg.setImageBitmap(Bytes2Bimap(Base64.decode(bean.getZlzp(), Base64.DEFAULT)));

		return convertView;
	}

	private Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	class ViewHolder {
		TextView rwName;
		TextView weather;
		TextView diseaseDescription;
		TextView managementDepartment;
		TextView data;
		ImageView rwImg;
	}
}
