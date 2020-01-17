import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
class Card {

	//Creates a playing card
	private int rank;//represents the rank of a card
	private int suit;//represents the suit of a card
	private int value;//represents the value of a card
	private static String[] ranks = {"Joker","Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
	private static String[] suits = {"Clubs","Diamonds","Hearts","Spades"};


	//Created with an integer that represents a spot in the String array ranks and the String array suits. This represents
	//the rank and suit of an individual card.
	Card(int suit, int values)
		{
		this.rank=values;
		this.suit=suit;
		}
		
	// Returns the string version of a card.
	public String toString()
		{
		return ranks[rank]+" of "+suits[suit];
		}
	
	//Returns the rank of a card.
	public int getRank()
		{
		return rank;
		}

	//Returns the suit of a card.
	public int getSuit()
		{
		return suit;
		}

	//Returns the value of a card. If a jack, queen, or king the value is ten. Aces are 11 for now.
 
	public int getValue()
		{		
		if(rank>10)
		{
		value=10;
		}
		else if(rank==1)
			{
			value=11;
			}
		else
			{
			value=rank;
			}
		return value;
		}

	//Sets the value of a card.
	public void setValue(int set)
		{
		value = set;
		}
		}


//Creates a dealer that the user plays against.
class Dealer {
	ArrayList<Card> hand;//represents the dealer's hand
	private int handvalue=0;//value of the dealer's hand (starts at 0)
	private Card[] aHand;//used to convert the dealer's hand to an array
	private int AceCounter;//counts the aces in the dealer's hand
	Dealer(Deck deck)
		{
		hand = new ArrayList<>();
		aHand = new Card[]{};
		int AceCounter=0;
		for(int i=0; i<2; i++)
			{
			hand.add(deck.drawCard());
			}
		aHand = hand.toArray(aHand);
		for(int i=0; i<aHand.length; i++)
			{
			handvalue += aHand[i].getValue();
        if(aHand[i].getValue()==11)
			{
				AceCounter++;
			}
			while(AceCounter>0 && handvalue>21)
				{
				handvalue-=10;
				AceCounter--;
				}
			}
		}

		
	//Prints the dealer's first card (the card face up at the beginning of a blackjack game).
	public void showFirstCard()
		{
		Card[] firstCard = new Card[]{};
		firstCard = hand.toArray(firstCard);
		System.out.println("["+firstCard[0]+"]");
		}
	
	
	//Gives the dealer another card and updates the value of his hand. Takes into account the value of aces.
	public void Hit(Deck deck)
		{
		hand.add(deck.drawCard());
		aHand = hand.toArray(aHand);
		handvalue = 0;
	for(int i=0; i<aHand.length; i++)
		{
		handvalue += aHand[i].getValue();
		if(aHand[i].getValue()==11)
			{
			AceCounter++;
			}
			while(AceCounter>0 && handvalue>21)
			{
			handvalue-=10;
			AceCounter--;
			}
		}
	}

	
	//Determines if the dealer wants to hit according to classic Blackjack rules.
	public boolean wantsToHit()
	{
		if(handvalue<17)
		{
			return true;
		}
		return false;
	}


	//Returns true if the dealer has blackjack. 
	public boolean hasBlackJack()
	{
		if(hand.size()==2 && handvalue==21)
		{
			System.out.println("The dealer has blackjack!");
			return true;
		}
		return false;
	}
	
	
	//Prints the dealer's hand.
	public void showHand()
	{
		System.out.println(hand);
	}

	
	//Returns the value of the dealer's hand.
	public int getHandValue()
	{
		return handvalue;
	}
	

	//Determines if a dealer has busted.
	public boolean busted(int handvalue)
	{
		if(handvalue>21)
		{
			System.out.println("The dealer busted!");
			return true;
		}
	return false;
	}

	
	//Takes the turn for the dealer and returns the value of his hand.
	public int takeTurn(Deck deck)
	{
		while(wantsToHit())
		{
			System.out.println("The dealer hits");
			Hit(deck);
			if(busted(handvalue))
		{
            break;
        }
    }
    if(handvalue<=21)
		{
			System.out.print("The dealer stands.");
		}
		return handvalue;
	}
	}


	//Creates and shuffles a deck of 52 playing cards.
	class Deck 
		{
		private ArrayList<Card> deck;//represents a deck of cards
		Deck()
	{
		deck = new ArrayList<Card>();
		for(int i=0; i<4; i++)
		{
			for(int j=1; j<=13; j++)
			{
				deck.add(new Card(i,j));
			}
		}
	}

	
	//Shuffles the deck by changing the indexes of 200 random pairs of cards in the deck.
	public void shuffle()
	{
		Random random = new Random();
		Card temp;
		for(int i=0; i<200; i++)
		{
			int index1 = random.nextInt(deck.size()-1);
			int index2 = random.nextInt(deck.size()-1);
			temp = deck.get(index2);
			deck.set(index2, deck.get(index1));
			deck.set(index1, temp);
		}
	}

	
	//Draws a card from the deck.
	public Card drawCard()
	{
		return deck.remove(0);
	}
}