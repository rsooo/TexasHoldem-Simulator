/**
 * Created by a-saitoh on 2015/12/20.
 */


import jp.rsooo.texasholdemsimulator.CardHelper
import jp.rsooo.texasholdemsimulator.board.{Kicker, HandRank}
import jp.rsooo.texasholdemsimulator.deck.NormalShoe
import org.scalatest._
import jp.rsooo.texasholdemsimulator.Card


class HandRankSpec extends FlatSpec with Matchers  {


  "HandRank" should "calculate Handrank" in {
    val hand : List[Card] = CardHelper.cardList( List("Ah", "Kh", "Qh", "Jh", "Th", "9h", "8h"))
    HandRank.calc(hand) should be (HandRank.STRAIGHT_FLUSH, Kicker(List(14)))
    val hand2 : List[Card] = CardHelper.cardList( List("8s", "8d", "8h", "8c", "Th", "2s", "Ac"))
    HandRank.calc(hand2) should be (HandRank.FOUR_OF_KIND, Kicker(List(8, 14)))
    val hand3 : List[Card] = CardHelper.cardList( List("8s", "8d", "8h", "3c", "3h", "Ts", "Tc"))
    HandRank.calc(hand3) should be (HandRank.FULL_HOUSE, Kicker(List(8, 10)))
    val hand4 : List[Card] = CardHelper.cardList( List("2s", "8s", "8h", "As", "Ah", "3s", "Js"))
    HandRank.calc(hand4) should be (HandRank.FLUSH, Kicker(List(14, 11, 8, 3, 2)))
    val hand5 : List[Card] = CardHelper.cardList( List("7s", "8s", "8h", "Ts", "Jh", "2s", "9c"))
    HandRank.calc(hand5) should be (HandRank.STRAIGHT, Kicker(List(11)))
    val hand6 : List[Card] = CardHelper.cardList( List("7s", "8c", "8h", "Tc", "8h", "3s", "As"))
    HandRank.calc(hand6) should be (HandRank.THREE_OF_KIND, Kicker(List(8,14,10)))
    val hand7 : List[Card] = CardHelper.cardList( List("7s", "8c", "8h", "Tc", "7d", "3s", "As"))
    HandRank.calc(hand7) should be (HandRank.TWO_PAIR, Kicker(List(8,7,14)))
    val hand8 : List[Card] = CardHelper.cardList( List("7s", "8c", "8h", "Tc", "7d", "Ts", "Qs"))
    HandRank.calc(hand8) should be (HandRank.TWO_PAIR, Kicker(List(10,8,12)))
    val hand9 : List[Card] = CardHelper.cardList( List("7s", "2c", "3c", "Tc", "7d", "As", "Qs"))
    HandRank.calc(hand9) should be (HandRank.ONE_PAIR, Kicker(List(7,14,12,10)))
    val hand10 : List[Card] = CardHelper.cardList( List("7s", "2c", "3c", "Tc", "8d", "As", "Qs"))
    HandRank.calc(hand10) should be (HandRank.HIGH_CARD, Kicker(List(14,12,10, 8, 7)))



  }


  "HandRank" should "distinguish Straight Hand" in {
    //val deck = new NormalShoe(1)
    val hand : List[Card] = CardHelper.cardList( List("Kh", "Qh", "Jh", "Th", "9h"))
    HandRank.isStraight(hand) should be ((true, Kicker(13 :: Nil)))
    val hand2 : List[Card] = CardHelper.cardList( List("Kh", "Qh", "Jh","Js", "Ts", "Th", "9h"))
    HandRank.isStraight(hand2) should be ((true, Kicker(13 :: Nil)))
    val hand3 : List[Card] = CardHelper.cardList( List("Ah", "Qh", "Jh","5s", "4s", "3h", "2h"))
    HandRank.isStraight(hand3) should be ((true, Kicker(5 :: Nil)))
    val hand4 : List[Card] = CardHelper.cardList( List("Ah", "Qh", "Jh","6s", "5s", "4h", "3h"))
    HandRank.isStraight(hand4) should be ((false , Kicker(Nil)))
    val hand5 : List[Card] = CardHelper.cardList( List("7s", "8s", "8h", "Ts", "Jh", "As", "9c"))
    HandRank.isStraight(hand5) should be ((true , Kicker(List(11))))

    val hand6 : List[Card] = CardHelper.cardList( List("7s", "8s", "8h", "Ts", "Jh", "2s", "9c")).sorted
    println("Hand6 " + hand6.mkString(", "))
    HandRank.isStraight(hand6) should be ((true , Kicker(List(11))))

  }

  "HandRank" should "distinguish Flush Hand" in {
    //val deck = new NormalShoe(1)
    val hand: List[Card] = CardHelper.cardList(List("Kh", "Qh", "3h", "Th", "7h"))
    HandRank.isFlush(hand) should be((true, Kicker(List(13,12,10,7,3))))
    val hand2 : List[Card] = CardHelper.cardList( List("Ks", "Qs", "Jh","Js", "Ts", "2s", "9s"))
    HandRank.isFlush(hand2) should be ((true, Kicker(List(13,12,11,10,9))))
  }

  }
