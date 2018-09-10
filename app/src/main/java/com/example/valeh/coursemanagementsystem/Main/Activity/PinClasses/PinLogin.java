package com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.Activity.LoginRegister;
import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.R;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;
import com.yakivmospan.scytale.Crypto;
import com.yakivmospan.scytale.Options;
import com.yakivmospan.scytale.Store;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.x500.X500Principal;

public class PinLogin extends AppCompatActivity{

    ImageView b10;
    ProgressDialog progressDialog;
    EditText pin_edt,pin_edt2;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    String encpin;
    Boolean bbb=false;
    String pages;
    String pintest;
    TextView cancelbtn;
    String decryptedDataTest;
    private static String SALT="!@#$%^&*";
    private String tokenn;
    private String pinkey;
    String alias;
    String pubkeyg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_fingerprint_login);
        SharedPreferences spreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean aBoolean = spreferences1.getBoolean("SCREEN_PROTECT", false);
     //   mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
        if(aBoolean){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);
        }
        if(!aBoolean){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Store storemain = new Store(getApplicationContext());

        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        encpin = spreferences.getString("PIN", "");
        pubkeyg = spreferences.getString("PBKY","");
        pintest = spreferences.getString("PINTEST", "");
        tokenn = spreferences.getString("TOKEN", "");
        decryptedDataTest = pintest;

        SharedPreferences pinloginfunc = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        pages = pinloginfunc.getString("PINLOGIN","4");
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button3);
        b3 = findViewById(R.id.button4);
        b4 = findViewById(R.id.button5);
        b5 = findViewById(R.id.button6);
        b6 = findViewById(R.id.button7);
        b7 = findViewById(R.id.button8);
        b8 = findViewById(R.id.button9);
        b9 = findViewById(R.id.button10);
        b10 = findViewById(R.id.button11);
        //fing_btn = findViewById(R.id.fing_print_img);
        pin_edt = findViewById(R.id.pin_edt);
        pin_edt.setTransformationMethod(new MyPasswordTransformationMethod());
        pin_edt2 = findViewById(R.id.pin_edt4);
        cancelbtn = findViewById(R.id.textView37);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
        passInput();
        alias = tokenn;
        passShow(storemain);

    }

    private void passInput() {

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)
                pin_edt.append("0");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)
                pin_edt.append("1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)pin_edt.append("2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)pin_edt.append("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)pin_edt.append("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)pin_edt.append("6");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)pin_edt.append("5");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)pin_edt.append("7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)pin_edt.append("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin_edt.getText().length()<=3)
                pin_edt.append("9");
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = pin_edt.getText().length();
                if (length > 0) {
                    pin_edt.getText().delete(length - 1, length);
                }
            }
        });

    }

    private void passShow(Store store) {

        pin_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if(pin_edt.getText().toString().length()==4) {
                if (pages.equals("3")) {
                    String pass = checkPin();
                    if (pass.equals(charSequence.toString())) {
                        progressDialog.cancel();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PIN", "").apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("FINGERENABLED", "").apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("FINGENTER", "").apply();
                        startActivity(new Intent(PinLogin.this, MainMenu.class));
                    }
                }
                if (pages.equals("4")) {
                   // checkPin();
                        String pass = checkPin();

                        if (pass.equals(charSequence.toString())) {
                            progressDialog.cancel();
                            startActivity(new Intent(PinLogin.this, MainMenu.class));
                        }
                }
                if (pages.equals("5")) {

                    String pass = checkPin();

                    if (pass.equals(charSequence.toString())) {
                        progressDialog.cancel();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("FINGERENABLED", "1").apply();
                        startActivity(new Intent(PinLogin.this, MainMenu.class));
                    }
                }
                if (pages.equals("6")) {


                    String pass = checkPin();

                    if (charSequence.toString().equals(decryptedDataTest)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            savePassword();
                        }
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PIN", "enabled").apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("FINGERENABLED", "1").apply();
                        startActivity(new Intent(PinLogin.this, MainMenu.class));
                    }
                }
                if (pages.equals("7")) {
//
                    String pass = checkPin();

                    if (pass.equals(charSequence.toString())) {
                        progressDialog.cancel();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("FINGERENABLED", "").apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("FINGENTER", "").apply();
                        startActivity(new Intent(PinLogin.this, MainMenu.class));
                    }

                }
                if (pages.equals("1")) {
                     if (charSequence.toString().equals(decryptedDataTest)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            savePassword();
                        }
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PIN", "enabled").apply();
                        startActivity(new Intent(PinLogin.this, MainMenu.class));
                    }
                }
                if (pages.equals("2")) {

                    String pass = checkPin();

                    if (pass.equals(charSequence.toString())) {
                        progressDialog.cancel();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINCHANGE", "1").apply();
                        startActivity(new Intent(PinLogin.this, PinChange.class));
                    }
                }

            }

                    if (!(pin_edt.getText().toString()).equals(decryptedDataTest) && pin_edt.getText().length() == 4) {
                        progressDialog.cancel();

                        pin_edt.setTextColor(Color.RED);
                        pin_edt.setTextColor(Color.RED);
                    }
                    if (pin_edt.getText().length() != 4) {
                        pin_edt.setTextColor(Color.WHITE);
                    }


            }
            @Override
            public void afterTextChanged(Editable editable) {
                Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                Animation fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                if(pin_edt.getText().length()==3 && bbb==true){
                    pin_edt2.startAnimation(fadein);
                    pin_edt2.setVisibility(View.VISIBLE);
                    bbb = false;
                }
                if(pin_edt.getText().length()==4){
                    pin_edt2.startAnimation(fadeout);
                    pin_edt2.setVisibility(View.GONE);
                    bbb=true;

                }
            }
        });



    }

    public static class MyPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        static class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;
            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }
            public char charAt(int index) {
                return '*'; // This is the important part
            }
            public int length() {
                return mSource.length(); // Return default
            }
            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    };

    public void hideSoftKeyboard(EditText editText) {
        if (editText == null)
            return;
        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void savePassword(){
        try{
            String passwordString = pin_edt.getText().toString();
            SecretKey secretKey = createKey();
            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+"/"+KeyProperties.BLOCK_MODE_CBC+"/"
                    +KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);

            byte[] encryptionIV = cipher.getIV();
            byte[] passwordBytes = passwordString.getBytes("UTF-8");
            byte[] encryptedPasswordBytes = cipher.doFinal(passwordBytes);
            String encryptedPassword = Base64.encodeToString(encryptedPasswordBytes,Base64.DEFAULT);

            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("epepepii", encryptedPassword).apply();
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("epepepii2", Base64.encodeToString(encryptionIV,Base64.DEFAULT)).apply();

        }catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }


    public SecretKey createKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                keyGenerator.init(new KeyGenParameterSpec.Builder("KEY",
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .build());
                return keyGenerator.generateKey();
            }
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
           e.printStackTrace();
           return null;
        }
    }

    public String checkPin(){
        progressDialog = ProgressDialog.show(PinLogin.this, "Please wait.", "Checking pin...", true);

        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String base64EncryptedPassword =  spreferences.getString("epepepii", "");
        String base64EncryptedIV = spreferences.getString("epepepii2", "");
        try {
        String password = null;
        byte[] encryptedIV = Base64.decode(base64EncryptedIV,Base64.DEFAULT);
        byte[] encryptedPassword = Base64.decode(base64EncryptedPassword,Base64.DEFAULT);


            KeyStore keyStore = KeyStore.getInstance("AndroidKeystore");
            keyStore.load(null);
            SecretKey secretKey = (SecretKey) keyStore.getKey("KEY",null);
            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC+
                    "/"+KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.DECRYPT_MODE,secretKey, new IvParameterSpec(encryptedIV));
            byte[] passwordBytes = cipher.doFinal(encryptedPassword);

            password = new String(passwordBytes,"UTF-8");
            return password;
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return null;
        } catch (CertificateException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();return null;
        } catch (IOException e) {
            e.printStackTrace();return null;
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();return null;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();return null;
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();return null;
        } catch (BadPaddingException e) {
            e.printStackTrace();return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();return null;
        } catch (InvalidKeyException e) {
            e.printStackTrace();return null;
        }


    }
}
