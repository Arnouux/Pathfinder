package visualization;

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
		
		this.view.getTable().changeCase(p.x, p.y);
		
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
			
			this.view.getTable().changeCase(p.x, p.y);
			
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
		
		Point p = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(p, this.view.getView());
		// get x,y relative from window position.
		Point p_window = view.getLocationOnScreen();
		p.x -= p_window.x; p.y -= p_window.y ;
		
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
