import java.util.Scanner

val scanner = Scanner(System.`in`)
var dirArchive: Directory<Directory<String>> =
    Directory() //Директория с архивами (одна на всю программу)

fun main(args: Array<String>) {

    var nameArchive: String? =
        null //Название текущего архива (если null, то текущий - архив с архивами, т.е. главный экран)
    var nameNote: String? = null //Название текущей(выбранной) заметки
    var menu: MutableList<String> = mutableListOf()// текущее меню

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
            menu = buildList {
                add("Создать заметку")
                addAll(dirArchive.getContent(nameArchive)?.getNamesList().orEmpty())
                add("Выход")
            }.toMutableList()
            printMenu(
                nameArchive,
                menu
            )
        }
        //Если нет выбранного архива - показываем список архивов (экран №1 по ТЗ)
        else {
            menu = buildList {
                add("Создать архив")
                addAll(dirArchive.getNamesList().orEmpty())
                add("Выход")
            }.toMutableList()
            printMenu("Архивы", menu)
        }

        val input = scanner.nextLine().toIntOrNull()// получаем либо число, либо null
        when (input) {
            0 -> {
                if (nameArchive == null) dirArchive.add(
                    inputString("Имя архива"),
                    Directory()// Вместо null - добавил на этапе отладки
                ) //Создаем пустой архив (экран №2 по ТЗ)
                else dirArchive.getContent(nameArchive)?.add(
                    inputString("Имя заметки"),
                    inputString("Текст заметки")
                ) //Создаем заметку (Экран №4 по ТЗ)
            }

            menu.lastIndex -> {
                if (nameArchive == null) break // Прерываем цикл while
                else nameArchive = null // Выход в главное меню
            }

            in 1 until menu.lastIndex -> {
                if (nameArchive == null) nameArchive = input?.let { menu[it] } // Переходим в архив
                else nameNote = input?.let { menu[it] }// Открываем заметку
            }

            else -> {
                println("------Не верный ввод------")
            }
        }
    }

}

//Выводит экраны №1 и № 3 по ТЗ
fun printMenu(
    nameMenu: String,
    list: List<String>?
) {
    println("------$nameMenu------")
    list?.forEachIndexed { index, item ->
        println("$index. $item")
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
fun inputString(ask: String): String {
    while (true) {
        println("------Введите $ask------")
        val input = scanner.nextLine().trim() //Убираем пробелы по краям
        if (input.isNotEmpty()) return input //Получаем непустую строку
        println("------Не верный ввод------")
    }
}
