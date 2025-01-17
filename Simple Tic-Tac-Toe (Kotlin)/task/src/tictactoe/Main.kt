package tictactoe

fun main() {
    val ticTacToe = TicTacToe()
    ticTacToe.start()
}

class TicTacToe {
    fun start() {
        val state = readln()
        var iterator = 0

        println("---------")
        for(row in 0..<GRID_SIZE) {
            print("| ")
            for(col in 0..<GRID_SIZE) {
                print("${state[iterator]} ")
                iterator++
            }
            println("|")
        }
        println("---------")
    }

    companion object {
        const val GRID_SIZE = 3
        const val CROSS = "X"
        const val CIRCLE = "O"
    }
}