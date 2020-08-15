package visualization;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ui extends JFrame implements ActionListener {
	
	private Drawer view;
	private Table table;
	
	
	private Button minus_button;
	private Button plus_button;
	private JLabel size;
	
	private Button a_star_button;
	
	private int size_int;

	public ui() {
		super("Visualization");
		
		this.size_int = 10;
		this.table = new Table();
		
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
			this.table.show();
			System.out.println("A*");
			A_star result = new A_star(this.table, this.view);
			this.view.invalidate();
			this.view.repaint();
			this.table.show();
		}
		


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
