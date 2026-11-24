// Общая логика для арихивов (хранят заметки) и папок с архивами (хранят архивы)
class Directory<T> {
    var contentsMap = mutableMapOf<String, T>()

    //Добавляем элемент, если название уникально. Возвращаем результат(да/нет)
    fun add(name: String, t: T): Boolean {
        if (name in contentsMap) return false
        else {
            contentsMap.put(name, t)
            return true
        }
    }
    //Доступ к значению по ключу (получаем либо заметку, либо архив)
    fun getContent(name: String): T? {
        if (name in contentsMap) return contentsMap[name]
        else return null
    }
}




