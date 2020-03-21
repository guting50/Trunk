package com.zhiluo.android.yunpu.utils;

/**
 * Created by YSL on 2018-09-05.
 */

public class ByteArrayToHexString {

    public static  String ByteArrayToHex(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9","A","B","C","D","E","F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        long x = Long.parseLong(out,  16);
//        int x = Integer.parseInt(out,16);
        out = String.valueOf(x);
        return out;
    }
}
