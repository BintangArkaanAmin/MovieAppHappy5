package com.example.testmovieapphappy5.base

interface BasePresenter<T> {
    fun onAttachView(view: T)

    fun onDettachView()
}