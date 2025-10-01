package part02;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.ImageIcon;

import part01.BookStatus;
import part01.BookType;
import part01.Library;
import part01.LibraryBook;
//import part01.Menu;
import console.Console;

public class QUBLibrary {
	
	private static Library myLibrary;
	private static Menu02 appMenu;
	private static Console conMenu = new Console(true);
	

	private static Library initialise() {
		Library aLibrary = new Library(); //defining new library
		//defining initial library books
		LibraryBook bk1 = new LibraryBook("Mere Christianity", "C.S.Lewis", "0007461216", BookType.NON_FICTION, 1, "a powerful, rational case for the Christian faith.", 8.99, "Mere Christianity Cover.jpeg");
		LibraryBook bk2 = new LibraryBook("The Christian Life", "Sinclair B.Ferguson ", "1848712596", BookType.NON_FICTION, 1, "lays out what salvation is and what it looks like in a life lived for Christ.", 7.00, "The Christian Life cover.jpeg");
		LibraryBook bk3 = new LibraryBook("Finding the Right Hills to Die On", "Gavin Ortlund", "1433567423", BookType.NON_FICTION, 1, "The Case for Theological Triage ", 10.59, "Finding the right hills to die on cover.jpeg");
		LibraryBook bk4 = new LibraryBook("Gentle and Lowly", "Dane Ortlund", "1433566133", BookType.NON_FICTION, 1, "Explores Jesus’s Heart to Reveal His Tender Love for Sinners and Sufferers", 11.66, "Gentle and Lowly cover.jpeg");
		LibraryBook bk5 = new LibraryBook("An Open Door", "Maud Kells", "1912373645", BookType.NON_FICTION, 1, "A True Story of Courage in Congo", 8.99, "An Open Door cover.jpeg");
		
		//set the ID of each book
		bk1.setId();
		bk2.setId();
		bk3.setId();
		bk4.setId();
		bk5.setId();
		
		//set the status of each book
		bk1.setStatus(BookStatus.AVAILABLE);
		bk2.setStatus(BookStatus.ON_LOAN);
		bk3.setStatus(BookStatus.AVAILABLE);
		bk4.setStatus(BookStatus.ON_LOAN);
		bk5.setStatus(BookStatus.AVAILABLE);
		
		//add each initial book to the library
		aLibrary.add(bk1);
		aLibrary.add(bk2);
		aLibrary.add(bk3);
		aLibrary.add(bk4);
		aLibrary.add(bk5);
		return aLibrary;
	}

	//setting up menu 
	private static Menu02 setupMenu() {
		String options[] = { "List All Books", "List All Books by Status", "Add a Book", "Remove a Book", "Borrow a Book", "Return a Book", "Display Ranked List", "Exit" };
		Menu02 mnu = new Menu02("Library Manager", options, conMenu);
		return mnu;
	}

	//main method with switch case statement
	public static void main(String[] args) {
		//setting up menu output for console
		conMenu.setSize(1000,1000);
		conMenu.setVisible(true);
		conMenu.setTitle("QUB Library");
		//initialising myLibrary
		myLibrary = initialise();
		appMenu = setupMenu();
		boolean done = false;
		do {
			int choice = appMenu.getUserChoice();
			switch (choice) {
			case 1:
				listAllBooks();
				conMenu.clear(); //clear the console after every action so it doesn't get crowded
				break;
			case 2:
				listAllBooksStatus();
				conMenu.clear();
				break;
			case 3:
				addBook();
				conMenu.clear();
				break;
			case 4:
				removeBook();
				conMenu.clear();
				break;
			case 5:
				borrowBook();
				conMenu.clear();
				break;
			case 6:
				returnBook();
				conMenu.clear();
				break;
			case 7:
				rankedList();
				conMenu.clear();
				break;
			case 8:
				done = true;
				break;
			default:
				conMenu.println("Invalid Option");
				conMenu.println("Expecting an integer from the list above. Try again. \n");
				conMenu.clear();
			}
		} while (!done);
		conMenu.println("Goodbye!");
		conMenu.setVisible(false); //close console upon exit
	}

	//method to list all books
	private static void listAllBooks() {
		Console con = new Console(true); //new console for the list of all the books
		con.setSize(1000,1000);
		con.setVisible(true);
		con.setTitle("QUB Library");
		con.println("List All Books: \n");
		//check to make sure that there are actually books in the library
		if (myLibrary.list().length != 0) {
			for(LibraryBook bk: myLibrary.list()) {
				con.println("--->" + "Id: " + bk.getId() + ", " +bk.toString() + "\n");
			}
			con.println();
			con.println("\nHit ENTER to close console or type ID for details.");
			try {
				String text = con.readLn();
				text = text.trim(); //trim needed if the user inputs enter, it removes any blank space, so the length of 'enter' is zero
				//if an ID is entered, print its details
				if ( text.length()>0 ) {
					Scanner input = new Scanner(text);
					int id = input.nextInt();
					LibraryBook bk = myLibrary.search(id);
					//check to make sure that the id inputed actual exists in the library
					if (bk != null) {
						String imgPath = "Images/" + bk.getCoverImage(); 
						ImageIcon img = new ImageIcon(imgPath);
						con.println(img);
						con.println("ID:" + bk.getId());
						con.println("Title: " + bk.getTitle());
						con.println("Author: " + bk.getAuthor());
						con.println("Isbn: " + bk.getIsbn());
						con.println("Type: " + bk.getType());
						con.println("Edition: " + bk.getEdition());
						con.println("Summary: " + bk.getSummary());
						con.println("Price: " + bk.getPrice());
						con.println("\nHit ENTER to continue");
						con.readLn();
					}
					else {
						con.println("Invalid input, expected one of ID's show above.");
						con.println("\nHit ENTER to continue");
						con.readLn();
					}
				}
			} catch(Exception ex) {
				con.println("Invalid input, expected one of ID's show above.");
				con.println("\nHit ENTER to continue");
				con.readLn();
			}
			con.setVisible(false);
		} else {
			con.println("Sorry there are no books in the library!");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
		}
	}

	//method to list all books by a particular status
	private static void listAllBooksStatus() {
		Console con = new Console(true); //setting up new console for listing all books
		con.setSize(1000, 1000);
		con.setVisible(true);
		con.setTitle("QUB Library");
		con.setBgColour(Color.BLUE);
		con.setColour(Color.WHITE);
		con.setFont(new Font("Consolas", Font.BOLD, 20));
		con.println("Enter book status(either Available, On loan, or Withdrawn): ");
		String requiredState = con.readLn();
		//converting input to a book status
		BookStatus requiredStatus = null;
		if (requiredState.toLowerCase().equals("available")) {
			requiredStatus = BookStatus.AVAILABLE;
		} else if (requiredState.toLowerCase().equals("on loan")) {
			requiredStatus = BookStatus.ON_LOAN;
		} else if (requiredState.toLowerCase().equals("withdrawn")) {
			requiredStatus = BookStatus.WITHDRAWN;
		} else {
			con.println("Status not recognised. Expected: Available, On loan, Withdrawn");
			con.println("\nHit ENTER to close console");
			con.readLn();
			con.setVisible(false);
			return;
		}
		con.println("List All Book by status: " + requiredState + "\n");

		LibraryBook[] requiredBooks = myLibrary.list(requiredStatus);
		if (requiredBooks.length != 0) {
			for (LibraryBook bk : requiredBooks) {
				con.println("--->" + bk.toString() + "\n");
			}
			con.println("\n Hit ENTER to close console.");
			String text = con.readLn();
			text = text.trim();
			con.setVisible(false);
		} else {
			con.println("Sorry there are no books in the library with that status!");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
		}
		
	}

	//method to add a new book to the library
	private static void addBook() {
		Console con = new Console(true);
		con.setSize(1000, 1000);
		con.setVisible(true);
		con.setTitle("QUB Library");
		con.setFont(new Font("Consolas", Font.BOLD, 20));
		con.println("Add new Book.\n");
		
		//title
		con.print("--->Enter Title:");
		String title = con.readLn();
		if (title == null || title.length() < 5 || title.length() > 40) {
			con.println("Title does not meet required parameters.");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
		}
		
		//author
		con.print("--->Enter Author:");
		String author = con.readLn();
		if (author == null || author.length() < 5 || author.length() > 40) {
			con.println("Entered Author does not meet required parameters.");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
		}
		
		//ISBN
		con.print("--->Enter ISBN: ");
		String isbn = con.readLn();
		if (isbn == null || isbn.length() != 10) {
			con.println("Entered ISBN does not meet required parameters.");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
		}
		
		//type
		con.println("--->Enter type, either: Fiction, Non_fiction, or Refernce.");
		String typeStr = con.readLn();
		BookType type = null;
		try {
			type = BookType.valueOf(typeStr.toUpperCase());
		}
		catch (Exception ex) {
			con.println("Unable to add book, invalid Book Type. Expected: Fiction, Non_fiction, or Reference");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
		}
		
		//edition
		con.println("--->Enter edition:");
		String text = con.readLn();
		text = text.trim();
		Scanner input = new Scanner(text);
		int edition;
		try {
			edition = input.nextInt();
			if (edition <= 0 && edition == (int)edition) {
				edition = 1;
				con.println("A defualt value of 1 has been given to edition as it cannot be lower than that.");
			}
		} catch(Exception ex) {
			con.println("Invalid input. Edition must be an integer.");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
			
		}
		
		//summary
		con.println("--->Enter summary:");
		String summary = con.readLn();
		if (summary == null || summary.length() < 20 || summary.length() > 150) {
			con.println("Entered summary does not meet required parameters.");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
		}
		
		//price
		con.println("--->Enter price:");
		String txt = con.readLn();
		txt = txt.trim();
		Scanner input1 = new Scanner(txt);
		double price;
		try {
			price = input1.nextFloat();
			if (price < 0.0) {
				con.println("Entered price does not meet required parameters, it must be greater than £0.00.");
				con.println("\nHit ENTER to continue");
				con.readLn();
				con.setVisible(false);
				return;
			}
		} catch(Exception ex) {
			con.println("Invalid input. Edition must be an integer.");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
		}
		
		
		//cover image
		con.println("--->Enter coverImage:");
		String coverImage = con.readLn();
		
		//status
		con.println("--->Enter Status, Please enter either: Available, On loan, or Withdrawn:");
		String statusStr = con.readLn();
		BookStatus status = null;
		try {
			status = BookStatus.valueOf(statusStr.toUpperCase());
			con.println("OK, Book has been added!");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
		}
		catch (Exception ex) {
			con.println("Unable to add book, invalid Book Status. Expected: Available, On loan, or Withdrawn");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
			return;
		}
		
		//actually adding the new book to the library
		LibraryBook bk  = new LibraryBook(title, author, isbn, type, edition, summary, price, coverImage);
		bk.setId();
		bk.setStatus(status);
		bk.setType(typeStr);
		myLibrary.add(bk);
		return;
	}

	//method to remove a book
	private static void removeBook() {
		Console con = new Console(true);
		con.setSize(1000,1000);
		con.setVisible(true);
		con.setTitle("QUB Library");
		con.setBgColour(Color.RED);
		con.setColour(Color.BLACK);
		con.println("Remove a book.\n");
		if (myLibrary.list().length != 0) {
			for(LibraryBook bk: myLibrary.list()) {
				con.println("--->"+ "Id: " + bk.getId() + ", " + bk.toString() + "\n");
			}
			con.println();
			con.println("Choose book by ID to remove or hit enter to return to menu: ");
			try {
				String text = con.readLn();
				text = text.trim();
				if (text.length() > 0) {
					Scanner input = new Scanner(text);
					int idToRemove = input.nextInt();
					if (myLibrary.search(idToRemove)!= null) {
						LibraryBook bk = myLibrary.search(idToRemove);
						String imgPath = "Images/" + bk.getCoverImage(); 
						ImageIcon img = new ImageIcon(imgPath);
						con.println(img);
						con.println("ID:" + bk.getId());
						con.println("Title: " + bk.getTitle());
						con.println("Author: " + bk.getAuthor());
						con.println("Isbn: " + bk.getIsbn());
						con.println("Type: " + bk.getType());
						con.println("Edition: " + bk.getEdition());
						con.println("Summary: " + bk.getSummary());
						con.println("Price: " + bk.getPrice());
						con.println("\nHit ENTER to continue");
						con.readLn();
						con.println("Remove book by ID: " + idToRemove + "? \nEnter Y for yes and N for no.");
						con.println();
						try {
							String option = con.readLn();
							if (option.toUpperCase().equals("Y")) {
								myLibrary.remove(idToRemove);
								con.println("Book has been removed. \n");
							}
							else if (option.toUpperCase().equals("N")) {
								con.println("Ok! Book has not been removed.");
							}
							else {
								con.println("Invalid input. Expected Y or N");
							}
						} catch(Exception ex) {
							con.println("Invalid input. Expected either Y or N.");
							con.println("\nHit ENTER to continue");
							con.readLn();
						}
						
					}
					else {
						con.println("This book does not exist!");
					}
				}
				
			} catch(Exception ex) {
				con.println("Invalid input. Expected an ID from above.");
				con.println("\nHit ENTER to continue");
				con.readLn();
			}
			
			con.println("\n Hit ENTER to close console.");
			String txt = con.readLn();
			txt = txt.trim();
			con.setVisible(false);
		} else {
			con.println("Sorry there are no books in the library to remove!");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
		}
		
	}

	//method to borrow a book
	private static void borrowBook() {
		Console con = new Console(true);
		con.setSize(1000,1000);
		con.setVisible(true);
		con.setTitle("QUB Library");
		con.setBgColour(Color.RED);
		con.setColour(Color.WHITE);
		con.setFont( new Font("Consolas", Font.BOLD, 20) );
		con.println("Borrow a book. \n");
		if (myLibrary.list(BookStatus.AVAILABLE).length != 0) {
			for(LibraryBook bk: myLibrary.list(BookStatus.AVAILABLE)) {
				con.println("--->"+ "Id: " + bk.getId() + ", " + bk.toString() + "\n");
			}
			con.println();
			con.println("Choose book by ID to borrow hit enter to return to menu: ");
			try {
				String text = con.readLn();
				text = text.trim();
				if (text.length() > 0) {
					Scanner input = new Scanner(text);
					int idToBorrow = input.nextInt();
					if (myLibrary.search(idToBorrow)!= null) {
						LibraryBook bk = myLibrary.search(idToBorrow);
						String imgPath = "Images/" + bk.getCoverImage(); 
						ImageIcon img = new ImageIcon(imgPath);
						con.println(img);
						con.println("ID:" + bk.getId());
						con.println("Title: " + bk.getTitle());
						con.println("Author: " + bk.getAuthor());
						con.println("Isbn: " + bk.getIsbn());
						con.println("Type: " + bk.getType());
						con.println("Edition: " + bk.getEdition());
						con.println("Summary: " + bk.getSummary());
						con.println("Price: " + bk.getPrice());
						con.println("\nHit ENTER to continue");
						con.readLn();
						con.println("Borrow book by ID: " + idToBorrow + "? \nEnter Y for yes and N for no.");
						con.println();
						try {
							String option = con.readLn();
							if (option.toUpperCase().equals("Y")) {
								myLibrary.borrowBook(idToBorrow);
								con.println("Book has been borrowed. \n");
							}
							else if (option.toUpperCase().equals("N")) {
								con.println("Ok! Book has not been Borrowed.");
							}
							else {
								con.println("Invalid input. Expected Y or N");
							}
						} catch(Exception ex) {
							con.println("Invalid input. Expected either Y or N.");
							con.println("\nHit ENTER to continue");
							con.readLn();
						}
						
					}
					else {
						con.println("This book does not exist!");
					}
				}
				
			} catch(Exception ex) {
				con.println("Invalid input. Expected an ID from above.");
				con.println("\nHit ENTER to continue");
				con.readLn();
			}
			
			con.println("\n Hit ENTER to close console.");
			String txt = con.readLn();
			txt = txt.trim();
			con.setVisible(false);
		} else {
			con.println("Sorry there are no books in the library to borrow!");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
		}
		
	}

	//method to return a book
	private static void returnBook() {
		Console con = new Console(true);
		con.setSize(1000,1000);
		con.setVisible(true);
		con.setTitle("QUB Library");
		con.setFont( new Font("Consolas", Font.PLAIN , 20) );
		con.println("Return a book. \n");
		LibraryBook[] requiredBooks = myLibrary.list(BookStatus.ON_LOAN);
		if (requiredBooks.length != 0) {
			for (LibraryBook bk : requiredBooks) {
				con.println("--->" + "Id: "+ bk.getId() + ", " + bk.toString());
			}
			con.println();
			con.println("Choose book by ID to return or hit enter to return to menu: ");
			try {
				String text = con.readLn();
				text = text.trim();
				if (text.length() > 0) {
					Scanner input = new Scanner(text);
					int idToReturn = input.nextInt();
					if (myLibrary.search(idToReturn) != null) {
						LibraryBook bk = myLibrary.search(idToReturn);
						if (bk.getStatus().equals(BookStatus.ON_LOAN)) {
							String imgPath = "Images/" + bk.getCoverImage(); 
							ImageIcon img = new ImageIcon(imgPath);
							con.println(img);
							con.println("ID:" + bk.getId());
							con.println("Title: " + bk.getTitle());
							con.println("Author: " + bk.getAuthor());
							con.println("Isbn: " + bk.getIsbn());
							con.println("Type: " + bk.getType());
							con.println("Edition: " + bk.getEdition());
							con.println("Summary: " + bk.getSummary());
							con.println("Price: " + bk.getPrice());
							con.println("\nHit ENTER to continue");
							con.readLn();
							con.println("Return book by ID: " + idToReturn + "? \nEnter Y for yes and N for no.");
							con.println();
							try {
								String option = con.readLn();
								if (option.toUpperCase().equals("Y")) {
									myLibrary.returnBook(idToReturn);
									con.println("Book has been returned. \n");
								} 
								else if (option.toUpperCase().equals("N")) {
									con.println("Ok! Book has not been returned.");
								}
								else {
									con.println("Invalid input. Expected Y or N");
								}
							} catch (Exception ex) {
								con.println("Invalid input. Expected either Y or N.");
								con.println("\nHit ENTER to continue");
								con.readLn();
							}
						} else {
							con.println("This book is not on loan.");
						}
						
					} else {
						con.println("This book does not exist!");
					}
				}
				
			} catch(Exception ex) {
				con.println("Invalid input. Expected an ID from above.");
				con.println("\nHit ENTER to continue");
				con.readLn();
			}
			con.println("\n Hit ENTER to close console.");
			String txt = con.readLn();
			txt = txt.trim();
			con.setVisible(false);
		} else {
			con.println("Sorry there are no books in the library to return!");
			con.println("\nHit ENTER to continue");
			con.readLn();
			con.setVisible(false);
		}
		
	}

	//method to list the ranked list of the most popular books(the ones that have been loaned the most)
	private static void rankedList() {
		Console con = new Console(true);
		con.setSize(1000, 1000);
		con.setVisible(true);
		con.setTitle("QUB Library");
		con.println("Display Ranked List:");
		con.println("~~~~~~~~~~~~~~~~~~~~ \n");
		LibraryBook[] rankedlist = myLibrary.mostPopular();
		con.println("Most Popular:  ");
		for (LibraryBook bk : rankedlist) {
			con.println("--->" + "Id: " + bk.getId() + "  " + "Title: " + bk.getTitle() + ", Author: " + bk.getAuthor()
					+ ", Number of Times borrowed: " + bk.getLoanCount());
		}
		con.println();
		con.println("\nHit ENTER to close console or type ID for details.");

		try {
			String text = con.readLn();
			text = text.trim();
			if (text.length() > 0) {
				Scanner input = new Scanner(text);
				int id = input.nextInt();
				LibraryBook bk = myLibrary.search(id);
				if (bk != null) {
					String imgPath = "Images/" + bk.getCoverImage();
					ImageIcon img = new ImageIcon(imgPath);
					con.println(img);
					con.println("ID:" + bk.getId());
					con.println("Title: " + bk.getTitle());
					con.println("Author: " + bk.getAuthor());
					con.println("Isbn: " + bk.getIsbn());
					con.println("Type: " + bk.getType());
					con.println("Edition: " + bk.getEdition());
					con.println("Summary: " + bk.getSummary());
					con.println("Price: " + bk.getPrice());
					con.println("\nHit ENTER to ccontinue");
					con.readLn();
				} else {
					con.println("Invalid input, expected one of ID's show above.");
					con.println("\nHit ENTER to continue");
					con.readLn();
				}
			}
			con.setVisible(false);
		} catch (Exception ex) {
			con.println("Invalid input, expected one of ID's show above.");
			con.println("\nHit ENTER to continue");
			con.readLn();
		}
		con.setVisible(false);
	}
		
}