package com.example.calculadora

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.RowId
import kotlin.math.pow


class Binario : AppCompatActivity() {
    var cont=0
    var selec="1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binario)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // Bloquear rotación de pantalla
        //cargamos nuestras view
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
        val button_A = findViewById<Button>(R.id.button_A)
        val button_B = findViewById<Button>(R.id.button_B)
        val button_C=findViewById<Button>(R.id.button_C)
        val button_erease =findViewById<Button>(R.id.button_erease)
        val button_D=findViewById<Button>(R.id.button_D)
        val button_E=findViewById<Button>(R.id.button_E)
        val button_F=findViewById<Button>(R.id.button_F)
        val button_clear=findViewById<Button>(R.id.button_clear)
        val button_dot = findViewById<Button>(R.id.button_dot)
        val button_bin=findViewById<Button>(R.id.button_binario)
        val button_uni=findViewById<Button>(R.id.unidades)
        val button_gr=findViewById<Button>(R.id.grados)
        val button_h=findViewById<Button>(R.id.home)
        //recibimos el paso de valores desde la otra activity y lo cragmos en el textview
        val mes=intent.getStringExtra(data_message).toString()
        editText.setText(mes)
        //esta funcion nos sirve para hacer la operacion y ponerla en el edit text
       fun res(){
           var origen=spinner1.selectedItem.toString()
           var salida=spinner2.selectedItem.toString()
           if(selec=="1"){
           editText2.setText(convertirNumero(editText.text.toString(),origen,salida).uppercase())
           }else{
               editText.setText(convertirNumero(editText2.text.toString(),salida,origen).uppercase())
           }
       }
        //llamaos a nustra funcion res en el on create ya que recibimos datos del anterior en el caso de que no se hayan recibido datos sera 0
        res()

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
        button_h.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        button_bin.setOnClickListener{
            finish()
        }
        button_uni.setOnClickListener{
            var message="0"
            var men:String
            if(selec=="1"){
                men=editText2.text.toString()
            }else{
                men=editText.text.toString()
            }
            if (men.contains(Regex("[ABCDEF]"))) {
                val decimal = convertirNumero(men, "hexadecimal", "Decimal")
                // Agregar aquí la lógica que se quiera ejecutar con el número convertido a decimal
                message=decimal
            }

            if(message.length>0){
                    val intent= Intent(this,Unidades::class.java).apply {
                    putExtra(data_message,message)
                }
                startActivity(intent)
            }else{
                val intent= Intent(this,Unidades::class.java).apply {
                    putExtra(data_message,"0")
                }
                startActivity(intent)
            }

        }
        button_gr.setOnClickListener{
            var message="0"
            var men:String
            if(selec=="1"){
                men=editText2.text.toString()
            }else{
                men=editText.text.toString()
            }

            if (men.contains(Regex("[ABCDEF]"))) {
                val decimal = convertirNumero(men, "hexadecimal", "Decimal")
                // Agregar aquí la lógica que se quiera ejecutar con el número convertido a decimal
                message=decimal
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
        button_F.setOnClickListener {
            if (selec=="1") {
                if(spinner1.selectedItem.toString().toLowerCase()=="hexadecimal" ){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "F")
                }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "F")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
        }

            }
            res()
        }
        button_E.setOnClickListener {
            if (selec=="1") {
                if(spinner1.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "E")
                }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "E")
                }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
            }
            res()
        }
        button_D.setOnClickListener {
            if (selec=="1") {
                if(spinner1.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "D")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
            }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="hexadecimal" ){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "D")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
        }
            }
            res()
        }
        button_C.setOnClickListener {
            if (selec=="1") {
                if(spinner1.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "C")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
            }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "C")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
            }
            }
            res()
        }
        button_B.setOnClickListener {
            if (selec=="1") {
                if(spinner1.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "B")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
            }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "B")
                }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
            }
            res()
        }
        button_A.setOnClickListener {
            if (selec=="1") {
                if(spinner1.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "A")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
            }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "A")
                }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
            }
            res()
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
                if(spinner1.selectedItem.toString().toLowerCase()=="decimal"||spinner1.selectedItem.toString().toLowerCase()=="hexadecimal" ){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "8")}else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="decimal"||spinner2.selectedItem.toString().toLowerCase()=="hexadecimal"  ){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "8")
                }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
            }
            res()

        }
        button9.setOnClickListener {
            if (selec=="1") {
                if(spinner1.selectedItem.toString().toLowerCase()=="decimal"||spinner1.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText.selectionStart
                val editable = editText.text
                editable.insert(index, "9")
            }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
            }
            }else{
                if(spinner2.selectedItem.toString().toLowerCase()=="decimal"||spinner2.selectedItem.toString().toLowerCase()=="hexadecimal"){
                val index = editText2.selectionStart
                val editable = editText2.text
                editable.insert(index, "9")
                }else{
                    if(cont==0) {
                        Toast.makeText(
                            this,
                            "A,B,C,D,E,F,8,9 Solo disp. en Hexadecimal y Decimal",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    cont=1
                }
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
    }

    fun convertirNumero(cadena: String, formatoOrigen: String, formatoSalida: String): String {
        val partes = cadena.split(".")
        var entero: Int
        var decimal: Double
        var resultado: String = ""

        // Validaciones de formato de origen
        try {
            when (formatoOrigen.toLowerCase()) {
                "decimal" -> {
                    entero = partes[0].toInt()
                    decimal = 0.0
                }
                "hexadecimal" -> {
                    entero = Integer.parseInt(partes[0], 16)
                    decimal = 0.0
                }
                "octal" -> {
                    entero = Integer.parseInt(partes[0], 8)
                    decimal = 0.0
                }
                "binario" -> {
                    entero = Integer.parseInt(partes[0], 2)
                    decimal = 0.0
                }
                else -> throw IllegalArgumentException("Formato de origen no válido")
            }
        } catch (e: Exception) {
            return "Error"
        }

        // Convertir parte decimal si existe
        if (partes.size > 1) {
            try {
                when (formatoOrigen.toLowerCase()) {
                    "decimal" -> {
                        decimal = partes[1].toDouble() / 10.0.pow(partes[1].length)
                    }
                    "hexadecimal" -> {
                        decimal = Integer.parseInt(partes[1], 16).toDouble() / 16.0.pow(partes[1].length)
                    }
                    "octal" -> {
                        decimal = Integer.parseInt(partes[1], 8).toDouble() / 8.0.pow(partes[1].length)
                    }
                    "binario" -> {
                        decimal = Integer.parseInt(partes[1], 2).toDouble() / 2.0.pow(partes[1].length)
                    }
                    else -> throw IllegalArgumentException("Formato de origen no válido")
                }
            } catch (e: Exception) {
                return "Erro"
            }
        }

        // Validaciones de formato de salida
        try {
            when (formatoSalida.toLowerCase()) {
                "decimal" -> resultado = (entero + decimal).toString()
                "hexadecimal" -> resultado = Integer.toHexString(entero) +"."+ Integer.toHexString((decimal * 16).toInt())
                "octal" -> resultado = Integer.toOctalString(entero) +"."+ Integer.toOctalString((decimal * 8).toInt())
                "binario" -> resultado = Integer.toBinaryString(entero) +"."+ Integer.toBinaryString((decimal * 2).toInt())
                else -> throw IllegalArgumentException("Formato de salida no válido")
            }
        } catch (e: Exception) {
            return "Error"
        }

        return resultado
    }



}

