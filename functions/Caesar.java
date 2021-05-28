package functions;
 


public class Caesar {
	private String str;
	private String[] oneByone;
	private final String[] small 	= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private final String[] large	= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	//private final String[] frequency= {"e","a","t","i","o","s","n","r","h","l","d","c","u","m","p","f","g","y","w","b","v","k","j","x","q","z"};
	//public HashMap<String,Integer> hmap=new HashMap<String,Integer>();
	private int n;
	
	public Caesar(String str,int n) {//コンストラクタ
		this.str=str;
		this.n=n;
		oneByone=new String[str.length()];
		for(int i=0;i<this.str.length();i++) 
			oneByone[i]=this.str.substring(i,i+1);
	}
	
	private int indexOf(String str) {//アルファベットの順番
		if(!str.matches("[A-Za-z]")) {
			System.out.println("Error");
			return -1;
		}
		str=str.toLowerCase();
		for(int i=0;i<small.length;i++) {
			if(str.equals(small[i]))
				return i;
		}
		return -1;
	}
	
	public String shiftStr() {//n文字後にずらす
		String shifted="";
		for(int i=0;i<oneByone.length;i++) {
			if(oneByone[i].matches("[A-Z]")) 
				shifted+=large[(indexOf(oneByone[i])+n)%26];
			else if(oneByone[i].matches("[a-z]")) 
				shifted+=small[(indexOf(oneByone[i])+n)%26];
			else 
				shifted+=oneByone[i];
		}
		return shifted;
	}
	
	
	
}
