class App {
    companion object {
        private const val NAME = "Application"
        private const val VERSION = "1.0"
        private const val AUTHOR = "Akhmad Fauzi"
    }

    fun chooseCommandLine(){

        println("Choose command\n")

        val arrCm = arrayListOf(CommandText.appNameView, CommandText.appVersionView, CommandText.appAuthorView)

        for (i in arrCm) println(i)
        for (i in 1..100) {
            print("=")
            if (i == 50) print("Choose command")
            if (i >= 100) println("\n")
        }

        val resultCommand: (String) -> String = {
            when(it) {
                NAME -> "$it adalah nama aplikasi yang sedang dibuat"
                VERSION -> "$it adalah versi aplikasi yang sedang dibuat"
                AUTHOR -> "$it adalah nama pembuat aplikasi yang sedang dibuat"
                else -> "Command not found"
            }
        }

        when(readln().lowercase()){
            arrCm[0] -> println(resultCommand(NAME))
            arrCm[1] -> println(resultCommand(VERSION))
            arrCm[2] -> println(resultCommand(AUTHOR))
        }

    }

}
