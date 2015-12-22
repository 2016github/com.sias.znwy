package com.sias.znwy.Util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
public class ActivityJump {
	public static void JumpActivity(Context context, Class clz) {
		Intent intent = new Intent(context, clz);
		context.startActivity(intent);
	}
	public static void JumpActivity(Context context, Class clz,Bundle bun) {
		Intent intent = new Intent(context, clz);
		intent.putExtras(bun);
		context.startActivity(intent);
	}
}
