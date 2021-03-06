import org.graalvm.compiler.core.common.calc.Condition

import java.lang.Thread.sleep
import scala.Boolean

/**
 * Глава 12: Функции высшего порядка
 * Ура, добрались: функциональная SCALA!!!
 */
/**
 * Если коротко, то функция в scala - это тоже значение, а значит, её можно, например, передавать как параметр,
 * или вернуть, как результат
 */
//переменная типа Int=>Int
val a:Int=>Int = _+1
//в данном случае _ указывает на то, что вместо него будет подставлен аргумент имеющий тип
//Int(так как он указан в сигнатуре)
/**
 * Кстати, можно вот так
 */
val b:Int=>Int=>Int = a=> _ + a
val d = b(1)    //функция
val e = b(1)(3) //вызов
val f = d(3)    //вызов
//-------------------------------------------Анонимные функции----------------------------------------------------------
/**
 * В принципе, речь о том, что у функций может не быть идентификаторов.
 */
(a:Int)=>1+a
//------------------------------------------Функции высшего порядка-----------------------------------------------------
/**
 * Это любые функции, принимающие другие функции, как аргументы.
 * Они, в принципе могут и возвращать функции.
 */
//Это функция, принимающая Int, возвращающая Int=>Int
//Но
val c:Int=>Int=>Int = a=>b=>a+b
//Функция принимаяя 2 инта, возвращающает инт.
/**
 * Поэтому, как мне кажется, стоит хотябы результат заключать в (), если он неочевиден.
 */
//принимает функцию A=>B, возвращает Функцию Iterable[A]=>Iterable[B]
def myMapConstructor[A,B] = (f:A=>B)=>(a:Iterable[A])=>for(el<-a) yield f(el)

/**
 * Тут говорится ещё про преобразование в SAM.
 */
//-----------------------------------------------Карринг(Каррирование)--------------------------------------------------
/**
 * Это рекурсивное преобразование функции, принимающей n переменных в функцию, принимающую 1 переменную и возвращающую
 * функцию, принимающую n-1 переменную.
 * Такие каррированные функции можно определять вот так:
 */
//Композиция. Принимает 2 функции. Исполняет их последовательно.
def comp[A,B,C](f1:A=>B)(f2:B=>C):A=>C = a=>f2(f1(a))
//------------------------------------------------Управляющие конструкции-----------------------------------------------
/**
 * Это скрипт без входных параметров и без возвращаемого значения.
 * Такую штуку, например, можно запускать в отдельном потоке
 */
def runInThread(code: ()=>Unit): Unit ={
  new Thread(new Runnable {
    override def run(): Unit = code
  })
}
runInThread(()=>{println("wello");sleep(1000);println("bye")})

/**
 * казалось бы, всё хорошо. НО! Можно написать короче и элегантнее
 * 1. Убрать () в определении функции и параметр тоже можно будет передавать без этой ерунды
 * 2. Создавать Thread сразу через code
 */
def runInThread2(code: =>Unit) = new Thread{override def run = code}
//Красота!
runInThread2 {println("wello");sleep(1000);println("bye")}
//Прелесть!
/**
 * В этот момент можно резко вспомнить некоторые языковые конструкции, например, while.
 * Как должно быть выглядит while?
 * Так?
 */
def wl(condition: =>Boolean)(code: =>Unit): Unit = {if(condition) code ;wl(condition)(code)}
//Ну, тогда можно и until бахнуть
def until(condition: =>Boolean)(code: =>Unit): Unit = {if(!condition) code;until(condition)(code)}
//И вообще, если подумать, то многие языовые конструкции начинают выглядеть просто и понятно.
var x = 10
wl(x!=0){
  x -=1
  print(x+' ')
}

/**
 * Увы, у меня эта штука, ровно как и runInThread работать здесь не стала, но мысль, наверное, понятна =)
 */