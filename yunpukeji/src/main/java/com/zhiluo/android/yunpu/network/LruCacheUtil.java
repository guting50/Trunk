package com.zhiluo.android.yunpu.network;

import android.util.LruCache;

/**
 * 缓存工具类
 * 作者：罗咏哲 on 2017/9/19 18:13.
 * 邮箱：137615198@qq.com
 */

public class LruCacheUtil {
    private LruCache<String, String> mCache;//LruCache缓存对象

    public LruCacheUtil() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置缓存的大小
        int cacheSize = maxMemory / 8;
        mCache = new LruCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String key, String value) {
                return value.length();
            }
        };
    }

    /**
     * 将请求获得的数据加入到缓存中
     *
     * @param url
     * @param response
     */
    public void addDateToCache(String url, String response) {
        if (response != null) {
            mCache.put(url, response);
        }
    }

    /**
     * 从缓存中取数据
     *
     * @param url
     * @return
     */
    public String getDataFromCache(String url) {
        if (url != null) {
            return mCache.get(url);
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public void removeCache(String key) {
        mCache.remove(key);
    }
}
