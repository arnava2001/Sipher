import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Sipher {
	final static String ALPHA = "abcdefghijklmnopqrstuvwxyz"; 
	final static String NUM = "123456789";
	
	private String message; 
	
	public Sipher()
	{
		message = ""; 
	}
	
	public void setMessage(String m)
	{
		message = m; 
	}
	
	//ROTATION CIPHER ENCODING
	public String encodeROT(int rot, String m)
	{
		String ret =  ""; 
		m = m.toLowerCase(); 
		for(int i = 0; i<m.length(); i++)
		{
			if(ALPHA.contains(m.charAt(i)+""))
			{
				int indexOne = ALPHA.indexOf(m.charAt(i));
				int newIndex = (indexOne + rot)%ALPHA.length(); 
				ret+=ALPHA.charAt(newIndex); 
			}
			else if(m.charAt(i) == ' ')
			{
				ret+=" "; 
			}
			else
			{
				System.out.println("Sorry, A-Z characters only");
				System.exit(0); 
			}
		}
		return ret; 
	}
	
	//ROTATION CYPHER DECODING
	public String decodeROT(int rot, String m)
	{
		m = m.toLowerCase(); 
		String ret = ""; 
		for(int i = 0; i<m.length(); i++)
		{
			if(ALPHA.contains(m.charAt(i)+""))
			{
				int indexOne = ALPHA.indexOf(m.charAt(i));
				int newIndex = indexOne - rot; 
				if(newIndex < 0)
				{
					newIndex = addToPositive(newIndex); 
				}
				ret+=ALPHA.charAt(newIndex); 
			}
			else if(m.charAt(i) == ' ')
			{
				ret+=" "; 
			}
			else
			{
				System.out.println("Sorry, A-Z characters only");
				System.exit(0); 
			}
		}
		return ret; 
	}
	
	//private helper method to add length of ALPHA until a number is positive again
	private static int addToPositive(int num)
	{
		if(num>=0)
			return num;
		else
			return addToPositive(num + ALPHA.length()); 
	}
	
	//VIGENERE TABLE ENCODE (Works)
	public String vignTable(String key, String m)
	{
		key = key.toLowerCase(); 
		m = m.toLowerCase(); 
		String[] split = m.split(" "); 
		m="";
		for(String s : split)
		{
			m+=s; 
		}
		String ret = ""; 
		String[][] vignTable = new String[26][26]; //Create table for vign encryption
		
		ArrayList<String> letters = new ArrayList<String>(); 
		for(int i = 0; i<ALPHA.length(); i++)  //load up letters array with the alphabet
		{
			letters.add(ALPHA.charAt(i)+""); 
		}
		
		
		for(int r = 0; r<vignTable.length; r++)
		{
			String[] currRow = vignTable[r];
			for(int i = 0; i<letters.size(); i++)
			{
				currRow[i] = letters.get(i); 
			}
			letters.add(letters.remove(0)); 
		}
				
		for(int i = 0; i<m.length(); i++)
		{
			String letter = m.charAt(i) + ""; 
			if(letter.equals(" "))
			{
				ret+=" "; 
			}
			else if(!ALPHA.contains(letter))
			{
				System.out.println("Sorry, no numbers"); 
				System.exit(0);
			}
			else
			{
				key = generateKey(m, key); 
				
				int row = ALPHA.indexOf(key.charAt(i)); 
				int col = ALPHA.indexOf(m.charAt(i)); 
				ret+=vignTable[row][col]; 
				
			}
		}
		
		return spaceOut(ret, 5); 
	}
	
	//extend key to same length as message for vignere
	private String generateKey(String str, String key) 
	{ 
	    int x = str.length(); 
	  
	    for (int i = 0; ; i++) 
	    { 
	        if (x == i) 
	            i = 0; 
	        if (key.length() == str.length()) 
	            break; 
	        key+=(key.charAt(i)); 
	    } 
	    return key; 
	} 
	
	//helper method to space out results
	private String spaceOut(String str, int ct)
	{
		int count = 0; 
		String ret = ""; 
		for(int i = 0; i<str.length(); i++)
		{
			if(count % ct == 0)
			{
				ret+=" "; 
			}
			count++; 
			ret+=str.charAt(i); 
		}
		return ret.trim(); 
	}
	  
	
	//helper method to print a matrix (Debug)
	private String printMat(String[][] mat)
	{
		String ret = ""; 
		for(int r = 0; r<mat.length; r++)
		{
			for(int c = 0; c<mat[r].length; c++)
			{
				System.out.print(mat[r][c] + " ");
			}
			System.out.println(); 
		}
		return ret; 
	}
	
	//DECODE VIGNERE TABLE (works)
	public String decodeVignTable(String key, String m)
	{
		String ret = "";
		String[] split = m.split(" ");
		
		String temp = "";
		for(int i = 0; i<split.length; i++) //remove spaces in the message
		{
			temp+=split[i]; 
		}
		m = temp; 
		
		m = m.toLowerCase(); 
		key = generateKey(m, key); 
		
		//create table
		String[][] vignTable = new String[26][26]; //Create table for vign encryption
		
		ArrayList<String> letters = new ArrayList<String>(); 
		for(int i = 0; i<ALPHA.length(); i++)  //load up letters array with the alphabet
		{
			letters.add(ALPHA.charAt(i)+""); 
		}
		
		
		for(int r = 0; r<vignTable.length; r++)
		{
			String[] currRow = vignTable[r];
			for(int i = 0; i<letters.size(); i++)
			{
				currRow[i] = letters.get(i); 
			}
			letters.add(letters.remove(0)); 
		}
		
		
		for(int i = 0; i<key.length(); i++)
		{
			int row = ALPHA.indexOf(key.charAt(i)); //get row value of table
			int col = 0; 
			
			//find col value
			for(int c = 0; c<vignTable[row].length; c++)
			{
				if(vignTable[row][c].equals(m.charAt(i)+""))
				{
					ret+=ALPHA.charAt(c); 
				}
			}
		}
		return spaceOut(ret, 5); 
	}
	
	public static String SHA1(String input) //One way encryption method, no decoding function other than brute force
	{ 
	        try { 
	            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
	  
	            byte[] messageDigest = md.digest(input.getBytes()); 
	  
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            String hashtext = no.toString(16); 
	  
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	  
	            return hashtext; 
	        } 
	  	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
	} 
	
	public static String PWGen(int length)
	{
		String ret = "";
		int nums = length/2; 
		int letters = length - nums;
		
		String specialchars = "!@#$%^&*()"; 
		
		String[] lets = new String[letters]; 
		String[] numbs = new String[nums]; 
		
		for(int i = 0; i<letters; i++)
		{
			Random r = new Random(); 
			int index = r.nextInt(ALPHA.length());
			lets[i] = ALPHA.charAt(index) + ""; 
		}
		
		for(int i = 0; i<nums; i++)
		{
			Random r = new Random(); 
			int index = r.nextInt(NUM.length());
			numbs[i] = NUM.charAt(index) + ""; 
		}
		
		int i = 0; 
		for(i = 0; i<numbs.length; i++)
		{
			ret+=lets[i]; 
			ret+=numbs[i];
		}
		if(i<lets.length - 1)
		{
			ret+=lets[lets.length-1]; 
		}
		
		ArrayList<String> pw = new ArrayList<String>(); 
		for(int c = 0; c<ret.length(); c++)
		{
			pw.add(ret.charAt(c) + ""); 
		}
		pw.remove(pw.size()-1); 
		Random rand = new Random(); 
		int index = rand.nextInt(specialchars.length()); 
		pw.add(specialchars.charAt(index)+""); 
		Collections.shuffle(pw);
		
		for(int x = 0; x<pw.size(); x++)
		{
			if(!pw.get(i).toUpperCase().equals(pw.get(i)))  //add an uppercase letter
			{
				pw.set(i, pw.get(i).toUpperCase()); 
				break; 
			}
		}
		if(length%2!=0)
		{
			Random r = new Random(); 
			int x = r.nextInt(ALPHA.length());
			pw.add(ALPHA.charAt(x)+""); 
		}
		String temp = "";
		for(int c = 0; c<pw.size(); c++)
		{
			temp+=pw.get(c); 
		}
		ret = temp; 
		return ret; 
	}
	
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
}
