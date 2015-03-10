/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import teamproject.system.BackUp;
import teamproject.system.Property;
import teamproject.system.SystemSetting;
import static teamproject.system.SystemSetting.initSystemSetting;

/**
 *
 * @author drgex_000
 */
public class Setting extends JFrame{
    private static final int argsLength = 2;
    private static final String backUpString = "backup";
    private static final String database = "database";
    private static final String settings = "settings";
     private static final String restoreString = "restore";
    public static void main(String[] args) throws IOException, BackingStoreException, InvalidPreferencesFormatException
    {
        initSystemSetting();
        if(args.length == 0)
        {
            Setting GUI = new Setting();
        }
        else if(args.length == argsLength)
        {
            
            if(args[0].equals(backUpString))
            {
                 System.out.println("Attempting to back up ");
                if(args[1].equals(database))
                {
                    System.out.println(database);
                    System.out.println("Creating backfile in " + SystemSetting.getProperty(Property.BackUpPath, ""));
                    
                    BackUp.createBackup();
                }
                else if(args[1].equals(settings))
                {
                    System.out.println(settings);
                    System.out.println("Creating backfile in " + SystemSetting.getProperty(Property.BackUpPath, ""));
                    
                    BackUp.backUpPreferences();
                }
            }
            else if(args[0].equals(restoreString))
            {
                if(args[1].equals(database))
                {
                    BackUp.restore();
                }
                else if(args[1].equals(settings))
                {
                    BackUp.restorePreferences();
                }
            }
            else
            {
                Property property = Property.getByName(args[0]);
                if(property != null)
                {
                    SystemSetting.setProperty(property, args[1], null);
                    SystemSetting.saveSettings();

                    System.out.println(args[0] + " set to " + SystemSetting.getProperty(property, ""));
                }
                else
                {
                    System.err.println("No property by the name " + args[0]);
                }
            }
        }
        else
        {
            System.err.println("Arguments length not valid. Expected " + argsLength + " got " + args.length + ".");
        }
    }
    
    
    public Setting()
    {
        super();
        SystemSetting.initSystemSetting();
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        for(Property p :Property.values())
        {
           panel.add(new PropertyPanel(p));
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setMaximumSize(new Dimension(800,600));
        scroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
//        scroll.add(panel);
        this.add(scroll);
        this.pack();
        this.setVisible(true);
    }
    
    private class PropertyPanel extends JPanel
    {
        
        Property property;
        JTextField textfield;
        JButton button;
        public PropertyPanel(Property p)
        {
            
            property = p;
            this.setLayout(new BorderLayout());
            JPanel labelPanel = new JPanel();
            labelPanel.add( new JLabel(p.name() +":"));      
       
            labelPanel.setPreferredSize(new Dimension(200,30));
            this.add( labelPanel,BorderLayout.WEST);
            textfield = new JTextField(40);
            textfield.setText(SystemSetting.getProperty(p, ""));
            this.add(textfield,BorderLayout.CENTER);
            button = new JButton("Save");
            button.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) 
                {
                    SystemSetting.setProperty(property,textfield.getText(),null);
                    SystemSetting.saveSettings();
                }
            });
            this.add(button,BorderLayout.EAST);
            
        }
    }
}
