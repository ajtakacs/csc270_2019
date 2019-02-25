import scala.io.Source
import edu.holycross.shot.cite


val myList = List("a","b","c")
val myArray = Array("a","b","c")
val myVector = Vector("a","b","c")


val filepath:String = "/vagrant/csc270_2019/projectText.txt"
val myText:Vector[String] = Source.fromFile(filepath).getLines.toVector.filter(_.size > 0)


val wordVec:Vector[String] = {
  val bigString:String = myText.mkString(" ")
  val noPunc:String = bigString.replaceAll("""[,.?;:!)(–—->_]""","").replaceAll(" +", " ")
  val tokenizedVector:Vector[String] = noPunc.split(" ").toVector.filter(_.size > 0)
  tokenizedVector
}

val wordMap:Map[String,Vector[String]] = wordVec.groupBy(w => w)
val quantMap:Map[String,Int] = wordMap.map(m => (m._1, m._2.size))
val mapVec:Vector[(String,Int)] = quantMap.toVector
val wordHisto = mapVec.sortBy(_._2).reverse
val uniqueWords:Vector[String] = wordHisto.map(_._1)

val dictpath:String = "/vagrant/csc270_2019/words.txt" /* I do not have a wordlist available for Hungarian so I used an English vocab list */
val dictEntries:Vector[String] =
Source.fromFile(dictpath).getLines.toVector.filter( _.size > 0 )


val badWords:Vector[String] = uniqueWords.filter( w => {
val fixedW:String = w.replaceAll("cz","c") // specific to my text!
val lowerCaseOkay:Boolean = dictEntries.contains(fixedW.toLowerCase)
val regularOkay:Boolean = dictEntries.contains(fixedW)
val okay:Boolean = (lowerCaseOkay | regularOkay) // '|' means "or"
val notOkay:Boolean = okay == false
notOkay // the last thing you name is the "return value" of a code-block
// if notOkey is true, we keep this word
})

val badWords:Vector[String] = uniqueWords.filter( w => {
  (dictEntries.contains(w.toLowerCase) == false) &
      (dictEntries.contains(w) == false)
})

println(s"\n\nmyText has ${badWords.size} incorrect words according to the English vocab list.\n")
