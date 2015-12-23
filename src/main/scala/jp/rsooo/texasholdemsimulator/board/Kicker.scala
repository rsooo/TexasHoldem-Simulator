package jp.rsooo.texasholdemsimulator.board

/**
 * Created by a-saitoh on 2015/12/23.
 */
case class Kicker(kickerList : List[Int]) extends Ordered[Kicker] {

  override def compare(that: Kicker): Int = {
    for( (xrank, yrank) <- this.kickerList.zipAll(that.kickerList, -1, -1 )){
      if (xrank > yrank ) return 1
      else if (xrank < yrank ) return -1
    }
    return 0

  }
}
