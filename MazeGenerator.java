package visualization;

import java.util.concurrent.ThreadLocalRandom;

public class MazeGenerator {

	
	static public void generate(Table table) {
		System.out.println( table.getSize());
		
		int size = table.getSize()/2;
		System.out.println(size);
		int[] maze = new int[size*size];
		int[] walls = new int[size*size*2];
		
		for(int i=0; i<size*size; i++) {
			maze[i] = i;
		}
		for(int i=0; i<size*size*2; i++) {
			walls[i] = 1;
		}
		
		int nb_cells = size*size;
		int nb_walls = nb_cells * 2;

		int[] table_random = new int[nb_walls];
		int c1 = 0;
		int c_case = 0;

		for (int i=0; i<nb_cells; ++i) {

			// pass max-east and max-south walls
			if (((i+1) % size) != 0) {
				table_random[c_case] = c1;
				c_case++;
			}
			++c1;
			if (i < nb_cells-size) {
				table_random[c_case] = c1;
				c_case++;
			}
			++c1;
		}
		int nb_case_random = c_case;

		// Shuffle
		for (int i=0; i<nb_case_random-2; ++i) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, size*size*2 + 1);
			int nb = randomNum % nb_case_random;

			int var = table_random[i];
			table_random[i] = table_random[nb];
			table_random[nb] = var;
		}

		// Pick a wall, check values, change values
		int c = 0;
		for(int c_tot=0; c_tot<(nb_cells-1); ++c_tot) {
			int end = 0;
			while (end == 0) {
				if (c >= nb_case_random)
					c = 0;
				int nb = table_random[c];
				if (nb%2 != 0) {
					if (maze[nb/2] != maze[(nb/2)+size]) {
						walls[nb] = 0;
						int val_min = maze[nb/2];
						int val_max = maze[(nb/2)+size];
						if (val_min > maze[(nb/2)+size]) {
							val_min = maze[(nb/2)+size];
							val_max = maze[(nb/2)];
						}
						for (int i=0; i<nb_cells; ++i) {
							if (maze[i] == val_max)
								maze[i] = val_min;
						}
						end = 1;
					}
				}
				else {
					if (maze[nb/2] != maze[(nb/2)+1]) {
						walls[nb] = 0;
						int val_min = maze[nb/2];
						int val_max = maze[(nb/2)+1];
						if (val_min > maze[(nb/2)+1]) {
							val_min = maze[(nb/2)+1];
							val_max = maze[(nb/2)];
						}
						for (int i=0; i<nb_cells; ++i) {
							if (maze[i] == val_max)
								maze[i] = val_min;
						}
						end = 1;
					}
				}
				c++;
				nb = table_random[c];
			}
			c++;
		}
		int oddOrNot = (table.getSize())%2;
		for (int i=0; i<size*2+oddOrNot; i++) {
			for (int j=0; j<size*2+oddOrNot; j++) {
				table.setWall(i, j);
			}
		}

		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				table.setNone(i*2+oddOrNot, j*2+oddOrNot);
				if (walls[(i*size+j)*2] != 1) {
					table.setNone(i*2+oddOrNot, j*2+1+oddOrNot);
				}
			}
			for (int j=0; j<size; j++) {
				if (walls[(i*size+j)*2+1] != 1)
					table.setNone(i*2+1+oddOrNot, j*2+oddOrNot);
			}
		}
	table.setStart(0, 1);
	table.setEnd(size*2-1+oddOrNot, size*2-2+oddOrNot);
	}
}
