package queries;

public interface InformationInterface {

	public void execute();
	
	public int numColumns();
	
	public String getColumnName(int index);
	
	public void close();
}
