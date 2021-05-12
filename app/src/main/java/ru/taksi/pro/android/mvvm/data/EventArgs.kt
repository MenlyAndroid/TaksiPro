package ru.taksi.pro.android.mvvm.data

class EventArgs (val text: String?, val event: Int) {
    companion object {
        const val PROCESS = 0
        const val DONE = 1
        const val ERROR = 2
    }
}