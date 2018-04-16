package edu.washington.zhang007.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI declarations
        val tip: Button = btn_tip as Button
        val amount: EditText = txt_amount as EditText

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
