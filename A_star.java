package visualization;

import java.util.ArrayList;
import java.util.List;

public class A_star {

	
	private Node start;
	private Node end;
	
	public A_star(Table table) {

		this.start = table.getStart();
		this.end = table.getEnd();
		
		List<Node> closedList = new ArrayList<Node>();
		List<Node> openList = new ArrayList<Node>();
		openList.add(this.start);
		
		while (!(openList.isEmpty())) {
			Node u = openList.get(openList.size() - 1);
			openList.remove(openList.size() - 1);
			if (u.getX() == end.getX() || u.getY() == end.getY()) {
				System.out.println("found");
				
				List<Node> totalPath = new ArrayList<Node>();
				totalPath.add(u);
//				while (contains(closedList, u)) {
//					u = closedList.
//				}
				System.out.println(closedList);
				
				break;
			}
			
			List<Node> neighbors = u.getNeighbors(table.getSize());
			for (Node n : neighbors) {
				if (!(contains(closedList, n) || containsInferiorCost(openList, n))) {
					n.setCost(u.getCost()+1);
					n.setHeurstic(n.getCost() + this.end.getX()-n.getX() + this.end.getY()-n.getY());
					openList.add(n);
				}
			}
			closedList.add(u);
			System.out.println(openList);
		}
		System.out.println("NOT FOUND");
		
	}
	
	public boolean contains(List<Node> list, Node n) {
		for (Node node : list) {
			if (node == n) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsInferiorCost(List<Node> list, Node n) {
		for (Node node : list) {
			if (node == n && node.getCost() < n.getCost()) {
				return true;
			}
		}
		return false;
	}
	
	
	public int compare2Nodes(Node n1, Node n2) {
		if (n1.getHeuristic() < n2.getHeuristic()) {
			return 1;
		}
		else if (n1.getHeuristic() == n2.getHeuristic()) {
			return 0;
		}
		else {
			return -1;
		}
	}

}
