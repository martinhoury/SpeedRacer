
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author martin
 */
public class Controller {
    private int idClient;
    private Menu interfaceMenu;
    private Login interfaceLogin;
    private Game interfaceGame;
    private Salon interfaceSalon;
    private CreateSalon interfaceCreateSalon;
    /**
     * @return the gGUI
     */
    public  GUI getgGUI() {
        return gGUI;
    }

    /**
     * @param agGUI the gGUI to set
     */
    public  void setgGUI(GUI agGUI) {
        gGUI = agGUI;
    }
    private SpeedRacerRMIServerInterface rmi;
    private GUI gGUI = null;
    public Controller(){
        interfaceMenu = new Menu(this);
        interfaceLogin = new Login(this);
        interfaceGame = new Game(this);
        interfaceSalon = new Salon(this);
        interfaceCreateSalon = new CreateSalon(this);
        System.out.println("Interface instanciée");
        try
        {
            //The GUI Thread
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                @Override public void run() {

                    //Create the GUI
                    setgGUI(new GUI());
                    
                    //Set size and location
                    getgGUI().setSize(500, 550);
                    getgGUI().setLocation(100, 100);

                    //Makes it visible
                    
                    //Set Visible Frame
                    getInterfaceMenu().setVisible(true);
                    

                }
            });}
        catch(Exception e)
        {
            e.printStackTrace();
        }

            //The Core
            //cCore = new Core(gGUI);
            //cCore.runGame();
        try {
            
            this.gGUI.setController(this);
            
            //On va rechercher l'interface publiée par le server et on le met dans "rmi"
            this.rmi = (SpeedRacerRMIServerInterface) Naming.lookup("rmi://localhost:1099/SpeedRacer");
            //On utilise les méthodes du server
            rmi.speedRacer();
            System.out.println("SpeedRacer client is ready to communicate");
            SpeedRacerRMIClientInterface speedRacerRMIClientInterface = new SpeedRacerRMIClientImplementation(this);
            this.idClient = this.rmi.register(speedRacerRMIClientInterface);
            if(this.idClient != 0){
                System.out.println("SpeedRacer client is registered");
                this.rmi.runGame(idClient);
            }
            
            
        } catch (NotBoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the rmi
     */
    public SpeedRacerRMIServerInterface getRmi() {
        return rmi;
    }

    /**
     * @param rmi the rmi to set
     */
    public void setRmi(SpeedRacerRMIServerInterface rmi) {
        this.rmi = rmi;
    }

    /**
     * @return the idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return the interfaceMenu
     */
    public Menu getInterfaceMenu() {
        return interfaceMenu;
    }

    /**
     * @param interfaceMenu the interfaceMenu to set
     */
    public void setInterfaceMenu(Menu interfaceMenu) {
        this.interfaceMenu = interfaceMenu;
    }

    /**
     * @return the interfaceLogin
     */
    public Login getInterfaceLogin() {
        return interfaceLogin;
    }

    /**
     * @param interfaceLogin the interfaceLogin to set
     */
    public void setInterfaceLogin(Login interfaceLogin) {
        this.interfaceLogin = interfaceLogin;
    }

    /**
     * @return the interfaceGame
     */
    public Game getInterfaceGame() {
        return interfaceGame;
    }

    /**
     * @param interfaceGame the interfaceGame to set
     */
    public void setInterfaceGame(Game interfaceGame) {
        this.interfaceGame = interfaceGame;
    }

    /**
     * @return the interfaceSalon
     */
    public Salon getInterfaceSalon() {
        return interfaceSalon;
    }

    /**
     * @param interfaceSalon the interfaceSalon to set
     */
    public void setInterfaceSalon(Salon interfaceSalon) {
        this.interfaceSalon = interfaceSalon;
    }

    /**
     * @return the interfaceCreateSalon
     */
    public CreateSalon getInterfaceCreateSalon() {
        return interfaceCreateSalon;
    }

    /**
     * @param interfaceCreateSalon the interfaceCreateSalon to set
     */
    public void setInterfaceCreateSalon(CreateSalon interfaceCreateSalon) {
        this.interfaceCreateSalon = interfaceCreateSalon;
    }
}
