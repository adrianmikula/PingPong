package components
import pong._
import pong.Game._
import pong.Game.Side._
import sam.View


class Player(val name:String, val keys:collection.Map[Int,Movement], val side:Side.Value) {

  val id :Int = Player.getNextId()
  var paddle = snapToSide(side)
  Player.players += this
  Player.sides(side) = this

  def resetPaddle(): Unit ={
    paddle = snapToSide(side)
  }

  private def snapToSide(side:Side.Value): Paddle=
  {
    val length = Settings.paddleLength
    val breadth = Settings.paddleBreadth

    //if (side == Side.Top)
    //{
    var paddle : Paddle = new Paddle(Bounds.right/2, breadth, length, breadth)
    // }
    //else

    if (side == Side.Bottom)
    {
      paddle = new Paddle(Bounds.right/2, Bounds.bottom - breadth, length, breadth)
    }
    else if (side == Side.Left)
    {
      paddle = new Paddle(breadth, Bounds.bottom/2, breadth, length)
    }
    else if (side == Side.Right)
    {
      paddle = new Paddle(Bounds.right-breadth, Bounds.bottom/2, breadth, length)
    }

    (paddle)

  }

}


object Player
{
  var players : collection.mutable.Set[Player] = collection.mutable.Set()
  private var nextId :Int = 0
  var sides: scala.collection.mutable.Map[Side.Value, Player] = scala.collection.mutable.Map()

  // get a unique id for this player, stating from 0
  def getNextId() :Int=
  {
    val id :Int = nextId
    nextId += 1
    id
  }
}

