import java.lang.System

import jp.rsooo.texasholdemsimulator.Card
import jp.rsooo.texasholdemsimulator.board.PairCount
import jp.rsooo.texasholdemsimulator.etc.Sfmt
import jp.rsooo.texasholdemsimurator.deck.NormalShoe
;

/**
 * Created by a-saitoh on 2015/11/27.
 */
object Main extends App {

//  println (System.currentTimeMillis().asInstanceOf[Int])
//  val sfmt = new Sfmt(System.currentTimeMillis().asInstanceOf[Int]);

  val c : Card = new Card()
//  for (i <- 1 until 100){
//    println( sfmt.NextInt(52))
//  }
//  val list = List(12,3,4)
//  t(List(2,3,4,112,3))
//  t(List())


  val deck = new NormalShoe(1)
  deck.shuffle
  println(deck.pickCard().toString)
  println(deck.pickCard().toString)
  println()
  println(deck.pickCard().toString)
  println(deck.pickCard().toString)
  //println(deck.toString)

  val a = List(deck.pickCard(),deck.pickCard(),deck.pickCard(), deck.pickCard(), deck.pickCard(), deck.pickCard(), deck.pickCard()).sorted
  println(a.mkString(", "))

  println( PairCount.create(a).toString)


  def t(list : List[Int]) : Unit = {
    list match {
      case List(a, _*) =>println(list.head);t(list.tail)
      case _  =>
    }

  }

}
