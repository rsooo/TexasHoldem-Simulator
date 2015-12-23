package jp.rsooo.texasholdemsimulator.board

import jp.rsooo.texasholdemsimulator.Card

/**
 * Created by a-saitoh on 2015/12/08.
 */
class PairCount(_pairCount : List[(Int,Int)] = Nil)  {
  //(Int, Int) -> (rank, count)
  val pairCount : List[(Int,Int)] = _pairCount

  override def toString = {
    pairCount.mkString(", ")
  }

  def filterPair(count : Int) = {
    pairCount.filter(_._2 == count)
  }
}

object PairCount{
  def calc(cards : List[Card]) = {
    new PairCount(sort(_calc(cards, Nil, None)))
  }

  def _calc(cards : List[Card], ret : List[(Int, Int)], prev : Option[(Int, Int)]) : List[(Int,Int)] = {
    cards match {
      case List(c, _*) => {
        val c = cards.head
        prev match {
          case Some(p) =>
            if (prev != null && c.Value() == p._1) {
              _calc(cards.tail, ret, Option(p._1, p._2 + 1))
            } else {
              _calc(cards.tail, p :: ret, Option(c.Value(), 1))
            }
          case None => {
            _calc(cards.tail, ret , Option(c.Value(), 1))
          }
        }
      }
      case _ => {
        prev match {
          case Some(p) => p :: ret
          case None => ret
        }
      }
    }
  }

  def sort(pairCount : List[(Int,Int)] ) = {
    pairCount.sortWith((a : (Int,Int), b : (Int,Int)) => a._2 > b._2 || (a._2 == b._2 && a._1 > b._1))
  }


}