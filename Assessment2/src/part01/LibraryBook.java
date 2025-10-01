package part01;

public class LibraryBook extends Book implements Lendable {
	private int id;
	private static int nextId = 0;
	private BookStatus status;
	private String coverImage;
	private int loanCount;
	
	public LibraryBook(String title, String author, String isbn, BookType type, int edition, String summary, double price, String coverImage) {
		super(title, author, isbn, type, edition, summary, price);
		this.coverImage= coverImage;
	}
	
	public boolean checkOut() {
		setStatus(BookStatus.ON_LOAN);
		return true;
	}
	
	public boolean checkIn() {
		setStatus(BookStatus.AVAILABLE);
		return true;
	}
	
	public void setId() {
		this.id = nextId;
		nextId++;
	}
	
	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public void setStatus(String status) {
		if (status == "Available") {
			setStatus(BookStatus.AVAILABLE);
		} else if (status == "On loan") {
			setStatus(BookStatus.ON_LOAN);
		} else if (status == "Withdrawn") {
			setStatus(BookStatus.WITHDRAWN);
		}
	}
	
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	
	public void setLoanCount() {
		loanCount++;
	}
	
	public int getId() {
		return this.id;
	}
	
	public BookStatus getStatus() {
		return status;
	}
	
	public String getCoverImage() {
		return this.coverImage;
	}
	
	public int getLoanCount() {
		return this.loanCount;
	}
	
	@Override
	public String toString() {
		String result = "Title: " + getTitle() + ", Author: " + getAuthor() + ", isbn: " + getIsbn() + ", Edition: "
				+ getEdition() + ", Summary: " + getSummary() + ", Price: £" + getPrice() + ", Cover Image: " + getCoverImage();
		return result;
	}
	
}
