package com.axisdesktop.poi.helper;

import java.util.ArrayList;
import java.util.List;

public class BaseRequestBody {
	private int start = 0;
	private int limit = 50;
	private List<Sorter> sorters = new ArrayList<>();

	public int getStart() {
		return start;
	}

	public void setStart( int start ) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit( int limit ) {
		this.limit = limit;
	}

	public List<Sorter> getSorters() {
		return sorters;
	}

	public void setSorters( List<Sorter> sorters ) {
		this.sorters = sorters;
	}

	@Override
	public String toString() {
		return "BaseRequestBody [start=" + start + ", limit=" + limit + ", sorters=" + sorters + "]";
	}

}
