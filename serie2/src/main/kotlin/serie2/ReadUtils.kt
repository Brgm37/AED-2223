package serie2
import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter

fun createReader(fileName: String): BufferedReader {
    return BufferedReader(FileReader(fileName))
}

fun createWriter(fileName: String?): PrintWriter {
    return PrintWriter(fileName)
}

/** Usage Example
 *  File on the project Directory:
 *  Copy Input File to OutputFile.
 * **/
fun main( ){
    val br=createReader("carochinha.txt")
    val pw=createWriter("output.txt")
    var line:String?
    line=br.readLine()
    while(line!=null){
        // val words = str.split(Regex("\\W"))
        val words = line.split(Regex("\\P{L}+"))
        pw.println(words)
        line=br.readLine()
    }
    pw.close()
}