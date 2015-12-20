package jp.rsooo.texasholdemsimulator.board

import jp.rsooo.texasholdemsimulator.deck.NormalShoe

/**
 * Created by a-saitoh on 2015/12/07.
 */
object Dealer {
  val shoe = new NormalShoe(1)
  shoe.shuffle

  def newHand = {
    List(shoe.pickCard() ,shoe.pickCard())
  }
}
