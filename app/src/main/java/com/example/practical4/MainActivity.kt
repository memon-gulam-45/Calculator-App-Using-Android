package com.example.practical4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outputWindow = findViewById<TextView>(R.id.outputWindow)
        val smallWindow = findViewById<TextView>(R.id.smallWindow)
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        val backBtn = findViewById<Button>(R.id.backBtn)
        val num1 = findViewById<Button>(R.id.num1)
        val num2 = findViewById<Button>(R.id.num2)
        val num3 = findViewById<Button>(R.id.num3)
        val num4 = findViewById<Button>(R.id.num4)
        val num5 = findViewById<Button>(R.id.num5)
        val num6 = findViewById<Button>(R.id.num6)
        val num7 = findViewById<Button>(R.id.num7)
        val num8 = findViewById<Button>(R.id.num8)
        val num9 = findViewById<Button>(R.id.num9)
        val num0 = findViewById<Button>(R.id.num0)
        val dot = findViewById<Button>(R.id.dot)
        val sumBtn = findViewById<Button>(R.id.sum)
        val subBtn = findViewById<Button>(R.id.sub)
        val mulBtn = findViewById<Button>(R.id.mul)
        val divBtn = findViewById<Button>(R.id.div)
        val equalBtn = findViewById<Button>(R.id.equal)

        var firstNum: Double = 0.0
        var secondNum: Double = 0.0
        var operator: String = ""

        clearBtn.setOnClickListener {
            outputWindow.text = ""
            smallWindow.text = ""
        }

        var calculationDone = false

        backBtn.setOnClickListener {
            if (!calculationDone) {
                val text = outputWindow.text.toString()
                if (text.isNotEmpty()) {
                    outputWindow.text = text.dropLast(1)
                    smallWindow.text = smallWindow.text.toString().dropLast(1)
                }
            }
        }

        val numButtons = listOf(num0, num1, num2, num3, num4, num5, num6, num7, num8, num9)
        for (button in numButtons){
            button.setOnClickListener {
                outputWindow.append((button.text))
                smallWindow.append((button.text))
            }
        }
        dot.setOnClickListener {
            if(!outputWindow.text.contains(".")){
                outputWindow.append(".")
                smallWindow.append(".")
            }
        }

        fun operatorClick(opBtn: Button, op: String){
            firstNum = outputWindow.text.toString().toDoubleOrNull() ?: 0.0
            outputWindow.text = ""
            smallWindow.append(" ${opBtn.text} ")
            operator = op
            calculationDone = false
        }

        sumBtn.setOnClickListener { operatorClick(sumBtn, "+") }
        subBtn.setOnClickListener { operatorClick(subBtn, "-")}
        mulBtn.setOnClickListener { operatorClick(mulBtn, "*")}
        divBtn.setOnClickListener { operatorClick(divBtn, "/")}

        equalBtn.setOnClickListener {
            secondNum = outputWindow.text.toString().toDoubleOrNull() ?: 0.0

            val finalResult = when(operator){
                "+" -> firstNum + secondNum
                "-" -> firstNum - secondNum
                "*" -> firstNum * secondNum
                "/" -> if (secondNum != 0.0) firstNum/secondNum else Double.NaN
                else -> 0.0
            }

            fun formatNumber(num: Double): String {
                return if (num %1 == 0.0) num.toInt().toString() else String.format("%.2f", num)
            }

            val formattedFirst = formatNumber(firstNum)
            val formattedSecond = formatNumber(secondNum)
            val formattedResult = formatNumber(finalResult)

            outputWindow.text = formattedResult
            smallWindow.text = "$formattedFirst $operator $formattedSecond = $formattedResult"

            firstNum = finalResult
            calculationDone = true
        }
    }
}