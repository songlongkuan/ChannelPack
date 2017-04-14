package cn.pencilso.channelpack;

import cn.pencilso.channelpack.utils.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Pencilso on 2017/4/13.
 */
public class ChannelPackMain {
    public static void main(String[] args) throws Exception {
        String configPath = FileUtils.getAppPath(ChannelPackMain.class) + "/json.config";
        if (!new File(configPath).exists()) throw new NullPointerException(configPath + " is not found file");

        {
            StringBuffer jsonBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new FileReader(new File(configPath)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
            Config.parse(new JSONObject(jsonBuffer.toString()));
        }
        if (new File(Config.getInstance().getFoldername()).exists()) {
            Scanner scanner = new Scanner(System.in);
            Log.i("输出目录已存在 是否跳过释放apk,进行多渠道打包.（YES | NO）");
            String inString = scanner.nextLine();
            if (!inString.toLowerCase().equals("yes")) {
                System.out.println("任务已放弃");
                return;
            }
        } else ZipUtils.unzipApk(Config.getInstance().getApk(), Config.getInstance().getOut());//释放apk文件
        JSONArray metaArray = Config.getInstance().getMeta_data();
        int leng = metaArray.length();
        for (int i = 0; i < leng; i++) {
            JSONObject mateJson = metaArray.getJSONObject(i);
            ReplaceUtils.replaceAndroidManifest(mateJson);
            ZipUtils.packApk(mateJson.getString("key"));
        }
        Log.i("任务执行完毕 渠道包存储路径:" + Config.getInstance().getFoldername()  + "dist");
    }
}
