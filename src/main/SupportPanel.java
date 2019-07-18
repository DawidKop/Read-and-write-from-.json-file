package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONObject;

@SuppressWarnings("serial")
public class SupportPanel extends JPanel implements ActionListener{
	
	//defining constants
	private final int WIDTH = 400;
	private final int HEIGHT = 600;
	//other variables
	JTextArea text;
	JButton save, load, clear;
	JScrollPane textknob;
	SupportManager sm;
	String currenttext;
	List <JSONObject> pom;
	
	//the constructor
	public SupportPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setName("Read and write to JSON file");
		setFocusable(true);
		text= new JTextArea();
		clear = new JButton("Clear");
		save = new JButton("Save");
		load = new JButton("Load");
		textknob = new JScrollPane(text);
		sm = new SupportManager();
		currenttext="";
		pom = new ArrayList<>();
		
		textknob.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textknob.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(textknob);
		
		add(save); add(load); add(clear); //adds the buttons
		save.addActionListener(this);
		load.addActionListener(this);
		clear.addActionListener(this);
		buttonSetUp(); //setup is done by a separate method
		
		text.setSize(340,540);
		text.setLineWrap(true);
		text.setRows(24);
		text.setFont(new Font("Calibri", Font.ITALIC, 16));
		
		setVisible(true); //sets panel visible at the end of constructor
	}
	
	private void buttonSetUp() {
		save.setFocusPainted(false); load.setFocusPainted(false); clear.setFocusPainted(false);
		save.setBackground(Color.ORANGE); load.setBackground(Color.GREEN); clear.setBackground(Color.BLUE);
		save.setForeground(Color.WHITE); load.setForeground(Color.WHITE); clear.setForeground(Color.WHITE);
		save.setFont(new Font("Calibri", Font.BOLD, 16));
		load.setFont(new Font("Calibri", Font.BOLD, 16));
		clear.setFont(new Font("Calibri", Font.BOLD, 16));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save){//actions for save button
			sm.saveJson(text.getText());
			sm.loadJson(); //reloads the json file
		}
		else if(e.getSource()==load){//actions for load button
			clearText(); //first, the text needs to be cleared
			int i=0;
			while (sm.database.get(i)!=(null)) {
				pom.add(sm.database.get(i));
				i++;
			}
			int numberOfObjects = pom.size();
			for(JSONObject j : pom) {
				currenttext+=j.toString(4);
				numberOfObjects--;
				if(numberOfObjects>=1)currenttext+="\n"; //fix as the last line cannot be blank
			}
			text.setText(currenttext);
		}
		else if(e.getSource()==clear){//actions for clear button
			clearText();
		}
	}
	
	private void clearText() {
		if(text.getText()!=""){text.setText("");}//clears the text
		currenttext=""; pom.clear();
	}
	
	public int returnWidth() {
		return WIDTH;
	}
	
	public int returnHeight() {
		return HEIGHT;
	}
}
