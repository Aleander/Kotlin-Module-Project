import java.util.Scanner

fun main(args: Array<String>, n: String?) {
    //var archive: Directory<String> = Directory()
    //archive.add("Заметка 1", "Всем привет. Это заметка 1")
    //dir.add("Архив 1",archive)
    //archive = dir.contentsMap.get("Архив 1")!!
    //var note = archive.getContent("Заметка 1")
    //note = dir.getContent("Архив 1")?.getContent("Заметка 1")


//    var dirArchive: Directory<Directory<String>> = Directory() //Директория с архивами (одна на всю программу)
//    val scanner = Scanner(System.`in`)
//    while (true){
//        println()
//        println("Приложение для работы с заметками")
//        println("-----Архивы-----")
//        println()
//        val menu = menuItems("Создать архив","Выход",dirArchive.getNamesList())
//        menu.forEachIndexed { index, item ->
//            println("$index. $item")
//        }
//        val input = scanner.nextLine().toIntOrNull()// получаем либо число, либо null
//        when (input) {
//            null -> println("Ошибка: введите цифру.")
//            0 -> {
//                // Логика создания архива
//                println("Создаём архив...")
//            }
//            menu.lastIndex -> {
//                println("Выход из программы.")
//                break // Прерываем цикл while
//            }
//            in 1 until menu.lastIndex -> {
//                val selectedArchiveName = menu[input]
//                println("Открываем архив: $selectedArchiveName")
//                // Переход в меню заметок этого архива
//            }
//            else -> println("Такого пункта нет. Введите число от 0 до ${menu.lastIndex}.")
//        }
//    }
//}
//fun menuItems(firstPosition:String, lastPosition: String, list: List<String>): List<String>{
//    return listOf(firstPosition) + list + lastPosition

    val scanner = Scanner(System.`in`)
    var dirArchive: Directory<Directory<String>> =
        Directory() //Директория с архивами (одна на всю программу)
    var nameArchive: String? =
        null //Название текущего архива (если null, то текущий - архив с архивами, т.е. главный экран)
    var nameNote: String? = null //Название текущей(выбранной) заметки

    while (true) {
        //Если есть выбранная заметка - показываем её (экран №5 по ТЗ)
        if (nameNote != null) {
            printNote(
                nameNote,
                dirArchive.getContent(nameArchive)?.getContent(nameNote)
            )
            nameNote = null // Сброс имени заметки - возврат в меню выбора заметок
        }
        //Если есть выбранный архив - показываем его содержимое (экран №3 по ТЗ)
        if (nameArchive != null) {
            printMenu(
                nameArchive,
                "Создать заметку",
                "Выход",
                dirArchive.getContent(nameArchive)?.getNamesList()
            )
        }
        //Если нет выбранного архива - показываем список архивов (экран №1 по ТЗ)
        else printMenu("Архивы", "Создать архив", "Выход", dirArchive.getNamesList())
    }

}

//Выводит экраны №1 и № 3 по ТЗ
fun printMenu(nameMenu: String, firstPosition: String, lastPosition: String, list: List<String>?) {
    val menu = listOf(firstPosition) + list + lastPosition
    println("---------------------")
    println("------$nameMenu------")
    menu.forEachIndexed { index, item ->
//            println("$index. $item")
        println("---------------------")
    }
}

//Выводит заметку (экран №5 по ТЗ)
fun printNote(nameNote: String, textNote: String?) {
    println("---------------------")
    println("------$nameNote------")
    println(textNote)
    println("---------------------")
}
