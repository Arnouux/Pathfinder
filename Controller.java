package visualization;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import java.awt.MouseInfo;
import java.awt.Point;

public class Controller implements MouseListener, MouseMotionListener, KeyListener {
	
	private ui view;
	
	private Point lastClick = new Point(-1,-1);
	private int lastClickType;
	
	public void setView(ui view) {
		this.view = view;
	}
	
	public void mousePressed(MouseEvent e)
	{
		Point p = e.getPoint();
		int size = this.view.getSizeTable();
		p.x /= (400/size);
		p.y /= (400/size);
		
		System.out.println(p);
		
		//CLEAR PATHS
		this.view.getTable().deleteOneType(4);
		this.view.getTable().deleteOneType(5);
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.view.getTable().setWall(p.x, p.y);
			this.lastClickType = 1;
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			this.view.getTable().setNone(p.x, p.y);
			this.lastClickType = 3;
		}
		
		
		this.view.invalidate();
		this.view.repaint();
		
		this.lastClick = new Point(p.x, p.y);
		
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{
	}
	
	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}
	
	public void mouseMoved(MouseEvent e)
	{
	}
	
	public void mouseDragged(MouseEvent e)
	{
		Point p = e.getPoint();
		int size = this.view.getSizeTable();
		p.x /= (400/size);
		p.y /= (400/size);
		
		if (p.x != this.lastClick.x || p.y != this.lastClick.y) {
			
			if (this.lastClickType == 1) {
				this.view.getTable().setWall(p.x, p.y);
			}
			else if (this.lastClickType == 3) {
				this.view.getTable().setNone(p.x, p.y);
			}
			
			this.view.invalidate();
			this.view.repaint();
		}
		
		
		this.lastClick = new Point(p.x, p.y);
	}
	
	public void keyTyped(KeyEvent e)
	{
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		//CLEAR PATHS
		this.view.getTable().deleteOneType(4);
		this.view.getTable().deleteOneType(5);
		
		Point p = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(p, this.view.getView());
		// get x,y relative from window position.
		//Point p_window = view.getLocationOnScreen();
		//p.x -= p_window.x; p.y -= p_window.y ;
		
		int size = this.view.getSizeTable();
		p.x /= (400/size);
		p.y /= (400/size);
		
		if (key == KeyEvent.VK_A) {
			if ( this.view.getTable().checkPresence(2)) {
				this.view.getTable().deleteOneType(2);
			}
			this.view.getTable().setStart(p.x, p.y);
		}
		else if(key == KeyEvent.VK_Z) {
			if ( this.view.getTable().checkPresence(3)) {
				this.view.getTable().deleteOneType(3);
			}
			this.view.getTable().setEnd(p.x, p.y);
		}
		else if(key == KeyEvent.VK_S) {
			this.view.getTable().show();
		}
		
		this.view.invalidate();
		this.view.repaint();
	}

	public void keyReleased(KeyEvent e)
	{
	}

}
