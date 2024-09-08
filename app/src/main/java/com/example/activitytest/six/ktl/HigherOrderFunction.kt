package com.example.activitytest.six.ktl

class HigherOrderFunction {}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    this.block()
    return this
}

inline fun inlineTest(block1: (String) -> Unit, noinline block2: (String) -> Unit) {
    block1("today")
    // 为什么Kotlin还要提供一个noinline关键字来排除内联功能呢？
    // 这是因为内联的函数类型参数在编译的时候会被进行代码替换，
    // 因此它没有真正的参数属性。
    // 非内联的函数类型参数可以自由地传递给其他任何函数，
    // 因为它就是一个真实的参数，而内联的函数类型参数只允许传递给另外一个内联函数，
    // 这也是它最大的局限性。
    inlineTest2({ i -> println(i) }, block2)
}

fun inlineTest2(block1: (String) -> Unit, block2: (String) -> Unit) {
    block1("hello")
    block2("小明")
}

fun printString(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}

inline fun runRunnable(crossinline fun1:()->Unit, noinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
    runnable.run()

    val runnable2 = Runnable {
        fun1()
    }
    runnable2.run()
}
fun main() {
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1, num2, ::plus)
    val result2 = num1AndNum2(num1, num2, ::minus)
    println("result1 is $result1")
    println("result2 is $result2")

    val result3 = num1AndNum2(num1, num2) { n1, n2 ->
        n1 + n2
    }
    val result4 = num1AndNum2(num1, num2) { n1, n2 ->
        n1 - n2
    }
    println("result1 is $result3")
    println("result2 is $result4")

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())

    inlineTest({a-> println(a) },{b-> println(b) })

    // 内联函数和非内联函数还有一个重要的区别，
    // 那就是内联函数所引用的Lambda表达式中是可以使用return关键字来进行函数返回的，
    // 而非内联函数只能进行局部返回。
    val str = ""
    printString(str) { s ->
        println("lambda start")
        // 局部返回
        if (s.isEmpty()) return@printString
        println(s)
        println("lambda end")
    }
    println("main end")

    runRunnable({
        println("lambda start")
        // 局部返回
        if (str.isEmpty()) return@runRunnable
        println(str)
    },{ println("lambda start")
        // 局部返回
        if (str.isEmpty()) return@runRunnable
        println(str)})
}