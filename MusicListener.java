import java.io.IOException;
import java.lang.Math;
import javax.swing.*;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class MusicListener extends Listener {
	private double speed = 0;
	private double volume = 0;
	private final double DELTA_S = .004;
	private final double DELTA_V = .7;
	private boolean tap = false;

	public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }
    public double getTempo() 
    {
    	return speed;
    }
    public double getVolume()
    {
    	return volume;
    }
    public boolean getTap()
    {
    	return tap;
    }
    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    
    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();

        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);

            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    CircleGesture circle = new CircleGesture(gesture);

                    // Calculate clock direction using the angle between circle normal and pointable

                    if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2) {
                        // Clockwise if angle is less than 90 degrees
                        speed += DELTA_S;
                    } else {
                        speed -= DELTA_S;
                    }

                    // Calculate angle swept since last frame
                    double sweptAngle = 0;
                    if (circle.state() != State.STATE_START) {
                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
                    }

                    break;
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    if (swipe.pointable().direction().getX() > 0)
                    {
                    	volume += DELTA_V;
                    }
                    else
                    {
                    	volume -= DELTA_V;
                    }
                    
                    break;
                
                case TYPE_SCREEN_TAP:
                	ScreenTapGesture tapGesture = new ScreenTapGesture(gesture);
                	tap = true;
                	
                default:
                    break;
            }
        }

    }
}

