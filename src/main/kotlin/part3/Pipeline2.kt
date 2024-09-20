package part3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/**
 * @Description : Pipeline2.java
 * @author      : heon
 * @since       : 2024-09-20
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-20       heon         최초 생성
 *
 * <pre>
 */
fun main1() = runBlocking {
    var cur = numbersFrom(2)
    for (i in 1..10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren()
    println("Done")
}

fun CoroutineScope.numbersFrom(start: Int) = produce {
    var x = start
    while (true) send(x++) // infinite stream of integers from start
}

fun CoroutineScope.filter(numbers:ReceiveChannel<Int>, prime:Int) = produce {
    for (x in numbers){
        if (x % prime != 0) send(x)
    }
}

fun main(args: Array<String>) {
    var cur = getNumberIteratorFrom(2)
    for (i in 1..10) {
        val prime = cur.next()
        println(prime)
        cur = getFilteredNumberIterator(cur, prime)
    }
    println("Done")
}

fun getNumberIteratorFrom(start: Int) = iterator {
    var x = start
    while (true) yield(x++) // infinite stream of integers from start
}

fun getFilteredNumberIterator(numbers: Iterator<Int>, prime: Int) = iterator {
    for (x in numbers) {
        if (x % prime != 0) yield(x)
    }
}