package lexicaltwist;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class LexicalTwist {
	public static boolean checkreversed(String s1,String s2) {
		int n=s2.length();
		if(s1.length()>s2.length() || s2.length()<s1.length()) return false;
		for(int i=0;i<s1.length();i++) {
			if(!Character.toString(s1.charAt(i)).equalsIgnoreCase(Character.toString(s2.charAt(n-i-1))))return false;
		}
		return true;
	}
	public static String reversefirst(String str1) {
		String rev = "";
		for(int i=str1.length()-1;i>=0;i--) {
			rev+=str1.charAt(i);
		}
		return rev;
	}
	public static String transformVowel(String str) {
		str=str.replaceAll("[aeiou]","@");
		return str;
	}
	public static int[] countVowelandConsonant(String str,HashSet<Character>vowel,HashSet<Character>consonant) {
	String vowelregex="[AEIOU]";
	int count[]=new int[2];
	for(char i :str.toCharArray()) {
		if(vowelregex.contains(Character.toString(i))) {
			vowel.add(i);
			count[0]++;
		}
		else {
			consonant.add(i);
			count[1]++;
		}
	}
	return count;
	}
public static void main(String[] args) {
	HashSet<Character> vowel= new LinkedHashSet<>();
	HashSet<Character> consonant= new LinkedHashSet<>();
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter first String : ");
	String str1=sc.nextLine();
	if(str1.contains(" ")) throw new IllegalArgumentException(str1+" is an invalid word.");
	System.out.println("Enter second String : ");
	String str2=sc.nextLine();
	if(str2.contains(" "))  throw new IllegalArgumentException(str2+" is an invalid word.");
	if(checkreversed(str1,str2)) {
	 str1 = reversefirst(str1).toLowerCase();
	 str1=transformVowel(str1);
	 System.out.println("Transformed String :"+ str1);
	}
	else {
		String str=(str1+str2).toUpperCase();
		int count[]=countVowelandConsonant(str,vowel,consonant);
		if(count[0]>count[1]) {
			String v="";
			int countVowel =0;
			Iterator<Character> itr = vowel.iterator();
			while(itr.hasNext() && countVowel<2) {
				v+=itr.next();
				countVowel++;
			}
			System.out.println("Vowels :"+v);
		}else if(count[1]>count[0]) {
			String c="";
			int countConsonant =0;
			Iterator<Character> itr = consonant.iterator();
			while(itr.hasNext() && countConsonant<2) {
				c+=itr.next();
				countConsonant++;
			}
			System.out.println("Consonants :"+c);
		}
		else {
			System.out.println("Vowels and consonants are equal");
		}
		
	}
	
}
}
