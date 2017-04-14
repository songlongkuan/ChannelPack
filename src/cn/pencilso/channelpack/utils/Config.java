package cn.pencilso.channelpack.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Pencilso on 2017/4/13.
 */
public class Config {
    private static Config config;
    private String foldername;

    public static void parse(JSONObject jsonObject) {
        config = new Config();
        config.apk = new File(jsonObject.getString("apk"));
        config.out = new File(jsonObject.getString("out"));
        if (!config.apk.exists())
            throw new NullPointerException("apk 文件不存在");
        config.meta_data = jsonObject.getJSONArray("meta-data");
        config.foldername = config.out.getAbsolutePath() + File.separator + config.apk.getName().substring(0, config.apk.getName().lastIndexOf(".")) + File.separator;
        config.store_alias = jsonObject.getString("store_alias");
        config.store_file = jsonObject.getString("store_file");
        config.store_pass = jsonObject.getString("store_pass");
    }

    public static Config getInstance() {
        return config;
    }

    private Config() {
    }

    private File apk;
    private File out;
    private String store_alias;//证书的别名
    private String store_file;//证书的路径
    private String store_pass;//证书的密码
    private JSONArray meta_data;

    public File getApk() {
        return apk;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setApk(File apk) {
        this.apk = apk;
    }

    public File getOut() {
        return out;
    }

    public void setOut(File out) {
        this.out = out;
    }

    public JSONArray getMeta_data() {
        return meta_data;
    }

    public void setMeta_data(JSONArray meta_data) {
        this.meta_data = meta_data;
    }

    @Override
    public String toString() {
        return "Config{" +
                "apk='" + apk + '\'' +
                ", out='" + out + '\'' +
                ", meta_data=" + meta_data +
                '}';
    }

    public String getStore_alias() {
        return store_alias;
    }

    public void setStore_alias(String store_alias) {
        this.store_alias = store_alias;
    }

    public String getStore_file() {
        return store_file;
    }

    public void setStore_file(String store_file) {
        this.store_file = store_file;
    }

    public String getStore_pass() {
        return store_pass;
    }

    public void setStore_pass(String store_pass) {
        this.store_pass = store_pass;
    }
}
