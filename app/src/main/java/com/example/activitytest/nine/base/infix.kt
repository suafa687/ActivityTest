package com.example.activitytest.nine.base

infix fun String.beginsWith(prefix: String) = startsWith(prefix)

class Man(val name: String)

infix fun Man.nameis(name: String) = this.name.equals(name)

infix fun <T> Collection<T>.has(element: T) = contains(element)

infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)

fun main() {
    if ("Hello Kotlin" beginsWith "Hello") {
        println("Hello Kotlin beginsWith Hello")
    }

    val man = Man("lll")
    if (man nameis "lll") {
        println("name is lll")
    }

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    if (list has "Banana") {
        println("list has Banana")
    }

    val map = mapOf("Apple" with 1, "Banana" with 2, "Orange" with 3, "Pear" with 4,
        "Grape" with 5)

    println(map)
}