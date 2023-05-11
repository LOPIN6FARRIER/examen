package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Unidades : AppCompatActivity() {
    var selec="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidades)
        val spinner1=findViewById<Spinner>(R.id.sp1)
        val spinner2=findViewById<Spinner>(R.id.sp2)
        val editText = findViewById<EditText>(R.id.ed1)
        val editText2 = findViewById<EditText>(R.id.ed2)
        val button1 = findViewById<Button>(R.id.button_1)
        val button2 = findViewById<Button>(R.id.button_2)
        val button3 = findViewById<Button>(R.id.button_3)
        val button4 = findViewById<Button>(R.id.button_4)
        val button5 = findViewById<Button>(R.id.button_5)
        val button6 = findViewById<Button>(R.id.button_6)
        val button7 = findViewById<Button>(R.id.button_7)
        val button8 = findViewById<Button>(R.id.button_8)
        val button9 = findViewById<Button>(R.id.button_9)
        val button0 = findViewById<Button>(R.id.button_0)
        val button_erease =findViewById<Button>(R.id.button_erease)
        val button_zz=findViewById<Button>(R.id.button_zz)
        val button_clear=findViewById<Button>(R.id.button_clear)
        val button_dot = findViewById<Button>(R.id.button_dot)
        val button_bin=findViewById<Button>(R.id.button_binario)
        val button_uni=findViewById<Button>(R.id.unidades)
        val button_gr=findViewById<Button>(R.id.grados)
        val button_h = findViewById<Button>(R.id.home)
        val mes=intent.getStringExtra(data_message).toString()
        editText.setText(mes)
        fun res(){
            var origen=spinner1.selectedItem.toString()
            var salida=spinner2.selectedItem.toString()
            if(selec=="1"){
                editText2.setText(convert(editText.text.toString(),origen,salida))
            }else{
                editText.setText(convert(editText2.text.toString(),salida,origen))
            }
        }
        res()
        spinner1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                selec="2"
                // Aquí es donde se maneja el evento de selección de elemento del Spinner
                res() // Llama a la función res() cada vez que se seleccione un elemento
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Este método se llama cuando no se ha seleccionado ningún elemento del Spinner
            }
        })
        spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                selec="1"
                // Aquí es donde se maneja el evento de selección de elemento del Spinner
                res() // Llama a la función res() cada vez que se seleccione un elemento
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Este método se llama cuando no se ha seleccionado ningún elemento del Spinner
            }
        })
        editText.setOnTouchListener { view, event ->
            if (view is EditText) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                view.requestFocus()
                view.setSelection(view.getOffsetForPosition(event.x, event.y))
            }
            selec="1"
            true
        }

        editText2.setOnTouchListener { view, event ->
            if (view is EditText) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                view.requestFocus()
                view.setSelection(view.getOffsetForPosition(event.x, event.y))
            }
            selec="2"
            true
        }
        button_h.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        button_bin.setOnClickListener{
            val message:String
            if(selec=="1"){
                message=editText2.text.toString()
            }else{
                message=editText.text.toString()
            }

            if(message.length>0){
                val intent= Intent(this,Binario::class.java).apply {
                    putExtra(data_message,message)
                }
                startActivity(intent)
            }else{
                val intent= Intent(this,Binario::class.java).apply {
                    putExtra(data_message,"0")
                }
                startActivity(intent)
            }
        }
        button_uni.setOnClickListener{
            finish()


        }
        button_gr.setOnClickListener{
            val message:String
            if(selec=="1"){
                message=editText2.text.toString()
            }else{
                message=editText.text.toString()
            }

            if(message.length>0){
                val intent= Intent(this,Grados::class.java).apply {
                    putExtra(data_message,message)
                }
                startActivity(intent)
            }else{
                val intent= Intent(this,Grados::class.java).apply {
                    putExtra(data_message,"0")
                }
                startActivity(intent)
            }
        }
        button_erease.setOnClickListener {
            if (selec=="1") {
                if(editText.text.length >0){
                    val index = editText.selectionStart
                    val editable = editText.text
                    if (index > 0) {
                        editable.delete(index - 1, index)
                    }
                }else{
                    editText2.setText("")
                }
            }else{
                if(editText2.text.length >0){
                    val index = editText2.selectionStart
                    val editable = editText2.text
                    if (index > 0) {
                        editable.delete(index - 1, index)
                    }

                }else{
                    editText.setText("")
                }
            }
            res()
        }
        button_clear.setOnClickListener {

            editText.setText("")

            editText2.setText("")

        }
        button_dot.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, ".")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, ".")
            }
            res()
        }
        button0.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "0")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "0")
            }
            res()
        }
        button1.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "1")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "1")
            }
            res()
        }
        button2.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "2")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "2")
            }
            res()
        }
        button3.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "3")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "3")
            }
            res()
        }
        button4.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "4")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "4")
            }
            res()
        }
        button5.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "5")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "5")
            }
            res()
        }
        button6.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "6")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "6")
            }
            res()
        }
        button7.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "7")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "7")
            }
            res()
        }
        button8.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "8")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "8")
            }
            res()
        }
        button9.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "9")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "9")
            }
            res()
        }
        button_zz.setOnClickListener {
            if (selec=="1") {
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "00")
            }else{
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "00")
            }
            res()
        }
    }

    fun convert(fromValue: String, fromFormat: String, toFormat: String): String {
        val units = hashMapOf(
            "Kilometro" to 1000.0,
            "Metro" to 1.0,
            "Decimetro" to 0.1,
            "Centimetro" to 0.01,
            "Micrometro" to 1e-6,
            "Milimetro" to 0.001,
            "Nanometro" to 1e-9,
            "Picometro" to 1e-12,
            "Año Luz" to 9460730472580800.0,
            "Parsec" to 30856775814913672.0,
            "Distancia Lunar" to 384400000.0,
            "Unidad Astronomica" to 149597870700.0,
            "Pulgada" to 0.0254,
            "Milla" to 1609.34,
            "Pie" to 0.3048,
            "Yarda" to 0.9144,
            "Milla Nautica" to 1852.0,
            "Braza" to 1.8288,
            "Furlong" to 201.168
        )

        val from = units[fromFormat]
        if (from == null) {
            println("Formato de origen inválido: $fromFormat")
            return "Formato de origen inválido"
        }

        val to = units[toFormat]
        if (to == null) {
            println("Formato de salida inválido: $toFormat")
            return "Formato de salida inválido"
        }

        val result = try {
            fromValue.toDouble() * from / to
        } catch (e: NumberFormatException) {
            println("Valor inválido: $fromValue")
            return "Valor inválido"
        }

        println("from: $from, to: $to, result: $result")

        return "%.2f".format(result)
    }




}