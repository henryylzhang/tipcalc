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
    private var tipPercent: Double = 0.0
    private var tipString: String = ""
    private var tip: Button? = null
    private var amount: EditText? = null
    private var percent: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI declarations
        tip = btn_tip as Button
        amount = txt_amount as EditText
        percent = spn_percent as Spinner

        // tip must not be clickable upon launch
        tip?.isEnabled = false

        // set up spinner adapter
        val percentAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.tip_array, android.R.layout.simple_spinner_item)
        percentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        percent?.adapter = percentAdapter

        percent?.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                tipString = parent.getItemAtPosition(position).toString() //this is your selected item
                tipPercent = (tipString.substring(0, 2).toDouble() / 100)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        amount?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val amountString = amount?.text.toString()

                if (amountString == "$") {
                    amount?.setText("")
                } else if ((!amountString.contains("$")) && (amountString != "")) {
                    amount?.setText("$$amountString")
                    amount?.setSelection(amountString.length + 1)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // if the string isn't "" return true
                tip?.isEnabled = !(amount?.text!!.isEmpty())
            }
        })

        tip?.setOnClickListener {
            val tipAmt = formatDecimal(amount?.text.toString().substring(1).toDouble() * tipPercent)

            Toast.makeText(this, "A $tipString tip on ${amount?.text} is \$$tipAmt", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formatDecimal(value: Double): String {
        val df = DecimalFormat("0.00")
        return df.format(java.lang.Double.valueOf(value))
    }
}
