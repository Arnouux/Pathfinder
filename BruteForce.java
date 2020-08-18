package visualization;

public class BruteForce {

	boolean stop_token = false;
	
	Node end;
	Drawer view;
	
	public BruteForce(Table table, Drawer view, ui ui) {
		this.view = view;
		Node current = table.getStart();
		end = table.getEnd();
		
		this.search(current, table);

	}
	
	private void search(Node current, Table table) {
		if (this.stop_token || (current.getX() == end.getX() && current.getY() == end.getY())) {
			stop_token = true;
			System.out.println("true foudn ahdjae");
			return;
		}
		else {
			for (Node neighbor : current.getNeighbors(table)) {
				table.setStart(neighbor.getX(), neighbor.getY());
				view.invalidate();
				view.repaint();
//				try {
//					Thread.sleep(100);
//				}
//				catch(InterruptedException e) {
//					
//				}
				search(neighbor, table);
			}
		}

	}
}
