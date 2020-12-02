package com.java.yangnj;

import java.io.UnsupportedEncodingException;

/**
 * @author ynj
 * @program: StringToHex
 * @description:
 * @date 2020-11-30 20:53:56
 */
public class ConvertUtils {
    /**
     * Unicode max code point in Basic Multilingual Plane (BMP) is 0xFFFF。
     */
    private static int UNICODE_BMP_LIMIT = 0xFFFF;
    public static String stringToHexStringByCharsetName(String src,String charsetName) throws UnsupportedEncodingException {
        byte[] byteArray = src.getBytes(charsetName);
        return bytesToHexString(byteArray);
    }

    public static String hexStringToStringByCharsetName(String hexStr,String charsetName) throws UnsupportedEncodingException {
        return(new String(hexStringToBytes(hexStr),charsetName));
    }

    private static String bytesToHexString(byte[] byteArray){
        StringBuffer sb = new StringBuffer(byteArray.length);
        String sTemp;
        for(int i = 0;i < byteArray.length;i++){
            sTemp = Integer.toHexString(byteArray[i]&0XFF);
            if(sTemp.length()<2){
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] hexStringToBytes(String hexString){
        byte[]  byteArray = new byte[hexString.length()/2];
        for(int i = 0;i < hexString.length()/2;i++){
            String item = hexString.substring(2*i,2*i+2);
            byteArray[i] = (byte) Integer.parseInt(item, 16);
        }
        return byteArray;
    }

    public static String getUnicodeFormUtf16(String str){
        int unicodeCodePoint;
        StringBuffer sb = new StringBuffer();
        char[] charArray = str.toCharArray();
        for(int i=0;i<charArray.length;){
            unicodeCodePoint = Character.codePointAt(charArray, i);
            /**
             * Code point greater than UNICODE_BMP_LIMIT(0xFFFF),The character
             * in Supplementary Planes, It requires two 16-bit code units
             *  from UTF-16.
             */
            if (UNICODE_BMP_LIMIT<unicodeCodePoint){
                i = i + 2;
            }else{
                i++;
            }
            sb.append(Integer.toHexString(unicodeCodePoint).toUpperCase());
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String charsToHexString(char[] charArray){
        StringBuffer sb = new StringBuffer(charArray.length);
        String sTemp;
        System.out.println(charArray.length);
        for(int i = 0;i < charArray.length;i++){
            sTemp = Integer.toHexString(charArray[i]);
            switch(sTemp.length()){
                case 1:
                    sb.append("000");
                    break;
                case 2:
                    sb.append("00");
                    break;
                case 3:
                    sb.append("0");
                    break;
                default:
                    break;
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

}
