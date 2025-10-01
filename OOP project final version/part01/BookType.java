package part01;

public enum BookType {
	FICTION("Fiction"), NON_FICTION("Non_fiction"), REFERENCE("Reference");

	private String bookTypeStr;

	private BookType(String type) {
		bookTypeStr = type;
	}

	public String toString() {
		return bookTypeStr;
	}

}
