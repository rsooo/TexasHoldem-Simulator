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

  def calc(cards : List[Card]) = {
    assert(cards.length == 7)

    val pairCount = PairCount.create(cards)
  }

  def isStraight(cards : List[Card]) : (Boolean, Int) = {
    def _isStraight(cards : List[Card], count : Int, kicker : Int) : (Int,Int)= {
      cards match {
        case List(c, c2, _*) => {
          if(c2.Value() == c.Value() - 1) {/*println("1: " + count + " " + kicker);*/_isStraight(cards.tail, count + 1, if(count == 0) c.Value() else kicker)}
          else if(c2.Value() == c.Value()) {/*println("2");*/ _isStraight(cards.tail, count, if(count == 0) c.Value() else kicker)}
          else {/*println("3");*/ _isStraight(cards.tail, 0, -1)}
        }
        case _ => (count, kicker)
      }
    }
    _isStraight(cards, 0, -1) match {
      case (count, kicker) if count >= 4 => (true, kicker)
      case (count, kicker) if count == 3 && kicker == 5=> if(cards.head.Value() == Card.IDByRank(Card.Rank.ACE)) (true, Card.IDByRank(Card.Rank.ACE)) else (false, -1)
      case _ => (false, -1)
    }

    /*
    def _isStraight(cards : List[Card], count : Int, prev : Int, kicker : Int) = {
      cards match {
        case List(c, _*) =>{
          if(prev == c.Value() - 1) _isStraight(cards.tail, count + 1, c.Value(), if(count == 0) c.Value() else kicker)
          else if(prev == c.Value()) _isStraight(cards.tail, count, c.Va)
          else _isStraight(cards.tail, 0, )
        }
        case _ =>{

        }
      }
    }
      */

  }
}
