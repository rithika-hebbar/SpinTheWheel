// Author: Rithika Hebbar

import java.util.*;

// data class for player data
    data class Player(var playerName: String? = "", var totalPoints: Int = 0)

// compact functions, Random() function
    fun randomNumber(): Int = Random().nextInt(9)
    fun toPoints(score: Int) = score * 10

// lambda
    var increment = { numValue : Int -> numValue + 1 }

// higher order function
    fun convert(score: Int, conversion: (Int)-> Int): Int{
        return conversion(score)
    }

// passing nullable type and an instance of Player class
    fun guessTheNumber(num: Int?, player: Player) {
        var randNo = increment( randomNumber() )

    // calling the higher order function with reference
        player.totalPoints += convert(randNo, ::toPoints)

        if (num == randNo) {
            println("Great guess!\nCongratulations, you won ${ convert(randNo, ::toPoints) } points!\nYour total points are ${ player.totalPoints }.")
        } else {
            println("That's wrong. The number was ${ randNo }. Better luck next time!")
        }
    }

// passing nullable type and an instance of Player class
    fun guessTheColor(clr: String?, player: Player) {
    // array of colors to guess from
        val colors = arrayOf("Blue", "Red", "Green", "Yellow", "Orange", "Purple")
        var colorNum = Random().nextInt(colors.size)

        if (clr == colors[colorNum] || clr == colors[colorNum].toLowerCase()) {
        // calling the higher order function with reference
            player.totalPoints += convert(increment(colorNum), ::toPoints)
            println("That's right!\nCongratulations, you won ${ convert(increment(colorNum), ::toPoints) } points!\nYour total points are ${ player.totalPoints }.")

        } else {
            println("That's wrong. The color was ${ colors[colorNum] }. Better luck next time!")
        }
    }

// function to riddle the player
    fun ask(quesNo: Int, ques: String, ans: String, player: Player) {
        println("Note: Answer with one word.\n${ ques }")
        var answerInput = readLine()

        if (answerInput == ans || answerInput == ans.toLowerCase()) {
        // calling the higher order function with reference
            player.totalPoints += convert(increment(quesNo), ::toPoints)
            println("That's correct!\nCongratulations, you won ${ convert(increment(quesNo), ::toPoints) } points!\nYour total points are ${ player.totalPoints }.")

        } else {
            println("That's wrong! The correct answer is $ans")
        }
    }

// default argument
    fun riddleMe(randQues: Int = randomNumber(), player: Player) {
    // immutable lists of riddles and answers
        val questions = listOf(
            "What grows when it eats, but dies when it drinks?",
            "What tastes better than it smells?",
            "What has hands but can not clap?",
            "What has many keys but can't open a single lock?",
            "I'm tall when I'm young and I'm short when I'm old. What am I?",
            "What can travel around the world while staying in a corner?",
            "What has an eye but can not see?",
            "Patches over patches... without any stitches... What am I?",
            "What has a neck but no head?"
        )
        val answers = listOf("Fire", "Tongue", "Clock", "Piano", "Candle", "Stamp", "Needle", "Cabbage", "Bottle")

        when (randQues) {
            // named parameters
            0 -> ask(0, questions[0], answers[0], player=player)
            1 -> ask(1, questions[1], answers[1], player=player)
            2 -> ask(2, questions[2], answers[2], player=player)
            3 -> ask(3, questions[3], answers[3], player=player)
            4 -> ask(4, questions[4], answers[4], player=player)
            5 -> ask(5, questions[5], answers[5], player=player)
            6 -> ask(6, questions[6], answers[6], player=player)
            7 -> ask(7, questions[7], answers[7], player=player)
            8 -> ask(8, questions[8], answers[8], player=player)
        }
    }


fun main() {
    // using the Scanner class from java library to show interoperability with Java
    val reader = Scanner(System.`in`)

    do {
        println(
            "\n" +
                    " __       _         _   _                     _               _ \n" +
                    "/ _\\_ __ (_)_ __   | |_| |__   ___  __      _| |__   ___  ___| |\n" +
                    "\\ \\| '_ \\| | '_ \\  | __| '_ \\ / _ \\ \\ \\ /\\ / / '_ \\ / _ \\/ _ \\ |\n" +
                    "_\\ \\ |_) | | | | | | |_| | | |  __/  \\ V  V /| | | |  __/  __/ |\n" +
                    "\\__/ .__/|_|_| |_|  \\__|_| |_|\\___|   \\_/\\_/ |_| |_|\\___|\\___|_|\n" +
                    "   |_|                                                          \n"
        )

        // creating an instance of Player class
        var player = Player()
        print("Welcome!\nPlayer name: ")
        player.playerName = readLine()
        println("Hello ${ player.playerName }! ")

        // immutable list of games on the wheel
        val games = listOf("Guess the color", "Unlucky", "Riddle me", "+25 bonus points", "Guess the number","+50 bonus points!")
        println("> ${games[0]}\n> ${games[1]}\n> ${games[2]}\n> ${games[3]}\n> ${games[4]}\n> ${games[5]}\nEnter s to spin!")
        var spin: String? = readLine()

        do {
            if (spin == "s") {
                var picker = Random().nextInt(6)
                println("-> ${games[picker]}!")
                when (picker) {
                    0 -> {
                        println("Color, color! \nBlue\tRed\t\tGreen\tYellow\tOrange\tPurple\nWhat color am I???")
                        var clr = readLine()
                        guessTheColor(clr,player)
                    }
                    1 -> println("Better luck next time!")
                    // named parameter
                    2 -> riddleMe(player=player)
                    3 -> {
                        player.totalPoints += 25
                        println("Yay! Your total points are now ${ player.totalPoints }!")
                    }
                    4 -> {
                        println("I am one of the below\n1\t2\t3\t4\t5\t6\t7\t8\t9\nGuess me!")
                        var number = reader.nextInt()
                        guessTheNumber(number,player)
                    }
                    5 -> {
                        player.totalPoints += 50
                        println("Hurray! Your total points are now ${ player.totalPoints }!")
                    }
                }
            } else {
                println("Please enter s the next time you want to spin.")
            }
            println("Enter s to spin again and play | Press any key and enter to quit the game")
            spin = readLine()
            if (spin != "s"){
                println("Nice play!")
            }
        } while (spin == "s")
    } while (true)
}