package gui.view;
import gui.controller.ActionManager;
import gui.controller.ExportAction;
import gui.controller.PrettyAction;
import gui.controller.RunAction;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{
    private JTextPane tekstPanel;
    private JPanel stabloPanel;
    private JPanel goreDesno;
    private JPanel dugmiciPanel;
    private JPanel desniPanel;
    private JSplitPane tekstIDugmici;
    private JTable tabelaPanel;
    private JButton export;
    private JButton run;
    private JButton pretty;
    private ActionManager actionManager;

    public void pokreniGUI(){
        //panel za stablo
        stabloPanel = new JPanel();
        stabloPanel.setBackground(Color.blue);
        stabloPanel.setPreferredSize(new Dimension(150, 100));

        //panel za tekst
        tekstPanel = new JTextPane();//tekstPane da bi tekst mogao da ide koliko god
        tekstPanel.setBackground(Color.ORANGE);
        tekstPanel.setPreferredSize(new Dimension(450, 250));
        tekstPanel.add(Box.createHorizontalGlue());

        run=new JButton("run");
        run.addActionListener(actionManager.getRunAction());
        run.setFocusable(false);
        pretty=new JButton("pretty");
        pretty.setFocusable(false);
        pretty.addActionListener(actionManager.getPrettyAction());
        export=new JButton("export");
        export.setFocusable(false);
        export.addActionListener(actionManager.getExportAction());

        //panel za dugmice
        dugmiciPanel = new JPanel();
        dugmiciPanel.setBackground(Color.red);
        dugmiciPanel.setLayout(new GridLayout(3,1,10,10));
        dugmiciPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        dugmiciPanel.add(run);
        dugmiciPanel.add(pretty);
        dugmiciPanel.add(export);
        dugmiciPanel.setPreferredSize(new Dimension(100, 50));

        // panel koji spaja dugmice i tekst
        goreDesno = new JPanel();
        tekstIDugmici = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(tekstPanel), dugmiciPanel);
        tekstIDugmici.setResizeWeight(1.0);//da velicinu menja samo levi(da stoji 0.0 velicinu bi menjao samo desni)
        tekstIDugmici.setDividerLocation(600);
        goreDesno.setLayout(new BoxLayout(goreDesno, BoxLayout.X_AXIS));
        goreDesno.add(tekstIDugmici);
        goreDesno.add(Box.createGlue());

        //panel za tabelu
        tabelaPanel = new JTable();
        tabelaPanel.setBackground(Color.green);
        tabelaPanel.setPreferredSize(new Dimension(150, 150));

        //panel koji spaja tekst i dugmice sa tabelom
        desniPanel = new JPanel();
        desniPanel.setLayout(new BorderLayout());
        desniPanel.add(goreDesno, BorderLayout.NORTH);
        desniPanel.add(tabelaPanel, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(stabloPanel), BorderLayout.WEST);
        this.add(desniPanel, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setVisible(true);
    }
    public MyFrame() {
        actionManager=new ActionManager();
        pokreniGUI();
    }
    public void updateTextPane() {
        //tekstPanel.setText(s);
        tekstPanel.revalidate();
        tekstPanel.repaint();

    }

    public  JTextPane getTekstPanel() {
        return tekstPanel;
    }
    public void setTekstPanel(JTextPane tekstPanel) {
        this.tekstPanel = tekstPanel;
    }
    //singletone

    private static MyFrame instance=null;

    public static MyFrame getInstance(){
        if(instance==null){
            instance = new MyFrame();
        }
        return instance;
    }
}