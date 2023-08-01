package com.enesaksu.jetpackcomposetemelleri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesaksu.jetpackcomposetemelleri.ui.theme.JetpackComposeTemelleriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }



}

@Composable
fun MainScreen(){
    Column(modifier = Modifier
        .background(color = Color.LightGray)
        .fillMaxSize()
        ,verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
        ) {
        
        CustomText(text = "HEllo World")
        Spacer(modifier = Modifier.padding(5.dp))
        CustomText(text = "Merhabaa")
        Spacer(modifier = Modifier.padding(5.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            CustomText(text = "Row Test 1")
            CustomText(text = "Row Text 2")
        }



    }


}


@Composable
fun CustomText(text : String){
    Text(modifier = Modifier
        .background(color = Color.Red)
        .padding(all = 10.dp)
        .clickable {
            println(text)
        }
        .padding(5.dp)
        , text = text,
        color = Color.Black,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTemelleriTheme {
        MainScreen()
    }
}