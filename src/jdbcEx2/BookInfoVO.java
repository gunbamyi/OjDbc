package jdbcEx2;

public class BookInfoVO {

	// b.book_id, b.title, b.pubs, b.pub_date, a.author_name
		private Long bookId;
		private String title;
		private String pubs;
		private String pubDate;
		private String authorName;
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
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		
		

	}


