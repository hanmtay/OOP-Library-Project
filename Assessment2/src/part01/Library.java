package part01;

import java.util.ArrayList;

public class Library {
	private ArrayList<LibraryBook> books;
	
	public Library() {
		books = new ArrayList<LibraryBook>();
	}
	
	public boolean borrowBook(int id) {
		LibraryBook bk = search(id);
		if (bk != null) {
			if (bk.getId() == id && bk.getStatus() == BookStatus.AVAILABLE) {
				bk.checkOut();
				bk.setLoanCount();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean returnBook(int id) {
		LibraryBook bk = search(id);
		if (bk != null) {
			if (bk.getId() == id && bk.getStatus() == BookStatus.ON_LOAN) {
				bk.checkIn();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public LibraryBook[] list() {
		LibraryBook[] listResult = new LibraryBook[books.size()];
		for(int index=0; index < listResult.length; index++) {
			listResult[index] = books.get(index);
		}
		return listResult;
	}
	
	public LibraryBook[] list(BookStatus status) {
		ArrayList<LibraryBook> requiredBooks;
		requiredBooks = new ArrayList<LibraryBook>();
		for (int index=0; index < books.size(); index++) {
			LibraryBook currentBook = books.get(index);
			if (currentBook.getStatus() == status) {
				requiredBooks.add(currentBook);
			}
		}
		LibraryBook[] statusResult = new LibraryBook[requiredBooks.size()];
		for(int index=0; index<statusResult.length; index++) {
			LibraryBook currentBook = requiredBooks.get(index);
				statusResult[index] = currentBook;
		}
		return statusResult;
	}
	
	public LibraryBook[] mostPopular() {
		LibraryBook[] mostPop = new LibraryBook[books.size()];
		mostPop = list();
		int swaps;
		do {
			swaps = 0;
			for(int i =0; i< mostPop.length-1; i++) {
				if (mostPop[i].getLoanCount() < mostPop[i+1].getLoanCount()) {
					LibraryBook temp = mostPop[i];
					mostPop[i] = mostPop[i+1];
					mostPop[i+1] = temp;
					swaps++;
				}
			}
		}
		while (swaps> 0);
		return mostPop;
	}
	
	public LibraryBook search(int id) {
		LibraryBook target = null;
		for (int index = 0; index < books.size(); index++) {
			LibraryBook bk = books.get(index);
			if (bk.getId() == id) {
				target = bk;
				break;
			}
		}
		return target;
	}
	
	public boolean add(LibraryBook bk) {
		if (bk != null) {
			LibraryBook existingBook = search(bk.getId());
			if (existingBook == null && bk.getPrice() > 0.0) {
				books.add(bk);
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(int id) {
		LibraryBook bk = search(id);
		if (bk != null) {
			return books.remove(bk);
		}
		else {
			return false;
		}
	}
	
}
