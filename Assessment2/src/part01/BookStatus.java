package part01;

public enum BookStatus {
	AVAILABLE("Available"), ON_LOAN("On loan"), WITHDRAWN("Withdrawn");

	private String bookStatusStr;

	private BookStatus(String status) {
		bookStatusStr = status;
	}

	public String toString() {
		return bookStatusStr;
	}

}
