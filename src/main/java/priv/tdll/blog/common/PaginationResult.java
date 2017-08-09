package priv.tdll.blog.common;

import java.util.LinkedList;

public class PaginationResult<T> {
	private long totalItem;
	private LinkedList<T> list = new LinkedList<>();

	public long getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(long totalItem) {
		this.totalItem = totalItem;
	}

	public LinkedList<T> getList() {
		return list;
	}

	public void setList(LinkedList<T> list) {
		this.list = list;
	}

}
