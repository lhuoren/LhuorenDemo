package com.syy.modulebase.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class AbStreamUtil {
    public AbStreamUtil() {
    }

    public static InputStream bytes2Stream(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    public static byte[] stream2bytes(InputStream inStream) throws IOException {
        byte[] buff = new byte[1024];
        byte[] data = null;

        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            boolean var4 = false;

            int read;
            while((read = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, read);
            }

            data = swapStream.toByteArray();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return data;
    }

    public static byte[] stream2Bytes(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];

        int count;
        int pos;
        for(pos = 0; pos < length && (count = in.read(bytes, pos, length - pos)) != -1; pos += count) {
        }

        if (pos != length) {
            throw new IOException("Expected " + length + " bytes, read " + pos + " bytes");
        } else {
            return bytes;
        }
    }

    public static int read(InputStream is) throws IOException {
        int b = is.read();
        if (b == -1) {
            throw new EOFException();
        } else {
            return b;
        }
    }

    public static void writeInt(OutputStream os, int n) throws IOException {
        os.write(n >> 0 & 255);
        os.write(n >> 8 & 255);
        os.write(n >> 16 & 255);
        os.write(n >> 24 & 255);
    }

    public static int readInt(InputStream is) throws IOException {
        int n = 0;
        n = n | read(is) << 0;
        n |= read(is) << 8;
        n |= read(is) << 16;
        n |= read(is) << 24;
        return n;
    }

    public static void writeLong(OutputStream os, long n) throws IOException {
        os.write((byte)((int)(n >>> 0)));
        os.write((byte)((int)(n >>> 8)));
        os.write((byte)((int)(n >>> 16)));
        os.write((byte)((int)(n >>> 24)));
        os.write((byte)((int)(n >>> 32)));
        os.write((byte)((int)(n >>> 40)));
        os.write((byte)((int)(n >>> 48)));
        os.write((byte)((int)(n >>> 56)));
    }

    public static long readLong(InputStream is) throws IOException {
        long n = 0L;
        n |= ((long)read(is) & 255L) << 0;
        n |= ((long)read(is) & 255L) << 8;
        n |= ((long)read(is) & 255L) << 16;
        n |= ((long)read(is) & 255L) << 24;
        n |= ((long)read(is) & 255L) << 32;
        n |= ((long)read(is) & 255L) << 40;
        n |= ((long)read(is) & 255L) << 48;
        n |= ((long)read(is) & 255L) << 56;
        return n;
    }

    public static void writeString(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes("UTF-8");
        writeLong(os, (long)b.length);
        os.write(b, 0, b.length);
    }

    public static String readString(InputStream is) throws IOException {
        int n = (int)readLong(is);
        byte[] b = stream2Bytes(is, n);
        return new String(b, "UTF-8");
    }

    public static void writeStringStringMap(Map<String, String> map, OutputStream os) throws IOException {
        if (map != null) {
            writeInt(os, map.size());
            Iterator var2 = map.entrySet().iterator();

            while(var2.hasNext()) {
                Entry<String, String> entry = (Entry)var2.next();
                writeString(os, (String)entry.getKey());
                writeString(os, (String)entry.getValue());
            }
        } else {
            writeInt(os, 0);
        }

    }

    public static Map<String, String> readStringStringMap(InputStream is) throws IOException {
        int size = readInt(is);
        Map<String, String> result = size == 0 ? Collections.emptyMap() : new HashMap(size);

        for(int i = 0; i < size; ++i) {
            String key = readString(is).intern();
            String value = readString(is).intern();
            ((Map)result).put(key, value);
        }

        return (Map)result;
    }
}
