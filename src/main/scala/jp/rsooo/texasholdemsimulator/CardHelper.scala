package jp.rsooo.texasholdemsimulator


/**
 * Created by a-saitoh on 2015/12/20.
 */
object CardHelper {
  def cardList(cardlist : List[String]) : List[Card]= {
    for(c <- cardlist) yield new Card(c)
  }
}
