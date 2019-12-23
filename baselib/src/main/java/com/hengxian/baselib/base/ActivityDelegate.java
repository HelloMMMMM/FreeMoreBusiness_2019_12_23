package com.hengxian.baselib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Activity生命周期代理
 * @author wfq
 */
public interface ActivityDelegate {

    /**
     * 代理onCreate() 方法
     *
     * @param savedInstanceState
     */
    void handleCreate(@Nullable Bundle savedInstanceState);
}
