package cn.pencilso.channelpack.utils;

import brut.apktool.Main;
import brut.common.BrutException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/13.
 */
public class ZipUtils {
    public static void unzipApk(String apkPath, String outPath) {
        unzipApk(new File(apkPath), new File(outPath));
    }

    public static final String JARSIGNERCMD = "cmd /c start jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore %s -storepass %s -signedjar %s %s %s -tsa https://timestamp.geotrust.com/tsa";

    public static void unzipApk(File apkPath, File outPath) {
        if (apkPath == null
                || !apkPath.exists()
                || outPath == null
                ) throw new NullPointerException("文件不存在");
        String args[] = {
                "d",//解压指令
                apkPath.getAbsolutePath(),//apk路径
                "-o",//我也不知道这是什么
                Config.getInstance().getFoldername()
        };
        try {
            System.out.println("正在释放apk文件");
            Main.main(args);
            System.out.println("释放完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrutException e) {
            e.printStackTrace();
        }
    }

    public static void packApk(String channel) {
        String apkName = Config.getInstance().getOut().getAbsolutePath() + File.separator + "%s.apk";
        String outFile = String.format(apkName, channel);
        String args[] = {
                "b",//重新打包命令
                Config.getInstance().getFoldername(),//apk释放的目录
                channel
        };
        try {
            System.out.println("正在重新打包 :" + channel + "   path:" + outFile);
            Main.main(args);
            System.out.println(channel + " 重新打包完成");
            /**
             * 进入释放目录  将dist目录下的apk文件重命名
             */
            File oldFile = new File(Config.getInstance().getFoldername() + "dist/" + Config.getInstance().getApk().getName());
            File newFile = new File(Config.getInstance().getFoldername() + "dist/" + channel + ".apk");
            FileUtils.renameFile(oldFile, newFile);
            sendCMD(channel);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrutException e) {
            e.printStackTrace();
        }
    }

    public static void sendCMD(String channel) {
        try {
            Config config = Config.getInstance();
            String cmd = String.format(JARSIGNERCMD,
                    config.getStore_file(),
                    config.getStore_pass(),
                    config.getFoldername() + File.separator + "dist" + File.separator + channel + "_signed.apk",
                    config.getFoldername() + File.separator + "dist"  + File.separator + channel + ".apk",
                    config.getStore_alias()
            );
            if (channel != null) {
                System.out.println("执行终端签名 ----" + channel);
                Runtime.getRuntime().exec(cmd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
