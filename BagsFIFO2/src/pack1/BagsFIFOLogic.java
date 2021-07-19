package pack1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BagsFIFOLogic {
	private ArrayList<Integer> current_stack = new ArrayList<>();
	private ArrayList<Integer> current_stack_temp = new ArrayList<>();
	private ArrayList<Integer> back_stack = new ArrayList<>();
	private ArrayList<Integer> page_fault_number_list = new ArrayList<>();

	private DataHolder dataholder;

	private ArrayList<Integer> ref_list = new ArrayList<>();
	private HashMap<Integer, ArrayList<Integer>> row_content = new HashMap<>();
	private int page_frame = 0; // page frame

	private int page_fault_count = 0;
	private int _blank_id = -1;
	private int counter = 1;
	
	
	public BagsFIFOLogic(ArrayList<Integer> ref_list, int page_frame) {
		this.ref_list = ref_list;
		this.page_frame = page_frame;
		initRowStorage();
	}

	public void startComputation() {
		int iteration_max = ref_list.size(); // iteration count
		// main loop - brain
		for (int i = 0; i < iteration_max; i++) {
			Object current_item = ref_list.get(i);

			if (current_stack.size() < page_frame) {
				if (current_stack.contains(current_item)) {
					// page hit
					current_stack_temp = new ArrayList<>();
					for (int j = 0; j < current_stack.size(); j++) {
						current_stack_temp.add(_blank_id);
					}
					addToRowContent(current_stack_temp);
					back_stack.add((int) current_item);
					page_fault_number_list.add(-1);
				} else {
					current_stack.add((int) current_item);
					back_stack.add((int) current_item);
					addToRowContent(current_stack);
				}
			} else {
				if (current_stack.contains(current_item)) {
					// page hit
					current_stack_temp = new ArrayList<>();
					for (int j = 0; j < current_stack.size(); j++) {
						current_stack_temp.add(_blank_id);
					}
					addToRowContent(current_stack_temp);
					back_stack.add((int) current_item);
					page_fault_number_list.add(-1);
				} else {
					// page fault
					// implement back stack scanning

					page_fault_count++;
					page_fault_number_list.add(counter);
					counter++;

					int to_get_index = back_stack.size() - back_stack.size();

					Object to_remove = back_stack.get(to_get_index); // 2
					int index_update = current_stack.indexOf(to_remove); // 1
					current_stack.remove(to_remove);
					current_stack.add(index_update, (int) current_item);

					// removing previous
					back_stack.remove(to_get_index);
					back_stack.add((int) current_item);

					addToRowContent(current_stack);
				}
			}
		}
		dataholder = new DataHolder(this.ref_list, this.page_frame, this.page_fault_count, this.row_content, this.page_fault_number_list);
	}
	
	public DataHolder getDataholder() {
		return dataholder;
	}

	private void initRowStorage() {
		// init rows storage
		for (int i = 0; i < page_frame; i++) {
			row_content.put(i + 1, new ArrayList<>());
		}
	}

	public void printAll() {
		// printing column title
		for (Integer item : ref_list) {
			System.out.print(item + " ");
		}
		System.out.println();
		// printing part rows
		for (int i = 0; i < page_frame; i++) {
			ArrayList<String> space_container = new ArrayList<String>();
			String space = " ";
			ArrayList<String> converted = parseToStringArray(row_content.get(i + 1));
			int number_of_space = ref_list.size() - converted.size();
			for (int j = 0; j < number_of_space; j++) {
				space_container.add(space);
			}
			space_container.addAll(converted);

			for (String to_print : space_container) {
				System.out.print(to_print + " ");
			}
			System.out.println();
		}

		// page fault indicator
		for (Integer item : page_fault_number_list) {
			if (item < 0) {
				System.out.print(" ");
			} else {
				System.out.print(item);
			}
		}
	}

	private ArrayList<String> parseToStringArray(ArrayList<Integer> current_row) {
		ArrayList<String> container = new ArrayList<>();
		for (Integer item : current_row) {
			if (item < 0) {
				container.add(" ");
			} else {
				container.add(String.valueOf(item));
			}
		}
		return container;
	}

	private void addToRowContent(ArrayList<Integer> current_stack) {
		for (int i = 0; i < current_stack.size(); i++) {
			row_content.get(i + 1).add(current_stack.get(i));
		}
	}
}