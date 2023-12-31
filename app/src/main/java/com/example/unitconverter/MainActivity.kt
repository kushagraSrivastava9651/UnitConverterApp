package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


val customText=      TextStyle(
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
    color = Color.Blue
)


@Composable
fun UnitConverter(){
    var InputVariable by remember {
        mutableStateOf("")
    }
    var OutValue by remember {
        mutableStateOf("")
    }
    var InputUnit by remember {
        mutableStateOf("Meters")
    }
    var OutputUnit by remember {
        mutableStateOf("Meters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    val conversionFactor= remember {
        mutableStateOf(1.0)
    }
    val oConversionFactor= remember {
        mutableStateOf(1.0)
    }

    fun UnitConvert(){
        val inputValueDouble=InputVariable.toDoubleOrNull() ?:0.0
       val result=((inputValueDouble * conversionFactor.value*100/oConversionFactor.value).roundToInt()/100.0)
        OutValue= result.toString()

    }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = "Unit Convertor", style = customText)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = InputVariable,
            onValueChange = {//there should happen when value changes
            InputVariable=it
                UnitConvert()

         },
            label ={
                Text("Enter Value")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
       Row {
          Box {
            Button(onClick = { iExpanded=true}) {
                Text(text = InputUnit)
              Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
            }
              DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {

                  DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                      iExpanded=false
                  conversionFactor.value=0.01
                      InputUnit="Centimeters"
                      UnitConvert() }   )

                  DropdownMenuItem(text = { Text("Meters") }, onClick ={
                      iExpanded=false
                      conversionFactor.value=1.0
                      InputUnit="Meters"
                      UnitConvert() }   )

                  DropdownMenuItem(text = { Text("Feets") }, onClick =  {
                      iExpanded=false
                      conversionFactor.value=0.3048
                      InputUnit="Feets"
                      UnitConvert() }   )

                  DropdownMenuItem(text = { Text("Millimeter") }, onClick =  {
                      iExpanded=false
                      conversionFactor.value= .001
                      InputUnit="Millimeter"
                      UnitConvert() }   )
              }
          }

      Spacer(modifier = Modifier.width(16.dp))

           Box {
               Button(onClick = { oExpanded=true }) {
                   Text(text =  OutputUnit)
                   Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
               }
               DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                   DropdownMenuItem(text = { Text("Centimeters") }, onClick =  {
                       oExpanded=false
                       oConversionFactor.value=0.01
                       OutputUnit="Centimeters"
                       UnitConvert() }   )
                   DropdownMenuItem(text = { Text("Meters") }, onClick = {
                       oExpanded=false
                       oConversionFactor.value=1.0
                       OutputUnit="Meters"
                       UnitConvert() }   )
                   DropdownMenuItem(text = { Text("Feets") }, onClick =  {
                       oExpanded=false
                       oConversionFactor.value=.3048
                       OutputUnit="Feets"
                       UnitConvert() }   )
                   DropdownMenuItem(text = { Text("Millimeter") }, onClick =  {
                       oExpanded=false
                       oConversionFactor.value=.001
                       OutputUnit="Millimeter"
                       UnitConvert() }   )
               }
           }
       }
        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Result : $OutValue  $OutputUnit", style = MaterialTheme.typography.headlineMedium)

    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
   UnitConverter()
}