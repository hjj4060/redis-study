package inaction.section4

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person2(val name: String) {
    companion object : JSONFactory<Person2> {
        override fun fromJSON(jsonText: String): Person2 {
            val cleanName = jsonText.replace("\"", "").trim()
            return Person2(name = cleanName)
        }
    }
}