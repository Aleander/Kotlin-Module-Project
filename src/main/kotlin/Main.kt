import java.util.Scanner


fun main(args: Array<String>) {
    //var archive: Directory<String> = Directory()
    //archive.add("Заметка 1", "Всем привет. Это заметка 1")
    //dir.add("Архив 1",archive)
    //archive = dir.contentsMap.get("Архив 1")!!
    //var note = archive.getContent("Заметка 1")
    //note = dir.getContent("Архив 1")?.getContent("Заметка 1")


    var dirArchive: Directory<Directory<String>> = Directory() //Директория с архивами (одна на всю программу)
    val scanner = Scanner(System.`in`)
    while (true){
        println()
        println("Приложение для работы с заметками")
        println("-----Архивы-----")
        println()
        val menu = menuItems("Создать архив","Выход",dirArchive.getNamesList())
        menu.forEachIndexed { index, item ->
            println("$index. $item")
        }
        val input = scanner.nextLine().toIntOrNull()// получаем либо число, либо null
        when (input) {
            null -> println("Ошибка: введите цифру.")
            0 -> {
                // Логика создания архива
                println("Создаём архив...")
            }
            menu.lastIndex -> {
                println("Выход из программы.")
                break // Прерываем цикл while
            }
            in 1 until menu.lastIndex -> {
                val selectedArchiveName = menu[input]
                println("Открываем архив: $selectedArchiveName")
                // Переход в меню заметок этого архива
            }
            else -> println("Такого пункта нет. Введите число от 0 до ${menu.lastIndex}.")
        }
    }
}
fun menuItems(firstPosition:String, lastPosition: String, list: List<String>): List<String>{
    return listOf(firstPosition) + list + lastPosition
}