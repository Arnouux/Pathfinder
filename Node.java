package visualization;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int x;
	private int y;
	private int cost;
	private int heuristic;
	private int value;
	
	private int f;
	private int g;
	private Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.cost = 0;
		this.heuristic = 0;
	} 
	
	public int getValue() {
		return this.value;
	}
	public void setParent(Node p) {
		this.parent = p;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public int getG() {
		return this.g;
	}
	
	public int getF() {
		return this.f;
	}
	
	public int getHeuristic() {
		return this.heuristic;
	}
	
	public void setHeurstic(int h) {
		this.heuristic = h;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public List<Node> getNeighbors(Table table) {
		int size = table.getSize();
		List<Node> neighbors = new ArrayList<Node>();
		if (this.getX() > 0 && (table.getCase(this.getX()-1, this.getY()) == 0 || table.getCase(this.getX()-1, this.getY()) == 3)) {
			neighbors.add(new Node(this.getX()-1, this.getY()));
		}
		if (this.getY() > 0 && (table.getCase(this.getX(), this.getY()-1) == 0 || table.getCase(this.getX(), this.getY()-1) == 3)) {
			neighbors.add(new Node(this.getX(), this.getY()-1));
		}
		
		if (this.getX() < size-1 && (table.getCase(this.getX()+1, this.getY()) == 0 || table.getCase(this.getX()+1, this.getY()) == 3)) {
			neighbors.add(new Node(this.getX()+1, this.getY()));
		}

		if (this.getY() < size-1 && (table.getCase(this.getX(), this.getY()+1) == 0 || table.getCase(this.getX(), this.getY()+1) == 3)) {
			neighbors.add(new Node(this.getX(), this.getY()+1));
		}
		
		return neighbors;
	}
}