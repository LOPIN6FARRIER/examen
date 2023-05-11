package com.example.calculadora

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale
import java.util.Stack

const val data_message="Datos"
class MainActivity : AppCompatActivity() {
   var cont=0

    @SuppressLint("ServiceCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // Bloquear rotación de pantalla
        //cargamos nuestras views
        val editText = findViewById<EditText>(R.id.edit_text_1)
        val res=findViewById<TextView>(R.id.edit_text_2)
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
        val button_pariz = findViewById<Button>(R.id.button_pariz)
        val button_dot = findViewById<Button>(R.id.button_dot)
        val button_mas=findViewById<Button>(R.id.button_add)
        val button_erease =findViewById<Button>(R.id.button_erease)
        val button_parder=findViewById<Button>(R.id.button_parder)
        val button_res=findViewById<Button>(R.id.button_subtract)
        val button_ediv=findViewById<Button>(R.id.button_divide)
        val button_clear=findViewById<Button>(R.id.button_clear)
        val button_equal=findViewById<Button>(R.id.button_equals)
        val button_mul=findViewById<Button>(R.id.button_multiply)
        val button_bin=findViewById<Button>(R.id.button_binario)
        val button_uni=findViewById<Button>(R.id.unidades)
        val button_gr=findViewById<Button>(R.id.grados)

        //con este listener personalizo mi edit text para que no se habra el teclado al dar clic
        editText.setOnTouchListener { view, event ->
            if (view is EditText) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                view.requestFocus()
                view.setSelection(view.getOffsetForPosition(event.x, event.y))
            }

            true

        }

        //esta funcion nos cacula el resultado
        fun res(){
            //formater nos sirve para fromatear texto por ejemplo 1000000 pasa a 1,000,000
            val formatters= NumberFormat.getInstance(Locale.US)
            try{
            val resultado = try {
                //calculo el resultado utilizanod el texto de mi edit text
                evaluarExpresion(addMultiplicationOperators(addSubtractionOperators(editText.text.toString().replace(")(", ")x("))))

            } catch (e: IllegalArgumentException) {
                0.0
            }
            if(resultado==null){
                res.text="Error"
            }else{
                val resfor=formatters.format(resultado)
                res.text=resfor.toString().replace(")x(", ")(")
            } }catch (e: IllegalArgumentException){
                res.text="Error"
            }
        }

        //esta funcion me sirve para lo mismo pero la utilice para no confundirme
        fun res2(){
            val formatters= NumberFormat.getInstance(Locale.US)
            try {
            val resultado = try {
                evaluarExpresion(addMultiplicationOperators(addSubtractionOperators(editText.text.toString().replace(")(", ")x("))))
            } catch (e: IllegalArgumentException) {
                0.0
            }
            if(resultado==null){
                res.text="Error"
            }else{
                val resfor=formatters.format(resultado)
                res.text=resfor.toString().replace(")x(", ")(")
            }}catch (e:IllegalArgumentException){
                res.text="Error"
            }
        }
        if(editText.text.length>0){
        res()
        }

        //los siguientes listeners me sirven para ir a las demas activitys
        button_bin.setOnClickListener{
            if(res.text.length>0){
                val edit=findViewById<TextView>(R.id.edit_text_2)
                val message=edit.text.toString()
                val intent=Intent(this,Binario::class.java).apply {
                    putExtra(data_message,message)
                }
                startActivity(intent)
            }
            if(res.text=="Error"||res.text.length==0){
            val intent=Intent(this,Binario::class.java).apply {
                putExtra(data_message,"0")
            }
            startActivity(intent)
            }
        }
        button_uni.setOnClickListener{
            if(res.text.length>0){
                val edit=findViewById<TextView>(R.id.edit_text_2)
                val message=edit.text.toString()
                val intent=Intent(this,Unidades::class.java).apply {
                    putExtra(data_message,message)
                }
                startActivity(intent)
            }
            if(res.text=="Error"||res.text.length==0){
                val intent=Intent(this,Unidades::class.java).apply {
                    putExtra(data_message,"0")
                }
                startActivity(intent)
            }
        }
        button_gr.setOnClickListener{
            if(res.text.length>0){
                val edit=findViewById<TextView>(R.id.edit_text_2)
                val message=edit.text.toString()
                val intent=Intent(this,Grados::class.java).apply {
                    putExtra(data_message,message)
                }
                startActivity(intent)
            }
            if(res.text=="Error"||res.text.length==0){
                val intent=Intent(this,Grados::class.java).apply {
                    putExtra(data_message,"0")
                }
                startActivity(intent)
            }
        }
        //los siguientes listener me sirven para agregar a mi edit text el valor del botn que precionen: 1,2,3,4 u operadores
        /*
        loq ue hago es recuperar la posicion de mi cursor y creo una link con mi texto para ingresar el carater en la posicion recuperada
        val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "1")
        */

        button_equal.setOnClickListener{
            if(editText.text.length>0){
            res()
            }
        }
        button1.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "1")
            res()

        }
        button2.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "2")
            res()
        }
        button3.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "3")
            res()
        }
        button4.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "4")
            res()
        }
        button5.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "5")
            res()
        }
        button6.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "6")
            res()
        }
        button7.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "7")
            res()
        }
        button8.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "8")
            res()
        }
        button9.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "9")
            res()
        }
        button0.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "0")
            res()
        }
        button_mas.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "+")
            res()

        }
        button_res.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "-")
            res()

        }
        button_ediv.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "÷")
            res()
        }
        button_mul.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "x")
            res()
        }
        button_dot.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, ".")
            res()
        }
        //los siguientes listeners me sirevn para borrartodo el texto o caracter por carater
        button_clear.setOnClickListener {
            editText.setText("")
            res.setText("")

        }
        button_erease.setOnClickListener {
            if(editText.text.length >0){
            val index = editText.selectionStart
            val editable = editText.text
            if (index > 0) {
                editable.delete(index - 1, index)
            }
                res2()
        }
        }

        button_parder.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, ")")
            res2()
        }
        button_pariz.setOnClickListener {
            val index = editText.selectionStart
            val editable = editText.text
            editable.insert(index, "(")
            res2()
        }


    }

    /*en esta funcion practicamnet checo que sea un numero negativo oq ue sea una resta en caso de que sea una resta con el regex agrego los parentesis solo en la logica no
    no en el texto para poder diferencialo de un numero negativo ya que para poder usar numeros negatovos teng que hacer (-5)*/
    fun addSubtractionOperators(expression: String): String {
        if(expression.startsWith("-") && cont==0){
            Toast.makeText(this,"Para numero neg. de la sig. forma (- 5)",Toast.LENGTH_LONG).show()
            cont=+1
        }
        val regexSubtraction = Regex("(\\d+)\\s*-\\s*(\\d+)")
        var result = expression.replace(regexSubtraction) { matchResult ->
            "(${matchResult.groupValues[1]})-(${matchResult.groupValues[2]})"
        }
        return result
    }
//esta funcion de aqui checa si hay por ejmplo (5)(5) lo cmabia a (5)x(5) ya que el caracter que uso para multiplicar es x
    fun addMultiplicationOperators(expression: String): String {
        val regexOpenParen = Regex("(\\d+)\\(")
        val regexCloseParen = Regex("\\)(\\d+)")
        var result = expression.replace(regexOpenParen) { matchResult ->
            "${matchResult.groupValues[1]}x("
        }
        result = result.replace(regexCloseParen) { matchResult ->
            ")x${matchResult.groupValues[1]}"
        }
        return result
    }

    fun evaluarExpresion(expresion: String): Double? {
        val regex = Regex("\\(([\\.\\+\\-x÷])\\)")
        // expresión regular que busca un paréntesis izquierdo, seguido de un simbolo, seguido de un paréntesis derecho
        val regex2 = Regex("\\((\\d+)[+\\-x÷\\\\.]\\)")
        // expresión regular que busca un paréntesis izquierdo, seguido de un número, seguido de un guión, seguido de un paréntesis derecho
        if (regex.matches(expresion)) {
            return null
        }
        if (regex2.matches(expresion)) {
            return null
        }
        //checa si la expresion tiene espacios o parentesis vacios
        if (expresion == "" || expresion.contains("()")) {
            return null
        }
        if (expresion==null){
            return null
        }
        if(expresion.contains(".÷")){
            return null
        }
        if (expresion.isEmpty() || !expresion.any { it.isDigit() } ||
            expresion.startsWith("+") || expresion.startsWith("x") ||
            expresion.startsWith("÷") ||
            expresion.endsWith("+") || expresion.endsWith("-") || expresion.endsWith("x") ||
            expresion.endsWith("÷") ||
            expresion.contains("..") || expresion.contains(Regex("[^+\\-x÷\\d.()]+")) ||
            expresion.count { it == '(' } != expresion.count { it == ')' } ||
            expresion.contains("--")) {
            // la expresión está vacía, no tiene dígitos, comienza o termina con un símbolo no permitido,
            // contiene dos puntos seguidos, contiene símbolos no permitidos, tiene un número desigual de paréntesis,
            // o tiene dos signos de resta consecutivos
            return null

        }

        val numeros = Stack<Double>()// pila para almacenar los números
        val operadores = Stack<Char>()// pila para almacenar los números
        var i = 0

        while (i < expresion.length) {// mientras no se haya recorrido toda la expresión
            val c = expresion[i]// obtener el caracter actual

            when {

                c.isDigit() || c == '.' || (c == '-' && esNumeroNegativo(expresion, i)) -> {// si el caracter es un número, un punto decimal o un signo menos (para números negativos)

                    var numero = ""
                    if (c == '-') {
                        numero += '-'// agregar el signo menos a la cadena
                        i++
                    }
                    while (i < expresion.length && (expresion[i].isDigit() || expresion[i] == '.')) {// mientras se encuentren más dígitos o puntos decimales
                        numero += expresion[i]// agregar el dígito o punto a la cadena del número
                        i++
                    }
                    numeros.push(numero.toDouble())// convertir la cadena del número a un Double y agregarlo a la pila de números
                    i--

            }
                c == '(' -> operadores.push(c) // si el caracter es un paréntesis de apertura, agregarlo a la pila de operadores
                c == ')' -> {// si el caracter es un paréntesis de cierre
                    while (operadores.peek() != '(') {
                        numeros.push(realizarOperacion(operadores.pop(), numeros.pop(), numeros.pop())) // realizar la operación correspondiente y agregar el resultado a la pila de números
                    }
                    operadores.pop()// sacar el paréntesis de apertura de la pila de operadores
                }
                (c == '+' || c == 'x' || c == '÷' ) || (c == '-' && (i == 0 || !expresion[i-1].isDigit())) -> {
                    if (i > 0 && (expresion[i - 1] == '+' || expresion[i - 1] == '-' || expresion[i - 1] == 'x' ||
                                expresion[i - 1] == '÷' )) {
                        // hay un operador doble consecutivo
                        return null
                    }
                    else {
                        while (!operadores.empty() && tienePrecedencia(c, operadores.peek())) {// mientras la pila de operadores no esté vacía y el operador actual tenga mayor o igual precedencia que el operador en la cima de la pila
                            numeros.push(realizarOperacion(operadores.pop(), numeros.pop(), numeros.pop()))// realizar la operación correspondiente y agregar el resultado a la pila de números
                        }
                        operadores.push(c)// agregar el operador actual a la pila de operadores
                    }

                }
            }
            i++
        }

        while (!operadores.empty()) {
            numeros.push(realizarOperacion(operadores.pop(), numeros.pop(), numeros.pop()))
        }

        return numeros.pop()
    }

    fun esNumeroNegativo(expresion: String, indice: Int): Boolean {
        if (indice == 0) {
            return true
        }

        val charAnterior = expresion[indice - 1]

        // Si el carácter anterior es un dígito, entonces no es un número negativo
        if (charAnterior.isDigit()) {
            return false
        }

        // Si el carácter anterior es un paréntesis que cierra, entonces no es un número negativo
        if (charAnterior == ')') {
            var cantidadParentesisAbiertos = 1
            var i = indice - 2

            while (i >= 0) {
                when (expresion[i]) {
                    '(' -> cantidadParentesisAbiertos--
                    ')' -> cantidadParentesisAbiertos++
                }

                if (cantidadParentesisAbiertos == 0) {
                    return false
                }

                i--
            }

            return true
        }

        // Si el siguiente carácter después de un signo negativo no es un número o un paréntesis de apertura, entonces no es un número negativo
        if (charAnterior == '-') {
            if (indice < expresion.length - 1) {
                val siguienteChar = expresion[indice + 1]
                if (!siguienteChar.isDigit() && siguienteChar != '(') {
                    return false
                }
            }
        }

        // En cualquier otro caso, es un número negativo
        return true
    }

    fun tienePrecedencia(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') {
            return false
        }
        if ((op1 == 'x' || op1 == '÷' ) && (op2 == '+' || op2 == '-')) {
            return false
        }
        return true
    }

    fun realizarOperacion(operador: Char, b: Double, a: Double): Double {
        return when (operador) {
            '+' -> a + b
            '-' -> a - b
            'x' -> a * b
            '÷' -> a / b
            else -> throw IllegalArgumentException("Operador desconocido: $operador")
        }
    }

}