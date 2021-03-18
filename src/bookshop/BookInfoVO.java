package bookshop;

public class BookInfoVO {
	private Long bookId;
	private String title;
	private String author;
	private String pubs;
	private String pubDate;
	
	
	
	/**
	 * @param bookId
	 * @param title
	 * @param author
	 * @param pubs
	 * @param pubDate
	 */
	public BookInfoVO(Long bookId, String title, String author, String pubs, String pubDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.pubs = pubs;
		this.pubDate = pubDate;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
