package com.dsouchon.wayo.visualfusion;

/**
 * Created by daniel on 28/03/2017.
 */


import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;


public class Local extends Activity {

    public SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";




//e.g. write("VendorID", "1")

    public synchronized static void write(Context context, String fileName, String settingValue) throws IOException {
        try {
            File settingsFile = new File(context.getFilesDir(), fileName);
            FileOutputStream out = new FileOutputStream(settingsFile);
            String id = settingValue;
            out.write(id.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//e.g. string VendorID = read("VendorID")

    public static String read(Context context, String fileName) throws IOException {
        File settingsFile = new File(context.getFilesDir(), fileName);
        if(settingsFile.exists()) {
            RandomAccessFile f = new RandomAccessFile(settingsFile, "r");

            byte[] bytes = new byte[(int) f.length()];
            f.readFully(bytes);
            f.close();
            return new String(bytes);
        }
        else
        {
            return "";
        }
    }

    public static Boolean isSet(Context con, String setting) {
        Boolean ret = false;
        try {
            String sett = read(con, setting);

            if (sett.equals("0") || sett.equals("")) {
                return false;
            }
            else {

                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    public static  void Setxx(Context con, String file, String settingValue){
        try{
            write(con, file, settingValue);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return;

    }

    public static  String Getxx(Context con, String file){


        String ret = "";

        try{
            ret = read(con, file);

        }
        catch (Exception e){
            e.printStackTrace();
            ret = "";
        }
        return ret;

    }





}

