package com.example.activitytest.ten.base

import android.content.Context
import android.content.Intent
import com.example.activitytest.ten.TenMainActivity

inline fun <reified T> getGenericType() = T::class.java

inline fun <reified T> startActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}
inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

open class Person(val name: String, val age: Int)
class Student(name: String, age: Int) : Person(name, age)
class Teacher(name: String, age: Int) : Person(name, age)
class SimpleData<T> {
    private var data: T? = null

    fun set(t: T?) {
        data = t
    }

    fun get(): T? {
        return data
    }
}
class SimpleData2<out T>(val data: T?) {
    fun get(): T? {
        return data
    }
}
fun handleSimpleData(data: SimpleData<Person>) {
    val teacher = Teacher("Jack", 35)
    data.set(teacher)
}
fun handleSimpleData2(data: SimpleData2<Person>) {
//    val teacher = Teacher("Jack", 35)
//    data.set(teacher)
    val va = data.get()
    println(va)
}

interface Transformer<T> {
    fun transform(t: T): String
}
interface Transformer2<in T> {
    fun transform(t: T): String
}
fun handleTransformer(trans: Transformer<Student>) {
    val student = Student("Tom", 19)
    val result = trans.transform(student)
}
fun handleTransformer2(trans: Transformer2<Student>) {
    val student = Student("Tom", 19)
    val result = trans.transform(student)
}
fun main() {
    val result1 = getGenericType<String>()
    val result2 = getGenericType<Int>()
    println("result1 is $result1")
    println("result2 is $result2")

//    startActivity<TenMainActivity>(TenMainActivity())
//
//    startActivity<TenMainActivity>(TenMainActivity()) {
//        putExtra("param1", "data")
//        putExtra("param2", 123)
//    }

    ///////////////协变
    val student = Student("Tom", 19)
    val data = SimpleData<Student>()
    data.set(student)
//    handleSimpleData(data) // 实际上这行代码会报错，这里假设它能编译通过
    val studentData = data.get()

    val data2 = SimpleData2<Student>(student)
    handleSimpleData2(data2) // 协变
    val studentData2 = data2.get()

    ///////////////////////////逆变
    val trans = object : Transformer<Person> {
        override fun transform(t: Person): String {
            return "${t.name} ${t.age}"
        }
    }
    // handleTransformer(trans) // 这行代码会报错

    val trans2 = object : Transformer2<Person> {
        override fun transform(t: Person): String {
            return "${t.name} ${t.age}"
        }
    }
    handleTransformer2(trans2) // 逆变
}
