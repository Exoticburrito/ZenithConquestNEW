package game;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	Clip clip;
	File file;

	public void play(String path) {
		new Thread() {
			@Override
			public void run() {
				try {
					file = new File(path);
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(file));
					clip.start();
					Thread.sleep(clip.getMicrosecondLength());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}.start();

	}

	public void stopMusic() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}

}
