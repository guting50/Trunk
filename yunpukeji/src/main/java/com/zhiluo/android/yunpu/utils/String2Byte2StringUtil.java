package com.zhiluo.android.yunpu.utils;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class String2Byte2StringUtil
{
    //String转byte[]
    public static byte[] stringToBcd(String src)
    {
        int inum = 0;
        int numlen = src.length();
        if ((numlen % 2) > 0)
            return null;
        byte[] dst = new byte[numlen / 2];

        for (int i = 0; i < numlen; )
        {
            //TODO: 过滤空格
            char hghch = ConvertHexChar(src.charAt(i));
            char lowch = ConvertHexChar(src.charAt(i + 1));

            dst[inum++] = (byte) (hghch * 16 + lowch);
            i += 2;
        }
        return dst;
    }

    private static char ConvertHexChar(char ch)
    {
        return ch >= 48 && ch <= 57 ? (char) (ch - 48) : (ch >= 65 && ch <= 70 ? (char) (ch - 65 + 10) : (ch >= 97 && ch <= 102 ? (char) (ch - 97 +
                10) : '\uffff'));
    }

    //byte[] 转string
    public static String bcdToString(byte[] bcdNum)
    {
        int len = bcdNum.length;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++)
        {
            sb.append(Integer.toHexString((bcdNum[i] & 0xF0) >> 4));
            sb.append(Integer.toHexString(bcdNum[i] & 0x0F));
        }
        return sb.toString().toUpperCase();
    }
}
