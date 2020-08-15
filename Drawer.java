package visualization;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;

public class Drawer extends JPanel implements FocusListener {
	
	private ui ui;
	private Table table;
	private Controller controller;
	
	public Drawer(ui ui, Table table) {
		this.ui = ui;
		this.table = table;
		this.controller = new Controller();
		this.controller.setView(this.ui);
		
		this.addMouseListener(this.controller);
		this.addMouseMotionListener(this.controller);
		this.addKeyListener(this.controller);
		this.addFocusListener(this);

		this.requestFocus();

	}
	
	public Controller getController() {
		return this.controller;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawTable(g);
		//this.draw(g);
		
		this.requestFocus();
	}
	
	public void drawTable(Graphics g) {
		int size = this.ui.getSizeTable();
		
		// 401/size*size for exact stop position, since not using floats
		for(int i=0; i<=size; ++i) {
			g.drawLine(401/size*i, 0, 401/size*i, 401/size*size);
			g.drawLine(0, 401/size*i, 401/size*size, 401/size*i);
		}
		
		
		for(int i=0; i < size; ++i) {
			for(int j=0; j < size; ++j) {
				if (this.table.getCase(i, j) == 1) {
					g.setColor(new Color(55, 55, 55));
					g.fillRect(401/size*i, 401/size*j, 401/size, 401/size);
					g.setColor(new Color(0));
					g.drawRect(401/size*i, 401/size*j, 401/size, 401/size);
				}
				else if (this.table.getCase(i, j) == 2) {
					g.setColor(new Color(2, 200, 55));
					g.fillRect(401/size*i, 401/size*j, 401/size, 401/size);
					g.setColor(new Color(0));
					g.drawRect(401/size*i, 401/size*j, 401/size, 401/size);
				}
				else if (this.table.getCase(i, j) == 3) {
					g.setColor(new Color(200, 2, 55));
					g.fillRect(401/size*i, 401/size*j, 401/size, 401/size);
					g.setColor(new Color(0));
					g.drawRect(401/size*i, 401/size*j, 401/size, 401/size);
				}
				else if (this.table.getCase(i, j) == 4) {
					g.setColor(new Color(2, 150, 55));
					g.fillRect(401/size*i, 401/size*j, 401/size, 401/size);
					g.setColor(new Color(0));
					g.drawRect(401/size*i, 401/size*j, 401/size, 401/size);
				}
			}
		}
	}	
	
	public void invalidate() {
		this.paintImmediately(getBounds());
	}
	
	public void draw(Graphics g) {
		this.table.show();
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
