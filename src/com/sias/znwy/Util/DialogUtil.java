package com.sias.znwy.Util;

import com.sias.znwy.activity.TZTGActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {

	private Context contetx;
	private Object obj;
	public DialogUtil(Context context,Object obj) {
		this.contetx = context;
		this.obj=obj;
		showDialog();
	}

	private void showDialog() {

		AlertDialog dialog = new AlertDialog.Builder(contetx).setTitle("提示")
				.setMessage((String) obj)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).create();
		dialog.show();

	}
}
