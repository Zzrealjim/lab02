package Week2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class JavaLab2 implements Runnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JavaLab2();	}
public JavaLab2() {
	SwingUtilities.invokeLater(this);
}

	@Override
	public void run() {
		
		
		JFrame jframe = new JFrame("Word Processor");
		JTextPane text = new JTextPane();
		text.setPreferredSize(new Dimension(500,500));
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jframe.getContentPane().add(new JLabel("Hello World"));
		jframe.add(text);
		
		JMenuBar bar = new JMenuBar();
		jframe.setJMenuBar(bar);
		
		JMenu file = new JMenu("File");
		bar.add(file);
		JMenuItem exit = new JMenuItem("exit");
		file.add(exit);
	
		JMenu tools = new JMenu("tools");
		bar.add(tools);
		JMenuItem count = new JMenuItem("count");
		tools.add(count);
		bar.add(tools);
		class exitaction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		}
		exit.addActionListener(new exitaction());
		
		class countaction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int s = text.getText().split("\\s+").length;
				JOptionPane.showMessageDialog(null, "Word count: "+s);
			}
			
		}
		count.addActionListener(new countaction());
		
		


		
		
		
		
		
		

		jframe.setVisible(true);
		jframe.pack();
	}

}
