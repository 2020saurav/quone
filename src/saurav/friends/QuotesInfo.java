package saurav.friends;

public class QuotesInfo {
	
	private String qid;
	private String quote;
	
	public QuotesInfo (String qid, String quote)
	{
		this.qid = qid;
		this.quote = quote;		
	}
	
	public String getQuote()
	{
		return quote;
	}
	public String getQid()
	{
		return qid;
	}
}
