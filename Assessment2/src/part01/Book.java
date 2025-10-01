package part01;

public class Book {
	private String title;
	private String author;
	private String isbn;
	private BookType type;
	private int edition;
	private String summary;
	private double price;

	public Book(String title, String author, String isbn, BookType type, int edition, String summary, double price) {
		setTitle(title);
		setAuthor(author);
		setIsbn(isbn);
		setType(type);
		setEdition(edition);
		setSummary(summary);
		setPrice(price);
	}

	public void setTitle(String title) {
		if (title != null && title.length() >= 5 && title.length() <= 40) {
			this.title = title;
		} else {
			if (this.title == null || title.length() < 5 || title.length() > 40) {
				// default value
				this.title = "No Title";
			}
		}
	}

	public void setAuthor(String author) {
		if (author != null && author.length() >= 5 && author.length() <= 40) {
			this.author = author;
		} else {
			if (this.author == null || author.length() < 5 || author.length() > 40) {
				// default value
				this.author = "No Author";
			}
		}
	}

	public void setIsbn(String isbn) {
		if (isbn != null && isbn.length() == 10) {
			this.isbn = isbn;
		} else {
			if (this.isbn == null || isbn.length() != 10) {
				// default value
				this.isbn = "No isbn";
			}
		}
	}

	public void CheckDigits() {
		for (int i = 0; i < this.isbn.length(); i++) {
			if (!Character.isDigit(this.isbn.charAt(i))) {
				this.isbn = "No isbn";
			}
		}
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public void setType(String type) {
		if (type == "Fiction") {
			setType(BookType.FICTION);
		} else if (type == "Non_fiction") {
			setType(BookType.NON_FICTION);
		} else if (type == "Reference") {
			setType(BookType.REFERENCE);
		}
	}

	public void setEdition(int edition) {
		if (edition >= 1) {
			this.edition = edition;
		} else {
			if (this.edition <= 0) {
				// Default value
				this.edition = 1;
			}
		}
	}

	public void setSummary(String summary) {
		if (summary != null && summary.length() > 20 && summary.length() <= 150) {
			this.summary = summary;
		} else {
			if (this.summary == null || summary.length() < 20 || summary.length() > 150) {
				// default value
				this.summary = "No Summary";
			}
		}

	}

	public void setPrice(double price) {
		if (price >= 0.0) {
			this.price = price;
		} else {
			if (price < 0.0) {
				this.price = 0.0;
			}
		}
	}

	public String getTitle() {
		return this.title;
	}

	public String getIsbn() {
		CheckDigits();
		return this.isbn;
	}
	
	public String getType() {
		return type.toString();
	}

	public String getAuthor() {
		return this.author;
	}

	public int getEdition() {
		return this.edition;
	}

	public String getSummary() {
		return this.summary;
	}

	public double getPrice() {
		return this.price;
	}

	public String toString() {
		String result = "Title: " + getTitle() + ", Author: " + getAuthor() + ", isbn: " + getIsbn() + ", Edition: "
				+ getEdition() + ", Summary: " + getSummary() + ", Price: £" + getPrice();
		return result;
	}

}
