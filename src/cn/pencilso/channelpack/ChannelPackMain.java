package cn.pencilso.channelpack;

import cn.pencilso.channelpack.utils.Config;
import cn.pencilso.channelpack.utils.ReplaceUtils;
import cn.pencilso.channelpack.utils.ZipUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/4/13.
 */
public class ChannelPackMain {
    public static void main(String[] args) throws Exception {
        String configPath = "C://apktool/json.config";
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
            System.out.println("输出目录已存在 是否跳过释放apk,进行多渠道打包.（YES | NO）");
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
        ZipUtils.sendCMD(null);
    }
}
