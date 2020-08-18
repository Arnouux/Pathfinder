package visualization;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class ui extends JFrame implements ActionListener {
	
	private Drawer view;
	private Table table;
	
	
	private Button minus_button;
	private Button plus_button;
	private JLabel size;
	
	private Button a_star_button;
	private Button brute_force_button;
	
	private Button maze_button;
	
	private JRadioButton x1_button;
	private JRadioButton x01_button;
	ButtonGroup bg;
	
	private int size_int;

	public ui() {
		super("Visualization");
		
		this.size_int = 101;
		this.table = new Table(this.size_int);
		
		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		setLayout(new FlowLayout());
		
		minus_button = new Button("-");
		minus_button.addActionListener(this);
		this.add(minus_button);
		
		this.size = new JLabel(Integer.toString(size_int));
		this.add(this.size);
		
		plus_button = new Button("+");
		plus_button.addActionListener(this);
		this.add(plus_button);
		
		
		a_star_button = new Button("A*");
		a_star_button.addActionListener(this);
		this.add(a_star_button);
		
		brute_force_button = new Button("Brute Force");
		brute_force_button.addActionListener(this);
		this.add(brute_force_button);
		
		maze_button = new Button("Maze Generator");
		maze_button.addActionListener(this);
		this.add(maze_button);
		
		x1_button = new JRadioButton("x1");
		x1_button.setSelected(true);
		x01_button = new JRadioButton("x0.1");
		
		this.bg = new ButtonGroup();    
		this.bg.add(x1_button);
		this.bg.add(x01_button);
		x1_button.setActionCommand("x1");
		x01_button.setActionCommand("x01");
		
		this.add(x1_button);
		this.add(x01_button);
		
	
		
		this.view = new Drawer(this, this.table);

//		this.addKeyListener(this.view.getController());
//		this.setFocusable(true);

        this.view.setPreferredSize(new Dimension(401,401));
        this.add(this.view);
        
        this.view.requestFocusInWindow();
		this.view.setFocusable(true);


	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
 
		//CLEAR PATHS
		this.table.deleteOneType(4);
		this.table.deleteOneType(5);
		this.view.invalidate();
		this.view.repaint();
		
		if(source == this.minus_button){
			System.out.println("-");
			this.size_int -= 1;
			refresh();
		} else if(source == plus_button){
			System.out.println("+");
			this.size_int += 1;
			refresh();
		}
		else if(source == a_star_button && table.checkPresence(2) && table.checkPresence(3)) {
			//this.table.show();
			System.out.println("A*");
			A_star result = new A_star(this.table, this.view, this);
			this.view.invalidate();
			this.view.repaint();
		}
		
		else if(source == brute_force_button && table.checkPresence(2) && table.checkPresence(3)) {
			//this.table.show();
			System.out.println("Brute Force");
			BruteForce result = new BruteForce(this.table, this.view, this);
			this.view.invalidate();
			this.view.repaint();
		}
		
		else if(source == maze_button) {
			this.table.deleteOneType(1);
			MazeGenerator.generate(this.table);
			
			this.view.invalidate();
			this.view.repaint();
		}

		


	}
	
	public String getSpeedButton() {
		return this.bg.getSelection().getActionCommand();
	}
	
	private void refresh() {
		this.table.resize(this.size_int);
		
		this.size.setText(Integer.toString(size_int));
		this.view.invalidate();
		this.view.repaint();
	}
	
	public Table getTable() {
		return this.table;
	}
	
	public int getSizeTable() {
		return this.size_int;
	}
	
	public Drawer getView() {
		return this.view;
	}
	
	public static void main(String[] args) {
		ui frame = new ui();		frame.pack();
		
		frame.getView().requestFocusInWindow();
		
		frame.setSize(420,480);
		frame.setVisible(true);
		

	}
}
