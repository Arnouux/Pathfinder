package visualization;

import java.util.ArrayList;
import java.util.List;

public class A_star {

	
	private Node start;
	private Node end;
	
	public A_star(Table table, Drawer view) {

		this.start = table.getStart();
		this.end = table.getEnd();
		
		List<Node> openSet = new ArrayList<Node>();
		List<Node> closedSet = new ArrayList<Node>();
		// https://stackoverflow.com/questions/5601889/unable-to-implement-a-star-in-java
		start.setG(0);
		start.setHeurstic(estimateDistance(start, end));
		start.setF(start.getHeuristic());

		openSet.add(start);
		
		while (true) {
			Node current = null;
			
			if (openSet.size() == 0) {
				throw new RuntimeException("no route");
			}
			
			for (Node node : openSet) {
				if (current == null || node.getF() < current.getF()) {
					current = node;
				}
			}

			//System.out.println(current.getX() + "  " +  current.getY());
			if (current.getX() == this.end.getX() && current.getY() == this.end.getY()) {
				end.setParent(current);
				System.out.println("found");
				break;
			}
			
			openSet.remove(current);
			closedSet.add(current);
			
			for (Node neighbor : current.getNeighbors(table)) {
				
//				int nextG = current.getG() + neighbor.getCost();
//				
//				if (nextG < neighbor.getG()) {
//					openSet.remove(neighbor);
//					closedSet.remove(neighbor);
//				}
				
				if (!(containsInferiorCost(openSet, neighbor) && closedSet.contains(neighbor))) {
					neighbor.setCost(current.getCost()+1);
//					neighbor.setG(nextG);
					neighbor.setHeurstic(estimateDistance(neighbor, end));
//					neighbor.setF(neighbor.getG() + neighbor.getHeuristic());
					neighbor.setParent(current);
					openSet.add(neighbor);
					table.setStart(current.getX(), current.getY());
					view.invalidate();
					view.repaint();
				}
			}
		}
		
		List<Node> nodes = new ArrayList<Node>();
		Node current = end;
		while (current.getParent() != null) {
			nodes.add(current);
			current = current.getParent();
			table.setPath(current.getX(), current.getY());
		}
		table.setEnd(end.getX(), end.getY());
		nodes.add(start);
		
		
		// FRENCH WIKI
//		List<Node> closedList = new ArrayList<Node>();
//		List<Node> openList = new ArrayList<Node>();
//		openList.add(this.start);
//		
//		while (!(openList.isEmpty())) {
//			Node u = openList.get(openList.size() - 1);
//			openList.remove(openList.size() - 1);
//			if (u.getX() == end.getX() || u.getY() == end.getY()) {
//				System.out.println("found");
//				
//				List<Node> totalPath = new ArrayList<Node>();
//				totalPath.add(u);
//
//				System.out.println(totalPath);
//				
//				break;
//			}
//			
//			List<Node> neighbors = u.getNeighbors(table);
//			for (Node n : neighbors) {
//				if (!(closedList.contains(n) || containsInferiorCost(openList, n))) {
//					n.setCost(u.getCost()+1);
//					n.setHeurstic(n.getCost() + this.end.getX()-n.getX() + this.end.getY()-n.getY());
//					openList.add(n);
//				}
//			}
//			closedList.add(u);
//			System.out.println(openList);
//		}
//		System.out.println("NOT FOUND");
//		
	}
	
//	public boolean contains(List<Node> list, Node n) {
//		for (Node node : list) {
//			if (node == n) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
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
	
	public int estimateDistance(Node node1, Node node2) {
	    return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
	}

}
