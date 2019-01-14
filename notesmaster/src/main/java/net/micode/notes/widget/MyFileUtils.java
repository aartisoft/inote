package net.micode.notes.widget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

/**
 * Created on 2019/01/12.
 */

public class MyFileUtils {

	/** 保存View为图片的方法 */
	public static String saveBitmap(View v) {

		String fileName = new Date().getTime() + ".png";

		Bitmap bm = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bm);
		v.draw(canvas);
		String TAG = "TIKTOK";
		Log.e(TAG, "保存图片");
		File file = new File("/sdcard/nodesmaster/img");
		file.mkdirs();
		File f = new File("/sdcard/nodesmaster/img/", fileName);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
			Log.i(TAG, "已经保存");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f.getAbsolutePath();
	}

	/**
	 * 存储缩放的图片
	 */
	public static String saveScalePhoto(Bitmap bitmap) {
		String name = DateFormat.format("yyyyMMdd_hhmmss",
				Calendar.getInstance(Locale.CHINA))
				+ ".jpg";
		File file = new File("/sdcard/nodesmaster/img");
		file.mkdirs();
		String filename = file.getPath() + "/" + name;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 99, fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filename;
	}

	/**
	 * 分享图片给QQ好友
	 * 
	 * @param bitmap
	 */
	public static void shareImageToQQ(String f_path, Context mContext) {
		if (PlatformUtil.isInstallApp(mContext, PlatformUtil.PACKAGE_MOBILE_QQ)) {
			try {
				Uri uriToImage = Uri.fromFile(new File(f_path));
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				shareIntent.setType("image/*");
				// 遍历所有支持发送图片的应用。找到需要的应用
				ComponentName componentName = new ComponentName(
						"com.tencent.mobileqq",
						"com.tencent.mobileqq.activity.JumpActivity");

				shareIntent.setComponent(componentName);
				// mContext.startActivity(shareIntent);
				mContext.startActivity(Intent.createChooser(shareIntent,
						"Share"));
			} catch (Exception e) {
				// 分享图片失败
			}
		}
	}

}
