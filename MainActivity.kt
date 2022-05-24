package com.example.currencyquotedoge

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.type_sensors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        var chosen = ""


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selected = spinner.selectedItem.toString()
                when (selected){
                    "Датчики окружающей среды" -> chosen = "physical"
                    "Датчики положения" -> chosen = "environment"
                    "Датчики состояния человека" -> chosen = "human"
                }
                val sensors = mapOf<String, ArrayList<Int>>(
                    "physical" to arrayListOf<Int>(1, 35, 11, 15, 20, 9, 4, 16, 36, 30, 8, 17, 29, 19, 18, 10),
                    "environment" to arrayListOf<Int>(13, 5, 2, 14, 6, 12),
                    "human" to arrayListOf<Int>(34, 31, 21)
                )

                var output = ""

                val sm = getSystemService(SENSOR_SERVICE) as SensorManager
                sm.getSensorList(Sensor.TYPE_ALL).forEach {
                    if (sensors[chosen]?.contains(it.type) == true){
                        output += it.name.toString() + "\n"
                    }
                }

                val textView: TextView = findViewById(R.id.textView)
                textView.text = output
            }

        }



    }
}
