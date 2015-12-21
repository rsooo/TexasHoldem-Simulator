/**
 * Created by a-saitoh on 2015/12/20.
 */


import jp.rsooo.texasholdemsimulator.CardHelper
import jp.rsooo.texasholdemsimulator.board.HandRank
import jp.rsooo.texasholdemsimulator.deck.NormalShoe
import org.scalatest._
import jp.rsooo.texasholdemsimulator.Card


class HandRankSpec extends FlatSpec with Matchers  {

  "HandRank" should "distinguish Straight Hand" in {
    //val deck = new NormalShoe(1)
    val hand : List[Card] = CardHelper.cardList( List("Kh", "Qh", "Jh", "Th", "9h"))
    HandRank.isStraight(hand) should be ((true, 13))
    val hand2 : List[Card] = CardHelper.cardList( List("Kh", "Qh", "Jh","Js", "Ts", "Th", "9h"))
    HandRank.isStraight(hand2) should be ((true, 13))
    val hand3 : List[Card] = CardHelper.cardList( List("Ah", "Qh", "Jh","5s", "4s", "3h", "2h"))
    HandRank.isStraight(hand3) should be ((true, 5))
    val hand4 : List[Card] = CardHelper.cardList( List("Ah", "Qh", "Jh","6s", "5s", "4h", "3h"))
    HandRank.isStraight(hand4) should be ((false, -1))
  }

  "HandRank" should "distinguish Flush Hand" in {
    //val deck = new NormalShoe(1)
    val hand: List[Card] = CardHelper.cardList(List("Kh", "Qh", "3h", "Th", "7h"))
    HandRank.isFlush(hand) should be((true, List(13,12,10,7,3)))
    val hand2 : List[Card] = CardHelper.cardList( List("Ks", "Qs", "Jh","Js", "Ts", "2s", "9s"))
    HandRank.isFlush(hand2) should be ((true, List(13,12,11,10,9)))
  }

  }
