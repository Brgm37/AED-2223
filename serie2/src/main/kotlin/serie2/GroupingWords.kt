package serie2
import serie2.mapCollections.AEDHashMapAED

fun main(args:Array<String>){
    println("which file wold you like to analise?")
    for (idx in 0 until args.lastIndex){ println("[$idx] ${args[idx]}") }
    var option: Int?
    print("chose the one option: ")
    option = readln().toInt()
    while (option == null || option !in 0 until args.lastIndex){
        print("invalid option chose another: ")
        option = readln().toInt()
    }
    val reader = createReader(args[option]) //create a reading file
    val writer = createWriter(args.last()) //create a writing file
    var line:String? //keep each line of the reader
    val hashMap = AEDHashMapAED<String, String>() //hash map tha contain all the words of the reader
    line = reader.readLine()
    while (line != null){
        val words = line.split(Regex("\\P{L}+")) //splits the line in to an array
        words.forEach { //take each word and make an array to generate a key
            var key = ""
            val chars = it.lowercase().toCharArray()
            chars.sort()
            chars.forEach { char -> key+=char }
            if (chars.isNotEmpty())
                hashMap.put(key, it.lowercase())
        }
        line = reader.readLine()
    }
    val printString = mutableListOf<String>() // the values that are going to be written
    var flag: String? = null // flag to differentiate each key
    for (el in hashMap.iterator()){
        if (flag == null){
            flag = el.key
            printString.add(el.value)
        }
        else if (el.key != flag){
            if (printString.size > 1) writer.println(printString)
            printString.clear()
            printString.add(el.value)
            flag = el.key
        }else
            if(el.value !in printString) printString.add(el.value)
    }
    if (printString.size > 1) writer.println(printString)
    writer.close()
    println("\nEnd of the process. Verify ${args.last()}")
}