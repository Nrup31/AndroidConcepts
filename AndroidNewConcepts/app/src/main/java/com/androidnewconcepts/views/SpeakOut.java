package com.androidnewconcepts.views;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.androidnewconcepts.R;

import org.w3c.dom.Text;

import java.util.Locale;

/**
 * Created by indianic on 18/01/16.
 */
public class SpeakOut extends Activity implements TextToSpeech.OnInitListener {
    private int _langTTSavailable = -1; // set up in onInit method
    // declaration
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        mTts=new TextToSpeech(this,(TextToSpeech.OnInitListener)this);
    }

    @Override
    protected void onDestroy() {
        // TTS shutdown!
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            _langTTSavailable = mTts.setLanguage(Locale.FRANCE); // Locale.FRANCE etc.
            if (_langTTSavailable == TextToSpeech.LANG_MISSING_DATA ||
                    _langTTSavailable == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("1","LANG_MISSING_DATA or LANG_NOT_SUPPORTED");
            } else if ( _langTTSavailable >= 0) {
                Log.e("2","2");
                mTts.speak("Bon matin",
                        TextToSpeech.QUEUE_FLUSH,  // Drop all pending entries in the playback queue.
                        null);
            }
        } else {
            // Initialization failed.
            Log.e("3","Initialization failed");
        }
    }
}
