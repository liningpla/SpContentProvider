package com.pl.sphelper;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.pl.sphelper.ConstantUtil.CONTENT_URI;
import static com.pl.sphelper.ConstantUtil.CURSOR_COLUMN_NAME;
import static com.pl.sphelper.ConstantUtil.CURSOR_COLUMN_TYPE;
import static com.pl.sphelper.ConstantUtil.CURSOR_COLUMN_VALUE;
import static com.pl.sphelper.ConstantUtil.NULL_STRING;
import static com.pl.sphelper.ConstantUtil.SEPARATOR;
import static com.pl.sphelper.ConstantUtil.TYPE_BOOLEAN;
import static com.pl.sphelper.ConstantUtil.TYPE_CLEAN;
import static com.pl.sphelper.ConstantUtil.TYPE_CONTAIN;
import static com.pl.sphelper.ConstantUtil.TYPE_FLOAT;
import static com.pl.sphelper.ConstantUtil.TYPE_GET_ALL;
import static com.pl.sphelper.ConstantUtil.TYPE_INT;
import static com.pl.sphelper.ConstantUtil.TYPE_LONG;
import static com.pl.sphelper.ConstantUtil.TYPE_STRING;
import static com.pl.sphelper.ConstantUtil.TYPE_STRING_SET;
import static com.pl.sphelper.ConstantUtil.VALUE;

public class SPHelper {

    private static final String TAG = "SPHelper";
    public static final String COMMA_REPLACEMENT="__COMMA__";

    public static final String SETTING_INFO = "setting_info";

    public static Context context;

    public static void checkContext() {
        if (context == null) {
            throw new IllegalStateException("context has not been initialed");
        }
    }

    public static void init(Application application) {
        context = application.getApplicationContext();
    }

    public interface DateChangeListener{
        void onChange(boolean selfChange);
    }

    public  synchronized static void registerContentObserver(String name, DateChangeListener listener){
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_STRING+ SEPARATOR + name);
            cr.registerContentObserver(uri,true,new MyContentObserver(new Handler(), listener));
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }
    }

    public synchronized static void save(String name, Boolean t) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }
    }

    public synchronized static void save(String name, String t) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_STRING + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

    }

    public synchronized static void save(String name, Integer t) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_INT + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

    }

    public synchronized static void save(String name, Long t) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_LONG + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

    }

    public synchronized static void save(String name, Float t) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

    }


    public synchronized static void save(String name, Set<String> t) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            Set<String> convert=new HashSet<>();
            for (String string:t){
                convert.add(string.replace(",",COMMA_REPLACEMENT));
            }
            cv.put(VALUE, convert.toString());
            cr.update(uri, cv, null, null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

    }

    public static String getString(String name, String defaultValue) {
        String rtn = "";
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_STRING + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }
        return rtn;
    }

    public static int getInt(String name, int defaultValue) {
        String rtn = "0";
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_INT + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

        return Integer.parseInt(rtn);
    }

    public static float getFloat(String name, float defaultValue) {
        String rtn = "0";
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_FLOAT + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

        return Float.parseFloat(rtn);
    }

    public static boolean getBoolean(String name, boolean defaultValue) {
        String rtn = "0";
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }
        return Boolean.parseBoolean(rtn);
    }

    public static long getLong(String name, long defaultValue) {
        String rtn = "0";
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_LONG + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

        return Long.parseLong(rtn);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(String name, Set<String> defaultValue) {
        Set<String> returns=new HashSet<>();
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
            String rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
            if (!rtn.matches("\\[.*\\]")){
                return defaultValue;
            }
            String sub=rtn.substring(1,rtn.length()-1);
            String[] spl=sub.split(", ");
            for (String t:spl){
                returns.add(t.replace(COMMA_REPLACEMENT,", "));
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

        return returns;
    }

    public static boolean contains(String name) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_CONTAIN + SEPARATOR + name);
            String rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return false;
            } else {
                return Boolean.parseBoolean(rtn);
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }
        return false;
    }

    public static void remove(String name) {
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_LONG + SEPARATOR + name);
            cr.delete(uri, null, null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

    }

    public static void clear(){
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_CLEAN);
            cr.delete(uri,null,null);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

    }

    public static Map<String,?> getAll(){
        HashMap resultMap=new HashMap();
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_GET_ALL);
            Cursor cursor=cr.query(uri,null,null,null,null);
            if (cursor!=null && cursor.moveToFirst()){
                int nameIndex=cursor.getColumnIndex(CURSOR_COLUMN_NAME);
                int typeIndex=cursor.getColumnIndex(CURSOR_COLUMN_TYPE);
                int valueIndex=cursor.getColumnIndex(CURSOR_COLUMN_VALUE);
                do {
                    String key=cursor.getString(nameIndex);
                    String type=cursor.getString(typeIndex);
                    Object value = null;
                    if (type.equalsIgnoreCase(TYPE_STRING)) {
                        value= cursor.getString(valueIndex);
                        if (((String)value).contains(COMMA_REPLACEMENT)){
                            String str= (String) value;
                            if (str.matches("\\[.*\\]")){
                                String sub=str.substring(1,str.length()-1);
                                String[] spl=sub.split(", ");
                                Set<String> returns=new HashSet<>();
                                for (String t:spl){
                                    returns.add(t.replace(COMMA_REPLACEMENT,", "));
                                }
                                value=returns;
                            }
                        }
                    } else if (type.equalsIgnoreCase(TYPE_BOOLEAN)) {
                        value= cursor.getString(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_INT)) {
                        value= cursor.getInt(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_LONG)) {
                        value= cursor.getLong(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_FLOAT)) {
                        value= cursor.getFloat(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_STRING_SET)) {
                        value= cursor.getString(valueIndex);
                    }
                    resultMap.put(key,value);
                }
                while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

        return resultMap;
    }

    public static String getString(String name){
        String rentruString = "";
        try{
            checkContext();
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + TYPE_GET_ALL);
            Cursor cursor=cr.query(uri,null,null,null,null);
            if (cursor!=null && cursor.moveToFirst()){
                int nameIndex=cursor.getColumnIndex(CURSOR_COLUMN_NAME);
                int typeIndex=cursor.getColumnIndex(CURSOR_COLUMN_TYPE);
                int valueIndex=cursor.getColumnIndex(CURSOR_COLUMN_VALUE);
                do {
                    String key=cursor.getString(nameIndex);
                    String type=cursor.getString(typeIndex);
                    if (type.equalsIgnoreCase(TYPE_STRING) && TextUtils.equals(key, name)) {
                        rentruString= cursor.getString(valueIndex);
                    }
                }
                while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }

        return rentruString;
    }
}