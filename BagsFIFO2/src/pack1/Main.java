package pack1;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<Integer> ref_list = new ArrayList<Integer>() {{
			add(1);
			add(2);
			add(3);
			add(4);
			add(1);
			add(2);
			add(5);
			add(1);
			add(2);
			add(3);
			add(4);
			add(5);
		}};
		
		int page_frame =5;
		
		BagsFIFOLogic logic_1 = new BagsFIFOLogic(ref_list, page_frame);
		logic_1.startComputation();
		
		DataHolder dataholder = logic_1.getDataholder();
		
		logic_1.printAll();
		
	}
}
