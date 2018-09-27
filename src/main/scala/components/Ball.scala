package components

//class Ball (var x : Int, var y : Int) {
object Ball {

    var x : Int = 0
    var y : Int = 0
    val radius:Int = 25
    val startX = scala.util.Random.nextInt(2) * 10 - 5
    val startY = scala.util.Random.nextInt(2) * 10 - 5
    val motion = new Movement(startX, startY)

}

//object Ball
//{
    //var balls : collection.mutable.Set[Ball] = collection.mutable.Set()
//}
