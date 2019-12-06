package farm.repository.query;

public class Pagination {

	int page;
	int size;
	
	private Pagination(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public static Pagination page(int page, int size) {
		
		if(page >= 0 && size > 0) {
			return new Pagination(page, size);			
		}
		
		return null;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
