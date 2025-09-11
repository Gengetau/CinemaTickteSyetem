package com.seiryo.util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * 音乐播放工具
 */
public class BGMPlayer {
	private static final Logger logger = Logger.getLogger(BGMPlayer.class.getName());
	private static Clip bgmClip;
	
	/**
	 * 背景音乐播放方法！
	 *
	 * @param musicPath 音乐文件在我们resources文件夹里的路径
	 */
	public static void playBGM(String musicPath) {
		try {
			// 1. 获取音乐文件的URL
			// getClass().getResource() 是寻找resources文件夹里文件的标准方法！
			URL musicUrl = BGMPlayer.class.getResource(musicPath);
			if (musicUrl == null) {
				System.out.println("找不到音乐文件: " + musicPath);
				return;
			}
			
			// 2. 读取“唱片”，获取音频输入流
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl);
			
			// 3. 准备我们的“留声机” (Clip)
			bgmClip = AudioSystem.getClip();
			
			// 4. 让“留声机”加载我们的音频流
			bgmClip.open(audioStream);
			
			// 5. 设置为无限循环播放
			bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (UnsupportedAudioFileException e) {
			System.out.println("不支持的音乐文件格式，请确认是.wav格式");
			logger.warning(e.getMessage());
		} catch (IOException e) {
			System.out.println("读取文件时发生错误了");
			logger.warning(e.getMessage());
		} catch (LineUnavailableException e) {
			System.out.println("播放设备不可用");
			logger.warning(e.getMessage());
		}
	}
	
	/**
	 * 停止音乐方法！
	 */
	public static void stopBGM() {
		if (bgmClip != null && bgmClip.isRunning()) {
			bgmClip.stop();
			bgmClip.close();
			System.out.println("背景音乐已停止。");
		}
	}
	
}
