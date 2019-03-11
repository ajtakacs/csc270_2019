import scala.io.Source
import edu.holycross.shot.cite._

val filepath:String = "/vagrant/csc270_2019/projectText.txt"
// Get the file as a vector of lines, ignoring empty lines
val myLines:Vector[String] = Source.fromFile(filepath).getLines.toVector.filter( _.size > 0 )

/* How to (a) remove punctuation, and (b) tokenize by word in a short chunk */
val myTokenizedLines:Vector[Vector[String]] = myLines.map( aLine => {
  val noPunc:String = aLine.replaceAll("""[,.?;":!)(–—-]""",""  ).replaceAll(" +"," ")
  val wordVec:Vector[String] = noPunc.split(" ").toVector
  wordVec
})

val myVec:Vector[Int] = Vector(1,2,3,4,5,6,7,8,9)
val myGroups:Vector[Vector[Int]] = myVec.sliding(3,1).toVector
// Type `myGroups` into the Console to see the result!
// Result: Vector[Vector[Int]] = Vector(Vector(1, 2, 3), Vector(2, 3, 4), Vector(3, 4, 5), Vector(4, 5, 6), Vector(5, 6, 7), Vector(6, 7, 8), Vector(7, 8, 9))
// What does the '3' do? What does the '1' do?
//'3' is the size of the group that is created and '1' is step size of the slide

/* How to "flatten" a list of lists into just a list */
// .flatten unites the vectors within a vector into a single vector
val v:Vector[Int] = Vector(1,2,3,4,5)
val vv:Vector[Vector[Int]] = Vector(Vector(1,2,3), Vector(4,5))
val didItFlatten:Boolean = v == vv.flatten

// Avoids repetition
val newVec:Vector[Int] = Vector(1,2,3,4,1,2,3,1,2,3,2,3,4,4,1,2)
val mySlided:Vector[Vector[Int]] = newVec.sliding(3,1).toVector
val grouped:Map[Vector[Int],Vector[Vector[Int]]] = mySlided.groupBy(n => n)
val madeVec:Vector[(Vector[Int], Vector[Vector[Int]])] = grouped.toVector

// Gives me the key-value pair
madeVec(0)
// Gives me the key
madeVec(0)._1
// Gives me the value(s)
madeVec(0)._2

// Change one part into a String
madeVec(0)._1.mkString(" ")
// print it adding quotation marks so you know it is a string
println(s""" "${madeVec(0)._1.mkString(" ")}" """)
// Wanted to see what would happen to the value
println(s""" "${madeVec(0)._2.mkString(" ")}" """)

// Change another part into a count (number of elements in the key vector)
madeVec(0)._1.size

// Try this with other individual items by changing (0) to other numbers
madeVec(1)._1.mkString(" ")
madeVec(1)._1.size

// Do that to all items in madeVec

val ng:Vector[(String, Int)] = madeVec.map(mv => {
// Shows me the the key
    val s:String = mv._1.mkString(" ")
// Shows me how many times the value occures
    val i:Int = mv._2.size
    (s,i)
})

// See the results
for (n <- ng) {
  println(s""" "${n._1}" occurs ${n._2}""")
}
