package conspect.Chapter9

//вынужден этот конспект писать в объекте, потому что в worksheet что-то слишком многое падает.

object conspect extends App {
  import scala.io.Source
  import scala.io.StdIn.{readDouble, readInt}                    //библиотека для чтения из файлов
  //имя файла и кодировка. кодировку можно опустить, если она совпадает с кодировкой по-умолчанию в системе
  val sourceDir = "C:\\WorkData\\Scala_Projects\\ScalaForImpatientConspect\\src\\main\\scala\\conspect\\Chapter9\\"
  val source = Source.fromFile(sourceDir+"input.txt","UTF-8")
  //не находит файл по косвенной ссылке, потому так.
  val lineIterator = source.getLines()      //возвращает итератор (что это такое, нам пока неизвестно, но запомни)
  for(l<-lineIterator) println(l)           //вот так делать можно
  val stringArr = source.getLines.toArray   //и так тоже.
  val AllInOneStr = source.mkString         //а вот это способ почитать всё в одну строку
  //ВАЖНО! Закончив использовать source нужно его source.close
  //если неоходимо обрабатывать файл посимвольно, то можно использовать в качестве итератора сам объект файла
  for(c <- source) print(c)
  //обработка следующего символа без сдвига указателя в файле:
  val buf = source.buffered
  while(buf.hasNext){
    buf.head    //тот самый следующий символ
    buf.next    //он же, но со сдвигом маркера чтения
  }
  //неуниверсвльный, но очень простой способ поделить текст на строчки разделённые пробелами
  /*val tokens = source.mkString.split("//s+")
  println(tokens.mkString(" "))
  //ну и можно всё преобразовать к чиселкам, если хочется.
  val numbers = for(s<-tokens) yield s.toDouble
  //кстати, строчка выше типовая и при этом довольно громоздкая, поэтому. Коротко, функционально, читабельно:
  val numbers2 = tokens.map(_.toDouble)  //делает то-же самое, что и строчка выше.
  */
  /**
   * Вот там чуть выше ерундень, с которой я не разобрался. По-идее, всё должно работать, более того, этот пример
   * я взял из книжки. Однако вот. Кто знает в чём дело - расскажите =)
   * Если нужно обрабатывать смесь цифр и символов, всегда можно воспользоваться java.util.Scanner
   */
  val age = readDouble()//или readDouble, readLong(),etc. - чтение из консоли
  source.close
  val source1 = Source.fromURL("https://www.google.com/")
  //вот так ХОБА! и почитали страничку!
  val text = source1.getLines
  println(text.mkString("\n"))
  source1.close
  val source2 = Source.fromString("Wello hold")
  for(c<-source2) print(c)
  source2.close
}
