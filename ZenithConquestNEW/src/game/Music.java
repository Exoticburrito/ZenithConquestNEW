package game;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	public void play(String path) {
		new Thread() {
			@Override
			public void run() {
				try {
					File file = new File(path);
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(file));
					clip.start();
					Thread.sleep(clip.getMicrosecondLength());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}.start();
	}
}
