package vik.linx.stormify;


import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.devadvance.circularseekbar.CircularSeekBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity implements OnClickListener {

	MediaPlayer player;
	CircularSeekBar seekbar;
	
	// Initializing a sliding menu
	SlidingMenu menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setting the layout
		setContentView(R.layout.activity_main);
		
		// configuring the SlidingMenu
		menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.leftmenu);
        
        buttonOnClicks(menu);
       
		//creating a random number generator
		//so the system will randomly pick a song to play
		Random randomNumber = new Random();
		
		// creating an integer between 0 and 3
		int number = randomNumber.nextInt(4);
		
		//using the random integer between 0 and 3 for system to choose a track to play
		switch (number) {
		case 1: // if number = 0
			player = MediaPlayer.create(getApplicationContext(), R.raw.summer);
			player.start();
			player.setLooping(true);
			break;
		case 2: // if number = 1
			player = MediaPlayer.create(getApplicationContext(), R.raw.singapore);
			player.start();
			player.setLooping(true);
			break;
		case 3: //if number = 2
			player = MediaPlayer.create(getApplicationContext(), R.raw.birdsong);
			player.start();
			player.setLooping(true);
			break;
		case 4: // if number = 3
			player = MediaPlayer.create(getApplicationContext(), R.raw.simplerain);
			player.start();
			player.setLooping(true);
			break;
		case 5: //if number = 4
			player = MediaPlayer.create(getApplicationContext(), R.raw.rainymood);
			player.start();
			player.setLooping(true);
		default: player = MediaPlayer.create(getApplicationContext(), R.raw.summer);
			break;
		}
		
		//initiating listeners for one tap and double tap
		tapListeners();
	}
	

	/**
	 * Code for listeners 
	 * for one tap
	 * and for double tap
	 */
	
public void tapListeners(){

	findViewById(R.id.rellay).setOnTouchListener(new OnTouchListener() {
	    private GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
	        @Override
	        public boolean onDoubleTap(MotionEvent e) {
	        	//on double tap
	        	Intent myIntent = new Intent(MainActivity.this, AboutApp.class);
	        	MainActivity.this.startActivity(myIntent);
	            Log.d("TEST", "onDoubleTap");
	            return super.onDoubleTap(e);
	        }
	         // implement here other callback methods like onFling, onScroll as necessary
	    });

	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
	    	//on one click
	        gestureDetector.onTouchEvent(event);
	        return true;
	    }
	});
}
	
    /**
     * Setting on click methods for opening and closing the menu
     * @param leftmenu
     */
   
    public void buttonOnClicks(final SlidingMenu leftmenu){
    	Button closeMenu = (Button)findViewById(R.id.closemenubtn);
    	Button openMenu = (Button)findViewById(R.id.openmenubtn);
    	Button summerstormbtn = (Button)findViewById(R.id.sumerstormbtn);
    	Button singapore = (Button)findViewById(R.id.singaporebtn);
    	Button birdsong = (Button)findViewById(R.id.birdsongbtn);
    	Button rain = (Button)findViewById(R.id.rainstormbtn);
        
    	//if open, it will close
		closeMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				leftmenu.toggle(true);
			}
		});
		
		//if closed, it will open
		openMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				leftmenu.toggle(true);
			}
		});
		
		summerstormbtn.setOnClickListener(this); 
		singapore.setOnClickListener(this);
		birdsong.setOnClickListener(this);
		rain.setOnClickListener(this);
    }
    

	//If back button is pressed, then stop the current track.
	 

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(menu.isEnabled() == true){
			menu.toggle(true);
		}else{
		player.stop();
        player.release();
		}
	}

	//Setting onClick methods for buttons 
	//in the sliding menu

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){
		case R.id.birdsongbtn:
			if(player.isPlaying()){
				player.stop();
				player.release();
				player = MediaPlayer.create(getApplicationContext(), R.raw.birdsong);
				player.start();
				player.setLooping(true);
			}else{
				player = MediaPlayer.create(getApplicationContext(), R.raw.birdsong);
			}
				
			break;
		case R.id.sumerstormbtn:
			if(player.isPlaying()){
				player.stop();
				player.release();
				player = MediaPlayer.create(getApplicationContext(), R.raw.summer);
				player.start();
				player.setLooping(true);
			}else{
				player = MediaPlayer.create(getApplicationContext(), R.raw.summer);
			}
			break;
		case R.id.rainstormbtn:
			if(player.isPlaying()){
				player.stop();
				player.release();
				player = MediaPlayer.create(getApplicationContext(), R.raw.simplerain);
				player.start();
				player.setLooping(true);
			}else{
				player = MediaPlayer.create(getApplicationContext(), R.raw.simplerain);
			}
			break;
		case R.id.singaporebtn:
			if(player.isPlaying()){
				player.stop();
				player.release();
				player = MediaPlayer.create(getApplicationContext(), R.raw.singapore);
				player.start();
				player.setLooping(true);
			}else{
				player = MediaPlayer.create(getApplicationContext(), R.raw.singapore);
			}
			break;
		default: player = MediaPlayer.create(getApplicationContext(), R.raw.summer);
		
		}
		
	}
}
