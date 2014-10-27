
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author martin
 */
public class SpeedRacerRMIClientImplementation extends UnicastRemoteObject implements SpeedRacerRMIClientInterface{
    Controller controller;
    
    public SpeedRacerRMIClientImplementation(Controller controller) throws RemoteException{
        super();
        this.controller = controller;
    }
    @Override
    public void test() throws RemoteException {
        System.out.println("Coucou ceci est un test");
    }


    @Override
    public void update(Vector<Rectangle> vDisplayRoad, Vector<Rectangle> vDisplayObstacles, Vector<Rectangle> vDisplayCars, Car myCar, int pos, int nbParticipants, boolean bGameOver, String sPosition) throws RemoteException {
        
        controller.getgGUI().update(vDisplayRoad, vDisplayObstacles, vDisplayCars, myCar, pos, nbParticipants, bGameOver, sPosition);
    }

    @Override
    public void enablePlayButton(boolean enable) throws RemoteException {
        controller.getgGUI().jButton1.setEnabled(true);
    }
    
}
