package br.com.matheus.pizzaapp.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.userStopDigit(text: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            text.invoke(p0.toString())
        }
    })
}