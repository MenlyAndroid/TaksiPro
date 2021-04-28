package ru.taksi.pro.android.mvvm.data

class UserProperties {

    val aggregators: MutableSet<String> = mutableSetOf()

    companion object{
        val instance = UserProperties()
        val DATE_OF_BIRD = "dateOfBird"
        val DATE_OF_ISSUED = "dataOfIssued"
        val DRIVER_ISSUED = "driverIssued"
        val DRIVER_ISSUED_TO = "driverIssuedTo"
        val PASSPORT_DATA = "passport"
        val DRIVER_DATA = "driver"
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

    var driverNumbers: String? = null
    var driverIssued: String? = null
    var driverIssuedTo: String? = null

    fun setData(value: String, field: String) {
        when (field) {
            DATE_OF_BIRD -> dateOfBird = value
            DATE_OF_ISSUED -> dataOfIssued = value
            DRIVER_ISSUED -> driverIssued = value
            DRIVER_ISSUED_TO -> driverIssuedTo = value
            PASSPORT_DATA -> passportData = value
            DRIVER_DATA -> driverNumbers = value
        }
    }
}