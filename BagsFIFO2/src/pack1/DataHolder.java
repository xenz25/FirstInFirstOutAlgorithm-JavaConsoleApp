package pack1;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHolder {
	private ArrayList<Integer> ref_list = new ArrayList<>();
	private int page_frame = 0;
	private int page_fault = 0;
	private HashMap<Integer, ArrayList<Integer>> row_content = new HashMap<>();
	private ArrayList<Integer> page_number_list = new ArrayList<>();
	
	
	
	public DataHolder(ArrayList<Integer> ref_list, int page_frame, int page_fault,
			HashMap<Integer, ArrayList<Integer>> row_content, ArrayList<Integer> page_number_list) {
		super();
		this.ref_list = ref_list;
		this.page_frame = page_frame;
		this.page_fault = page_fault;
		this.row_content = row_content;
		this.page_number_list = page_number_list;
	}

	public ArrayList<Integer> getRef_list() {
		return ref_list;
	}



	public int getPage_frame() {
		return page_frame;
	}



	public int getPage_fault() {
		return page_fault;
	}



	public HashMap<Integer, ArrayList<Integer>> getRow_content() {
		return row_content;
	}



	public ArrayList<Integer> getPage_number_list() {
		return page_number_list;
	}
	
}
