package edu.washington.zhang007.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import android.text.Editable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI declarations
        val tip: Button = btn_tip as Button
        val amount: EditText = txt_amount as EditText

        // tip must not be clickable upon launch
        tip.isEnabled = false

        amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // if the string isn't "" return true
                tip.isEnabled = (amount.text.toString() != "")
            }
        })

        tip.setOnClickListener {
            val tipAmt = formatDecimal(amount.text.toString().toDouble() * .15)

            Toast.makeText(this, "You should tip: \$$tipAmt", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formatDecimal(value: Double): String {
        val df = DecimalFormat("#.##")
        return df.format(java.lang.Double.valueOf(value))
    }
}
