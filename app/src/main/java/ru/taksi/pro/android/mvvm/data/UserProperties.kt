package ru.taksi.pro.android.mvvm.data

class UserProperties {

    val aggregators: MutableSet<String> = mutableSetOf()

    companion object{
        val instance = UserProperties()
        val DATE_OF_BIRD = "dateOfBird"
        val DATE_OF_ISSUED = "dataOfIssued"
    }

    var token: String? = null
    var tariff: String? = null
    var surname: String? = null
    var name: String? = null
    var patronymic: String? = null
    var dateOfBird: String? = null
    var passportData: String? = null
    var whoIssued: String? = null
    var dataOfIssued: String? = null
    var city: String? = null
    var district: String? = null
    var street: String? = null
    var home: String? = null
    var apartments: String? = null

    fun setDate(value: String, field: String) {
        when (field) {
            DATE_OF_BIRD -> dateOfBird = value
            DATE_OF_ISSUED -> dataOfIssued = value
        }
    }
}