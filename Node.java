package visualization;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int x;
	private int y;
	private int cost;
	private int heuristic;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.cost = 0;
		this.heuristic = 0;
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
	
	public List<Node> getNeighbors(int size) {
		List<Node> neighbors = new ArrayList<Node>();
		if (this.getX() > 0) {
			neighbors.add(new Node(this.getX()-1, this.getY()));
		}
		if (this.getX() < size-1) {
			neighbors.add(new Node(this.getX()+1, this.getY()));
		}
		if (this.getY() > 0) {
			neighbors.add(new Node(this.getX(), this.getY()-1));
		}
		if (this.getY() < size-1) {
			neighbors.add(new Node(this.getX(), this.getY()+1));
		}
		
		return neighbors;
	}
}