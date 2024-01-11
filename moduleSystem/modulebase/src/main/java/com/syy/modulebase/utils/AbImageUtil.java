package com.syy.modulebase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class AbImageUtil {
    private static final String TAG = "AbImageUtil";
    public static final int CUTIMG = 0;
    public static final int SCALEIMG = 1;
    public static final int ORIGINALIMG = 2;
    public static final int MAX_WIDTH = 2048;
    public static final int MAX_HEIGHT = 2048;

    public AbImageUtil() {
    }

    public static Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        URLConnection con = null;
        InputStream is = null;

        try {
            URL imageURL = new URL(url);
            con = imageURL.openConnection();
            con.setDoInput(true);
            con.connect();
            is = con.getInputStream();
            bitmap = BitmapFactory.decodeStream(is, (Rect)null, (Options)null);
        } catch (Exception var13) {
            LogUtils.d(TAG, "" + var13.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception var12) {
                var12.printStackTrace();
            }

        }

        return bitmap;
    }

    public static Bitmap getBitmap(File file) {
        Bitmap resizeBmp = null;

        try {
            resizeBmp = BitmapFactory.decodeFile(file.getPath());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return resizeBmp;
    }

    public static Bitmap getBitmap(String url, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;
        URLConnection con = null;
        InputStream is = null;

        try {
            URL imageURL = new URL(url);
            con = imageURL.openConnection();
            con.setDoInput(true);
            con.connect();
            is = con.getInputStream();
            bitmap = getBitmap(is, desiredWidth, desiredHeight);
            if (bitmap.getWidth() > desiredWidth || bitmap.getHeight() > desiredHeight) {
                bitmap = getCutBitmap(bitmap, desiredWidth, desiredHeight);
            }
        } catch (Exception var15) {
            var15.printStackTrace();
            LogUtils.d(TAG, "" + var15.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }

        }

        return bitmap;
    }

    public static Bitmap getBitmap(InputStream inputStream, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;

        try {
            byte[] data = AbStreamUtil.stream2bytes(inputStream);
            bitmap = getBitmap(data, desiredWidth, desiredHeight);
        } catch (Exception var13) {
            var13.printStackTrace();
            LogUtils.d(TAG, "" + var13.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception var12) {
                var12.printStackTrace();
            }

        }

        return bitmap;
    }

    public static Bitmap getBitmap(byte[] data, int desiredWidth, int desiredHeight) {
        Bitmap resizeBmp = null;

        try {
            Options opts = new Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, opts);
            int srcWidth = opts.outWidth;
            int srcHeight = opts.outHeight;
            int[] size = resizeToMaxSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
            desiredWidth = size[0];
            desiredHeight = size[1];
            opts.inPreferredConfig = Config.RGB_565;
            opts.inPurgeable = true;
            opts.inInputShareable = true;
            int sampleSize = findBestSampleSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
            opts.inSampleSize = sampleSize;
            opts.inJustDecodeBounds = false;
            opts.inDither = false;
            resizeBmp = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
            if (resizeBmp != null) {
                resizeBmp = getCutBitmap(resizeBmp, desiredWidth, desiredHeight);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            LogUtils.d(TAG, "" + var9.getMessage());
        }

        return resizeBmp;
    }

    public static Bitmap getScaleBitmap(File file, int desiredWidth, int desiredHeight) {
        Bitmap resizeBmp = null;
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), opts);
        int srcWidth = opts.outWidth;
        int srcHeight = opts.outHeight;
        int[] size = resizeToMaxSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
        desiredWidth = size[0];
        desiredHeight = size[1];
        opts.inPreferredConfig = Config.RGB_565;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        int sampleSize = findBestSampleSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
        opts.inSampleSize = sampleSize;
        opts.inJustDecodeBounds = false;
        opts.inDither = false;
        resizeBmp = BitmapFactory.decodeFile(file.getPath(), opts);
        float scale = getMinScale(resizeBmp.getWidth(), resizeBmp.getHeight(), desiredWidth, desiredHeight);
        if (scale < 1.0F) {
            resizeBmp = scaleBitmap(resizeBmp, scale);
        }

        if (resizeBmp.getWidth() > desiredWidth || resizeBmp.getHeight() > desiredHeight) {
            resizeBmp = getCutBitmap(resizeBmp, desiredWidth, desiredHeight);
        }

        return resizeBmp;
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, int desiredWidth, int desiredHeight) {
        if (!checkBitmap(bitmap)) {
            return null;
        } else {
            Bitmap resizeBmp = null;
            int srcWidth = bitmap.getWidth();
            int srcHeight = bitmap.getHeight();
            int[] size = resizeToMaxSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
            desiredWidth = size[0];
            desiredHeight = size[1];
            float scale = getMinScale(srcWidth, srcHeight, desiredWidth, desiredHeight);
            resizeBmp = scaleBitmap(bitmap, scale);
            if (resizeBmp.getWidth() > desiredWidth || resizeBmp.getHeight() > desiredHeight) {
                resizeBmp = getCutBitmap(resizeBmp, desiredWidth, desiredHeight);
            }

            return resizeBmp;
        }
    }

    public static Bitmap getCutBitmap(File file, int desiredWidth, int desiredHeight) {
        Bitmap resizeBmp = null;
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), opts);
        int srcWidth = opts.outWidth;
        int srcHeight = opts.outHeight;
        int[] size = resizeToMaxSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
        desiredWidth = size[0];
        desiredHeight = size[1];
        opts.inPreferredConfig = Config.RGB_565;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        int sampleSize = findBestSampleSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
        opts.inSampleSize = sampleSize;
        opts.inJustDecodeBounds = false;
        opts.inDither = false;
        resizeBmp = BitmapFactory.decodeFile(file.getPath(), opts);
        if (resizeBmp != null) {
            resizeBmp = getCutBitmap(resizeBmp, desiredWidth, desiredHeight);
        }

        return resizeBmp;
    }

    public static Bitmap getCutBitmap(Bitmap bitmap, int desiredWidth, int desiredHeight) {
        if (!checkBitmap(bitmap)) {
            return null;
        } else if (!checkSize(desiredWidth, desiredHeight)) {
            return null;
        } else {
            Bitmap resizeBmp = null;

            try {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int offsetX = 0;
                int offsetY = 0;
                if (width > desiredWidth) {
                    offsetX = (width - desiredWidth) / 2;
                } else {
                    desiredWidth = width;
                }

                if (height > desiredHeight) {
                    offsetY = (height - desiredHeight) / 2;
                } else {
                    desiredHeight = height;
                }

                resizeBmp = Bitmap.createBitmap(bitmap, offsetX, offsetY, desiredWidth, desiredHeight);
            } catch (Exception var11) {
                var11.printStackTrace();
            } finally {
                if (resizeBmp != bitmap) {
                    bitmap.recycle();
                }

            }

            return resizeBmp;
        }
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, float scale) {
        if (!checkBitmap(bitmap)) {
            return null;
        } else if (scale == 1.0F) {
            return bitmap;
        } else {
            Bitmap resizeBmp = null;

            try {
                int bmpW = bitmap.getWidth();
                int bmpH = bitmap.getHeight();
                Matrix matrix = new Matrix();
                matrix.postScale(scale, scale);
                resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bmpW, bmpH, matrix, true);
            } catch (Exception var9) {
                var9.printStackTrace();
            } finally {
                if (resizeBmp != bitmap) {
                    bitmap.recycle();
                }

            }

            return resizeBmp;
        }
    }

    public static float[] getBitmapSize(File file) {
        float[] size = new float[2];
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), opts);
        size[0] = (float)opts.outWidth;
        size[1] = (float)opts.outHeight;
        return size;
    }

    private static float getMinScale(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
        float scale = 0.0F;
        float scaleWidth = (float)desiredWidth / (float)srcWidth;
        float scaleHeight = (float)desiredHeight / (float)srcHeight;
        if (scaleWidth > scaleHeight) {
            scale = scaleWidth;
        } else {
            scale = scaleHeight;
        }

        return scale;
    }

    private static int[] resizeToMaxSize(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
        int[] size = new int[2];
        if (desiredWidth <= 0) {
            desiredWidth = srcWidth;
        }

        if (desiredHeight <= 0) {
            desiredHeight = srcHeight;
        }

        float scaleHeight;
        if (desiredWidth > 2048) {
            desiredWidth = 2048;
            scaleHeight = (float)desiredWidth / (float)srcWidth;
            desiredHeight = (int)((float)desiredHeight * scaleHeight);
        }

        if (desiredHeight > 2048) {
            desiredHeight = 2048;
            scaleHeight = (float)desiredHeight / (float)srcHeight;
            desiredWidth = (int)((float)desiredWidth * scaleHeight);
        }

        size[0] = desiredWidth;
        size[1] = desiredHeight;
        return size;
    }

    private static boolean checkBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            LogUtils.e(TAG, "原图Bitmap为空了");
            return false;
        } else if (bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
            return true;
        } else {
            LogUtils.e(TAG, "原图Bitmap大小为0");
            return false;
        }
    }

    private static boolean checkSize(int desiredWidth, int desiredHeight) {
        if (desiredWidth > 0 && desiredHeight > 0) {
            return true;
        } else {
            LogUtils.e(TAG, "请求Bitmap的宽高参数必须大于0");
            return false;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        BitmapDrawable mBitmapDrawable = null;

        try {
            if (bitmap == null) {
                return null;
            }

            mBitmapDrawable = new BitmapDrawable(bitmap);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return mBitmapDrawable;
    }

    public static TransitionDrawable bitmapToTransitionDrawable(Bitmap bitmap) {
        TransitionDrawable mBitmapDrawable = null;

        try {
            if (bitmap == null) {
                return null;
            }

            mBitmapDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(0), new BitmapDrawable(bitmap)});
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return mBitmapDrawable;
    }

    public static TransitionDrawable drawableToTransitionDrawable(Drawable drawable) {
        TransitionDrawable mBitmapDrawable = null;

        try {
            if (drawable == null) {
                return null;
            }

            mBitmapDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(0), drawable});
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return mBitmapDrawable;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat mCompressFormat, boolean needRecycle) {
        byte[] result = null;
        ByteArrayOutputStream output = null;

        try {
            output = new ByteArrayOutputStream();
            bitmap.compress(mCompressFormat, 100, output);
            result = output.toByteArray();
            if (needRecycle) {
                bitmap.recycle();
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (Exception var13) {
                    var13.printStackTrace();
                }
            }

        }

        return result;
    }

    public static int getByteCount(Bitmap bitmap, CompressFormat mCompressFormat) {
        int size = 0;
        ByteArrayOutputStream output = null;

        try {
            output = new ByteArrayOutputStream();
            bitmap.compress(mCompressFormat, 100, output);
            byte[] result = output.toByteArray();
            size = result.length;
            Object var15 = null;
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (Exception var12) {
                    var12.printStackTrace();
                }
            }

        }

        return size;
    }

    public static Bitmap bytes2Bimap(byte[] b) {
        Bitmap bitmap = null;

        try {
            if (b.length != 0) {
                bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap imageView2Bitmap(ImageView view) {
        Bitmap bitmap = null;

        try {
            bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return bitmap;
    }

    public static Drawable view2Drawable(View view) {
        BitmapDrawable mBitmapDrawable = null;

        try {
            Bitmap newbmp = view2Bitmap(view);
            if (newbmp != null) {
                mBitmapDrawable = new BitmapDrawable(newbmp);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return mBitmapDrawable;
    }

    public static Bitmap view2Bitmap(View view) {
        Bitmap bitmap = null;

        try {
            if (view != null) {
                view.setDrawingCacheEnabled(true);
                view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                view.buildDrawingCache();
                bitmap = view.getDrawingCache();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return bitmap;
    }

    public static byte[] view2Bytes(View view, CompressFormat compressFormat) {
        byte[] b = null;

        try {
            Bitmap bitmap = view2Bitmap(view);
            b = bitmap2Bytes(bitmap, compressFormat, true);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return b;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float degrees) {
        Bitmap mBitmap = null;

        try {
            Matrix m = new Matrix();
            m.setRotate(degrees % 360.0F);
            mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return mBitmap;
    }

    public static Bitmap rotateBitmapTranslate(Bitmap bitmap, float degrees) {
        Object mBitmap = null;

        try {
            Matrix matrix = new Matrix();
            int width;
            int height;
            if (degrees / 90.0F % 2.0F != 0.0F) {
                width = bitmap.getWidth();
                height = bitmap.getHeight();
            } else {
                width = bitmap.getHeight();
                height = bitmap.getWidth();
            }

            int cx = width / 2;
            int cy = height / 2;
            matrix.preTranslate((float)(-cx), (float)(-cy));
            matrix.postRotate(degrees);
            matrix.postTranslate((float)cx, (float)cy);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return (Bitmap)mBitmap;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float roundPx;
            float left;
            float top;
            float right;
            float bottom;
            float dst_left;
            float dst_top;
            float dst_right;
            float dst_bottom;
            if (width <= height) {
                roundPx = (float)(width / 2);
                top = 0.0F;
                bottom = (float)width;
                left = 0.0F;
                right = (float)width;
                height = width;
                dst_left = 0.0F;
                dst_top = 0.0F;
                dst_right = (float)width;
                dst_bottom = (float)width;
            } else {
                roundPx = (float)(height / 2);
                float clip = (float)((width - height) / 2);
                left = clip;
                right = (float)width - clip;
                top = 0.0F;
                bottom = (float)height;
                width = height;
                dst_left = 0.0F;
                dst_top = 0.0F;
                dst_right = (float)height;
                dst_bottom = (float)height;
            }

            Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            int color = -12434878;
            Paint paint = new Paint();
            Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
            Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
            RectF rectF = new RectF(dst);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
            return output;
        }
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap, int roundPx) {
        if (bitmap == null) {
            return null;
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float left;
            float top;
            float right;
            float bottom;
            float dst_left;
            float dst_top;
            float dst_right;
            float dst_bottom;
            if (width <= height) {
                top = 0.0F;
                bottom = (float)width;
                left = 0.0F;
                right = (float)width;
                height = width;
                dst_left = 0.0F;
                dst_top = 0.0F;
                dst_right = (float)width;
                dst_bottom = (float)width;
            } else {
                float clip = (float)((width - height) / 2);
                left = clip;
                right = (float)width - clip;
                top = 0.0F;
                bottom = (float)height;
                width = height;
                dst_left = 0.0F;
                dst_top = 0.0F;
                dst_right = (float)height;
                dst_bottom = (float)height;
            }

            Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            int color = -12434878;
            Paint paint = new Paint();
            Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
            Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
            RectF rectF = new RectF(dst);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, (float)roundPx, (float)roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
            return output;
        }
    }

    public static Bitmap toReflectionBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        } else {
            try {
                int reflectionGap = 1;
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                Matrix matrix = new Matrix();
                matrix.preScale(1.0F, -1.0F);
                Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);
                Bitmap bitmapWithReflection = Bitmap.createBitmap(width, height + height / 2, Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapWithReflection);
                canvas.drawBitmap(bitmap, 0.0F, 0.0F, (Paint)null);
                Paint deafaultPaint = new Paint();
                canvas.drawRect(0.0F, (float)height, (float)width, (float)(height + reflectionGap), deafaultPaint);
                canvas.drawBitmap(reflectionImage, 0.0F, (float)(height + reflectionGap), (Paint)null);
                Paint paint = new Paint();
                LinearGradient shader = new LinearGradient(0.0F, (float)bitmap.getHeight(), 0.0F, (float)(bitmapWithReflection.getHeight() + reflectionGap), 1895825407, 16777215, TileMode.CLAMP);
                paint.setShader(shader);
                paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
                canvas.drawRect(0.0F, (float)height, (float)width, (float)(bitmapWithReflection.getHeight() + reflectionGap), paint);
                bitmap = bitmapWithReflection;
            } catch (Exception var11) {
                var11.printStackTrace();
            }

            return bitmap;
        }
    }

    public static void releaseBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    LogUtils.d(TAG, "Bitmap释放" + bitmap.toString());
                    bitmap.recycle();
                }
            } catch (Exception var2) {
            }

            bitmap = null;
        }

    }

    public static void releaseBitmapArray(Bitmap[] bitmaps) {
        if (bitmaps != null) {
            try {
                Bitmap[] var1 = bitmaps;
                int var2 = bitmaps.length;

                for(int var3 = 0; var3 < var2; ++var3) {
                    Bitmap bitmap = var1[var3];
                    if (bitmap != null && !bitmap.isRecycled()) {
                        LogUtils.d(TAG, "Bitmap释放" + bitmap.toString());
                        bitmap.recycle();
                    }
                }
            } catch (Exception var5) {
            }
        }

    }

    public static String getHashCode(Bitmap bitmap) {
        Bitmap temp = Bitmap.createScaledBitmap(bitmap, 8, 8, false);
        int width = temp.getWidth();
        int height = temp.getHeight();
        Log.i("th", "将图片缩小到8x8的尺寸:" + width + "*" + height);
        int[] pixels = new int[width * height];

        int avgPixel;
        for(avgPixel = 0; avgPixel < width; ++avgPixel) {
            for(int j = 0; j < height; ++j) {
                pixels[avgPixel * height + j] = rgbToGray(temp.getPixel(avgPixel, j));
            }
        }

        releaseBitmap(temp);
        avgPixel = AbMathUtil.average(pixels);
        int[] comps = new int[width * height];

        for(int i = 0; i < comps.length; ++i) {
            if (pixels[i] >= avgPixel) {
                comps[i] = 1;
            } else {
                comps[i] = 0;
            }
        }

        StringBuffer hashCode = new StringBuffer();

        for(int i = 0; i < comps.length; i += 4) {
            int result = comps[i] * (int)Math.pow(2.0D, 3.0D) + comps[i + 1] * (int)Math.pow(2.0D, 2.0D) + comps[i + 2] * (int)Math.pow(2.0D, 1.0D) + comps[i + 2];
            hashCode.append(AbMathUtil.binaryToHex(result));
        }

        String sourceHashCode = hashCode.toString();
        return sourceHashCode;
    }

    public static String getDCTHashCode(Bitmap bitmap) {
        Bitmap temp = Bitmap.createScaledBitmap(bitmap, 32, 32, false);
        int width = temp.getWidth();
        int height = temp.getHeight();
        Log.i("th", "将图片缩小到32x32的尺寸:" + width + "*" + height);
        int[] pixels = new int[width * height];

        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                pixels[i * height + j] = rgbToGray(temp.getPixel(i, j));
            }
        }

        releaseBitmap(temp);
        int[][] pxMatrix = AbMathUtil.arrayToMatrix(pixels, width, height);
        double[][] doublePxMatrix = AbMathUtil.intToDoubleMatrix(pxMatrix);
        double[][] dtc = FDCT.fDctTransform(doublePxMatrix);
        double[] dctResult = AbMathUtil.matrixToArray(dtc);
        int avgPixel = AbMathUtil.average(dctResult);
        int[] comps = new int[64];

        for(int i = 0; i < comps.length; ++i) {
            if (dctResult[i] >= (double)avgPixel) {
                comps[i] = 1;
            } else {
                comps[i] = 0;
            }
        }

        StringBuffer hashCode = new StringBuffer();

        for(int i = 0; i < comps.length; i += 4) {
            int result = comps[i] * (int)Math.pow(2.0D, 3.0D) + comps[i + 1] * (int)Math.pow(2.0D, 2.0D) + comps[i + 2] * (int)Math.pow(2.0D, 1.0D) + comps[i + 2];
            hashCode.append(AbMathUtil.binaryToHex(result));
        }

        String sourceHashCode = hashCode.toString();
        return sourceHashCode;
    }

    public static int[] getColorHistogram(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] areaColor = new int[64];

        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                int pixels = bitmap.getPixel(i, j);
                int alpha = pixels >> 24 & 255;
                int red = pixels >> 16 & 255;
                int green = pixels >> 8 & 255;
                int blue = pixels & 255;
                int redArea = 0;
                int greenArea = 0;
                int blueArea = 0;
                if (red >= 192) {
                    redArea = 3;
                } else if (red >= 128) {
                    redArea = 2;
                } else if (red >= 64) {
                    redArea = 1;
                } else if (red >= 0) {
                    redArea = 0;
                }

                if (green >= 192) {
                    greenArea = 3;
                } else if (green >= 128) {
                    greenArea = 2;
                } else if (green >= 64) {
                    greenArea = 1;
                } else if (green >= 0) {
                    greenArea = 0;
                }

                if (blue >= 192) {
                    blueArea = 3;
                } else if (blue >= 128) {
                    blueArea = 2;
                } else if (blue >= 64) {
                    blueArea = 1;
                } else if (blue >= 0) {
                    blueArea = 0;
                }

                int index = redArea * 16 + greenArea * 4 + blueArea;
                int var10002 = areaColor[index]++;
            }
        }

        return areaColor;
    }

    public static int hammingDistance(String sourceHashCode, String hashCode) {
        int difference = 0;
        int len = sourceHashCode.length();

        for(int i = 0; i < len; ++i) {
            if (sourceHashCode.charAt(i) != hashCode.charAt(i)) {
                ++difference;
            }
        }

        return difference;
    }

    private static int rgbToGray(int pixels) {
        int _red = pixels >> 16 & 255;
        int _green = pixels >> 8 & 255;
        int _blue = pixels & 255;
        return (int)(0.3D * (double)_red + 0.59D * (double)_green + 0.11D * (double)_blue);
    }

    private static int findBestSampleSize(int width, int height, int desiredWidth, int desiredHeight) {
        double wr = (double)width / (double)desiredWidth;
        double hr = (double)height / (double)desiredHeight;
        double ratio = Math.min(wr, hr);

        float n;
        for(n = 1.0F; (double)(n * 2.0F) <= ratio; n *= 2.0F) {
        }

        return (int)n;
    }

    public Bitmap yuv2GrayBitmap(byte[] yuvData, int width, int height) {
        int[] pixels = new int[width * height];
        byte[] yuv = yuvData;
        int inputOffset = 0;

        for(int y = 0; y < height; ++y) {
            int outputOffset = y * width;

            for(int x = 0; x < width; ++x) {
                int grey = yuv[inputOffset + x] & 255;
                pixels[outputOffset + x] = -16777216 | grey * 65793;
            }

            inputOffset += width;
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static Bitmap yuv2Bitmap(byte[] data, int width, int height) {
        YuvImage image = new YuvImage(data, 17, width, height, (int[])null);
        ByteArrayOutputStream os = new ByteArrayOutputStream(data.length);
        if (!image.compressToJpeg(new Rect(0, 0, width, height), 100, os)) {
            return null;
        } else {
            byte[] tmp = os.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(tmp, 0, tmp.length);
            return bitmap;
        }
    }

    public static Bitmap cropYuv2Bitmap(byte[] data, int width, int height, Rect rect) {
        int w = rect.width();
        int h = rect.height();
        int frameSize = width * height;
        int[] pixels = new int[w * h];
        byte[] yuv = data;
        int yOffset = rect.top * width + rect.left;
        int uvOffset = rect.top / 2 * width + rect.left / 2 * 2 + frameSize;

        for(int i = 0; i < h; ++i) {
            int outputOffset = i * w;

            for(int j = 0; j < w; ++j) {
                int y = (255 & yuv[yOffset + j]) - 16;
                int k = j >> 1 << 1;
                int v = (255 & yuv[uvOffset + k]) - 128;
                int u = (255 & yuv[uvOffset + k + 1]) - 128;
                int y1192 = 1192 * y;
                int r = y1192 + 1634 * v;
                int g = y1192 - 833 * v - 400 * u;
                int b = y1192 + 2066 * u;
                if (r < 0) {
                    r = 0;
                } else if (r > 262143) {
                    r = 262143;
                }

                if (g < 0) {
                    g = 0;
                } else if (g > 262143) {
                    g = 262143;
                }

                if (b < 0) {
                    b = 0;
                } else if (b > 262143) {
                    b = 262143;
                }

                pixels[outputOffset + j] = -16777216 | r << 6 & 16711680 | g >> 2 & '\uff00' | b >> 10 & 255;
            }

            yOffset += width;
            if ((rect.top + i & 1) == 1) {
                uvOffset += width;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }

    public static String savetoJPEG(byte[] data, int width, int height, String file) {
        Rect frame = new Rect(0, 0, width, height);
        YuvImage img = new YuvImage(data, 17, width, height, (int[])null);
        OutputStream os = null;
        File jpgfile = new File(file);

        try {
            os = new FileOutputStream(jpgfile);
            img.compressToJpeg(frame, 100, os);
            os.flush();
            os.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return jpgfile.getPath();
    }

    public static String saveToLocal(Bitmap bitmap, Context context) {
        String mFileName = System.currentTimeMillis() + ".jpg";
        File FILE_LOCAL = new File(AbFileUtil.getImageDownloadDir(context));
        if (!FILE_LOCAL.exists()) {
            FILE_LOCAL.mkdirs();
        }

        String path = FILE_LOCAL + File.separator + mFileName;

        try {
            FileOutputStream fos = new FileOutputStream(path);
            bitmap.compress(CompressFormat.JPEG, 75, fos);
            fos.flush();
            fos.close();
            return path;
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
    }
}