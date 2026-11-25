import java.util.Scanner
val scanner = Scanner(System.`in`)
fun main(args: Array<String>, n: String?) {

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

        val input = scanner.nextLine().toIntOrNull()// получаем либо число, либо null
        when (input) {
            0 -> {
                // Создаем архив или заметку
                println("Создаём архив или заметку")
            }
            menu.lastIndex -> {
                // либо выходим из программы либо переходим в меню архивов
                println("Выход из программы.")
                break // Прерываем цикл while
            }
            in 1 until menu.lastIndex -> {
                val selectedArchiveName = menu[input]
                println("Открываем архив: $selectedArchiveName")
                // Переход в меню заметок этого архива
            }
            else -> println("------Не верный ввод------")
        }
    }

}

//Выводит экраны №1 и № 3 по ТЗ
fun printMenu(nameMenu: String, firstPosition: String, lastPosition: String, list: List<String>?) {
    val menu = listOf(firstPosition) + list + lastPosition
    println("------$nameMenu------")
    menu.forEachIndexed { index, item ->
//            println("$index. $item")
    }
}

//Выводит заметку (экран №5 по ТЗ)
fun printNote(nameNote: String, textNote: String?) {
    println("------$nameNote------")
    println(textNote)
    println("------Для выхода введите любой символ------")
    val input = scanner.nextLine() //Ждем пока пользовать прочитает заметку
}

//Запрос строки (для организации экранов №2 и №4 по ТЗ)
fun inputString(ask: String): String{
    while (true){
        println("------Введите $ask------")
        val input = scanner.nextLine().trim() //Убираем пробелы по краям
        if (input.isNotEmpty()) return input //Получаем непустую строку
        println("------Не верный ввод------")
    }
}
