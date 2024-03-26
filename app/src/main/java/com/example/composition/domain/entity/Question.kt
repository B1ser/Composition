package com.example.composition.domain.entity

class Question (
    //фактическое число
    val sum : Int,
    //первое число
    val visibleNumbers : Int,
    //варианты второго числа
    val options : List<Int>
){
    val rightAnswer : Int
        get() = sum - visibleNumbers
}