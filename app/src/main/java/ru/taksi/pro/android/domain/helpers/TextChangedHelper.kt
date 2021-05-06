package ru.taksi.pro.android.domain.helpers

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.helpers.TextFormatHelper

object TextChangedHelper {

    fun getDateTextWatcher(textInput: TextInputEditText, field: String): TextWatcher =
        object : TextWatcher {
            var current = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var text = textInput.text
                text?.let {
                    if (it.toString() != current) {
                        current = TextFormatHelper.dateFormat(it.toString())
                        textInput.setText(current)
                        textInput.setSelection(textInput.text.toString().length)
                        UserProperties.instance.setData(current, field)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }

    fun getSerialEndNumberTextWatcher(
        textInput: TextInputEditText,
        field: String
    ): TextWatcher = object : TextWatcher {
        var current = ""
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            var text = textInput.text
            text?.let {
                if (it.toString() != current) {
                    current = TextFormatHelper.serialEndNumberFormat(it.toString())
                    textInput.setText(current)
                    textInput.setSelection(textInput.text.toString().length)
                    UserProperties.instance.setData(current, field)
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}