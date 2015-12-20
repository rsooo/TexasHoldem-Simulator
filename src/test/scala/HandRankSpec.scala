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


  }
}
