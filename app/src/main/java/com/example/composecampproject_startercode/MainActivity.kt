package com.example.composecampproject_startercode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecampproject_startercode.ui.theme.ComposeCampProjectStarterCodeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCampProjectStarterCodeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Heading()
                        val bill = billTipSplit()
                        FinalAmount(bill)
                    }
                }
            }
        }
    }
}

@Composable
fun LabelText(label: String) {
    Text(
        fontSize = 18.sp,
        text = label,
        color = Color.Gray,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
fun Heading() {

    // TODO("ADD HEADING WITH A LIGHT GREEN BACKGROUND")
}

@Composable
fun billTipSplit(): Double {
    var billInput by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(0.0) }
    var splitValue by remember { mutableStateOf(0) }
    val bill = billInput.toDoubleOrNull() ?: 0.0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(start = 60.dp, end = 60.dp)
    ) {

        // TEXT INPUT FUNCTION CALLED
        LabelText(label = "Enter total bill")
        BillInputField(userInput = billInput, onUserInputValueChange = { billInput = it })

        // Add Spacer to space out the elements:
        Spacer(modifier = Modifier.height(48.dp))

        // CHOOSE TIP BUTTONS CALLED
        LabelText(label = "Choose tip")
        Spacer(modifier = Modifier.height(16.dp))

        fun onTipChange(buttonTip: Double): Double {
            if (tipPercent != buttonTip) {
                tipPercent = buttonTip
            } else {
                tipPercent = 0.0
            }
            return tipPercent
        }

        TipButton(buttonText = "10%", tipPercent, value = 10.0) { onTipChange(10.0) }
    }

    // SPLIT BUTTONS CALLED
    LabelText(label = "Split")
    SplitBetweenPersons(
        splitValue = splitValue,
        onSplitIncrease = { splitValue++ },
        onSplitDecrease = { splitValue-- }
    )
    return calculateTip(bill, tipPercent, splitValue)
}

@Composable
fun BillInputField(
    userInput: String,
    onUserInputValueChange: (String) -> Unit
) {
    // TODO("ADD USER INPUT FIELD")
}

@Composable
fun TipButton(buttonText: String, tipPercent: Double, value: Double, onTipChange: () -> Unit) {

    // TODO("ADD TIP BUTTON")

}

@Composable
fun SplitBetweenPersons(
    splitValue: Int,
    onSplitIncrease: () -> Unit,
    onSplitDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        // TODO("ADD ICON BUTTONS AND SPLIT VALUE")

    }
}

@Composable
fun FinalAmount(final_bill: Double = 0.0) {

    val finalBillInCurrency = NumberFormat.getCurrencyInstance().format(final_bill)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .background(Color(0xFFCEF5E6), RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
    ) {

        // TODO(reason = "ADD TEXTS TO DISPLAY FINAL BILL PER PERSON")

    }
}

// FUNCTION TO CALCULATE TIP
fun calculateTip(bill: Double = 0.0, tipPercent: Double, split: Int): Double {
    val tip = tipPercent / 100 * bill
    val finalBill = bill + tip
    if (split != 0) {
        return finalBill / split
    } else {
        return finalBill
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCampProjectStarterCodeTheme {



    }
}