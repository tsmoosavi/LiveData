package com.example.livedata

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun mutiplication_isCorrect(){
        assertEquals(6,2*3)
    }

    @Test
    fun getLength_isCorrect(){
        var str = "salam"
        assertEquals(5,getLength(str))
    }
    fun getLength(str:String):Int{
        return  str.length
    }


    fun validateName(name: String): Boolean{
        return name.first().isUpperCase() && name.length < 10
    }
    @Test
    fun validateName_isvalid(){
        var name = "Salam"
        assertEquals(true,validateName(name))
    }

    @Test
    fun validateName_isInvalid(){
        var name = "salam"
        assertEquals(false,validateName(name))
    }
    fun longRunning() {
        Thread.sleep(3000)
    }
    @Test
    fun longRunning_withThread() {
        val before = System.currentTimeMillis()
        val thread =  Thread(Runnable {longRunning()}).start()
        val after = System.currentTimeMillis()
        assertTrue(after - before <=3000)

    }


}