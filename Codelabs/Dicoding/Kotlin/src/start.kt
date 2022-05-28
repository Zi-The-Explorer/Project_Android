fun main() {
    Huruf().a()
}

class Huruf {
    fun a() {
        val r = 1..10
        for (i in r){
            println("*")
            if (i >= 1){
                println("|")
            }
        }
    }
}