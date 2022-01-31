package game;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicDriver extends Thread {

	MusicDriver() {

	}

	public void run() {
		String song = "https://stream.simulatorradio.com/stream.mp3";
		Player mp3player = null;
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new URL(song).openStream());
			mp3player = new Player(in);
			mp3player.play();
			System.out.println("Now Playing Simulator Radio!");
			System.out.println("https://simulatorradio.com");
		} catch (MalformedURLException ex) {
		} catch (IOException e) {
		} catch (JavaLayerException e) {
		} catch (NullPointerException ex) {
		} catch (Exception e) {
			System.out.println("ignore me!");
		}

	}

}
