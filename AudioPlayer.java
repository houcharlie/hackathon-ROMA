
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
public class AudioPlayer { 
	
	private String name;
	private double bpm;
	public AudioPlayer(String name, double bpm)
	{
		this.name = name;
		//allows us to keep track of notes up to sixteenth notes, which are as far as songs go usually
		this.bpm = bpm * 4;
	}
	public void play()
	{

		// TODO Auto-generated method stub
		// Create a sample listener and controller
        MusicListener listener = new MusicListener();
        Controller controller = new Controller();
        
        System.out.println("Press Enter to quit...");
        
        //creates the pseudo-Song thing
        double tempo = 0;
        double volume = 0;
        double[] d = StdAudio.read(name);
		//stores double values into an array and then converts that array into its playable version
		double[] songArray = new double[d.length/2];
        for (int i = 0; i < d.length/2 ; i++) {
            songArray[i] = d[2*i];
        }
        double samplingWindow = 44100.0/(bpm/60);
        
        
        controller.addListener(listener);
        
        for(int i = 0; i < songArray.length; i += samplingWindow)
		{
        	
			for(int j = i; j < i + samplingWindow * Math.pow(1.5,-tempo) && j < songArray.length; j++)
			{

				
				tempo = listener.getTempo();
				volume = listener.getVolume();
				if(j > i + samplingWindow)
				{
					StdAudio.play(Math.pow(1.05, volume) * songArray[j - (i + (int) (samplingWindow+1))]);
					
				}
				else
				{
					StdAudio.play(Math.pow(1.05, volume) * songArray[j]);
				}
				
				if(listener.getTap())
				{
					return;
				}
				
				
			}
			
        	
		}
		
        // Remove the sample listener when done
        controller.removeListener(listener);
	}
	
}


