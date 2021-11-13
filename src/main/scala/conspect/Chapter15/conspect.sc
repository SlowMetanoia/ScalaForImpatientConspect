/**
 * Глава 15: Аннотации
 */
/**
 * Аннотации - это способ добавить в код теги, которые будет обрабатывать компилятор, или другие инструменты.
 * Здесь речь пойдёт об аннотациях из Java и Scala
 * В Scala можно безболезненно использовать и те, и другие.
 * Аннотиации Java не влияют на порядок компиляции, а просто добавляют в байт-код данные для дальнейшей обработки
 * чем-то ещё
 * Аннотиации Scala напрямую влияют на процесс компиляции.
 * Например, @BeanProperty - вызывает автогенерацию методов чтения и записи.
 */
/**
 * В этой главе куча специфики, которую я описывать не буду, на пару интересных моментов, таки скажу.
 */
/**
 * Для совместимости с Java перед функцией, кидающей исключения нужна аннотация @throws(className)
 * Если используются JavaBeans, то нужно глянуть на @BeanProperty
 */
/**
 * Оптимизация.
 * @tail-rec для функций с хвостовой рекурсией преобразует их в циклы и экономит место на стеке.
 * @switch арантирует, что match скомпилируется в таблицу переходов.
 */
//TODO:Почитать и написать сюда про JUnit и его аннотации. Слишком полезен, чтобы не пользоваться.
