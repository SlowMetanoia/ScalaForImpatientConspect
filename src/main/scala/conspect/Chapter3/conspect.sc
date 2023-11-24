import scala.collection.mutable.ArrayBuffer
import scala.language.postfixOps

//--------------------------------------------------------массивы-------------------------------------------------------
//массивы из scala и java являются совместимыми (как, пока хз)
val numbers = new Array[Int](3) //инициализируется "0"
val numbers1 = Array(1,2,3,4)
println(numbers)
val strings = new Array[String](10) //инициализируются null
val s = Array("Hello", "Hold") //при инициализиции значениями НЕ нужно писать new
s(0) //() для доступа к элементам
//---------------------------------------буферы(массивы нефиксированной длинны)-----------------------------------------

val b = new ArrayBuffer[Int](0) //по неизвестной причине тут это всё не работает, но работает в someTests
b.+=(1) //добавление элемента в конец буфера (!)
b += (1, 2, 3, 4, 5) //можно и так
b ++= Array(8, 13, 21) //можно долить в буффер коллекцию
b.trimEnd(5) //удалить 5 элементов с концадобавление и удаление элементов с конца производится за константное время
b.insert(2, 7) //добавление элемента по индексу
b
b.remove(2) //удаление элемента по индексу
b.remove(2, 2) //нескольких
b.toArray //преобразование в массив, можно и обратно(toBuffer)

val func4: Int=>Int = x => if(x==1) 1 else x * func4(x-1)
val func5: (Double,Double) => Double = (x,y) => math.sqrt(x*x+y*y)
val func6: Int=>Boolean = x => {
  x%2 == 0
}

val func7:Int=>Int = x=>x+1

val tuple2:(Int,String) = (123,"sdf")

Seq(1,3,5,7,10,20).filter(func6)
Seq(1,3,5,7,9,20).map(x=>x%3)
Seq(1,3,5,7,9,20).reduce((x,y)=>x+y)
Seq(1,3,5,7,9,20).find(_ == 7)
Seq(1,3,5,7,9,20).contains(7)
Seq(1,3,5,7,9,20).indexOf(7)
Seq(1,3,5,7,9,20).product
Seq(1,3,5,7,9,20).zip(Seq(6,5,4,3,2,1).map(_.toString))
Seq(1,3,5,7,9,20).forall(func6)
Seq(1,3,5,7,9,20).exists(func6)
Seq(1,3,5,7,9,20).count(func6)

def compose[A,B,C](f1:A=>B,f2:B=>C):A=>C =
  x => f2(f1(x))

val func8 = compose[Int,Int,Boolean](func4,func6)
func8(1)

//----------------------------------------------------обход массивов----------------------------------------------------
val b = ArrayBuffer(1, 2, 3, 4, 5)
for (i <- 0 until b.length) print(s"($i): ${b(i)} ")

// for i <- range - обеспечивет подстановку в i  всех элементов из range
// 1 to 10 = (1 2 3 4 5 6 7 8 9 10) - все
// 1 until 10 = (1 2 3 4 5 6 7 8 9) - все без последнего
for (i <- b.length - 1 until 0 by -1) print(f"$i: ${b(i)} ")  //в обратном порядке
for (i <- b.indices) print(f"$i: ${b(i)} ")               //потому что индексы можно вытащить и они тоже образуют коллекцию
for (i <- b.indices.reverse) print(f"$i: ${b(i)} ")       //в обратном порядке (Ругается, но работает)
for (i <- b) print(f"$i")                                 //foreach, если индексы не нужны
//-----------------------------------------------преобразование массивов------------------------------------------------
val a = Array(1, 2, 3, 4, 5)
val r0 = for (elem <- a) yield 2 * elem //мы не изменяем старый массив, а создаём на его основе новый, используя генератор
                                        //кстати, yield  создаёт коллекцию того-же типа, что и итерируемая, то есть в данном случае - буфер
val r1 = for (elem <- a if elem % 2 == 0) yield 2 * elem  //цикл, удваивающий чётыне элементы
val r2 = a.filter(_ % 2 == 0) map {2 * _}                 //функциональное решение из будущего =)
//Задачка: есть последовательность целых чисел. Нужно удалить все отрицательные.
val f = ArrayBuffer(1,-1,-4,5,7,-11,0,-1)
val r3 = for (elem <- f if elem >=0) yield elem           //новый массив n

val positionsToRemove = for(i <- a.indices if a(i) < 0) yield i
for(i <- positionsToRemove.reverse) f.remove(i)           //старый изменённый n*log n

val positionsToKeep = for(i <- a.indices if a(i)>=0) yield i
for(j <- positionsToKeep) a(j) = a(positionsToKeep(j))
f.trimEnd(a.length - positionsToKeep.length)              //старый изменённый эффективно n
//---------------------------------------------------типичные алгоритмы-------------------------------------------------
Array(1,2,7,8).sum      //сумма
Array(1,2,7,8).min      //min
Array(1,2,7,8).max      //max
                        //тип должен быть числовой.
val r4 = ArrayBuffer(7,1,2,5,0,-3).sorted           //сортировка
val r5 = ArrayBuffer(7,1,2,5,0,-3).sortWith(_ > _)  //можно задать свою функцию  (неясно, как это работает)
val r6 = ArrayBuffer(7,1,2,5,0,-3).sortWith(_ < _)

val g = Array(1,2,5,3,6,1,2,9)
scala.util.Sorting.quickSort(g)//сортировка "на месте"
//чтобы использовать min,max,quickSort, типы элементов должны поддерживать операцию сравнения, то есть иметь trait Ordered
g.toString                //Java   но выводит тип
g.mkString("<",",",">")   //Scala  нормальное форматирование

//-------------------------------------------------------Scaladoc-------------------------------------------------------
// Трейты в Scala
//Traversable
//Iterable
//Почитать Scaladoc
//------------------------------------------------------Многомерные-----------------------------------------------------
//val arr = new Array.ofDim[Int](3,4) - но эта хренть не работает
//-----------------------------------------------------transactions-----------------------------------------------------
//-----------------------------------------------------упражнения-------------------------------------------------------
//1
def range(n:Int) = {
  (0 until n).toArray
  }
//2
def swapp(arr:Array[Int]):Unit = {
  for (i <- arr.indices if (i%2==0)&&(i<arr.length-1)) {
    val a = arr(i)
    arr(i) = arr(i+1)
    arr(i+1) = a
    }
}
//3
def swapp2(arr:Array[Int]) ={
  for (i<-arr.indices) yield if((i%2==0)&&(i<arr.length-1)) {
      val a = arr(i)
      arr(i) = arr(i+1)
      arr(i+1) = a
      arr(i)
    }
  else arr(i)
}
//5
def avg(arr:Array[Double]) =
  {
    arr.sum/arr.length
  }
//6
//где-то тут я задолбался...