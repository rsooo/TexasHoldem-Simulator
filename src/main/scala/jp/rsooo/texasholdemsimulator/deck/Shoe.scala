package jp.rsooo.texasholdemsimulator.deck

import jp.rsooo.texasholdemsimulator.Card

/**
 * Created by a-saitoh on 2015/11/29.
 */

trait Shoe {
  def pickCard(): Card
  def shuffle


}