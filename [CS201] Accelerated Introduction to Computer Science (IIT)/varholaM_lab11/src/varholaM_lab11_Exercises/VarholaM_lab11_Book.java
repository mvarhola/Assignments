/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/12/2015
 * 	Program Name: Book class (Lab 10 Activity)
 */

package varholaM_lab11_Exercises;

public class VarholaM_lab11_Book{
	private String title, author, publisher,category,price;
	private VarholaM_lab11_ISBN10 isbn;
	private int numInStock;
	
	public VarholaM_lab11_Book(){
		this.title = "";
		this.author = "";
		this.isbn = new VarholaM_lab11_ISBN10("0");
		this.publisher = "";
		this.category = "";
		this.price = "";
		this.numInStock = 0;
	}
	
	public VarholaM_lab11_Book(String title, String author, VarholaM_lab11_ISBN10 isbn, String publisher, String category, String price, int numInStock){
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.category = category;
		this.price = price;
		this.numInStock = numInStock;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public VarholaM_lab11_ISBN10 getIsbn() {
		return isbn;
	}

	public void setIsbn(VarholaM_lab11_ISBN10 isbn) {
		this.isbn = isbn;
	}

	public int getNumInStock() {
		return numInStock;
	}

	public void setNumInStock(int numInStock) {
		this.numInStock = numInStock;
	}

	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher="
				+ publisher + ", category=" + category + ", price=" + price
				+ ", isbn=" + isbn + ", numInStock=" + numInStock + "]";
	}

	public int compareTo(VarholaM_lab11_Book book){
		if(getAuthor().compareTo(book.getAuthor()) > 0){
			return 1;
		}else if (getAuthor().compareTo(book.getAuthor()) < 0){
			return -1;
		}else{
			return 0;
		}
	}

	
}
