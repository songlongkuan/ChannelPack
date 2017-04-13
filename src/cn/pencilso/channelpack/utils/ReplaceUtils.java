package cn.pencilso.channelpack.utils;

import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/4/13.
 */
public class ReplaceUtils {
    public static void replaceAndroidManifest(JSONObject jsonObject) {
        File androidManifest = new File(Config.getInstance().getFoldername() + "/AndroidManifest.xml");
        if (!androidManifest.exists())
            throw new NullPointerException("not found AndroidManifest.xml");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(androidManifest));
            StringBuffer mainifest = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().startsWith("<meta-data")) {
                    mainifest.append(replaceMetaData(line, jsonObject));
                } else mainifest.append(line);
                mainifest.append("\n");
            }
            bufferedReader.close();
            FileOutputStream outputStream = new FileOutputStream(androidManifest);
            outputStream.write(mainifest.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String replaceMetaData(String meta, JSONObject jsonObject) {
        String oldMeta = meta;
        try {
            if (meta.indexOf("android:value") == -1)
                return meta;
            String name = meta.substring(meta.indexOf("\"") + 1, meta.indexOf(" android:value") - 1);
            String value = meta.substring(meta.indexOf("value=\"") + 7, meta.lastIndexOf("\""));
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.equals("key") || !name.equals(key))
                    continue;
                meta = meta.replace(value, jsonObject.getString(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
            meta = oldMeta;
        }
        return meta;
    }
}
