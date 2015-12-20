package jp.rsooo.texasholdemsimulator;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

public class Card implements  Comparable<Card>
{
    @Override
    public int compareTo(Card o) {
        if (IDByRank(this.rank) > IDByRank(o.rank) || (IDByRank(this.rank) == IDByRank(o.rank) && IDBySuit(this.suit) > IDBySuit(o.suit)) )return -1;
        if (IDByRank(this.rank) == IDByRank(o.rank) && IDBySuit(this.suit) == IDBySuit(o.suit) )return 0;
        else return 1;
    }

    public static enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS};
    public static enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};

    public static Image[][] card_images;
    public static Image card_back;
    public static Image busted_image, blackjack_image, twenty_one_image;
    public static final int CARD_WIDTH = 69;
    public static final int CARD_HEIGHT = 100;

    public Suit suit;
    public Rank rank;

    public boolean given_away = false;

    public static void LoadImages() throws IOException
    {
        int x, y, s, r, k;
        BufferedImage image = ImageIO.read(new File("data/cards.png"));

        card_images = new Image[4][];
        for(s=0; s<4; s++)
        {
            card_images[s] = new Image[13];
            y = image.getHeight() - (s + 2) * CARD_HEIGHT;
            for(r=0; r<13; r++)
            {
                if(r == 12)
                    k = 0;
                else
                    k = r + 1;
                x = k * CARD_WIDTH;
                card_images[s][r] = image.getSubimage(x, y, CARD_WIDTH, CARD_HEIGHT);
            }
        }
        busted_image = ImageIO.read(new File("data/busted.png"));
        blackjack_image = ImageIO.read(new File("data/blackjack.png"));
        twenty_one_image = ImageIO.read(new File("data/21.png"));
        card_back = ImageIO.read(new File("data/card_back.png"));
    }

    public Card(Card.Suit s, Card.Rank r)
    {
        suit = s;
        rank = r;
    }

    public Card(int s, int r){
        suit = Card.SuitByID(s);
        rank = Card.RankByNum(r);
    }

    public Card()
    {
        suit = Suit.SPADES;
        rank = Rank.TWO;
    }

    public Card(String s){
        if(s.length() != 2){
            throw new IllegalArgumentException("card info length must be 2");
        }
        switch(s.charAt(0)){
            case 's' :
            case 'S' :
                suit = Suit.SPADES;
                break;
            case 'c' :
            case 'C' :
                suit = Suit.CLUBS;
                break;
            case 'h' :
            case 'H' :
                suit = Suit.HEARTS;
                break;
            case 'd' :
            case 'D' :
                suit = Suit.DIAMONDS;
                break;
            default:
                throw new IllegalArgumentException("unknown suit");
        }
        switch (s.charAt(1)){
            case 'a':
            case 'A' :
                rank = Rank.ACE;
                break;
            case 't' :
            case 'T' :
                rank = Rank.TEN;
                break;
            case 'j':
            case 'J' :
                rank = Rank.JACK;
                break;
            case 'q' :
            case 'Q' :
                rank = Rank.QUEEN;
                break;
            case 'k' :
            case 'K' :
                rank = Rank.KING;
                break;
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                rank = Card.RankByID(Integer.parseInt("" + s.charAt(1)));
                break;
            default:
                throw new IllegalArgumentException("unknown rank");
        }

    }

    public int ValueBJ()
    {
        return ValueBJ(rank);
    }
    public int Value(){return Value(rank);}

    Image CardImage()
    {
        int s, r;
        s = IDBySuit(suit);
        r = IDByRank(rank);
        return card_images[s][r];
    }

    String RankString()
    {
        return StringByRank(rank);
    }

    String SuitString()
    {
        return StringBySuit(suit);
    }

    String CardString()
    {
        return StringByCard(this);
    }

//	    void GiveToPlayer(Player p)
//	    {
//	        p.AddCard(this);
//	        given_away = true;
//	    }

    public static int IDByRank(Card.Rank v)
    {
        switch(v)
        {
            case TWO:       return 2;
            case THREE:     return 3;
            case FOUR:      return 4;
            case FIVE:      return 5;
            case SIX:       return 6;
            case SEVEN:     return 7;
            case EIGHT:     return 8;
            case NINE:      return 9;
            case TEN:       return 10;
            case JACK:      return 11;
            case QUEEN:     return 12;
            case KING:      return 13;
            case ACE:       return 14;
        }
        return 0;
    }

    public static Card.Rank RankByID(int v)
    {
        switch(v)
        {
            case 0:         return Card.Rank.TWO;
            case 1:         return Card.Rank.THREE;
            case 2:         return Card.Rank.FOUR;
            case 3:         return Card.Rank.FIVE;
            case 4:         return Card.Rank.SIX;
            case 5:         return Card.Rank.SEVEN;
            case 6:         return Card.Rank.EIGHT;
            case 7:         return Card.Rank.NINE;
            case 8:         return Card.Rank.TEN;
            case 9:         return Card.Rank.JACK;
            case 10:        return Card.Rank.QUEEN;
            case 11:        return Card.Rank.KING;
            case 12:        return Card.Rank.ACE;
        }
        return Card.Rank.TWO;
    }

    public static Card.Rank RankByNum(int v)
    {
        switch(v)
        {
            case 1:         return Card.Rank.ACE;
            case 2:         return Card.Rank.TWO;
            case 3:         return Card.Rank.THREE;
            case 4:         return Card.Rank.FOUR;
            case 5:         return Card.Rank.FIVE;
            case 6:         return Card.Rank.SIX;
            case 7:         return Card.Rank.SEVEN;
            case 8:         return Card.Rank.EIGHT;
            case 9:         return Card.Rank.NINE;
            case 10:         return Card.Rank.TEN;
            case 11:         return Card.Rank.JACK;
            case 12:        return Card.Rank.QUEEN;
            case 13:        return Card.Rank.KING;
        }
        return Card.Rank.TWO;
    }



    public static Card.Suit SuitByID(int v)
    {
        switch(v)
        {
            case 3:         return Card.Suit.SPADES;
            case 2:         return Card.Suit.HEARTS;
            case 1:         return Card.Suit.DIAMONDS;
            case 0:         return Card.Suit.CLUBS;
        }
        return Card.Suit.SPADES;
    }

    public static int IDBySuit(Card.Suit v)
    {
        switch(v)
        {
            case SPADES:    return 3;
            case HEARTS:    return 2;
            case DIAMONDS:  return 1;
            case CLUBS:     return 0;
        }
        return 0;
    }

    public static String StringByRank(Card.Rank v)
    {
        switch(v)
        {
            case TWO:       return "2";
            case THREE:     return "3";
            case FOUR:      return "4";
            case FIVE:      return "5";
            case SIX:       return "6";
            case SEVEN:     return "7";
            case EIGHT:     return "8";
            case NINE:      return "9";
            case TEN:       return "T";
            case JACK:      return "J";
            case QUEEN:     return "Q";
            case KING:      return "K";
            case ACE:       return "A";
        }
        return "";
    }

    public static String StringBySuit(Card.Suit s)
    {
        switch(s)
        {
            case SPADES:        return "Spades";
            case HEARTS:        return "Hearts";
            case DIAMONDS:      return "Diamonds";
            case CLUBS:         return "Clubs";
        }
        return "";
    }

    public static String StringByCard(Card c)
    {
        return c.RankString() + " of " + c.SuitString();
    }

    public static int ValueBJ(Card.Rank v)
    {
        return ValueBJ(v, false);
    }

    public static int Value(Card.Rank r){
        return Card.IDByRank(r);
    }

    public static int ValueBJ(Card.Rank v, boolean ace_is_one)
    {
        switch(v)
        {
            case TWO:       return 2;
            case THREE:     return 3;
            case FOUR:      return 4;
            case FIVE:      return 5;
            case SIX:       return 6;
            case SEVEN:     return 7;
            case EIGHT:     return 8;
            case NINE:      return 9;
            case TEN:       return 10;
            case JACK:      return 10;
            case QUEEN:     return 10;
            case KING:      return 10;
            case ACE:       return ace_is_one ? 1 : 11;
        }
        return 0;
    }

    public String toString(){
        return StringByCard(this);
    }

}
