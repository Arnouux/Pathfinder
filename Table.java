package visualization;

public class Table {
	private int[][] table;
	private int size;
	
	public Table(int size) {
		this.table = new int[size][size];
		this.size = size;
	}
	
	public void show() {
		for(int i=0; i<this.table.length; i++) {
			for(int j=0; j<this.table[0].length; j++) {
				System.out.print(this.table[i][j]);
			}
			System.out.println();
		}
	}
	
	public void changeCase(int x, int y) {
		this.table[x][y] = this.table[x][y] == 0 ?  1 : 0;
	}
	
	public boolean checkPresence(int x) {
		for(int i=0; i<this.table.length; i++) {
			for(int j=0; j<this.table[0].length; j++) {
				if (this.table[i][j] == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void deleteOneType(int x) {
		for(int i=0; i<this.table.length; i++) {
			for(int j=0; j<this.table[0].length; j++) {
				if (this.table[i][j] == x) {
					this.table[i][j] = 0;
				}
			} 
		}
	}
	
	public void setStart(int x, int y) {
		this.table[x][y] = 2;
	}
	
	public void setWall(int x, int y) {
		this.table[x][y] = 1;
	}
	
	public void setNone(int x, int y) {
		this.table[x][y] = 0;
	}
	
	public void setEnd(int x, int y) {
		this.table[x][y] = 3;
	}
	
	public void setPossiblePath(int x, int y) {
		this.table[x][y] = 4;
	}
	
	public void setPath(int x, int y) {
		this.table[x][y] = 5;
	}

	
	public int getCase(int x, int y) {
		return this.table[x][y];
	}
	
	public Node getStart() {
		for(int i=0; i<this.table.length; i++) {
			for(int j=0; j<this.table[0].length; j++) {
				if(this.table[i][j] == 2) {
					return new Node(i,j);
				}
			}
		}
		return new Node(0,0);
	}
	
	public Node getEnd() {
		for(int i=0; i<this.table.length; i++) {
			for(int j=0; j<this.table[0].length; j++) {
				if(this.table[i][j] == 3) {
					return new Node(i,j);
				}
			}
		}
		return new Node(0,0);
	}
	
	public void resize(int size) {
		this.table = new int[size][size];
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
}
