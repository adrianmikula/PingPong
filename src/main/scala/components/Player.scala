package components
import pong._
import pong.Game._
import pong.Game.Side._
import sam.View


class Player(val name:String, val keys:collection.Map[Int,Movement], val side:Side.Value) {

  val id :Int = Player.getNextId()
  val (paddle, goal) = initialiseShapes(side)
  Player.players += this



  private def initialiseShapes(side:Side.Value): (Paddle, Goal)=
  {
    val length = Settings.paddleLength
    val breadth = Settings.paddleBreadth

    //if (side == Side.Top)
    //{
    var paddle : Paddle = new Paddle(Bounds.right/2, breadth, length, breadth)
    var goal :Goal = new Goal(0,0,Bounds.right, breadth)
    // }
    //else

    if (side == Side.Bottom)
    {
      paddle = new Paddle(Bounds.right/2, Bounds.bottom - breadth, length, breadth)
      goal = new Goal(0,Bounds.bottom, Bounds.right, breadth)
    }
    else if (side == Side.Left)
    {
      paddle = new Paddle(breadth, Bounds.bottom/2, breadth, length)
      goal = new Goal(0,0, breadth, Bounds.bottom)
    }
    else if (side == Side.Right)
    {
      paddle = new Paddle(Bounds.right-breadth, Bounds.bottom/2, breadth, length)
      goal = new Goal(Bounds.right,0, breadth, Bounds.bottom)
    }

    (paddle, goal)

  }

}


object Player
{
  var players : collection.mutable.Set[Player] = collection.mutable.Set()
  private var nextId :Int = 0

  def getNextId() :Int=
  {
    val id :Int = nextId
    nextId += 1
    id
  }
}

