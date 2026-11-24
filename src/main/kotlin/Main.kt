import java.util.Scanner


fun main(args: Array<String>) {
    println("Приложение для работы с заметками")
    var archive: Directory<String> = Directory()
    var dir: Directory<Directory<String>> = Directory()
    archive.add("Заметка 1", "Всем привет. Это заметка 1")
    dir.add("Архив 1",archive)
    archive = dir.contentsMap.get("Архив 1")!!

    var note = archive.getContent("Заметка 1")
    note = dir.getContent("Архив 1")?.getContent("Заметка 1")


}