val help = """help - show help message +
    stop | exit - exit program +
    clear - erase all keys +
    list - show all keys and values +

    add <key> - add new <key> +
    set <key> - set <value> to <key> +
    del <key> - delete <key>
    del <value> - delete key with <value>
    show <key> - show value for <key>
    find <part> - show all keys and values that contains <part>

    <key> = <value> - see `set <key>`
    <key> - see `show <key>`
""".trimIndent()

var myMap: MutableMap <String, String>? = null

fun load(filename: String = "data.json") {
    //TODO
}

fun write(filename: String = "data.json") {
    //TODO
}

fun set(key: String, value: String) {
    if (myMap == null)
        myMap = mutableMapOf()
    myMap!![key] = value
}

fun del(key: String) : Boolean {
    if (myMap != null && myMap!!.containsKey(key)) {
        myMap!!.remove(key)
        return true
    }
    return false
}

fun get(key: String) : String? {
    if (myMap != null)
        return myMap!!.getOrDefault(key, null)
    return null
}

fun clear() {
    if (myMap != null)
        myMap!!.clear()
}

fun contains(key: String) : Boolean {
    return myMap != null && myMap!!.containsKey(key)
}

fun printBold(text: String) {
    val ESC = "\u001B"
    val BOLD   = ESC + "[1"
    val NORMAL = ESC + "[0"
    val colorWhite = ";37m"

    print("$BOLD$colorWhite$text")
    print("$NORMAL$colorWhite${""}")
}

fun main(args: Array<String>) {
    printBold("Some bold text\n")

    print("Hello!\n" + help + "\n")
    load()
    while (true) {
        try {
            print("\nYour command: ")
            val input = readLine()!!
            if (input.startsWith("add")) {
                if (input.length > "add ".length) {
                    val key = input.substring("add ".length + 1)
                    if (contains(key))
                        println("This key already exists!")
                    else {
                        print("And now, value for this key: ")
                        val value = readLine()!!
                        set(key, value)
                        println("Added successfully")
                    }
                } else
                    print("usage: `add <key>`")
            } else if (input == "help")
                println(help)
            else if (input.startsWith("set")) {
                if (input.length > "set ".length) {
                    val key = input.substring("add ".length + 1)
                    if (contains(key)) {
                        print("And now, value for this key: ")
                        val value = readLine()!!
                        set(input.substring("set ".length + 1), value)
                        println("Set successfully")
                    } else
                        println("I haven't this key!")
                } else
                    println("usage: `set <key>`")
            } else if (input == "stop" || input == "exit") {
                write()
                println("Goodbye...")
                break
            } else if (input == "clear") {
                clear()
                println("Cleared!")
            } else if (input == "list")
                for ((key, value) in myMap!!)
                    println("\"" + key + "\" : \"" + value + "\"")
            else if (input.startsWith("del")) {
                if (input.length > "add ".length) {

                } else
                    print("usage: `del <key>`")
            }
        } catch (e: InterruptedException) {
            //println("\nCanceled")
            println()
        } catch (e: Exception) {
            println("Sorry, but an exception occupied (" + e.message + ")")
        }
    }
}