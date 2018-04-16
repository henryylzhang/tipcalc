package edu.washington.zhang007.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import android.text.Editable
import android.view.View
import android.widget.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener



class MainActivity : AppCompatActivity() {
    var tipPercent: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI declarations
        val tip: Button = btn_tip as Button
        val amount: EditText = txt_amount as EditText
        val percent: Spinner = spn_percent as Spinner

        // tip must not be clickable upon launch
        tip.isEnabled = false

        // set up spinner adapter
        val percentAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.tip_array, android.R.layout.simple_spinner_item)
        percentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        percent.adapter = percentAdapter

        percent.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val tipString = parent.getItemAtPosition(position).toString() //this is your selected item
                tipPercent = (tipString.substring(0, 2).toDouble() / 100)
                print(tipPercent)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

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
            val tipAmt = formatDecimal(amount.text.toString().toDouble() * tipPercent)

            Toast.makeText(this, "You should tip: \$$tipAmt", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formatDecimal(value: Double): String {
        val df = DecimalFormat("0.00")
        return df.format(java.lang.Double.valueOf(value))
    }
}
