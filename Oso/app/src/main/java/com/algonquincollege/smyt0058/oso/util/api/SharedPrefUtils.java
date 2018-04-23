package com.algonquincollege.smyt0058.oso.util.api;

/**
 * Created by AlexG on 2018-03-30.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.security.KeyPairGeneratorSpec;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

public class SharedPrefUtils {

    private static final String TAG = SharedPrefUtils.class.getSimpleName();
    private static final String KEYSTORE = "AndroidKeyStore";
    private static final String ALIAS = "MY_APP";
    private static final String TYPE_RSA = "RSA";
    private static final String CYPHER = "RSA/ECB/PKCS1Padding";
    private static final String ENCODING = "UTF-8";

    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    public static final String ISLOGGED = "ISLOGGED";
    public static final String ISFIRSTLOGGED = "IS_FIRST_LOGGED";

    public static final String PAW_POINTS = "PAW_POINTS";
    public static final String SHIRT_WEAR_BOOL = "SHIRT_WEAR_BOOL";
    public static final String HAT_WEAR_BOOL = "HAT_WEAR_BOOL";
    public static final String HEADBOW_WEAR_BOOL = "HEADBOW_WEAR_BOOL";
    public static final String MONOCLE_WEAR_BOOL = "MONOCLE_WEAR_BOOL";
    public static final String BOWTIE_WEAR_BOOL = "BOWTIE_WEAR_BOOL";
    public static final String PINK_BOWTIE_WEAR_BOOL = "PINK_BOWTIE_WEAR_BOOL";
    public static final String WATCH_WEAR_BOOL = "WATCH_WEAR_BOOL";
    public static final String MP3_WEAR_BOOL = "MP3_WEAR_BOOL";

    public static final String MP3_BUY_BOOL = "MP3_BUY_BOOL";
    public static final String HEADBOW_BUY_BOOL = "HEADBOW_BUY_BOOL";
    public static final String PINK_BOWTIE_BUY_BOOL = "PINK_BOWTIE_BUY_BOOL";

    public static final String NOTIFICATION_BOOL = "NOTIFICATION_BOOL";
    public static final String THEME = "THEME";



    public static void put(Context ctx, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        if (value == null) {
            prefs.edit().putString(key, null).apply();
        } else {
            try {
                prefs.edit().putString(key, encryptString(ctx, value)).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    public static void putThemeState(Context ctx, String theme){
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putString(THEME, theme).apply();
    }

    public static void putSettingsState(Context ctx, boolean isNotificationOn, String theme){
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putBoolean(NOTIFICATION_BOOL, isNotificationOn).putString(THEME, theme).apply();
    }

    public static void putMarketState(Context ctx, int pawPoints, boolean mp3BuyBool, boolean headbowBuyBool, boolean pinkBowtieBuyBool){
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putInt(PAW_POINTS, pawPoints)
                    .putBoolean(MP3_BUY_BOOL, mp3BuyBool)
                    .putBoolean(HEADBOW_BUY_BOOL, headbowBuyBool)
                    .putBoolean(PINK_BOWTIE_BUY_BOOL, pinkBowtieBuyBool).apply();
    }

    public static void putPawPointState(Context ctx, int pawPoints) {
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putInt(PAW_POINTS, pawPoints).apply();
    }



    public static void putClosetState(Context ctx,
                                      boolean shirtWearBool,
                                      boolean hatWearBool,
                                      boolean headbowWearBool,
                                      boolean monocleWearBool,
                                      boolean bowtieWearBool,
                                      boolean pinkBowtieWearBool,
                                      boolean watchWearBool,
                                      boolean mp3WearBool){
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putBoolean(SHIRT_WEAR_BOOL, shirtWearBool)
                .putBoolean(HAT_WEAR_BOOL, hatWearBool)
                .putBoolean(HEADBOW_WEAR_BOOL, headbowWearBool)
                .putBoolean(MONOCLE_WEAR_BOOL, monocleWearBool)
                .putBoolean(BOWTIE_WEAR_BOOL, bowtieWearBool)
                .putBoolean(PINK_BOWTIE_WEAR_BOOL, pinkBowtieWearBool)
                .putBoolean(WATCH_WEAR_BOOL, watchWearBool)
                .putBoolean(MP3_WEAR_BOOL, mp3WearBool).apply();
    }

    public static void putInitialState(Context ctx,
                                      int pawPoints,
                                      boolean shirtWearBool,
                                      boolean hatWearBool,
                                      boolean headbowWearBool,
                                      boolean monocleWearBool,
                                      boolean bowtieWearBool,
                                      boolean pinkBowtieWearBool,
                                      boolean watchWearBool,
                                      boolean mp3WearBool,
                                      boolean mp3BuyBool,
                                      boolean headbowBuyBool,
                                      boolean pinkBowtieBuyBool,
                                       boolean isNotificationOn,
                                       String theme ){
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putInt(PAW_POINTS, pawPoints)
                .putBoolean(SHIRT_WEAR_BOOL, shirtWearBool)
                .putBoolean(HAT_WEAR_BOOL, hatWearBool)
                .putBoolean(HEADBOW_WEAR_BOOL, headbowWearBool)
                .putBoolean(MONOCLE_WEAR_BOOL, monocleWearBool)
                .putBoolean(BOWTIE_WEAR_BOOL, bowtieWearBool)
                .putBoolean(PINK_BOWTIE_WEAR_BOOL, pinkBowtieWearBool)
                .putBoolean(WATCH_WEAR_BOOL, watchWearBool)
                .putBoolean(MP3_WEAR_BOOL, mp3WearBool)
                .putBoolean(MP3_BUY_BOOL, mp3BuyBool)
                .putBoolean(HEADBOW_BUY_BOOL, headbowBuyBool)
                .putBoolean(PINK_BOWTIE_BUY_BOOL, pinkBowtieBuyBool)
                .putBoolean(NOTIFICATION_BOOL, isNotificationOn)
                .putString(THEME, theme).apply();
    }

    public static void putHeadbowState(Context ctx, boolean isHeadbowPurchased) {
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putBoolean(HEADBOW_BUY_BOOL, isHeadbowPurchased).apply();
    }

    public static void putPinkBowtieState(Context ctx, boolean isPinkBowtiePurchased) {
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putBoolean(PINK_BOWTIE_BUY_BOOL, isPinkBowtiePurchased).apply();
    }

    public static void putMp3State(Context ctx, boolean isMp3Purchased) {
        SharedPreferences prefs = ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
        prefs.edit().putBoolean(MP3_BUY_BOOL, isMp3Purchased).apply();
    }

    public static SharedPreferences getAppState(Context ctx){
        return ctx.getSharedPreferences(ctx.getString(R.string.preference_file_key), ctx.MODE_PRIVATE);
    }

    public static String get(Context ctx, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        final String pref = prefs.getString(key, "");

        if (!TextUtils.isEmpty(pref)) {
            return decryptString(ctx, pref);
        }

        return null;
    }

    public static void saveIsLoggedIn(Context ctx, String key, boolean value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
            try {
                prefs.edit().putBoolean(key, value).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static Boolean getIsLoggedIn(Context ctx){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return prefs.getBoolean(ISLOGGED, false);
    }

    public static void saveIsFirstLoggedIn(Context ctx, String key, boolean value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        try {
            prefs.edit().putBoolean(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean getIsFirstLoggedIn(Context ctx){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return prefs.getBoolean(ISFIRSTLOGGED, false);
    }




    private static String encryptString(Context context, String toEncrypt) {
        try {
            final KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKey(context);
            if (privateKeyEntry != null) {
                final PublicKey publicKey = privateKeyEntry.getCertificate().getPublicKey();

                // Encrypt the text
                Cipher input = Cipher.getInstance(CYPHER);
                input.init(Cipher.ENCRYPT_MODE, publicKey);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                CipherOutputStream cipherOutputStream = new CipherOutputStream(
                        outputStream, input);
                cipherOutputStream.write(toEncrypt.getBytes(ENCODING));
                cipherOutputStream.close();

                byte[] vals = outputStream.toByteArray();
                return Base64.encodeToString(vals, Base64.DEFAULT);
            }
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
        return null;
    }

    private static String decryptString(Context context, String encrypted) {
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKey(context);
            if (privateKeyEntry != null) {
                final PrivateKey privateKey = privateKeyEntry.getPrivateKey();

                Cipher output = Cipher.getInstance(CYPHER);
                output.init(Cipher.DECRYPT_MODE, privateKey);

                CipherInputStream cipherInputStream = new CipherInputStream(
                        new ByteArrayInputStream(Base64.decode(encrypted, Base64.DEFAULT)), output);
                ArrayList<Byte> values = new ArrayList<>();
                int nextByte;
                while ((nextByte = cipherInputStream.read()) != -1) {
                    values.add((byte) nextByte);
                }

                byte[] bytes = new byte[values.size()];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = values.get(i);
                }

                return new String(bytes, 0, bytes.length, ENCODING);
            }

        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }

        return null;
    }

    private static KeyStore.PrivateKeyEntry getPrivateKey(Context context) throws KeyStoreException,
            CertificateException, NoSuchAlgorithmException,
            IOException, UnrecoverableEntryException {

        KeyStore ks = KeyStore.getInstance(KEYSTORE);

        // Weird artifact of Java API.  If you don't have an InputStream to load, you still need
        // to call "load", or it'll crash.
        ks.load(null);

        // Load the key pair from the Android Key Store
        KeyStore.Entry entry = ks.getEntry(ALIAS, null);

        /* If the entry is null, keys were never stored under this alias.
         */
        if (entry == null) {
            Log.w(TAG, "No key found under alias: " + ALIAS);
            Log.w(TAG, "Generating new key...");
            try {
                createKeys(context);

                // reload keystore
                ks = KeyStore.getInstance(KEYSTORE);
                ks.load(null);

                // reload key pair
                entry = ks.getEntry(ALIAS, null);

                if (entry == null) {
                    Log.w(TAG, "Generating new key failed...");
                    return null;
                }
            } catch (NoSuchProviderException e) {
                Log.w(TAG, "Generating new key failed...");
                e.printStackTrace();
                return null;
            } catch (InvalidAlgorithmParameterException e) {
                Log.w(TAG, "Generating new key failed...");
                e.printStackTrace();
                return null;
            }
        }

        /* If entry is not a KeyStore.PrivateKeyEntry, it might have gotten stored in a previous
         * iteration of your application that was using some other mechanism, or been overwritten
         * by something else using the same keystore with the same alias.
         * You can determine the type using entry.getClass() and debug from there.
         */
        if (!(entry instanceof KeyStore.PrivateKeyEntry)) {
            Log.w(TAG, "Not an instance of a PrivateKeyEntry");
            Log.w(TAG, "Exiting signData()...");
            return null;
        }

        return (KeyStore.PrivateKeyEntry) entry;
    }

    /**
     * Creates a public and private key and stores it using the Android Key Store, so that only
     * this application will be able to access the keys.
     */
    private static void createKeys(Context context) throws NoSuchProviderException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        // Create a start and end time, for the validity range of the key pair that's about to be
        // generated.
        Calendar start = new GregorianCalendar();
        Calendar end = new GregorianCalendar();
        end.add(Calendar.YEAR, 25);

        // The KeyPairGeneratorSpec object is how parameters for your key pair are passed
        // to the KeyPairGenerator.  For a fun home game, count how many classes in this sample
        // start with the phrase "KeyPair".
        KeyPairGeneratorSpec spec =
                new KeyPairGeneratorSpec.Builder(context)
                        // You'll use the alias later to retrieve the key.  It's a key for the key!
                        .setAlias(ALIAS)
                        // The subject used for the self-signed certificate of the generated pair
                        .setSubject(new X500Principal("CN=" + ALIAS))
                        // The serial number used for the self-signed certificate of the
                        // generated pair.
                        .setSerialNumber(BigInteger.valueOf(1337))
                        // Date range of validity for the generated pair.
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build();

        // Initialize a KeyPair generator using the the intended algorithm (in this example, RSA
        // and the KeyStore.  This example uses the AndroidKeyStore.
        final KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance(TYPE_RSA, KEYSTORE);
        kpGenerator.initialize(spec);

        final KeyPair kp = kpGenerator.generateKeyPair();
        Log.d(TAG, "Public Key is: " + kp.getPublic().toString());
    }

}
