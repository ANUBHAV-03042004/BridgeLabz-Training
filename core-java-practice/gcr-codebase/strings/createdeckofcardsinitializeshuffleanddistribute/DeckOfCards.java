package createdeckofcardsinitializeshuffleanddistribute;
import java.util.*;
public class DeckOfCards {
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
System.out.println("Enter the number of players :");
int numberofplayers=sc.nextInt();
String suits[]= {"Heart","Diamond","Spade","Club"};
String ranks[]= {"2","3","4","5","6","7","8","9","10","Ace","Jack","Queen","King"};
int numofcards=suits.length*ranks.length;
String Deck[]=new String[numofcards];
Deck =  createDeck(suits,ranks,Deck);
Deck=shuffleDeck(Deck);
Distribute(Deck,numberofplayers);
}
public static void Distribute(String Deck[],int numberofplayers)
{
	int eachplayerwillget= Deck.length/ numberofplayers,index=0;
	for(int i=0;i<numberofplayers;i++)
	{
		System.out.printf("player %d will get :\n", (i+1));
		for(int j=0;j<eachplayerwillget;j++)
		{
		System.out.println(Deck[index++]);
		}
		System.out.println();
	}
}

public static String [] shuffleDeck(String Deck[]){
	for(int i=0;i<Deck.length;i++)
	{
		int randomcardnumber= i+ (int)(Math.random()*(Deck.length-i));
		String temp=Deck[i];
		Deck[i]=Deck[randomcardnumber];
		Deck[randomcardnumber]=temp;
	}
	return Deck;
}
public static String[] createDeck(String []suits, String [] ranks,String Deck[]) {
	  int index = 0;
      for (String suit : suits) {
          for (String rank : ranks) {
              Deck[index++] = rank + " of " + suit;
          }
      }
	return Deck;
}
}
