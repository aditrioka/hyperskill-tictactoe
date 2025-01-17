package tictactoe

import kotlin.random.Random

const val GRID_SIZE = 3
const val CROSS = "X"
const val CIRCLE = "O"

fun main() {
    repeat(GRID_SIZE) {
        repeat(GRID_SIZE) {
            val character = if (Random.nextBoolean()) CIRCLE else CROSS
            print(character)
            if (!isLastCell(it)) print(" ")

        }
        if(!isLastCell(it)) println()
    }
}

fun isLastCell(col: Int): Boolean = col == GRID_SIZE