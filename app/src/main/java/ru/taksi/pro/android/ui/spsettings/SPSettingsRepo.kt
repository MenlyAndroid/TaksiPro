package ru.taksi.pro.android.ui.spsettings

import android.content.Context
import ru.taksi.pro.android.mvvm.model.spsettings.ISPSettingsRepo

class SPSettingsRepo (context: Context) : ISPSettingsRepo {
    private val SP_SETTINGS = "settings"
    val settingsSP = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE)
    val editor = settingsSP.edit()

}