package com.example.calculatorappdevsky
//combine

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorappdevsky.Recycler_View.DataModel
import com.example.calculatorappdevsky.Recycler_View.HistoryActivity
import com.example.calculatorappdevsky.databinding.ActivityScientificMainBinding

import kotlin.math.*


class ScientificMainActivity : AppCompatActivity() {

    object CalculationHistory {
        var historyList: ArrayList<DataModel> = ArrayList()
    }


    fun TextView.appendText(text: String) {
        this.append(text)
    }

    private lateinit var binding: ActivityScientificMainBinding
    var check : Boolean=true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScientificMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //loadData()
        binding.buttonHistory.setOnClickListener {

            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }

        binding.buttonCancel.setOnClickListener {
            binding.inputTextView.text = ""
            binding.outputTextView.text = ""
        }

        binding.buttonDelete.setOnClickListener {
            var str=binding.inputTextView.text.toString()
            if (!str.equals("")) {
                str = str.substring(0, str.length - 1)
                binding.inputTextView.text = str
            }
            else
            {
                binding.outputTextView.textSize = 30F
                //binding.outputTextView.text = "empty"
            }
        }

        binding.buttonZero.setOnClickListener {
            binding.inputTextView.appendText("0")

        }
        binding.buttonOne.setOnClickListener {
            binding.inputTextView.appendText("1")

        }
        binding.buttonTwo.setOnClickListener {
            binding.inputTextView.appendText("2")

        }
        binding.buttonThree.setOnClickListener {
            binding.inputTextView.appendText("3")

        }
        binding.buttonFour.setOnClickListener {
            binding.inputTextView.appendText("4")

        }
        binding.buttonFive.setOnClickListener {
            binding.inputTextView.appendText("5")

        }
        binding.buttonSix.setOnClickListener {
            binding.inputTextView.appendText("6")

        }
        binding.buttonSeven.setOnClickListener {
            binding.inputTextView.appendText("7")

        }
        binding.buttonEight.setOnClickListener {
            binding.inputTextView.appendText("8")

        }
        binding.buttonNine.setOnClickListener {
            binding.inputTextView.appendText("9")

        }

        binding.buttonStartBracket.setOnClickListener {
            binding.inputTextView.appendText("(")

        }

        binding.buttonCloseBracket2.setOnClickListener {
            binding.inputTextView.appendText(")")

        }


        binding.buttonPlus.setOnClickListener {
            //handleOperation('+')
            check1()

        }
        binding.buttonMinus.setOnClickListener {
            //handleOperation('-')
            check2()

        }
        binding.buttonMultiply.setOnClickListener {
            //handleOperation('*')
            check3()

        }
        binding.buttonDivide.setOnClickListener {
            //handleOperation('/')
            check4()

        }
        binding.buttonDot.setOnClickListener {
            //binding.inputTextView.appendText(".")
            checkDot()
        }


        binding.buttonSin.setOnClickListener {
            handleScientificOperation("sin(")
        }
        binding.buttonCos.setOnClickListener {
            handleScientificOperation("cos(")
        }
        binding.buttonTan.setOnClickListener {
            handleScientificOperation("tan(")
        }
        binding.buttonLog.setOnClickListener {
            handleScientificOperation("log(")
        }
        binding.buttonLn.setOnClickListener {
            handleScientificOperation("ln(")
        }
        binding.buttonInverse.setOnClickListener {
            handleScientificOperation("1/")
        }
        binding.buttonSquare.setOnClickListener {
            handleScientificOperation("^2")
        }
        binding.buttonPower.setOnClickListener {
            //handleOperation('^')
            check5()
        }
        binding.buttonSqrt.setOnClickListener {
            handleScientificOperation("√(")
        }
        binding.buttonPi.setOnClickListener {
            binding.inputTextView.appendText(PI.toString())
        }

        binding.buttonEqual.setOnClickListener {
            val inputExpression = binding.inputTextView.text.toString()

            try {
                val result = evaluateExpression(inputExpression)
                binding.outputTextView.text = formatResult(result)
                binding.outputTextView.textSize = 30F
                val historyItem = DataModel(inputExpression, formatResult(result))
                CalculationHistory.historyList.add(historyItem)

            } catch (e: Exception) {
                binding.outputTextView.textSize = 30F
                binding.outputTextView.text = " ${e.message}"
            }
        }
    }



    //dot
    private fun checkDot() {
        val currentInput = binding.inputTextView.text.toString()
        //not allow more than 1 dot
        if(check)
        {
            if (canAppendDot(currentInput) && check) {
                binding.inputTextView.appendText(".")
                check=false
            }
        }
    }
    private fun canAppendDot(expression: String): Boolean {
        return expression.isNotEmpty() && expression.last() != '.'
    }
    //plus
    private fun check1() {
        val currentInput = binding.inputTextView.text.toString()
        if (canAppend1(currentInput)) {
            binding.inputTextView.appendText("+")
            check=true
        }
    }
    private fun canAppend1(expression: String): Boolean {
        return expression.isNotEmpty() && expression.last() != '+'
    }
    //minus
    private fun check2() {
        val currentInput = binding.inputTextView.text.toString()
        if (canAppend2(currentInput)) {
            binding.inputTextView.appendText("-")
            check=true
        }
    }
    private fun canAppend2(expression: String): Boolean {
        return expression.isNotEmpty() && expression.last() != '-'
    }
    //multiply
    private fun check3() {
        val currentInput = binding.inputTextView.text.toString()
        if (canAppend3(currentInput)) {
            binding.inputTextView.appendText("*")
            check=true
        }
    }
    private fun canAppend3(expression: String): Boolean {
        return expression.isNotEmpty() && expression.last() != '*'
    }
    //divide
    private fun check4() {
        val currentInput = binding.inputTextView.text.toString()
        if (canAppend4(currentInput)) {
            binding.inputTextView.appendText("/")
            check=true
        }
    }
    private fun canAppend4(expression: String): Boolean {
        return expression.isNotEmpty() && expression.last() != '/'
    }
    //power^
    private fun check5() {
        val currentInput = binding.inputTextView.text.toString()
        if (canAppend5(currentInput)) {
            binding.inputTextView.appendText("^")
            check=true
        }
    }
    private fun canAppend5(expression: String): Boolean {
        return expression.isNotEmpty() && expression.last() != '^'
    }




    private fun handleOperation(operator: Char) {
        binding.inputTextView.appendText(operator.toString())
    }
    private fun handleScientificOperation(operation: String) {
        when (operation) {
            "sin(", "cos(", "tan(", "log(", "ln(", "1/", "^2", "√("-> {
                binding.inputTextView.appendText(operation)
                check=true
            }
        }
    }



    private fun evaluateExpression(expression: String): Double {
        if (expression.contains("/0")) {
            throw ArithmeticException("Division by zero")
        }

        return try {
            expression.toDouble()
        } catch (e: NumberFormatException) {

            if (expression.contains("(") && expression.contains(")")) { // check parentheses first
                val openIndex = expression.lastIndexOf("(")
                val closeIndex = expression.indexOf(")", openIndex)
                if (openIndex in 0 until closeIndex) {
                    val innerExpression = expression.substring(openIndex + 1, closeIndex)
                    val innerResult = evaluateExpression(innerExpression)
                    val newExpression = expression.replaceRange(openIndex, closeIndex + 1, innerResult.toString())
                    return evaluateExpression(newExpression)
                }
            }

            when {
                expression.startsWith("sin(") -> {  // scientific operations
                    val operand = evaluateExpression(expression.substring(4))
                    return sin(operand)
                }
                expression.startsWith("cos(") -> {
                    val operand = evaluateExpression(expression.substring(4))
                    return cos(operand)
                }
                expression.startsWith("tan(") -> {
                    val operand = evaluateExpression(expression.substring(4))
                    return tan(operand)
                }
                expression.startsWith("log(") -> {
                    val operand = evaluateExpression(expression.substring(4))
                    return log(operand)
                }
                expression.startsWith("ln(") -> {
                    val operand = evaluateExpression(expression.substring(3))
                    return ln(operand)
                }
                expression.startsWith("1/") -> {
                    val operand = evaluateExpression(expression.substring(2))
                    return onebyx(operand)
                }
                expression.startsWith("^2") -> {
                    val operand = evaluateExpression(expression.substring(2))
                    return square(operand)
                }
                expression.startsWith("√(") -> {
                    val operand = evaluateExpression(expression.substring(2))
                    return squareRoot(operand)
                }
                else -> {

                    /*val operators = listOf(listOf('/','*','^'), listOf('+', '-'))
                    for (precedence in operators) {
                        var i = 0
                        while (i < expression.length) {
                            val currentChar = expression[i]
                            if (precedence.contains(currentChar)) {
                                val operator = currentChar.toString()
                                val (operand1, operand2) = expression.split(operator, limit = 2)

                                val operand1Value = evaluateExpression(operand1)
                                val operand2Value = evaluateExpression(operand2)

                                return when (operator) {
                                    "+" -> operand1Value + operand2Value
                                    "-" -> operand1Value - operand2Value
                                    "*" -> operand1Value * operand2Value
                                    "/" -> operand1Value / operand2Value
                                    "^" -> power(operand1Value, operand2Value)
                                    else -> throw IllegalArgumentException("Invalid operator")
                                }
                            }
                            i++
                        }
                    }
                    throw IllegalArgumentException("Invalid expression")*/

                    val operators = listOf('+', '-', '*', '/', '^')  //arithmetic operations
                    for (operator in operators) {
                        if (expression.contains(operator)) {
                            val (operand1, operand2) = expression.split(operator, limit = 2)
                            val operand1Value = evaluateExpression(operand1)
                            val operand2Value = evaluateExpression(operand2)

                            return when (operator) {
                                '+' -> addition(operand1Value, operand2Value)
                                '-' -> subtraction(operand1Value, operand2Value)
                                '*' -> multiplication(operand1Value, operand2Value)
                                '/' -> division(operand1Value, operand2Value)
                                '^' -> power(operand1Value, operand2Value)
                                else -> throw IllegalArgumentException("Invalid operator")
                            }
                        }
                    }
                    throw IllegalArgumentException("Invalid expression")
                }
            }
        }
    }


    //functions
    private fun addition(a: Double, b: Double): Double {
        return a + b
    }

    private fun subtraction(a: Double, b: Double): Double {
        return a - b
    }

    private fun multiplication(a: Double, b: Double): Double {
        return a * b
    }

    private fun division(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw ArithmeticException("Division by zero!")
        }
        return a / b
    }

    private fun formatResult(result: Double): String {
        return if (result.toLong().toDouble() == result) {
            result.toLong().toString()
        } else {
            result.toString()
        }
    }

    private fun sin(operand: Double): Double {
        return kotlin.math.sin(operand)
    }

    private fun cos(operand: Double): Double {
        return kotlin.math.cos(operand)
    }

    private fun tan(operand: Double): Double {
        return kotlin.math.tan(operand)
    }

    private fun log(operand: Double): Double {
        return  log10(operand)
    }

    private fun ln(operand: Double): Double {
        return kotlin.math.ln(operand)
    }

    private fun onebyx(operand: Double): Double {
        return 1 / operand
    }

    private fun square(operand: Double): Double {
        return operand.pow(2)
    }

    private fun power(base: Double, exponent: Double): Double {
        return base.pow(exponent)
    }

    private fun squareRoot(operand: Double): Double {
        return sqrt(operand)
    }
}
