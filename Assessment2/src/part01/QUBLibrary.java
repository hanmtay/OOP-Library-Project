package part01;

import java.util.Scanner;

public class QUBLibrary {

	private static Library myLibrary; 
	private static Scanner input = new Scanner(System.in);
	private static Menu appMenu;

	private static Library initialise() {
		Library aLibrary = new Library(); //defining new library
		//defining the initial books
		LibraryBook bk1 = new LibraryBook("Mere Christianity", "C.S.Lewis", "0007461216", BookType.NON_FICTION, 1, "a powerful, rational case for the Christian faith.", 8.99, "Mere Christianity Cover.jpeg");
		LibraryBook bk2 = new LibraryBook("The Christian Life", "Sinclair B.Ferguson ", "1848712596", BookType.NON_FICTION, 1, "lays out what salvation is and what it looks like in a life lived for Christ.", 7.00, "The Christian Life cover.jpeg");
		LibraryBook bk3 = new LibraryBook("Finding the Right Hills to Die On", "Gavin Ortlund", "1433567423", BookType.NON_FICTION, 1, "The Case for Theological Triage ", 10.59, "Finding the right hills to die on cover.jpeg");
		LibraryBook bk4 = new LibraryBook("Gentle and Lowly", "Dane Ortlund", "1433566133", BookType.NON_FICTION, 1, "Explores Jesus’s Heart to Reveal His Tender Love for Sinners and Sufferers", 11.66, "Gentle and Lowly cover.jpeg");
		LibraryBook bk5 = new LibraryBook("An Open Door", "Maud Kells", "1912373645", BookType.NON_FICTION, 1, "A True Story of Courage in Congo", 8.99, "An Open Door cover.jpeg");
		
		//set id for each initial book
		bk1.setId();
		bk2.setId();
		bk3.setId();
		bk4.setId();
		bk5.setId();
		
		//set the status of each initial book
		bk1.setStatus(BookStatus.AVAILABLE);
		bk2.setStatus(BookStatus.AVAILABLE);
		bk3.setStatus(BookStatus.AVAILABLE);
		bk4.setStatus(BookStatus.AVAILABLE);
		bk5.setStatus(BookStatus.AVAILABLE);
		

		//adding each book to the library
		aLibrary.add(bk1);
		aLibrary.add(bk2);
		aLibrary.add(bk3);
		aLibrary.add(bk4);
		aLibrary.add(bk5);
		return aLibrary;
	}

	//setting up menu
	private static Menu setupMenu() {
		String options[] = { "List All Books", "List All Books by Status", "Add a Book", "Remove a Book", "Borrow a Book", "Return a Book", "Display Ranked List", "Exit" };
		Menu mnu = new Menu("Library Manager", options);
		return mnu;
	}

	public static void main(String[] args) {
		myLibrary = initialise();
		appMenu = setupMenu();
		boolean done = false;
		do {
			int choice = appMenu.getUserChoice();
			switch (choice) {
			case 1:
				listAllBooks();
				break;
			case 2:
				listAllBooksStatus();
				break;
			case 3:
				addBook();
				break;
			case 4:
				removeBook();
				break;
			case 5:
				borrowBook();
				break;
			case 6:
				returnBook();
				break;
			case 7:
				rankedList();
				break;
			case 8:
				done = true;
				break;
			default:
				System.out.println("Invalid Option");
				System.out.println("Expecting an integer from the list above. Try again. \n");
			}
		} while (!done);
		System.out.println("Goodbye!");
	}

	// method to list all books in the library
	private static void listAllBooks() {
		System.out.println("List All Books.\n");
		// for each library book in the library, print out its details
		if (myLibrary.list().length != 0) {
			for (LibraryBook bk : myLibrary.list()) {
				System.out.println("--->" + "Id: " + bk.getId() + ", " + bk.toString());
			}
		} else {
			System.out.println("There are no books in the library!");
		}
		System.out.println();
	}

	// method to list all books by a particular status inputed by the user
	private static void listAllBooksStatus() {
		System.out.println("Enter book status of either available, on loan, or withdrawn: ");
		String requiredState = input.nextLine();
		//converting input to a book status
		BookStatus requiredStatus = null;
		if (requiredState.toLowerCase().equals("available")) {
			requiredStatus = BookStatus.AVAILABLE;
		} else if (requiredState.toLowerCase().equals("on loan")) {
			requiredStatus = BookStatus.ON_LOAN;
		} else if (requiredState.toLowerCase().equals("withdrawn")) {
			requiredStatus = BookStatus.WITHDRAWN;
		} else {
			System.out.println("Status not recognised. Expected: Available, On loan, Withdrawn");
			return;
		}
		System.out.println("List All Book by status: " + requiredState + "\n");

		LibraryBook[] requiredBooks = myLibrary.list(requiredStatus);
		// for each library book with the inputed status, print out its details
		if (requiredBooks.length != 0) {
			for (LibraryBook bk : requiredBooks) {
				System.out.println("--->" + bk.toString());
			}
		} else {
			System.out.println("There are no books currerntly with this status.");
		}
		
		System.out.println();
	}

	// method to add a book to the library
	private static void addBook() {
		System.out.println("Add new Book.\n");
		// title
		System.out.print("--->Enter Title:");
		String title = input.nextLine();
		if (title == null || title.length() < 5 || title.length() > 40) {
			System.out.println("Title does not meet required parameters.");
			return;
		}

		// author
		System.out.print("--->Enter Author:");
		String author = input.nextLine();
		if (author == null || author.length() < 5 || author.length() > 40) {
			System.out.println("Entered Author does not meet required parameters.");
			return;
		}

		// ISBN
		System.out.print("--->Enter ISBN:");
		String isbn = input.nextLine();
		if (isbn == null || isbn.length() != 10) {
			System.out.println("Entered ISBN does not meet required parameters.");
			return;
		}

		// type
		System.out.println("--->Enter type, either: Fiction, Non_fiction, or Reference.");
		String typeStr = input.nextLine();
		BookType type = null;
		try {
			type = BookType.valueOf(typeStr.toUpperCase());
		} catch (Exception ex) {
			System.out.println("Unable to add book, invalid Book Type. Expected: Fiction, Non_fiction, or Reference");
			return;
		}

		// edition
		System.out.println("--->Enter edition:");
		int edition; 
		try {
			edition = input.nextInt();
			if (edition <= 0) {
				edition = 1;
				System.out.println("A defualt value of 1 has been given to edition as it cannot be lower than that.");
			}
		}catch(Exception ex) {
			System.out.println("Invalid input. Edition must be an integer.");
			return;
		}
		
		input.nextLine();

		// summary
		System.out.println("--->Enter summary:");
		String summary = input.nextLine();
		if (summary == null || summary.length() < 20 || summary.length() > 150) {
			System.out.println("Entered summary does not meet required parameters.");
			return;
		}

		// price
		System.out.println("--->Enter price:");
		double price ;
		try {
			price = input.nextFloat();
			if (price < 0.0) {
				System.out.println("Entered price does not meet required parameters. It must be greater than £0.00");
			}
		} catch(Exception ex) {
			System.out.println("Invalid input. Edition must be a number.");
			return;
		}
		input.nextLine();

		// cover image
		System.out.println("--->Enter coverImage:");
		String coverImage = input.nextLine();

		// status
		System.out.println("--->Enter Status, Please enter either: Available, On loan, or Withdrawn:");
		String statusStr = input.nextLine();
		BookStatus status = null;
		try {
			status = BookStatus.valueOf(statusStr.toUpperCase());
		} catch (Exception ex) {
			System.out.println("Unable to add book, invalid Book Status. Expected: Available, On loan, or Withdrawn");
			return;
		}

		// actually adding the new book to the library
		LibraryBook bk = new LibraryBook(title, author, isbn, type, edition, summary, price, coverImage);
		bk.setId();
		bk.setStatus(status);
		bk.setType(typeStr);
		myLibrary.add(bk);
	}

	// method to remove a book
	private static void removeBook() {
		System.out.println("Remove a book.\n");
		if (myLibrary.list().length != 0) {
			for (LibraryBook bk : myLibrary.list()) {
				System.out.println("--->" + "Id: " + bk.getId() + ", " + bk.toString());
			}
			System.out.println();
			System.out.println("Choose book by ID to remove: ");
			try {
				int idToRemove = input.nextInt();
				if (myLibrary.search(idToRemove) != null) {
					System.out.println("Remove book by ID: " + idToRemove + "? \nEnter Y for yes and N for no.");
					try {
						input.nextLine();
						String option = input.next();
						if (option.toUpperCase().equals("Y")) {
							myLibrary.remove(idToRemove);
							System.out.println("Book has been removed. \n");
						} else if (option.toUpperCase().equals("N")) {
							System.out.println("Ok! Book has not been removed.");
						}

						else {
							System.out.println("Invalid input. Expected Y or N");
						}
					} catch (Exception ex) {
						System.out.println("Invalid input. Expected either Y or N.");
						return;
					}
				} else {
					System.out.println("This book does not exist!");
				}

			} catch (Exception ex) {
				System.out.println("Invalid input. Expecting an integer.");
				return;
			}

		} else {
			System.out.println("Sorry, there are no books in the library to remove.");
			return;
		}

	}

	// method to borrow a book
	private static void borrowBook() {
		System.out.println("Borrow a book. \n");
		if (myLibrary.list(BookStatus.AVAILABLE).length != 0) {
			for (LibraryBook bk : myLibrary.list(BookStatus.AVAILABLE)) {
				System.out.println("--->" + "Id: " + bk.getId() + ", " + bk.toString());
			}
			System.out.println();
			System.out.println("Choose book by ID to borrow: ");
			try {
				int idToBorrow = input.nextInt();
				if (myLibrary.search(idToBorrow) != null) {
					System.out.println("Borrow book by ID: " + idToBorrow + "? \nEnter Y for yes and N for no.");
					input.nextLine();
					try {
						String option = input.next();
						if (option.toUpperCase().equals("Y")) {
							myLibrary.borrowBook(idToBorrow);
							System.out.println("Book has been borrowed. \n");
						} else if (option.toUpperCase().equals("N")) {
							System.out.println("Ok! Book has not been Borrowed.");
						} else {
							System.out.println("Invalid input. Expected Y or N");
						}
					} catch (Exception ex) {
						System.out.println("Invalid input. Expected either Y or N.");
						return;
					}
				} else {
					System.out.println("This book does not exist!");
				}

			} catch (Exception ex) {
				System.out.println("Invalid input. Expecting an integer.");
				return;
			}
		} else {
			System.out.println("Sorry, there are currently no books available to borrow.");
		}
	}

	// method to return a book
	private static void returnBook() {
		System.out.println("Return a book. \n");
		LibraryBook[] requiredBooks = myLibrary.list(BookStatus.ON_LOAN);
		// only printing the books that are of status on loan
		if (myLibrary.list(BookStatus.ON_LOAN).length != 0) {
			for (LibraryBook bk : requiredBooks) {
				System.out.println("--->" + "Id: " + bk.getId() + ", " + bk.toString());
			}
			System.out.println();
			System.out.println("Choose book by ID to return: ");
			try {
				int idToReturn = input.nextInt();
				if (myLibrary.search(idToReturn) != null) {
					System.out.println("Return book by ID: " + idToReturn + "? \nEnter Y for yes and N for no.");
					input.nextLine();
					try {
						String option = input.next();
						if (option.toUpperCase().equals("Y")) {
							myLibrary.returnBook(idToReturn);
							System.out.println("Book has been returned. \n");
						} else if (option.toUpperCase().equals("N")) {
							System.out.println("Ok! Book has not been returned.");
						} else {
							System.out.println("Invalid input. Expected Y or N");
						}
					} catch (Exception ex) {
						System.out.println("Invalid input. Expected either Y or N.");
						return;
					}

				} else {
					System.out.println("This book does not exist!");
				}

			} catch (Exception ex) {
				System.out.println("Invalid input. Expecting an integer.");
				return;
			}

		} else {
			System.out.println("Sorry, there are currently no books to reutrn!");
			return;
		}

	}

	//method to return a ranked list of the most popular books(the ones that have been loaned the most)
	private static void rankedList() {
		System.out.println("Display Ranked List.");
		LibraryBook[] rankedlist = myLibrary.mostPopular();
		System.out.println("---> Most Popular: ");
		for (LibraryBook bk : rankedlist) {
			System.out.println("Title: " + bk.getTitle() + ", Author: " + bk.getAuthor() + ", Number of Times borrowed: " + bk.getLoanCount() + "\n");
		}
	}
}