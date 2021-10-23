package conspect.Chapter3

import scala.collection.mutable.ArrayBuffer

object someTests extends App{
  val b = new ArrayBuffer[Int](0)
  b+=1                  //добавление элемента в конец буфера (!)
  b+=(1,2,3,4,5)        //можно и так
  println(b)
}
