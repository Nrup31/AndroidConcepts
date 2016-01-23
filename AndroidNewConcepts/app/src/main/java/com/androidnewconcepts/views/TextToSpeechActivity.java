package com.androidnewconcepts.views;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnewconcepts.R;

import java.util.Locale;

/**
 * Created by indianic on 05/01/16.
 */
public class TextToSpeechActivity extends Activity {
    private TextToSpeech t1;
    private EditText ed1;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        ed1 = (EditText) findViewById(R.id.editText);
        b1 = (Button) findViewById(R.id.button);

        final Locale locale = new Locale("ar_EG");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        Locale[] locales = Locale.getAvailableLocales();

        for (int i = 0; i < locales.length; i++) {
            Log.e("Available Locals ", "" + locales[i]);
        }
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
//                    t1.setLanguage(Locale.UK); // Male
                    t1.setLanguage(locale); // Male
                    Log.e("LOCALS ", "" + Locale.getAvailableLocales());
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String toSpeak = ed1.getText().toString();
//                String toSpeak = "كيف حالك";
//                String toSpeak = "Hello How are you?";
                String toSpeak="مرحبا كيف الحال";
//                Toast.makeText(getApplicationContext(), arabicText, Toast.LENGTH_SHORT).show();

                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    public void onPause() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }


}