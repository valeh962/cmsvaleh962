package com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
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
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.valeh.coursemanagementsystem.R;
import com.yakivmospan.scytale.Crypto;
import com.yakivmospan.scytale.Options;
import com.yakivmospan.scytale.Store;

import javax.crypto.SecretKey;

import es.dmoral.toasty.Toasty;

public class PinChange extends AppCompatActivity {

    ImageView b10;
    EditText pin_edt,pin_edt2,pin_edt3,pin_edt4;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    String encpin;
    Boolean bbb=false,bbb1=false,ddd=false;
    Store store;
    SecretKey key;
    TextView cancelbtn;
    ViewSwitcher viewSwitcher;
    Crypto crypto;
    String pages;
    private String pinlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_change);

        SharedPreferences spreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean aBoolean = spreferences1.getBoolean("SCREEN_PROTECT", false);
        if(aBoolean){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);
        }
        if(!aBoolean){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewSwitcher = findViewById(R.id.viewswitchpin);
        store = new Store(getApplicationContext());
        if (!store.hasKey("coursems")) {
            SecretKey key = store.generateSymmetricKey("coursems", null);
        }
        key = store.getSymmetricKey("coursems", null);
        crypto = new Crypto(Options.TRANSFORMATION_SYMMETRIC);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        encpin = spreferences.getString("PIN", "");
        Log.i("Scytale", "Encrypted data: " + encpin);
        Log.i("Scytale", "Encrypted data length: " + encpin.length());
        cancelbtn = findViewById(R.id.textView37);

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
        pin_edt = findViewById(R.id.pin_edt2);
        pin_edt.setTransformationMethod(new MyPasswordTransformationMethod1());
        pin_edt2 = findViewById(R.id.pin_edt6);
        pin_edt3 = findViewById(R.id.pin_edt3);
        pin_edt3.setTransformationMethod(new MyPasswordTransformationMethod1());
        pin_edt4 = findViewById(R.id.pin_edt5);
        SharedPreferences pinloginfunc = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        pages = pinloginfunc.getString("PINCHANGE","4");
        pinlogin = pinloginfunc.getString("PINLOGIN","");
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        passInput(1);
        passShow();

    }

    private void passInput(int edt_change) {
        if(edt_change==1) {
            b0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("0");
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("1");
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("2");
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("3");
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("4");
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("6");
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("5");
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("7");
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt.append("8");
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
        if(edt_change==2){
            b0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("0");
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("1");
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("2");
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("3");
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("4");
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("6");
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("5");
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("7");
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("8");
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pin_edt3.append("9");
                }
            });
            b10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int length = pin_edt3.getText().length();
                    if (length > 0) {
                        pin_edt3.getText().delete(length - 1, length);
                    }
                }
            });
        }
    }
    private void passShow() {

        pin_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(pin_edt.getText().length()==4){
                    viewSwitcher.showNext();
                    pin_edt3.requestFocus();
                    passInput(2);
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

        pin_edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.length()==0){
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewSwitcher.showPrevious();
                            pin_edt.requestFocus();
                            passInput(1);
                        }
                    });
                }
                if(pin_edt.getText().toString().equals(pin_edt3.getText().toString()) && pin_edt3.getText().length()==4){
                    String encryptedData = crypto.encrypt(pin_edt3.getText().toString(), key);
                    Log.i("Scytale", "Encrypted data: " + encryptedData);
                    if(pages.equals("1")) {
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINTEST", pin_edt.getText().toString()).apply();
                        if(pinlogin.equals("6"))
                        {
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINLOGIN", "6").apply();
                        }
                        else {
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINLOGIN", "1").apply();
                        }
                        startActivity(new Intent(getApplicationContext(), PinLogin.class));
                        finish();
                    }
                }
                else{
                    if (pin_edt3.getText().length() == 4 || pin_edt.getText().length()==4) {
                        if (!pin_edt.getText().toString().equals(pin_edt3.getText().toString())) {
                            pin_edt.setTextColor(Color.RED);
                            pin_edt3.setTextColor(Color.RED);
                        }
                    }
                    if (pin_edt3.getText().length() != 4 || pin_edt.getText().length()!=4) {
                        pin_edt.setTextColor(Color.WHITE);
                        pin_edt3.setTextColor(Color.WHITE);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

                Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                Animation fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                if(pin_edt3.getText().length()==3 && bbb1==true){
                    pin_edt4.startAnimation(fadein);
                    pin_edt4.setVisibility(View.VISIBLE);
                    bbb1 = false;
                }
                if(pin_edt3.getText().length()==4){
                    pin_edt4.startAnimation(fadeout);
                    pin_edt4.setVisibility(View.GONE);
                    bbb1=true;

                }
            }
        });
    }
    public class MyPasswordTransformationMethod1 extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PinLogin.MyPasswordTransformationMethod.PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
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
    }

    public void hideSoftKeyboard(EditText editText) {
        if (editText == null)
            return;
        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
