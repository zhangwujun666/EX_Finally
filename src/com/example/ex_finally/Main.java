package com.example.ex_finally;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

public class Main extends Activity {
//public:
TabHost th;
MediaPlayer mp;
String[] musicFormat=new String[]{"wav","mp3","3gp","wma","ape"};
int currentItem=0;
boolean isPlaying=false;
List showlist,musiclist;
//Basic.xml:
private ListView music_list;
private ImageButton play_pause,next,back,stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    
		        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		
		/*
		 * 注册
		 */

		/*
		 * TabHost
		 */
		th=(TabHost) super.findViewById(android.R.id.tabhost);
		th.setup();
		
		LayoutInflater inf=LayoutInflater.from(this);
		inf.inflate(R.layout.basic, th.getTabContentView());
		
		TabSpec t1=th.newTabSpec("tab1").setIndicator("音乐列表").setContent(R.id.basic);
		
		th.addTab(t1);
		
		/*
		 * basic页面
		 */
		music_list=(ListView) super.findViewById(R.id.music_list);
		play_pause=(ImageButton) super.findViewById(R.id.play_pause);
		next=(ImageButton) super.findViewById(R.id.next);
		back=(ImageButton) super.findViewById(R.id.back);
		stop=(ImageButton) super.findViewById(R.id.stop);
		//mp=new MediaPlayer();
		
		musiclist=new ArrayList<String>();
		showlist=new ArrayList<String>();
		
		play_pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mp==null)	//第一次播放
				{
					//Intent in=new Intent(Main.this,MusicService.class);
					Intent intent=new Intent(MusicService.ACTION_PLAY);
					startService(intent);
					//startService(in);
					Toast.makeText(Main.this, "dddd", 1).show();
					play_pause.setImageDrawable
					(getResources().getDrawable(R.drawable.pause));
				}
				else
				{
					if(mp.isPlaying())	//播放中暂停
					{
						Intent intent=new Intent(MusicService.ACTION_PAUSE);
						startService(intent);
						
						play_pause.setImageDrawable
						(getResources().getDrawable(R.drawable.play));
						Toast.makeText(Main.this, "aaaa", 1).show();
					}
					else	//暂停中继续播放
					{
						Intent intent=new Intent(MusicService.ACTION_CONTINUE);
						startService(intent);
						play_pause.setBackgroundResource(R.drawable.pause);
						Toast.makeText(Main.this, "bbbb", 1).show();
					}
				}
			}
		});
		
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MusicService.ACTION_STOP);
				startService(intent);
			}
		});
		
		/*
		 * 收藏页面
		 */
		
	}
	

}
