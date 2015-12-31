package jp.rsooo.texasholdemsimulator.etc

import jp.rsooo.texasholdemsimulator.board.HandRank
import jp.rsooo.texasholdemsimulator.deck.NormalShoe

import scala.collection.parallel.mutable

/**
 * Created by a-saitoh on 2015/12/31.
 */
class HandSimurator(trial : Int = 1000000) {

  val deck = new NormalShoe(1)

  def printFiveCardHandProbability() = {
    val map: scala.collection.mutable.Map[Int, Int] = new scala.collection.mutable.HashMap[Int, Int]()
    for (i <- 1.to(trial)) {
      deck.shuffle
      val hand = List(deck.pickCard(), deck.pickCard(), deck.pickCard(), deck.pickCard(), deck.pickCard() /*, deck.pickCard(), deck.pickCard()*/)
      val (handRank, kicker) = HandRank.calc(hand)
      if (!map.contains(handRank)) map += handRank -> 1
      else map += (handRank -> (map.get(handRank).get + 1))
    }

    println("---FIVE CARD POKER HAND PROBABIRITY---")
    for (key <- map.keySet.toList.sorted.reverse) {
      val rankStr = key match {
        case 8 => "STRAIGHT_FLUSH"
        case 7 => "FOUR_OF_KIND"
        case 6 => "FULL_HOUSE"
        case 5 => "FLUSH"
        case 4 => "STRAIGHT"
        case 3 => "THREE_OF_KIND"
        case 2 => "TWO_PAIR"
        case 1 => "ONE_PAIR"
        case 0 => "HIGH_CARD"
        case _ => "UNKNOWN"
      }
      println(s"${rankStr} : ${map.get(key).get} : " + f"${map.get(key).get / trial.toDouble * 100}%2.4f" + "%")
    }


  }


  def printSevenCardHandProbability() = {
    val map : scala.collection.mutable.Map[Int,Int] = new scala.collection.mutable.HashMap[Int,Int]()
    for(i <- 1.to(trial)){
      deck.shuffle
      val hand = List(deck.pickCard(),deck.pickCard(),deck.pickCard(), deck.pickCard(), deck.pickCard(), deck.pickCard(), deck.pickCard())
      val (handRank, kicker) = HandRank.calc(hand)
      if (!map.contains(handRank)) map += handRank -> 1
      else map += (handRank -> (map.get(handRank).get + 1))
    }

    println("---SEVEN CARD POKER HAND PROBABIRITY---")
    for(key <- map.keySet.toList.sorted.reverse){
      val rankStr = key match {
        case 8 => "STRAIGHT_FLUSH"
        case 7 => "FOUR_OF_KIND"
        case 6 => "FULL_HOUSE"
        case 5 => "FLUSH"
        case 4 => "STRAIGHT"
        case 3 => "THREE_OF_KIND"
        case 2 => "TWO_PAIR"
        case 1 => "ONE_PAIR"
        case 0 => "HIGH_CARD"
        case _ => "UNKNOWN"
      }
      println(s"${rankStr} : ${map.get(key).get} : " + f"${map.get(key).get / trial.toDouble * 100}%2.4f" + "%" )
    }

  }

}