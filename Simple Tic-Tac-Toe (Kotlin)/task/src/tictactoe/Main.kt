package tictactoe

fun main() {
    val state = readln()
    val ticTacToe = TicTacToe(state)
    ticTacToe.start()
}

class TicTacToe(private val input: String) {

    private var winner = ""
    private val state = mutableListOf<MutableList<Char>>()
    private var numOfCross = 0
    private var numOfCircle = 0
    private var numOfBlank = 0


    init {
        initState()
    }

    fun start() {
        printState()
        play()
        printState()
//        evaluate()
    }

    private fun play() {
        var isInputValid = false
        do {
            val input = readln()

            try {
                val (row, col) = input.split(" ").map { it.toInt().dec() }
                isInputValid = move(row, col)
            } catch (e: NumberFormatException) {
                println("You should enter numbers")
            }
        } while(!isInputValid)
    }

    private fun move(row: Int, col: Int): Boolean {
        return when {
            !isValidCoordinates(row, col) -> {
                println("Coordinates should be from 1 to 3!")
                false
            }
            isBlank(row, col) -> {
                state[row][col] = CROSS
                true
            } else -> {
                println("This cell is occupied! Choose another one!")
                false
            }
        }
    }

    private fun isBlank(row: Int, col: Int): Boolean {
        return state[row][col] == BLANK
    }

    private fun isValidCoordinates(row: Int, col: Int): Boolean {
        return row < GRID_SIZE && col < GRID_SIZE
    }

    private fun initState() {
        var iterator = 0

        for(row in 0..<GRID_SIZE) {
            state.add(mutableListOf())
            for(col in 0..<GRID_SIZE) {
                val character = input[iterator]
                state[row].add(character)
                count(character)
                iterator++
            }
        }
    }

    private fun count(character: Char) {
        when (character) {
            CIRCLE -> numOfCircle++
            CROSS -> numOfCross++
            BLANK -> numOfBlank++
        }
    }

    private fun isValidRatio(): Boolean {
        return Math.abs(numOfCircle - numOfCross) < 2
    }

    private fun printState() {
        println("---------")
        for(row in 0..<GRID_SIZE) {
            print("| ")
            for(col in 0..<GRID_SIZE) {
                print("${state[row][col]} ")
            }
            println("|")
        }
        println("---------")
    }

    private fun evaluate() {
        when {
            isImpossibleState() -> println("Impossible")
            isWinState() -> println("$winner wins")
            isDrawState() -> println("Draw")
            else -> println("Game not finished")
        }
    }

    private fun isImpossibleState(): Boolean {
        return !isValidRatio() || (isWins(CROSS) && isWins(CIRCLE))
    }

    private fun isWinState(): Boolean {
        val isCrossWins = isWins(CROSS)
        val isCircleWins = isWins(CIRCLE)

        return when {
            isCircleWins -> {
                winner = CIRCLE.toString()
                true
            }
            isCrossWins -> {
                winner = CROSS.toString()
                true
            }
            else -> false
        }
    }

    private fun isDrawState(): Boolean {
        return numOfBlank == 0
    }

    private fun isWins(character: Char): Boolean {
        var countWin = 0

        for (row in 0..<GRID_SIZE) {
            val isWin = state[row].all { it == character }
            if (isWin) countWin++
        }

        for (col in 0..<GRID_SIZE) {
            val isWin = (0..<GRID_SIZE).all { row ->
                state[row][col] == character
            }
            if (isWin) countWin++
        }

        val isDiagonalWin = (0..<GRID_SIZE).count { index ->
            state[index][index] == character
        } == GRID_SIZE

        if (isDiagonalWin) countWin++

        val isAntiDiagonalWin = (0..<GRID_SIZE).count { index ->
            state[index][GRID_SIZE - 1 - index] == character
        } == GRID_SIZE

        if (isAntiDiagonalWin) countWin++

        return countWin > 0
    }

    companion object {
        const val GRID_SIZE = 3
        const val CROSS = 'X'
        const val CIRCLE = 'O'
        const val BLANK = '_'
    }
}