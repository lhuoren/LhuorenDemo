package com.syy.modulebase.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Response;

public class AbFileUtil {
    private static final String TAG = "AbFileUtil";
    private static String downloadRootDir = null;
    private static String imageDownloadDir = null;
    private static String fileDownloadDir = null;
    private static String cacheDownloadDir = null;
    private static String dbDownloadDir = null;
    private static int freeSdSpaceNeededToCache = 209715200;

    private static String publicDownloadRootDir = null;
    private static String publicImageDownloadDir = null;
    private static String publicFileDownloadDir = null;
    private static String publicCacheDownloadDir = null;
    private static String publicDbDownloadDir = null;

    public AbFileUtil() {
    }

    public static Bitmap getBitmapFromSD(File file, int type, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;

        try {
            if (!isCanUseSD()) {
                return null;
            }

            if (!file.exists()) {
                return null;
            }

            if (type == 0) {
                bitmap = AbImageUtil.getCutBitmap(file, desiredWidth, desiredHeight);
            } else if (type == 1) {
                bitmap = AbImageUtil.getScaleBitmap(file, desiredWidth, desiredHeight);
            } else {
                bitmap = AbImageUtil.getBitmap(file);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromSD(File file) {
        Bitmap bitmap = null;

        try {
            if (!isCanUseSD()) {
                return null;
            }

            if (!file.exists()) {
                return null;
            }

            bitmap = AbImageUtil.getBitmap(file);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromByte(byte[] imgByte, String fileName, int type, int desiredWidth, int desiredHeight) {
        FileOutputStream fos = null;
        DataInputStream dis = null;
        ByteArrayInputStream bis = null;
        Bitmap bitmap = null;
        File file = null;

        try {
            if (imgByte != null) {
                file = new File(imageDownloadDir + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }

                fos = new FileOutputStream(file);

                bis = new ByteArrayInputStream(imgByte);
                dis = new DataInputStream(bis);
                byte[] buffer = new byte[1024];

                int readLength;
                while ((readLength = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, readLength);

                    try {
                        Thread.sleep(500L);
                    } catch (Exception var30) {
                    }
                }

                fos.flush();
                bitmap = getBitmapFromSD(file, type, desiredWidth, desiredHeight);
            }
        } catch (Exception var31) {
            var31.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (Exception var29) {
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception var28) {
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception var27) {
                }
            }

        }

        return bitmap;
    }

    public static Bitmap getBitmapFromURL(String url, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;

        try {
            bitmap = AbImageUtil.getBitmap(url, desiredWidth, desiredHeight);
        } catch (Exception var5) {
            LogUtils.d(TAG, "下载图片异常：" + var5.getMessage());
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromSrc(String src) {
        Bitmap bit = null;

        try {
            bit = BitmapFactory.decodeStream(AbFileUtil.class.getResourceAsStream(src));
        } catch (Exception var3) {
            LogUtils.d(TAG, "获取图片异常：" + var3.getMessage());
        }

        return bit;
    }

    public static Bitmap getBitmapFromAsset(Context context, String fileName) {
        Bitmap bit = null;

        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            bit = BitmapFactory.decodeStream(is);
        } catch (Exception var5) {
            LogUtils.d(TAG, "获取图片异常：" + var5.getMessage());
        }

        return bit;
    }

    public static Drawable getDrawableFromAsset(Context context, String fileName) {
        Drawable drawable = null;

        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            drawable = Drawable.createFromStream(is, (String) null);
        } catch (Exception var5) {
            LogUtils.d(TAG, "获取图片异常：" + var5.getMessage());
        }

        return drawable;
    }

    public static int getContentLengthFromUrl(String Url) {
        int mContentLength = 0;

        try {
            URL url = new URL(Url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) url.openConnection();
            mHttpURLConnection.setConnectTimeout(5000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", Url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200) {
                mContentLength = mHttpURLConnection.getContentLength();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            LogUtils.d(TAG, "获取长度异常：" + var4.getMessage());
        }

        return mContentLength;
    }

    public static String getRealFileNameFromUrl(String url) {
        Object name = null;

        try {
            if (AbStrUtil.isEmpty(url)) {
                return (String) name;
            }

            URL mUrl = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(5000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("User-Agent", "");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200) {
                int i = 0;

                while (true) {
                    String mine = mHttpURLConnection.getHeaderField(i);
                    if (mine == null) {
                        break;
                    }

                    if ("content-disposition".equals(mHttpURLConnection.getHeaderFieldKey(i).toLowerCase())) {
                        Matcher m = Pattern.compile(".*filename=(.*)").matcher(mine.toLowerCase());
                        if (m.find()) {
                            return m.group(1).replace("\"", "");
                        }
                    }

                    ++i;
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
            LogUtils.e(TAG, "网络上获取文件名失败");
        }

        return (String) name;
    }

    public static String getRealFileName(HttpURLConnection connection) {
        Object name = null;

        try {
            if (connection == null) {
                return (String) name;
            }

            if (connection.getResponseCode() == 200) {
                int i = 0;

                while (true) {
                    String mime = connection.getHeaderField(i);
                    if (mime == null) {
                        break;
                    }

                    if ("content-disposition".equals(connection.getHeaderFieldKey(i).toLowerCase())) {
                        Matcher m = Pattern.compile(".*filename=(.*)").matcher(mime.toLowerCase());
                        if (m.find()) {
                            return m.group(1).replace("\"", "");
                        }
                    }

                    ++i;
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            LogUtils.e(TAG, "网络上获取文件名失败");
        }

        return (String) name;
    }

    public static String getRealFileName(Response response) {
        String name = null;

        try {
            if (response == null) {
                return name;
            }

            List<String> headers = response.headers("content-disposition");

            for (int i = 0; i < headers.size(); ++i) {
                Matcher m = Pattern.compile(".*filename=(.*)").matcher((CharSequence) headers.get(i));
                if (m.find()) {
                    name = m.group(1).replace("\"", "");
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            LogUtils.e(TAG, "网络上获取文件名失败");
        }

        return name;
    }

    public static String getCacheFileNameFromUrl(String url) {
        if (AbStrUtil.isEmpty(url)) {
            return null;
        } else {
            String name = null;

            try {
                name = AbMd5.MD5(url);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            return name;
        }
    }

    public static String getCacheFileNameFromUrl(String url, Response response) {
        if (AbStrUtil.isEmpty(url)) {
            return null;
        } else {
            String name = null;

            try {
                String suffix = getMIMEFromUrl(url, response);
                if (AbStrUtil.isEmpty(suffix)) {
                    suffix = ".ab";
                }

                name = AbMd5.MD5(url) + suffix;
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return name;
        }
    }

    public static String getCacheFileNameFromUrl(String url, HttpURLConnection connection) {
        if (AbStrUtil.isEmpty(url)) {
            return null;
        } else {
            String name = null;

            try {
                String suffix = getMIMEFromUrl(url, connection);
                if (AbStrUtil.isEmpty(suffix)) {
                    suffix = ".ab";
                }

                name = AbMd5.MD5(url) + suffix;
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return name;
        }
    }

    public static String getMIMEFromUrl(String url, HttpURLConnection connection) {
        if (AbStrUtil.isEmpty(url)) {
            return null;
        } else {
            String suffix = null;

            try {
                if (url.lastIndexOf(".") != -1) {
                    suffix = url.substring(url.lastIndexOf("."));
                    if (suffix.indexOf("/") != -1 || suffix.indexOf("?") != -1 || suffix.indexOf("&") != -1) {
                        suffix = null;
                    }
                }

                if (AbStrUtil.isEmpty(suffix)) {
                    String fileName = getRealFileName(connection);
                    if (fileName != null && fileName.lastIndexOf(".") != -1) {
                        suffix = fileName.substring(fileName.lastIndexOf("."));
                    }
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return suffix;
        }
    }

    public static String getMIMEFromUrl(String url, Response response) {
        if (AbStrUtil.isEmpty(url)) {
            return null;
        } else {
            String mime = null;

            try {
                if (url.lastIndexOf(".") != -1) {
                    mime = url.substring(url.lastIndexOf("."));
                    if (mime.indexOf("/") != -1 || mime.indexOf("?") != -1 || mime.indexOf("&") != -1) {
                        mime = null;
                    }
                }

                if (AbStrUtil.isEmpty(mime)) {
                    String fileName = getRealFileName(response);
                    if (fileName != null && fileName.lastIndexOf(".") != -1) {
                        mime = fileName.substring(fileName.lastIndexOf("."));
                    }
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return mime;
        }
    }

    public static byte[] getByteArrayFromSD(String path) {
        byte[] bytes = null;
        ByteArrayOutputStream out = null;

        FileInputStream in;
        try {
            File file = new File(path);
            Object var22;
            if (!isCanUseSD()) {
                var22 = null;
                return (byte[]) var22;
            }

            if (!file.exists()) {
                var22 = null;
                return (byte[]) var22;
            }

            long fileSize = file.length();
            if (fileSize <= 2147483647L) {
                in = new FileInputStream(path);
                out = new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];
                boolean var8 = false;

                int size;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }

                in.close();
                bytes = out.toByteArray();
                return bytes;
            }

            in = null;
        } catch (Exception var20) {
            var20.printStackTrace();
            return bytes;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception var19) {
                }
            }

        }

        return bytes;
    }

    public static void writeByteArrayToSD(String path, byte[] content, boolean create) {
        FileOutputStream fos = null;

        try {
            File file = new File(path);
            if (!isCanUseSD()) {
                return;
            }

            if (!file.exists()) {
                if (!create) {
                    return;
                }

                File parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                    file.createNewFile();
                }
            }

            fos = new FileOutputStream(path);
            fos.write(content);
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception var15) {
                }
            }

        }

    }

    public static void writeBitmapToSD(String path, Bitmap bitmap, boolean create) {
        FileOutputStream fos = null;

        try {
            File file = new File(path);
            if (!isCanUseSD()) {
                return;
            }

            if (!file.exists() && create) {
                File parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                    file.createNewFile();
                }
            }

            fos = new FileOutputStream(path);
            bitmap.compress(CompressFormat.PNG, 100, fos);
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception var14) {
                }
            }

        }

    }

    public static void copyAssets2SD(Context context, String assetDir, String outDir) {
        try {
            String[] files = context.getAssets().list(assetDir);
            File outDirFile = new File(outDir);
            if (!outDirFile.exists()) {
                outDirFile.mkdirs();
            }

            for (int i = 0; i < files.length; ++i) {
                String fileName = files[i];
                String[] filesChild = context.getAssets().list(fileName);
                if (filesChild != null && filesChild.length > 0) {
                    copyAssets2SD(context, fileName, outDir + "/" + fileName);
                } else {
                    InputStream in = null;
                    if (!AbStrUtil.isEmpty(assetDir)) {
                        in = context.getAssets().open(assetDir + "/" + fileName);
                    } else {
                        in = context.getAssets().open(fileName);
                    }

                    File outFile = new File(outDir + "/" + fileName);
                    if (outFile.exists()) {
                        outFile.delete();
                    }

                    outFile.createNewFile();
                    OutputStream out = new FileOutputStream(outFile);
                    byte[] buf = new byte[1024];

                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    in.close();
                    out.close();
                }
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception var1) {
            var1.printStackTrace();
            return false;
        }
    }

    public static void initPublicFileDir(Context context) {
        AbAppConfig.DOWNLOAD_ROOT_DIR = "esp";
        PackageInfo info = getPackageInfo(context);
        String downloadRootPath = File.separator + AbAppConfig.DOWNLOAD_ROOT_DIR + File.separator + info.packageName + File.separator;
        String imageDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_IMAGE_DIR + File.separator;
        String fileDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_FILE_DIR + File.separator;
        String cacheDownloadPath = downloadRootPath + AbAppConfig.CACHE_DIR + File.separator;
        String dbDownloadPath = downloadRootPath + AbAppConfig.DB_DIR + File.separator;

        try {
            if (!isCanUseSD()) {
                return;
            }

            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                //卸载app同时会删除该目录
                root = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            }

            LogUtils.e("root_getAbsolutePath", root.getAbsolutePath());

            File downloadDir = new File(root.getAbsolutePath() + downloadRootPath);
            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }

            publicDownloadRootDir = downloadDir.getPath();

            LogUtils.e("publicDownloadRootDir", "publicDownloadRootDir:" + publicDownloadRootDir);
            File cacheDownloadDirFile = new File(root.getAbsolutePath() + cacheDownloadPath);
            if (!cacheDownloadDirFile.exists()) {
                cacheDownloadDirFile.mkdirs();
            }

            publicCacheDownloadDir = cacheDownloadDirFile.getPath();
            File imageDownloadDirFile = new File(root.getAbsolutePath() + imageDownloadPath);
            if (!imageDownloadDirFile.exists()) {
                imageDownloadDirFile.mkdirs();
            }

            publicImageDownloadDir = imageDownloadDirFile.getPath();
            File fileDownloadDirFile = new File(root.getAbsolutePath() + fileDownloadPath);
            if (!fileDownloadDirFile.exists()) {
                fileDownloadDirFile.mkdirs();
            }

            publicFileDownloadDir = fileDownloadDirFile.getPath();
            LogUtils.e("publicDownloadRootDir", "publicFileDownloadDir:" + publicFileDownloadDir);

            File publicDbDownloadDirFile = new File(root.getAbsolutePath() + dbDownloadPath);
            if (!publicDbDownloadDirFile.exists()) {
                publicDbDownloadDirFile.mkdirs();
            }

            publicDbDownloadDir = publicDbDownloadDirFile.getPath();
        } catch (Exception var13) {
            var13.printStackTrace();
        }
    }

    public static void initFileDir(Context context) {
        AbAppConfig.DOWNLOAD_ROOT_DIR = "esp";
        PackageInfo info = getPackageInfo(context);
        String downloadRootPath = File.separator + AbAppConfig.DOWNLOAD_ROOT_DIR + File.separator + info.packageName + File.separator;
        String imageDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_IMAGE_DIR + File.separator;
        String fileDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_FILE_DIR + File.separator;
        String cacheDownloadPath = downloadRootPath + AbAppConfig.CACHE_DIR + File.separator;
        String dbDownloadPath = downloadRootPath + AbAppConfig.DB_DIR + File.separator;

        try {
            if (!isCanUseSD()) {
                return;
            }

            File root = Environment.getExternalStorageDirectory();

            LogUtils.e("getExternalStorageDirectory-1", root.getAbsolutePath());

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                root = new File(getExternalFilesDir(context));
                LogUtils.e("getExternalStorageDirectory-2", root.getAbsolutePath() + ",getExternalFilesDir:" + getExternalFilesDir(context));
            }

//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                root = Environment.getExternalStoragePublicDirectory("");
//                LogUtils.e("getExternalStorageDirectory-2", root.getAbsolutePath()+",getExternalFilesDir:"+ getExternalFilesDir(context));
//            }

            File downloadDir = new File(root.getAbsolutePath() + downloadRootPath);
            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }

            downloadRootDir = downloadDir.getPath();
            File cacheDownloadDirFile = new File(root.getAbsolutePath() + cacheDownloadPath);
            if (!cacheDownloadDirFile.exists()) {
                cacheDownloadDirFile.mkdirs();
            }

            cacheDownloadDir = cacheDownloadDirFile.getPath();
            File imageDownloadDirFile = new File(root.getAbsolutePath() + imageDownloadPath);
            if (!imageDownloadDirFile.exists()) {
                imageDownloadDirFile.mkdirs();
            }

            imageDownloadDir = imageDownloadDirFile.getPath();
            File fileDownloadDirFile = new File(root.getAbsolutePath() + fileDownloadPath);
            if (!fileDownloadDirFile.exists()) {
                fileDownloadDirFile.mkdirs();
            }

            fileDownloadDir = fileDownloadDirFile.getPath();
            File dbDownloadDirFile = new File(root.getAbsolutePath() + dbDownloadPath);
            if (!dbDownloadDirFile.exists()) {
                dbDownloadDirFile.mkdirs();
            }

            dbDownloadDir = dbDownloadDirFile.getPath();
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    private static String getExternalFilesDir(Context context) {
        // SDCard地址 /storage/emulated/0
        // getExternalStorageDirectory在29已废弃
//        String saveDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        // getExternalFilesDir()  用于获取SDCard/Android/data/你的应用的包名/files/ 目录
        File externalFileRootDir = context.getExternalFilesDir(null);
        do {
            externalFileRootDir = Objects.requireNonNull(externalFileRootDir).getParentFile();
        } while (Objects.requireNonNull(externalFileRootDir).getAbsolutePath().contains("/Android"));

        return Objects.requireNonNull(externalFileRootDir).getAbsolutePath();
    }

    @SuppressLint("WrongConstant")
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;

        try {
            String packageName = context.getPackageName();
            info = context.getPackageManager().getPackageInfo(packageName, 1);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return info;
    }


    public static int freeSpaceOnSD() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdFreeMB = (double) stat.getAvailableBlocks() * (double) stat.getBlockSize() / 1024.0D * 1024.0D;
        return (int) sdFreeMB;
    }

    public static boolean clearDownloadFile() {
        try {
            File fileDirectory = new File(downloadRootDir);
            deleteFile(fileDirectory);
            return true;
        } catch (Exception var1) {
            var1.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile(File file) {
        try {
            if (!isCanUseSD()) {
                return false;
            } else if (file == null) {
                return true;
            } else {
                if (file.isDirectory()) {
                    File[] files = file.listFiles();

                    for (int i = 0; i < files.length; ++i) {
                        deleteFile(files[i]);
                    }
                } else {
                    file.delete();
                }

                return true;
            }
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static String readAssetsByName(Context context, String name, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;

        try {
            inputReader = new InputStreamReader(context.getAssets().open(name));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();

            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }

            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }

                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception var15) {
                var15.printStackTrace();
            }

        }

        return text;
    }

    public static String readRawByName(Context context, int id, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;

        try {
            inputReader = new InputStreamReader(context.getResources().openRawResource(id));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();

            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }

            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }

                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception var15) {
                var15.printStackTrace();
            }

        }

        return text;
    }

    public static String getDownloadRootDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }

        return downloadRootDir;
    }

    public static String getImageDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }

        return imageDownloadDir;
    }

    public static String getFileDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }

        return fileDownloadDir;
    }

    public static String getCacheDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }

        return cacheDownloadDir;
    }

    public static String getDbDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }

        return dbDownloadDir;
    }

    public static String getPublicFileDownloadDir(Context context) {
        if (publicDownloadRootDir == null) {
            initPublicFileDir(context);
        }

        return publicFileDownloadDir;
    }

    public static String getPublicDbDownloadDir(Context context) {
        if (publicDownloadRootDir == null) {
            initPublicFileDir(context);
        }
        return publicDbDownloadDir;
    }

    public static int getFreeSdSpaceNeededToCache() {
        return freeSdSpaceNeededToCache;
    }

    public static class FileLastModifSort implements Comparator<File> {
        public FileLastModifSort() {
        }

        public int compare(File arg0, File arg1) {
            if (arg0.lastModified() > arg1.lastModified()) {
                return 1;
            } else {
                return arg0.lastModified() == arg1.lastModified() ? 0 : -1;
            }
        }
    }
}