import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yisroel
 */
public class FortuneTellerFrame extends JFrame 
{
    JPanel top, middle, bottom, main;
    JLabel topLbl, bottomLbl;
    JButton actionBtn, quitBtn;
    JTextArea textArea;
    JScrollPane scroller;
    JScrollBar verticle;
    ImageIcon icon;
    ArrayList<String> fortunes = new ArrayList<>();
    ArrayList<Integer> repeatChecker = new ArrayList<>();
   
    public int index;
    
    public FortuneTellerFrame()
    {
        super("Fortune Teller");
        main = new JPanel();      
        createTopPanel();
        createMiddlePanel();
        createBottomPanel();
        loadFortunes();
        
        // Now add sub panels to main
        main.setLayout(new BorderLayout());
        main.add(top,BorderLayout.NORTH);
        main.add(middle,BorderLayout.CENTER);
        main.add(bottom,BorderLayout.SOUTH);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        
        setSize(3 * (screenSize.width / 4), 3 * (screenSize.height / 4));
        setLocationRelativeTo(null);
        
        
        add(main);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    
    
    private void loadFortunes()
    {
        fortunes.add("Silence is golden, ducktape is silver.");
        fortunes.add("Friends help you move a couch, best friends help you move a body.");
        fortunes.add("You will meet the significant other or your dreams...Never!");
        fortunes.add("Tomorrrow you will wake up....what? Did you want something else? You asked for a fortune, not a meaningful one.");
        fortunes.add("You will see the light, just not the one you are expecting.");
        fortunes.add("Meh....try again later.");
        fortunes.add("Hearty laughter is a good way to jog internally without having to go outdoors.");
        fortunes.add("The fortune you seek is not here.");
        fortunes.add("A palm can say much, especially when it smacks.");
        fortunes.add("Today might be a huge improvement over yesterday.");
        fortunes.add("Never tease an armed midget witha high five.");
        fortunes.add("You will__________ insert quarter for rest of fortune.");
    }
    
    private void createTopPanel()   
    {    
        top = new JPanel();
        icon = new ImageIcon("fortuneTeller.png");
        topLbl = new JLabel("Fortune Teller", icon, SwingConstants.CENTER);
        topLbl.setFont(new Font("Comic Sans MS",Font.PLAIN, 36));
        top.add(topLbl);
    }
        
    private void createMiddlePanel()    
    {    
       middle = new JPanel();
       textArea = new JTextArea(10, 50);
       scroller = new JScrollPane(textArea);
       
       scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
       scroller.setPreferredSize(new Dimension(700, 400));
//       scroller.setAlignmentX(CENTER_ALIGNMENT);
//       verticle = scroller.getVerticalScrollBar();
//       verticle.setValue(verticle.getMaximum());

       middle.setFont(new Font("Bradley Hand ITC", Font.ITALIC, 20));
       
       middle.add(scroller);
    }
    
    private void createBottomPanel()
    {        
        bottom = new JPanel();
        bottomLbl = new JLabel();
        
        actionBtn = new JButton("Read My Fortune!");
        actionBtn.addActionListener((ActionEvent ae) -> {
             mixFortunes();
        });
        
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
             System.exit(0);
        });
        
        bottom.setFont(new Font("Bradley Hand ITC", Font.ITALIC, 12));
        bottom.add(actionBtn);
        bottom.add(quitBtn);
    }    
     
    public void mixFortunes() throws ArrayIndexOutOfBoundsException
    {
        Random random = new Random();
        int previousNum;
        
        if(repeatChecker.size() > 1)
        {
            previousNum = repeatChecker.size() -1;
        }
        else
        {
            previousNum = 0;
        }
        
        while (true)
        {
          index = random.nextInt(fortunes.size());
          repeatChecker.add(index);
          if (index != repeatChecker.get(previousNum)) break; 
        }
        
        textArea.append(fortunes.get(index) + "\n");
//        verticle.setValue(verticle.getMaximum());
    }
}

