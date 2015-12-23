package jp.rsooo.texasholdemsimulator.board

import jp.rsooo.texasholdemsimulator.Card

/**
 * Created by a-saitoh on 2015/12/12.
 */
class HandRank(handRank: Int, kicker : List[Int]) {


}

object HandRank {
  val HIGH_CARD = 0
  val ONE_PAIR = 1
  val TWO_PAIR = 2
  val THREE_OF_KIND = 3
  val STRAIGHT = 4
  val FLUSH = 5
  val FULL_HOUSE = 6
  val FOUR_OF_KIND = 7
  val STRAIGHT_FLUSH = 8

  def calc(cards : List[Card]) : (Int, Kicker) = {

//    assert(cards.length == 7)
    val sortedCards = cards.sorted
    val pairCount = PairCount.calc(sortedCards)
    val (flush, flushKicker) = isFlush(sortedCards)
    val (straight, straightKicker) = isStraight(cards)
    val quadsPair = pairCount.filterPair(4)
    val setPair = pairCount.filterPair(3)
    val pair = pairCount.filterPair(2)
//    val noPair = pairCount.filterPair(1)

    if(flush && straight) (STRAIGHT_FLUSH, straightKicker)
    else if(quadsPair.length >= 1) (FOUR_OF_KIND, Kicker(List(quadsPair.head._1, cards.filter(_.Value() != quadsPair.head._1).sorted.head.Value())))
    else if(setPair.length >= 1 && setPair.length + pair.length >= 2) {
      val setKickerVal = setPair.map(_._1).max
      (FULL_HOUSE, Kicker(List(setKickerVal, (setPair.filter(_._1 != setKickerVal) ::: pair).map(_._1).max)))
    }
    else if (flush) (FLUSH, flushKicker)
    else if (straight) (STRAIGHT, straightKicker)
    else if (setPair.length >= 1) (THREE_OF_KIND, Kicker(setPair.head._1 ::  cards.filter(_.Value() != setPair.head._1).sorted.take(2).map(_.Value())))
    else if (pair.length >= 2) {
      val (pairKickerVal1 :: pairKickerVal2 :: Nil) = pair.map(_._1).sorted.reverse.take(2)
      (TWO_PAIR, Kicker(List(pairKickerVal1, pairKickerVal2, cards.filter(c => c.Value() != pairKickerVal1 && c.Value() != pairKickerVal2).sorted.head.Value())))
    }
    else if (pair.length >= 1) {
      val pairKickerVal = pair.map(_._1).sorted.head
      (ONE_PAIR, Kicker(pairKickerVal :: cards.filter(_.Value() != pairKickerVal).sorted.take(3).map(_.Value())))
    }
    else (HIGH_CARD, Kicker(cards.sorted.take(5).map(_.Value())))

  }

  def isStraight(cards : List[Card]) : (Boolean, Kicker) = {
    def _isStraight(cards : List[Card], count : Int, kicker : Int) : (Int,Int)= {
      cards match {
        case List(c, c2, _*) => {
          if(c2.Value() == c.Value() - 1) {
            val nextcnt = count + 1
            //println("1: " + nextcnt + " " + kicker)
            if (nextcnt >= 4) (nextcnt, kicker)
            else _isStraight(cards.tail, count + 1, if(count == 0) c.Value() else kicker)
          }
          else if(c2.Value() == c.Value()) {/*println("2");*/ _isStraight(cards.tail, count, if(count == 0) c.Value() else kicker)}
          else {/*println("3");*/ _isStraight(cards.tail, 0, -1)}
        }
        case _ => (count, kicker)
      }
    }
    _isStraight(cards.sorted, 0, -1) match {
      case (count, kicker) if count >= 4 => (true, Kicker(List(kicker)))
      case (count, kicker) if count == 3 && kicker == 5=> if(cards.head.Value() == Card.IDByRank(Card.Rank.ACE)) (true, Kicker(List(Card.IDByRank(Card.Rank.FIVE)))) else (false, Kicker(Nil))
      case _ => (false, Kicker(Nil))
    }
  }

  def isFlush(cards : List[Card]) : (Boolean, Kicker) = {
    def _isFlush(cards : List[Card], nums : List[List[Int]]) : (Boolean, Kicker)= {
      nums match {
        case List(s,h,d,c) => {
          cards match {
            case List(card, _*) =>{
              card.suit match {
                case Card.Suit.SPADES => _isFlush(cards.tail, List(card.Value() :: s ,h,d,c))
                case Card.Suit.HEARTS => _isFlush(cards.tail, List(s, card.Value() :: h,d,c))
                case Card.Suit.DIAMONDS => _isFlush(cards.tail, List(s, h, card.Value() :: d, c))
                case Card.Suit.CLUBS => _isFlush(cards.tail, List(s, h, d, card.Value() :: c))
                case _ => throw new IllegalArgumentException("wrong suit")
              }
            }
            case _ => {
              val topNumberSuit = nums.sortWith(_.length > _.length).head
              if(topNumberSuit.length >= 5) (true, Kicker(topNumberSuit.reverse.take(5))) else (false, Kicker(Nil))
            }
          }
        }
        case _ =>  throw new IllegalArgumentException("wrong argument")
      }
    }
    _isFlush(cards.sorted, List(Nil, Nil, Nil, Nil))
  }

}
