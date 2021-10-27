//-------------------------------------------------файлы и regex--------------------------------------------------------
import scala.io.Source
import scala.io.StdIn.{readDouble, readInt}                    //библиотека для чтения из файлов
//имя файла и кодировка. кодировку можно опустить, если она совпадает с кодировкой по-умолчанию в системе
val source = Source.fromFile(
  "C:\\WorkData\\Scala_Projects\\ScalaForImpatientConspect\\src\\main\\scala\\conspect\\Chapter9\\input.txt","UTF-8")
//не находит файл по косвенной ссылке, потому так.
val lineIterator = source.getLines()      //возвращает итератор (что это такое, нам пока неизвестно, но запомни)
for(l<-lineIterator) println(l)           //вот так делать можно
val stringArr = source.getLines.toArray   //и так тоже.
val AllInOneStr = source.mkString         //а вот это способ почитать всё в одну строку
//но не работатет, зараза.
//ВАЖНО! Закончив использовать source нужно его source.close
//если неоходимо обрабатывать файл посимвольно, то можно использовать в качестве итератора сам объект файла
for(c <- source) print(c)                   //точнее говоря, так написано, но это не работатет.
//обработка следующего символа без сдвига указателя в файле:
val buf = source.buffered
while(buf.hasNext){
  buf.head//тот самый следующий символ
}
//неуниверсвльный, но очень простой способ поделить текст на строчки разделённые пробелами
source.close
val tokens = source.mkString.split("//s+")
println(source.toArray.mkString)
println(tokens.mkString(" "))
//ну и можно всё преобразовать к чиселкам, если хочется.
val numbers = for(s<-tokens) yield s.toDouble
//кстати, строчка выше типовая и при этом довольно громоздкая, поэтому. Коротко, функционально, читабельно:
val numbers = tokens.map(_.toDouble)  //делает то-же самое, что и строчка выше.

/**
 * Если нужно обрабатывать смесь цифр и символов, всегда можно воспользоваться java.util.Scanner
 */
val age = readDouble()//или readDouble, readLong(),etc. - чтение из консоли
