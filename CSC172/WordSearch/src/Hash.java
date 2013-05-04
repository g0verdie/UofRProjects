public class Hash implements HashInterface
{
	//size of table
	private int size;
	//words to be inserted
	private LinkedList[] words;
	//the hash value
	private int hash1;
	
	public Hash(int s)
	{
		//twice the size of input to ensure even distribution
		size = s*2;
		//the table is an array of linked lists, each must be initialized
		words = new LinkedList[size];
		for(int i = 0; i < words.length; i++)
			words[i] = new LinkedList();
		hash1 = size;
	}
	
	//insert a word into the hash table
	public void insert(String input)
	{
		//convert to integer value for hashing
		int sum = str2Int(input);
		//hash and insert
		int hold1 = sum % hash1;
		words[hold1].insert(input);
	}
	
	//find a variable
	public int lookup(String key)
	{
		//convert to integer value for hashing
		int sum = str2Int(key);
		//find hash area
		int hold1 = sum % hash1;
		//return place of word
		if (words[hold1].lookUp(key,words[hold1].callHead())) 
			return hold1;
		//if not found, return -1
		else
			return -1;
	}
	
	//convert the word to a numeric value for hashing
	public int str2Int(String str)
	{
		int product = 1;
		//for each letter in the word
		for(int i = 0; i < str.length(); i++)
		//if its a capital, treat it as lower case
			if(65 <= (int)str.charAt(i) && (int)str.charAt(i) <= 90)
			{
				int ctoi = (int)str.charAt(i);
				ctoi += 32;
				ctoi*= i+1;
				product += ctoi;
			}
			else
			product += (int)23*str.charAt(i) * (i+53);
		//take the absolute value of the product
		product = Math.abs(product);
		//Main.print(product);
		return product;
	}
}
