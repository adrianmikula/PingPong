package components
import pong._
import pong.Game._
import pong.Game.Side._


class Player(val name:String, val keys:collection.Map[Int,Movement], val side:Side.Value) {

  val id :Int = Player.getNextId()
  val (paddle, goal) = initialiseShapes(side)
  Player.players += this



  private def initialiseShapes(side:Side.Value): (Paddle, Goal)=
  {
    var paddle : Paddle = new Paddle(0, 10, 50, 10)
    var goal :Goal = new Goal(0,0,Bounds.right, 10)

    //if (side == Side.Top)
    //{
    //     paddle = new Paddle(0, 10, 50, 10)
    //     goal = new Goal(0,0,Bounds.right, 10)
    // }
    //else
    if (side == Side.Bottom)
    {
      paddle = new Paddle(0, Bounds.bottom - 10, 50, 10)
      goal = new Goal(0,Bounds.bottom, Bounds.right, 10)
    }
    else if (side == Side.Left)
    {
      paddle = new Paddle(10, 0, 10, 50)
      goal = new Goal(0,0, 10, Bounds.bottom)
    }
    else if (side == Side.Right)
    {
      paddle = new Paddle(Bounds.right-10, 0, 10, 50)
      goal = new Goal(Bounds.right,0, 10, Bounds.bottom)
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

