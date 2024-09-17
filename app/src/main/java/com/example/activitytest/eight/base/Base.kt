package com.example.activitytest.eight.base

import android.content.UriMatcher
import kotlin.reflect.KProperty

class MyClass<T> {
    fun method(param: T): T {
        return param
    }
}
class MyClass2 {
    fun <T> method(param: T): T {
        return param
    }
}

class MyClass3 {
    fun <T : Number> method(param: T): T {
        return param
    }
}
//Kotlin中委托使用的关键字是by，
// 我们只需要在接口声明的后面使用by关键字，
// 再接上受委托的辅助对象，就可以免去之前所写的一大堆模板式的代码了
class MySet<T>(val helperSet: HashSet<T>) : Set<T> by helperSet {
    fun helloWorld() = println("Hello World")
    override fun isEmpty() = false
}

class MyClass4 {
    var p by Delegate()
}

class Delegate {
    var propValue: Any? = null
    operator fun getValue(myClass: MyClass4, prop: KProperty<*>): Any? {
        return propValue
    }
    operator fun setValue(myClass: MyClass4, prop: KProperty<*>, value: Any?) {
        propValue = value
    }
}

fun main() {
    val myClass = MyClass<Int>()
    val result = myClass.method(123)

    val myClass2 = MyClass2()
    val result2 = myClass2.method<Int>(123)

    val bookDir = 0
    val bookItem = 1
    val categoryDir = 2
    val categoryItem = 3
    val authority = "com.example.activitytest.eight"
    val uriMatcher by later {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, "book", bookDir)
        matcher.addURI(authority, "book/#", bookItem)
        matcher.addURI(authority, "category", categoryDir)
        matcher.addURI(authority, "category/#", categoryItem)
        matcher
    }
}