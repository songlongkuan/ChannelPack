package cn.pencilso.channelpack.utils;

import java.io.File;

/**
 * Created by Administrator on 2017/4/13.
 */
public class FileUtils {
    /** */
    /**
     * 文件重命名
     *
     * @param oldfile 原来的文件名
     * @param newfile 新文件名
     */
    public static void renameFile(File oldfile, File newfile) {
        if (!oldfile.exists()) {
            return;//重命名文件不存在
        }
        if (newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
            newfile.delete();
        oldfile.renameTo(newfile);
    }
}
