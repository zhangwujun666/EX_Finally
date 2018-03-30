package com.example.ex_finally;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MusicService extends Service {
	
public final static String ACTION_PLAY="ACTION_PLAY";
public final static String ACTION_PAUSE="ACTION_PAUSE";
public final static String ACTION_STOP="ACTION_STOP";
public final static String ACTION_CONTINUE="ACTION_CONTINUE";

List musiclist=new ArrayList();
String[] musicFormat=new String[]{"wav","mp3","3gp","wma","ape"};

private MediaPlayer mp;
	public MusicService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		mp=new MediaPlayer();
		mp.setLooping(true);
		super.onCreate();
	}
	
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		String action=intent.getAction();
		
		//getFiles("/sdcard/");//测试
		//String path2=(String) musiclist.get(0);
		
		if(ACTION_PLAY.equals(action))
		{
			//
			//String path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + File.separator + "a.mp3";
			
			mp.reset();
			Toast.makeText(MusicService.this, "1", 1).show();
			try {
				mp.setDataSource("/storage/sdcard/Music/test.mp3");
				mp.prepare();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mp.start();
			Toast.makeText(MusicService.this, "2", 1).show();
		}
		else if(ACTION_PAUSE.equals(action))
		{
			if(mp.isPlaying())
			{
				mp.pause();
			}
		}
		else if(ACTION_CONTINUE.equals(action))
		{
			if(!mp.isPlaying())
			{
				mp.start();
			}
		}
		else if(ACTION_STOP.equals(action))
		{
			if(mp!=null)
			{
				mp.stop();
				mp.release();
			}
		}
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	//************************************************//
	protected void getFiles(String path)	//获取路径
	{
		File files=new File(path);
		File[] file=files.listFiles();
		for(File f:file)
		{
			if(f.isDirectory())
			{
				getFiles(f.getAbsolutePath());
			}
			else
			{
				if(isMusicFile(f.getPath()))
				{
					musiclist.add(f.getPath());	//?
					
					//String fileName=f.getPath().trim();
					//showlist.add
					//(fileName.substring( fileName.lastIndexOf("/")+1 ));
				}
			}
		}
	}
	
	//
	/*
	private void MusicList()
	{
		getFiles("/sdcard/");
		ArrayAdapter<String> apt=new ArrayAdapter<String>
		(this, android.R.layout.simple_list_item_1, musiclist);	//已改
		//list.setAdapter(apt);
		
		/*
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currentItem=position;
				playmusic( (String) musiclist.get(currentItem) );
			}
		});
		
	}
	*/
	//
	protected boolean isMusicFile(String path)	//判断是否是音乐文件
	{
		for(String format:musicFormat)
		{
			if(path.contains(format))
			{
				return true;
			}
		}
		return false;
	}
}
