package com.RogersCenter.readwritespell;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.RogersCenter.readwritespell.dictionary.Dict_10_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_11_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_12_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_13_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_14_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_15_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_16_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_17_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_1_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_2_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_3_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_4_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_5_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_6_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_7_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_8_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_9_Activity;
import com.RogersCenter.readwritespell.langs.Lang_10_Activity;
import com.RogersCenter.readwritespell.langs.Lang_11_Activity;
import com.RogersCenter.readwritespell.langs.Lang_12_Activity;
import com.RogersCenter.readwritespell.langs.Lang_13_Activity;
import com.RogersCenter.readwritespell.langs.Lang_14_Activity;
import com.RogersCenter.readwritespell.langs.Lang_15_Activity;
import com.RogersCenter.readwritespell.langs.Lang_16_Activity;
import com.RogersCenter.readwritespell.langs.Lang_17_Activity;
import com.RogersCenter.readwritespell.langs.Lang_18_Activity;
import com.RogersCenter.readwritespell.langs.Lang_19_Activity;
import com.RogersCenter.readwritespell.langs.Lang_1_Activity;
import com.RogersCenter.readwritespell.langs.Lang_20_Activity;
import com.RogersCenter.readwritespell.langs.Lang_21_Activity;
import com.RogersCenter.readwritespell.langs.Lang_22_Activity;
import com.RogersCenter.readwritespell.langs.Lang_23_Activity;
import com.RogersCenter.readwritespell.langs.Lang_24_Activity;
import com.RogersCenter.readwritespell.langs.Lang_25_Activity;
import com.RogersCenter.readwritespell.langs.Lang_2_Activity;
import com.RogersCenter.readwritespell.langs.Lang_3_Activity;
import com.RogersCenter.readwritespell.langs.Lang_4_Activity;
import com.RogersCenter.readwritespell.langs.Lang_5_Activity;
import com.RogersCenter.readwritespell.langs.Lang_6_Activity;
import com.RogersCenter.readwritespell.langs.Lang_7_Activity;
import com.RogersCenter.readwritespell.langs.Lang_8_Activity;
import com.RogersCenter.readwritespell.langs.Lang_9_Activity;
import com.RogersCenter.readwritespell.letters.Letter_BCA_Activity;
import com.RogersCenter.readwritespell.letters.Letter_DF_Activity;
import com.RogersCenter.readwritespell.letters.Letter_HE_Activity;
import com.RogersCenter.readwritespell.letters.Letter_JKLI_Activity;
import com.RogersCenter.readwritespell.letters.Letter_MGT_Activity;
import com.RogersCenter.readwritespell.letters.Letter_PNO_Activity;
import com.RogersCenter.readwritespell.letters.Letter_RSU_Activity;
import com.RogersCenter.readwritespell.letters.Letter_XYZ_Activity;
import com.RogersCenter.readwritespell.letters.Lettet_QVW_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt10_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt11_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt12_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt13_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt14_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt15_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt1_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt2_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt3_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt4_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt5_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt6_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt7_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt8_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt9_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_10_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_11_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_12_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_13_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_1_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_2_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_3_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_4_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_5_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_6_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_7_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_8_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_9_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt10_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt11_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt12_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt13_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt14_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt15_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt16_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt17_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt18_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt19_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt1_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt20_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt2_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt3_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt4_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt5_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt6_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt7_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt8_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt9_Activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class StaticValues {
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    public static String BaseURL_LETTERS = "https://www.app4learning.com/AppforLearning/letters/";
    public static String BaseURL_READING = "https://www.app4learning.com/AppforLearning/reading/";
    public static String BaseURL_WRITING = "https://www.app4learning.com/AppforLearning/writing/";
    public static String BaseURL_SPELLING = "https://www.app4learning.com/AppforLearning/spelling/";
    public static String BaseURL_LANGS = "https://www.app4learning.com/AppforLearning/langs/";
    public static String BaseURL_DICTIONARY = "https://www.app4learning.com/AppforLearning/dictionary/";
    public static String BaseURL_TUTORIALS = "https://www.app4learning.com/AppforLearning/tutorials/";
//    public static String APIKEY = "a151sa-161sfs6c1sd4c4w-cb5d8w4680";
//    public static String IMGPATH = "http://www.dotcompreview.com/sarjan/webfiles/Banner/";

    public static void SavePreferences(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void RemovePreferences(String key, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void RemoveALLPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static String GetPreferences(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static void GOTOLastOpenedCLASS(Context context, String classname) {
        Intent i;
        if (classname.equalsIgnoreCase("Letter_BCA_Activity")) {
            i = new Intent(context, Letter_BCA_Activity.class);
            context.startActivity(i);
        } else if (classname.equalsIgnoreCase("Letter_DF_Activity")) {
            i = new Intent(context, Letter_DF_Activity.class);
            context.startActivity(i);
        } else if (classname.equalsIgnoreCase("Letter_HE_Activity")) {
            i = new Intent(context, Letter_HE_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Letter_JKLI_Activity")) {
            i = new Intent(context, Letter_JKLI_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Letter_MGT_Activity")) {
            i = new Intent(context, Letter_MGT_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Letter_PNO_Activity")) {
            i = new Intent(context, Letter_PNO_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Letter_RSU_Activity")) {
            i = new Intent(context, Letter_RSU_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Letter_XYZ_Activity")) {
            i = new Intent(context, Letter_XYZ_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lettet_QVW_Activity")) {
            i = new Intent(context, Lettet_QVW_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_1_Activity")) {
            i = new Intent(context, Dict_1_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_2_Activity")) {
            i = new Intent(context, Dict_2_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_3_Activity")) {
            i = new Intent(context, Dict_3_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_4_Activity")) {
            i = new Intent(context, Dict_4_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_5_Activity")) {
            i = new Intent(context, Dict_5_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_6_Activity")) {
            i = new Intent(context, Dict_6_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_7_Activity")) {
            i = new Intent(context, Dict_7_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_8_Activity")) {
            i = new Intent(context, Dict_8_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_9_Activity")) {
            i = new Intent(context, Dict_9_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_10_Activity")) {
            i = new Intent(context, Dict_10_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_11_Activity")) {
            i = new Intent(context, Dict_11_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_12_Activity")) {
            i = new Intent(context, Dict_12_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_13_Activity")) {
            i = new Intent(context, Dict_13_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_14_Activity")) {
            i = new Intent(context, Dict_14_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_15_Activity")) {
            i = new Intent(context, Dict_15_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_16_Activity")) {
            i = new Intent(context, Dict_16_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Dict_17_Activity")) {
            i = new Intent(context, Dict_17_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_1_Activity")) {
            i = new Intent(context, Lang_1_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_2_Activity")) {
            i = new Intent(context, Lang_2_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_3_Activity")) {
            i = new Intent(context, Lang_3_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_4_Activity")) {
            i = new Intent(context, Lang_4_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_5_Activity")) {
            i = new Intent(context, Lang_5_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_6_Activity")) {
            i = new Intent(context, Lang_6_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_7_Activity")) {
            i = new Intent(context, Lang_7_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_8_Activity")) {
            i = new Intent(context, Lang_8_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_9_Activity")) {
            i = new Intent(context, Lang_9_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_10_Activity")) {
            i = new Intent(context, Lang_10_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_11_Activity")) {
            i = new Intent(context, Lang_11_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_12_Activity")) {
            i = new Intent(context, Lang_12_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_13_Activity")) {
            i = new Intent(context, Lang_13_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_14_Activity")) {
            i = new Intent(context, Lang_14_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_15_Activity")) {
            i = new Intent(context, Lang_15_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_16_Activity")) {
            i = new Intent(context, Lang_16_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_17_Activity")) {
            i = new Intent(context, Lang_17_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_18_Activity")) {
            i = new Intent(context, Lang_18_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_19_Activity")) {
            i = new Intent(context, Lang_19_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_20_Activity")) {
            i = new Intent(context, Lang_20_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_21_Activity")) {
            i = new Intent(context, Lang_21_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_22_Activity")) {
            i = new Intent(context, Lang_22_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_23_Activity")) {
            i = new Intent(context, Lang_23_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_24_Activity")) {
            i = new Intent(context, Lang_24_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Lang_25_Activity")) {
            i = new Intent(context, Lang_25_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt1_Activity")) {
            i = new Intent(context, Reading_Chapt1_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt2_Activity")) {
            i = new Intent(context, Reading_Chapt2_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt3_Activity")) {
            i = new Intent(context, Reading_Chapt3_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt4_Activity")) {
            i = new Intent(context, Reading_Chapt4_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt5_Activity")) {
            i = new Intent(context, Reading_Chapt5_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt6_Activity")) {
            i = new Intent(context, Reading_Chapt6_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt7_Activity")) {
            i = new Intent(context, Reading_Chapt7_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt8_Activity")) {
            i = new Intent(context, Reading_Chapt8_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt9_Activity")) {
            i = new Intent(context, Reading_Chapt9_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt10_Activity")) {
            i = new Intent(context, Reading_Chapt10_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt11_Activity")) {
            i = new Intent(context, Reading_Chapt11_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt12_Activity")) {
            i = new Intent(context, Reading_Chapt12_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt13_Activity")) {
            i = new Intent(context, Reading_Chapt13_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt14_Activity")) {
            i = new Intent(context, Reading_Chapt14_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Reading_Chapt15_Activity")) {
            i = new Intent(context, Reading_Chapt15_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_1_Activity")) {
            i = new Intent(context, Spell_1_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_2_Activity")) {
            i = new Intent(context, Spell_2_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_3_Activity")) {
            i = new Intent(context, Spell_3_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_4_Activity")) {
            i = new Intent(context, Spell_4_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_5_Activity")) {
            i = new Intent(context, Spell_5_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_6_Activity")) {
            i = new Intent(context, Spell_6_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_7_Activity")) {
            i = new Intent(context, Spell_7_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_8_Activity")) {
            i = new Intent(context, Spell_8_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_9_Activity")) {
            i = new Intent(context, Spell_9_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_10_Activity")) {
            i = new Intent(context, Spell_10_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_11_Activity")) {
            i = new Intent(context, Spell_11_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_12_Activity")) {
            i = new Intent(context, Spell_12_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Spell_13_Activity")) {
            i = new Intent(context, Spell_13_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt1_Activity")) {
            i = new Intent(context, Writing_Chapt1_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt2_Activity")) {
            i = new Intent(context, Writing_Chapt2_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt3_Activity")) {
            i = new Intent(context, Writing_Chapt3_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt4_Activity")) {
            i = new Intent(context, Writing_Chapt4_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt5_Activity")) {
            i = new Intent(context, Writing_Chapt5_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt6_Activity")) {
            i = new Intent(context, Writing_Chapt6_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt7_Activity")) {
            i = new Intent(context, Writing_Chapt7_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt8_Activity")) {
            i = new Intent(context, Writing_Chapt8_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt9_Activity")) {
            i = new Intent(context, Writing_Chapt9_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt10_Activity")) {
            i = new Intent(context, Writing_Chapt10_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt11_Activity")) {
            i = new Intent(context, Writing_Chapt11_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt12_Activity")) {
            i = new Intent(context, Writing_Chapt12_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt13_Activity")) {
            i = new Intent(context, Writing_Chapt13_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt14_Activity")) {
            i = new Intent(context, Writing_Chapt14_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt15_Activity")) {
            i = new Intent(context, Writing_Chapt15_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt16_Activity")) {
            i = new Intent(context, Writing_Chapt16_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt17_Activity")) {
            i = new Intent(context, Writing_Chapt17_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt18_Activity")) {
            i = new Intent(context, Writing_Chapt18_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt19_Activity")) {
            i = new Intent(context, Writing_Chapt19_Activity.class);
            context.startActivity(i);
        }else if (classname.equalsIgnoreCase("Writing_Chapt20_Activity")) {
            i = new Intent(context, Writing_Chapt20_Activity.class);
            context.startActivity(i);
        }

    }

    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
//            Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    public static boolean unzip(File zipFile, File targetDirectory) throws IOException {
        boolean bit = false;
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(new FileInputStream(zipFile)));
        try {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " +
                            dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                    zipFile.delete();
                    bit = true;
                } finally {
                    fout.close();
                }

            /* if time should be restored as well
            long time = ze.getTime();
            if (time > 0)
                file.setLastModified(time);
            */
            }
        } finally {
            zis.close();
        }
        return bit;
    }
}
