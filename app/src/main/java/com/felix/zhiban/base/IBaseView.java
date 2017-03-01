package com.felix.zhiban.base;

import android.content.Context;

/**
 * 公共View接口
 */

public interface IBaseView {
    /**
     * 显示可取消的进度条
     * @param message
     */
    void showProgress(String message);

    /**
     * 根据字符串弹出Dialog
     * @param msg
     */
    void showToast(String msg);

    /**
     * 获取当前上下文对象
     * @return
     */
    Context getContext();
}
