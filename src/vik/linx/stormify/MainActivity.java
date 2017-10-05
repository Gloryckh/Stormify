package vik.linx.stormify;


import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	MediaPlayer player;

	private String[] slideMenuContent = {"About", "Exit"};
	private ListView menuListView;
	private DrawerLayout menuLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// configure the SlidingMenu
		menuLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		menuListView = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		menuListView.setAdapter(new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, slideMenuContent));

		// Set the list's click listener
		menuListView.setOnItemClickListener(new DrawerItemClickListener());

		int track = randomTrack();

		startTrack(player, track);
	}


	private void startTrack(MediaPlayer player, int trackID) {
		player = MediaPlayer.create(getApplicationContext(), trackID);
		player.start();
		player.setLooping(true);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (player != null && player.isPlaying()) {
			player.pause();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (player != null && !player.isPlaying()) {
			player.start();
		}

		if (player == null) {
			player = new MediaPlayer();
			startTrack(player, R.raw.birdsong);
		}
	}

	private int randomTrack() {
		//creating random number generator
		Random randomNumber = new Random();

		// creating an integer between 0 and 3
		int number = randomNumber.nextInt(4);

		switch (number) {
			case 1: // if number = 0
				return R.raw.summer;
			case 2: // if number = 1
				return R.raw.singapore;
			case 3: //if number = 2
				return R.raw.birdsong;
			case 4: // if number = 3
				return R.raw.simplerain;
			default:
				return R.raw.summer;
		}
	}



	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment and specify the planet to show based on position
		switch (position) {
			case 0:
				//About
				Intent myIntent = new Intent(MainActivity.this, AboutApp.class);
				MainActivity.this.startActivity(myIntent);
				break;
			case 1:
				//Exit
				finish();
				break;
			default:
				break;
		}
	}

}
