package br.com.cortes.will.request;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.TimeZone;

public class Authentication {
    public static final String PRIVATE_KEY = "4a17c7d00fd9c5bb9a30dae10c56cc539b3e634d";
    public static final String PUBLIC_KEY = "4229ec74923668a9fb03e57a002c03e8";
    private long mTs;
    private String mHash;
    private static Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    public Authentication() {
        create();
    }

    public void create() {
        this.mTs = mCalendar.getTimeInMillis() / 1000L;
        this.mHash = md5(String.valueOf(this.mTs) + PRIVATE_KEY + PUBLIC_KEY);
    }

    public long getmTs() {
        return mTs;
    }

    public String getmHash() {
        return mHash;
    }

    private String md5(final String s) {
        final String MD5 = "MD5";
        try {

            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2)
                    h.insert(0, "0");
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            Log.e("Exception: ", e.getMessage());
        }
        return "";
    }
}
