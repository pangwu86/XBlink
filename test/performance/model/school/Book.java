package performance.model.school;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("book")
public class Book {

	private String bookName;

	private Map inners;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Map getInners() {
		return inners;
	}

	public void setInners(Map inners) {
		this.inners = inners;
	}

}
