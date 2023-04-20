package core;
//okuplja sve interfejse
public class ApplicationFramework {
    protected Gui gui;

    public  void run(){
        this.gui.start();
    };

    public void izvrsi(Gui gui){
        this.gui=gui;
    }

    private static ApplicationFramework instance;
    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance= new ApplicationFramework();

        }
        return instance;
    }

}
