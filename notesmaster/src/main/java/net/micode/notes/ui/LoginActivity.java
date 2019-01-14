package net.micode.notes.ui;

import net.micode.notes.R;
import net.micode.notes.widget.gesturelock.view.GestureLockViewGroup;
import net.micode.notes.widget.gesturelock.view.listener.GestureEventListener;
import net.micode.notes.widget.gesturelock.view.listener.GesturePasswordSettingListener;
import net.micode.notes.widget.gesturelock.view.listener.GestureUnmatchedExceedListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends Activity {

	GestureLockViewGroup mGestureLockViewGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.gesturelock);
//		mGestureLockViewGroup.removePassword();// 清除图案密码
		mGestureLockViewGroup.setVisibility(View.VISIBLE);
		initGesture();
	}

	private void initGesture() {
		gestureEventListener();
		gesturePasswordSettingListener();
		gestureRetryLimitListener();
	}

	private void gestureEventListener() {
		mGestureLockViewGroup
				.setGestureEventListener(new GestureEventListener() {
					@Override
					public void onGestureEvent(boolean matched) {
						if (matched) {
							Toast.makeText(LoginActivity.this, "登录成功",
									Toast.LENGTH_SHORT).show();
							new Handler().postDelayed(new Runnable() {
								public void run() {
									startActivity(new Intent(
											LoginActivity.this,
											NotesListActivity.class));
									finish();
								}
							}, 1000);

						} else {
							Toast.makeText(LoginActivity.this, "手势密码错误",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	private void gesturePasswordSettingListener() {
		mGestureLockViewGroup
				.setGesturePasswordSettingListener(new GesturePasswordSettingListener() {
					@Override
					public boolean onFirstInputComplete(int len) {
						if (len > 3) {
							Toast.makeText(LoginActivity.this, "再次绘制手势密码",
									Toast.LENGTH_SHORT).show();
							return true;
						} else {
							Toast.makeText(LoginActivity.this,
									"最少连接4个点，请重新输入!", Toast.LENGTH_SHORT)
									.show();
							return false;
						}
					}

					@Override
					public void onSuccess() {
						Toast.makeText(LoginActivity.this, "密码已保存",
								Toast.LENGTH_SHORT).show();
						startActivity(new Intent(LoginActivity.this,
								NotesListActivity.class));
						finish();
					}

					@Override
					public void onFail() {
						Toast.makeText(LoginActivity.this, "与上一次绘制不一致，请重新绘制",
								Toast.LENGTH_SHORT).show();
						mGestureLockViewGroup.removePassword();// 清除图案密码
					}
				});
	}

	private void gestureRetryLimitListener() {
		mGestureLockViewGroup.setGestureUnmatchedExceedListener(3,
				new GestureUnmatchedExceedListener() {
					@Override
					public void onUnmatchedExceedBoundary() {
						Toast.makeText(LoginActivity.this, "错误次数过多！",
								Toast.LENGTH_SHORT).show();
						finish();
					}
				});
	}

}
