package com.brandmatic.offerwall.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Preferences {


    companion object {

        private val TAG = "Prefrences"
        private var prefs: SharedPreferences? = null

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
        }


        fun setPreference(context: Context, key: String?, `val`: String?) {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.edit()
            editor.putString(key, `val`)
            editor.commit()
        }

        fun setPreference(context: Context, key: String?, `val`: Any?) {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.edit()
            val gson = Gson()
            val json = gson.toJson(`val`)
            editor.putString(key, json)
            editor.commit()
        }

        fun getPreferenceOfObject(context: Context, key: String?, typeClass: Class<*>?): Any {
            val gson = Gson()
            prefs = Preferences.getSharedPreferences(context)
            prefs!!.getString(key, "")
            return gson.fromJson(prefs!!.getString(key, ""), typeClass)
        }

        fun getPreferenceObject(
            context: Context,
            key: String?
        ): ArrayList<Any> {
            prefs = Preferences.getSharedPreferences(context)
            val gson = Gson()
            val jsonCars = prefs!!.getString(key, "")
            val type =
                object : TypeToken<ArrayList<Any?>?>() {}.type
            return gson.fromJson(jsonCars, type)
        }

        /* public static ArrayList<Object> getPreferenceList(Context mContext, String key) {
            SharedPreferences prefs = Preferences.getSharedPreferences(mContext);
            Gson gson = new Gson();
            String jsonCars = prefs.getString(key, "");
            Type type = new TypeToken<ArrayList<RegResponseChild>>() {
            }.getType();
            ArrayList<Object> carsList = gson.fromJson(jsonCars, type);

            return carsList;
        }*/

        /* public static ArrayList<Object> getPreferenceList(Context mContext, String key) {
            SharedPreferences prefs = Preferences.getSharedPreferences(mContext);
            Gson gson = new Gson();
            String jsonCars = prefs.getString(key, "");
            Type type = new TypeToken<ArrayList<RegResponseChild>>() {
            }.getType();
            ArrayList<Object> carsList = gson.fromJson(jsonCars, type);

            return carsList;
        }*/
        fun setPreference_float(context: Context, key: String?, `val`: Float) {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.edit()
            editor.putFloat(key, `val`)
            editor.commit()
        }


        fun setPreference(context: Context, key: String?, `val`: Boolean) {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.edit()
            editor.putBoolean(key, `val`)
            editor.commit()
        }


        fun setPreference_int(context: Context, key: String?, `val`: Int) {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.edit()
            editor.putInt(key, `val`)
            editor.commit()
        }


//    public static void setPreference_long(Context context, String key, long val) {
//        SharedPreferences settings = Preferences.getSharedPreferences(context);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putLong(key, val);
//        editor.commit();
//    }


//    public static boolean setPreferenceArray(Context mContext, String key, ArrayList<String> array) {
//        SharedPreferences prefs = Preferences.getSharedPreferences(mContext);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(key + "_size", array.size());
//        for (int i = 0; i < array.size(); i++)
//            editor.putString(key + "_" + i, array.get(i));
//        return editor.commit();
//    }

//    public static boolean setPreferenceList(Context mContext, String key, ArrayList<Object> array) {
//        SharedPreferences prefs = Preferences.getSharedPreferences(mContext);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(key + "_size", array.size());
//        Gson gson = new Gson();
//        String jsonCars = gson.toJson(array);
//        return editor.commit();
//    }

        //    public static void setPreference_long(Context context, String key, long val) {
        //        SharedPreferences settings = Preferences.getSharedPreferences(context);
        //        SharedPreferences.Editor editor = settings.edit();
        //        editor.putLong(key, val);
        //        editor.commit();
        //    }
        //    public static boolean setPreferenceArray(Context mContext, String key, ArrayList<String> array) {
        //        SharedPreferences prefs = Preferences.getSharedPreferences(mContext);
        //        SharedPreferences.Editor editor = prefs.edit();
        //        editor.putInt(key + "_size", array.size());
        //        for (int i = 0; i < array.size(); i++)
        //            editor.putString(key + "_" + i, array.get(i));
        //        return editor.commit();
        //    }
        //    public static boolean setPreferenceList(Context mContext, String key, ArrayList<Object> array) {
        //        SharedPreferences prefs = Preferences.getSharedPreferences(mContext);
        //        SharedPreferences.Editor editor = prefs.edit();
        //        editor.putInt(key + "_size", array.size());
        //        Gson gson = new Gson();
        //        String jsonCars = gson.toJson(array);
        //        return editor.commit();
        //    }
//    fun setPreferenceArray_String(
//        mContext: Context,
//        key: String,
//        array: List<RefreshAppListData>
//    ) {
//        val prefs: SharedPreferences =
//            Preferences.getSharedPreferences(mContext)
//        val responseChild = RefreshAppListResponse()
//        val gson = Gson()
//        val json = gson.toJson(responseChild)
//        val editor = prefs.edit()
//        editor.putString(key, json)
//        editor.putInt(key + "_size", array.size)
//        for (i in array.indices) editor.putString(key + "_" + i, array[i].getCid())
//        editor.apply()
//    }

        /* public static void setPreferenceToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            final Gson gson = new Gson();
            String serializedObject = gson.toJson(object);
            sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
            sharedPreferencesEditor.apply();
        }

        public static <GenericClass> GenericClass getPreferenceFromSharedPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
            if (sharedPreferences.contains(preferenceKey)) {
                final Gson gson = new Gson();
                return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
            }
            return null;
        }*/


//    public static void clearPreferenceArray(Context c, String key) {
//
//        SharedPreferences settings = Preferences.getSharedPreferences(c);
//
//        if (getPreferenceArray(c, key) != null && getPreferenceArray(c, key).size() > 0) {
//            for (Integer element : getPreferenceArray(c, key)) {
//                if (findPrefrenceKey(c, element) != null && settings.contains(findPrefrenceKey(c, element))) {
//                    SharedPreferences.Editor editor = settings.edit();
//                    editor.remove(findPrefrenceKey(c, element));
//                    editor.remove("element");
//                    editor.commit();
//                }
//            }
//        }
//    }

        /* public static void setPreferenceToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            final Gson gson = new Gson();
            String serializedObject = gson.toJson(object);
            sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
            sharedPreferencesEditor.apply();
        }

        public static <GenericClass> GenericClass getPreferenceFromSharedPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
            if (sharedPreferences.contains(preferenceKey)) {
                final Gson gson = new Gson();
                return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
            }
            return null;
        }*/
        //    public static void clearPreferenceArray(Context c, String key) {
        //
        //        SharedPreferences settings = Preferences.getSharedPreferences(c);
        //
        //        if (getPreferenceArray(c, key) != null && getPreferenceArray(c, key).size() > 0) {
        //            for (Integer element : getPreferenceArray(c, key)) {
        //                if (findPrefrenceKey(c, element) != null && settings.contains(findPrefrenceKey(c, element))) {
        //                    SharedPreferences.Editor editor = settings.edit();
        //                    editor.remove(findPrefrenceKey(c, element));
        //                    editor.remove("element");
        //                    editor.commit();
        //                }
        //            }
        //        }
        //    }
        fun findPrefrenceKey(con: Context, value: Int): String? {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(con)
            val editor = settings.all
            for ((key, value1) in editor) {
                if (value == value1) {
                    return key
                }
            }
            return null // not found
        }


        fun getPreference(context: Context, key: String?): String? {
            prefs = Preferences.getSharedPreferences(context)
            return prefs!!.getString(key, "")
        }


        fun getPreferenceArray(mContext: Context, key: String): ArrayList<String?> {
            prefs = Preferences.getSharedPreferences(mContext)
            val size = prefs!!.getInt(key + "_size", 0)
            val array = ArrayList<String?>(size)
            for (i in 0 until size) array.add(prefs!!.getString(key + "_" + i, ""))
            return array
        }


//    public static long getPreference_long(Context context, String key) {
//        SharedPreferences prefs = Preferences.getSharedPreferences(context);
//        return prefs.getLong(key, 0);
//
//    }

        //    public static long getPreference_long(Context context, String key) {
        //        SharedPreferences prefs = Preferences.getSharedPreferences(context);
        //        return prefs.getLong(key, 0);
        //
        //    }
        fun getPreference_boolean(context: Context, key: String?): Boolean {
            prefs = Preferences.getSharedPreferences(context)
            return prefs!!.getBoolean(key, false)
        }

//    public static int getPreference_int(Context context, String key) {
//        prefs = Preferences.getSharedPreferences(context);
//        return prefs.getInt(key, 0);
//
//    }

//    public static int getPreference_intNumber(Context context, String key) {
//        prefs = Preferences.getSharedPreferences(context);
//        return prefs.getInt(key, 0);
//
//    }

        /**
         * Clear all stored preferences
         *
         * @param context
         *            - context
         */
        // public static void removeAllPreference(Context context) {
        // SharedPreferences settings = Preferences.getSharedPreferences(context);
        // SharedPreferences.Editor editor = settings.edit();
        // editor.clear();
        // editor.commit();
        // }

        //    public static int getPreference_int(Context context, String key) {
        //        prefs = Preferences.getSharedPreferences(context);
        //        return prefs.getInt(key, 0);
        //
        //    }
        //    public static int getPreference_intNumber(Context context, String key) {
        //        prefs = Preferences.getSharedPreferences(context);
        //        return prefs.getInt(key, 0);
        //
        //    }
        /**
         * Clear all stored preferences
         *
         * @param context
         * - context
         */
        // public static void removeAllPreference(Context context) {
        // SharedPreferences settings = Preferences.getSharedPreferences(context);
        // SharedPreferences.Editor editor = settings.edit();
        // editor.clear();
        // editor.commit();
        // }
        /**
         * Clear all stored preferences
         *
         * @param context - context
         */
        fun getAllPreference(context: Context): String {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.all
            var text = ""
            try {
                for ((key, value1) in editor) {
                    val value = value1!!
                    // do stuff
                    text += "\t$key = $value\t"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return text
        }

        /**
         * Remove preference key
         *
         * @param context - context
         * @param key     - the key which you stored before
         */
        fun removePreference(context: Context, key: String?) {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.edit()
            editor.remove(key)
            editor.commit()
        }

        /**
         * Clear all stored preferences
         *
         * @param context - context
         */
        fun removeAllPreference(context: Context) {
            val settings: SharedPreferences =
                Preferences.getSharedPreferences(context)
            val editor = settings.edit()
            editor.clear()
            editor.commit()
        }

        fun getPreferenceProductType(context: Context, key: String?): String? {
            prefs = Preferences.getSharedPreferences(context)
            return prefs!!.getString(key, "service")
        }


        fun setSharedPreferenceIntList(pContext: Context, pKey: String, pData: ArrayList<Int?>) {
            val editor = pContext.getSharedPreferences(pKey, Activity.MODE_PRIVATE).edit()
            editor.putInt(pKey + "size", pData.size)
            editor.commit()
            for (i in pData.indices) {
                val editor1 = pContext.getSharedPreferences(pKey, Activity.MODE_PRIVATE).edit()
                editor1.putInt(pKey + i, pData[i]!!)
                editor1.commit()
            }
        }


        fun getSharedPreferenceIntList(pContext: Context, pKey: String): ArrayList<Int> {
            Log.e("pkey", "" + pKey)
            val size =
                pContext.getSharedPreferences(pKey, Activity.MODE_PRIVATE).getInt(pKey + "size", 0)
            val list = ArrayList<Int>()
            for (i in 0 until size) {
                list.add(
                    pContext.getSharedPreferences(pKey, Activity.MODE_PRIVATE).getInt(pKey + i, 0)
                )
            }
            return list
        }


        fun removeSharedPreferenceIntList(pContext: Context, pKey: String): ArrayList<Int> {
            Log.e("pkey1", "" + pKey)
            val size =
                pContext.getSharedPreferences(pKey, Activity.MODE_PRIVATE).getInt(pKey + "size", 0)
            val list = ArrayList<Int>()
            for (i in 0 until size) {
                list.removeAt(
                    pContext.getSharedPreferences(pKey, Activity.MODE_PRIVATE).getInt(pKey + i, 0)
                )
            }
            return list
        }


//    public static void clearPreferenceArrayList(Context c, String key) {
//        Log.e("sizw", "" + key);
//        SharedPreferences.Editor editor = c.getSharedPreferences(key, Activity.MODE_PRIVATE).edit();
//        editor.remove(key);
//        editor.commit();

//        SharedPreferences settings = Preferences.getSharedPreferences(c);

//        if (getPreferenceArray(c, key) != null && getPreferenceArray(c, key).size() > 0) {
//            for (Integer element : getPreferenceArray(c, key)) {
//                if (findPrefrenceKey(c, element) != null && settings.contains(findPrefrenceKey(c, element))) {
//                    SharedPreferences.Editor editor = settings.edit();
//                    editor.remove(findPrefrenceKey(c, element));
//                    editor.remove(key);
        // editor.clear();
        // editor.commit();
//                }
//            }
//        }
//    }

    }
}
