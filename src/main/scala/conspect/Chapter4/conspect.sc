import java.io.File
import java.util.Scanner
import scala.collection.mutable

//-----------------------------------------------------Map--------------------------------------------------------------
val scores = Map("Alice"->10,"Bob"->4,"Onew"->7)//инициализация неизменяемого ассоциативного массива
val cores = new mutable.HashMap[String,Int]     //создание изменяемого map
//в Scala ассоциативный массив - это коллекция пар (KeySet is not set?)
scores("Alice")                                               //обращение к map
//scores("Hon")                                               //выкинет исключение если не найдёт такой ключ
val bobScore = if(scores.contains("Bob")) scores("Bob") else 0//проверка на наличие
val onewScore = scores.getOrElse("Bob",0)                     //тоже что и выше, но более компактно
val aliceScore = scores.get("Alice")                       //вернёт объект Option значение типа value(в map) либо None, если не найдёт ключа
val scores1 = scores.withDefaultValue(0)                   //если не найдёт элемент - вернёт 0
val zeldaScore2 = scores1("Zelda")
val scores2 = scores.withDefault(_.length)                 //вернёт результат функции length применённой к key то есть количество симводов
val zeldaScore = scores2("Zelda")
cores("Bob") = 7                          //cores - mutable, тогда поместит туда пару "Bob"->7
cores
cores("Bob") = 70                         //cores - mutable, тогда изменит связанное с "Bob" значение на 70
cores
cores += ("Fred"->666,"Tony"->86)         //добавляем нескольео элементов
cores
cores -= "Bob"                            //удаление пары по ключу
cores
val newScores = scores - ("Alice","Bob")//неизменяемый массив нельзя изменить(Неожиданно...) однако можно создать новый с учётом изменений
//scores = scores + ("Loyd"->20)        //оно не работает, но пищут, будто должно (ошибка?)
//при создании из immutable нового immutable объекта они поделят старую часть, так что по сути мы не будем тратить память
//---------------------------------------------обход и сортировка-------------------------------------------------------
val map = Map("a"->1,"b"->2)
for((k,v) <- map) print(k+"->"+v+", ")
map.keySet//keys
map.values//values
val map1 = for((k,v)<-map) yield (v,k)//инвертируем ассоциативный массив
//---------------------------------------------------Кортежи------------------------------------------------------------
val a = (1,"75","sdf",3.14)   //создание кортежа
a._1                          //обращение к первому элементу(НЕ 0, А К 1!)
a _2                          //как вседа, так тоже можно
val (fst,sec,third,forth) = a //разобрать на части
fst
sec
third
forth
val (r,_,_,t) = a             //_ вместо ненужных элементов
val arr0 = Array(2,20,3)
val arr1 = Array("<","-",">")
val pairs = arr1.zip(arr0)    //создаст массив пар из соответствующих элементов arr1 и arr0
pairs.foreach(n=>print(n._1 * n._2))
//---------------------------------------------------Упражнения---------------------------------------------------------
//1
val prices = Map("tool"->10)
val divPrices = for((k,v)<-prices) yield k->v*(9/10)
//2
val scanner = new Scanner(new File("input.txt"))
var map0 = new mutable.HashMap[String,Int]()
while(scanner.hasNext()) {
  {
    val str = scanner.next()
    if(map0.contains(str))
      map0 += (str->(map0(str)+1))
    else
      map0 += (str->1)
  }
}
for((k,v)<-map0) println(f"$k amount = $v")
//3
