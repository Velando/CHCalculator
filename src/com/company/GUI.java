package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Philip on 27-06-2016.
 */
public class GUI{
    private Data data = Data.getInstance();

    public GUI(){
        show();
    }

    private void show(){
        JFrame mainFrame = new JFrame("Clicker Heroes Calculator");
        mainFrame.setSize(800,600);
        mainFrame.setLayout(new GridLayout(3,1));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);

        JLabel label = new JLabel("Paste your save string below",JLabel.CENTER);
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        JButton button = new JButton("Calculate!");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new SaveDecoder(textArea.getText()).getAncients() == null){
                    JOptionPane.showMessageDialog(null, "Invalid save string, please try again!");
                }
                else{
                    data.init(textArea.getText());
                    showNew();
                    mainFrame.setVisible(false);
                }
            }
        });
        KeyStroke keyStroke = KeyStroke.getKeyStroke("ENTER");
        textArea.getActionMap().put(textArea.getInputMap(JComponent.WHEN_FOCUSED).get(keyStroke), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.doClick();
            }
        });

        mainFrame.add(label);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainFrame.add(scrollPane);
        mainFrame.add(button);

        mainFrame.setVisible(true);
    }

    private void showNew(){
        JFrame mainFrame = new JFrame("Clicker Heroes Calculator");
        mainFrame.setSize(800,600);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);


        JTable table = new JTable(data.getColumnData(), data.getColumnNames());
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(125);

        JButton button = new JButton("Back");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show();
                mainFrame.setVisible(false);
            }
        });


        mainFrame.add(table.getTableHeader(), BorderLayout.PAGE_START);
        mainFrame.add(table, BorderLayout.CENTER);
        mainFrame.add(button,BorderLayout.PAGE_END);

        mainFrame.setVisible(true);
    }

}
