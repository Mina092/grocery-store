// Java program to play an Audio
// file using Clip Object
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ClickSound
{

	// to store current position
	Long currentFrame;
	Clip clip;
	
	// current status of clip
	String status;
	
	AudioInputStream audioInputStream;
	static String filePath;

	// constructor to initialize streams and clip
	public ClickSound()
		throws UnsupportedAudioFileException,
		IOException, LineUnavailableException
	{
		// create AudioInputStream object
		audioInputStream =
				AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		
		// create clip reference
		clip = AudioSystem.getClip();
		
		// open audioInputStream to the clip
		clip.open(audioInputStream);
		
		clip.loop(0);
	}

	public static void sound()
	{
		try
		{
			filePath = "src/sounds/clicksound.wav";
			ClickSound audioPlayer =
							new ClickSound();
			
			audioPlayer.play();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		catch (Exception ex)
		{
		
		}
	}

	// Method to play the audio
	public void play()
	{
		//start the clip
		clip.start();
		
		status = "play";
	}
	
	
}
