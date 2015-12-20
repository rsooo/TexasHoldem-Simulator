package jp.rsooo.texasholdemsimurator.deck

import jp.rsooo.texasholdemsimulator.Card
import jp.rsooo.texasholdemsimulator.etc.Sfmt

/**
 * Created by a-saitoh on 2015/11/29.
 */
class NormalShoe(size : Int) extends Shoe
{
  private val cards : Array[Card] = new Array[Card](size * 52)
  private var index : Int = 0
  private val sfmt = new Sfmt(System.currentTimeMillis().asInstanceOf[Int])

  init

  override def pickCard(): Card = {
    val nextCard = if (cards.length <= index) null else cards(index)
    index = index + 1
    nextCard
  }

   override def shuffle: Unit = {
     //val sfmt : Sfm

     for (times <- 0 until 3) {
       for (j <- 0 until 52 * size) {
         val r = sfmt.NextInt(52 * size)
         val tmp = cards(j)
         cards(j) = cards(r)
         cards(r) = tmp
       }
     }
     index = 0;
   }

  def init = {
    for(i <- 0 until size){
      for(j <- 0 until 52){
        cards(j + i * 52) = new Card(j / 13, (j % 13) + 1)
      }
    }
    index = 0
  }

  override def toString = {
    "INDEX:" + index + "\n" + cards.mkString("\n")
  }

}
