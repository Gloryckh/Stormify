package vik.linx.stormify;


import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	MediaPlayer player = null;

	private String[] slideMenuContent = {"About", "Exit"};
	private ListView menuListView;
	private DrawerLayout menuLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Integer minutes = getIntent().getIntExtra("TIME_AMOUNT", 60);

		Button openMenuBtn = (Button) findViewById(R.id.open_menu_btn);

		TextView timeRemaining = (TextView) findViewById(R.id.time_remaining);


		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int height = displayMetrics.heightPixels;
		int width = displayMetrics.widthPixels;

		ImageView imageView = (ImageView) findViewById(R.id.logo_img_view);


		android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
		layoutParams.width = width/2;
		layoutParams.height = height/2;
		imageView.setLayoutParams(layoutParams);
		
		// configure the SlidingMenu
		menuLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		menuListView = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		menuListView.setAdapter(new ArrayAdapter<>(this,
				R.layout.listitem, slideMenuContent));

		// Set the list's click listener
		menuListView.setOnItemClickListener(new DrawerItemClickListener());

		setOnClick(openMenuBtn);

		int track = randomTrack();


		startTrack(player, track);


		//set a countdown until we stop
		//We get onTick() callbacks every minute (60000 ms)
		new CountDownTimer(minutes * 60000, 60000) {

			public void onTick(long millisUntilFinished) {
				int numMinutes = (int)millisUntilFinished/60000;
				timeRemaining.setText(String.format("%d",numMinutes)  + " Minutes Left");
			}

			public void onFinish() {
				if (player != null) {
					player.stop();
					player.release();
				} else {
					Toast.makeText(getApplicationContext(), R.string.cant_stop, Toast.LENGTH_LONG).show();
				}
			}
		}.start();
	}

	private void setOnClick(Button button) {
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (menuLayout.isDrawerOpen(menuListView)) {
					menuLayout.closeDrawer(menuListView);
				} else {
					menuLayout.openDrawer(menuListView);
				}
			}
		});
	}


	private void startTrack(MediaPlayer player, int trackID) {
		player = MediaPlayer.create(getApplicationContext(), trackID);
		player.start();
		player.setLooping(true);
	}


	/**
	 * Randomly chooses a track to start playing.
	 * @return
	 */
	private int randomTrack() {
		Random randomNumber = new Random();

		int number = randomNumber.nextInt(4);

		switch (number) {
			case 1:
				return R.raw.summer;
			case 2:
				return R.raw.singapore;
			case 3:
				return R.raw.birdsong;
			case 4:
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

	@Override
	public void onBackPressed() {
		if (player != null) {
			player.pause();
			player.stop();
			player.release();
		}
	}

	/** Does different behavior based on menu item clicked */
	private void selectItem(int position) {
		switch (position) {
			case 0:
				//About
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.message)
						.setTitle(R.string.title);

				builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				builder.create().show();
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
