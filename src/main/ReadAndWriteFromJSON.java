/**
 * This is the main class,
 * @author Dawid S. Kopczewski
 */

package main;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ReadAndWriteFromJSON extends JFrame{
	SupportPanel spanel;
	
	public ReadAndWriteFromJSON() {
		spanel = new SupportPanel();
		add(spanel);
		setSize(new Dimension(spanel.returnWidth(), spanel.returnHeight()));
		setLayout(new FlowLayout());
		setTitle("Read and write to JSON file");
		setUndecorated(false);
		setResizable(false);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ReadAndWriteFromJSON();
	}
}
