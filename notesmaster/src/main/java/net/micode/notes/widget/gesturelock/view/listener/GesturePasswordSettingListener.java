package net.micode.notes.widget.gesturelock.view.listener;

/**
 * 
 * 类描述：
 * 
 * 创建时间：2019/1/12 20:41
 */
public interface GesturePasswordSettingListener {
    boolean onFirstInputComplete(int len);
    void onSuccess();
    void onFail();
}
