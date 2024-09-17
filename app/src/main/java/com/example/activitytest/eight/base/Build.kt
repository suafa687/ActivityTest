package com.example.activitytest.eight.base

fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}