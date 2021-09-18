package com.lessons.mvp

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterClick(id: Int) {
        view.setButtonText(id, model.next(id).toString())
    }
}