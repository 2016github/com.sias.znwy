package com.sias.znwy.Util;
import com.sias.znwy.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
public class WatingDialog extends Dialog{
	private LayoutInflater inflater;
	private AlertDialog dialog;
	private Context context;
	public WatingDialog(Context context) {
		super(context);
		this.context=context;
		inflater=LayoutInflater.from(context);
	}

	public void ShowWatingDialog(){
		View view = inflater.inflate(R.layout.activity_watingdialog, null);
		AlertDialog.Builder bulider = new Builder(context);
		dialog = bulider.create();
		dialog.setView(view, 0, 0, 0, 0);
		dialog.show();
	}
	public void diaologdimiss(){
		if (dialog!=null) {
			dialog.dismiss();
		}
	}
}
