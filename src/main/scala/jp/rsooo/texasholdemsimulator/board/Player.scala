package jp.rsooo.texasholdemsimulator.board

/**
 * Created by a-saitoh on 2015/12/07.
 */
class Player(_stack : Int) {
  var stack : Int = _stack
  val hand = newHand

  def newHand = {
    Dealer.newHand
  }
  def action = {

  }
}
