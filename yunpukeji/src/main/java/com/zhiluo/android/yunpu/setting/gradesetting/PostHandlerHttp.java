package com.zhiluo.android.yunpu.setting.gradesetting;

/**
 * iauthor：Cheng
 * date: 2017/6/5 09:33
 * email：jasoncheng9111@gmail.com
 */
public class PostHandlerHttp
{
    //计次设定handler
//    private static Handler handler = MemberChargeManagementActivity.postRecordNumberSetting_Handler;

    /**
     * 查询会员等级的相关设定----计次设定
     * @param context
     */
//    private static void postRecordNumberSetting(Context context)
//    {
//        AsyncHttpClient client = new AsyncHttpClient();
//        PersistentCookieStore cookie = new PersistentCookieStore(context);
//        client.setCookieStore(cookie);
//        RequestParams params = new RequestParams();
//        params.put("GID", "");
//        client.post(HttpAPI.API().USERGRADE, params, new AsyncHttpResponseHandler()
//        {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
//            {
//                if (statusCode == 200)
//                {
//                    PostHandlerHttp.sendMessage(responseBody);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
//            {
//
//            }
//        });
//
//    }
//
//    private static void sendMessage(byte[] responseBody)
//    {
//        Bundle bundle = new Bundle();
//        bundle.putByteArray("responsebody",responseBody);
//        Message msg = handler.obtainMessage();
//        msg.setData(bundle);
//        handler.sendMessage(msg);
//    }
}
