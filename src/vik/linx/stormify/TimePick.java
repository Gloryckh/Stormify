package vik.linx.stormify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Vik on 10/17/2017.
 */

public class TimePick extends Activity implements SeekBar.OnSeekBarChangeListener,
Button.OnClickListener{

    private TextView timeText;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepick);

        seekBar = (SeekBar) findViewById(R.id.time_seekbar);
        timeText = (TextView) findViewById(R.id.time_textView);
        Button continueButton = (Button) findViewById(R.id.continue_btn);


        seekBar.setMax(120); //max two hours
        seekBar.setProgress(60); //default value
        timeText.setText( String.format("%d", seekBar.getProgress()) + " Minutes");

        continueButton.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("TIME_AMOUNT", seekBar.getProgress());
        startActivity(intent);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        timeText.setText(String.format("%d", progress) + " Minutes");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
}
