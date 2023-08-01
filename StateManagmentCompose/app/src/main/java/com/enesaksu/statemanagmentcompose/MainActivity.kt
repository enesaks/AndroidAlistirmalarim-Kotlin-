package com.enesaksu.statemanagmentcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesaksu.statemanagmentcompose.ui.theme.StateManagmentComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    //State Hosting Uygulaması
    var myString by remember { mutableStateOf("") }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            SpecialText(text = "Test")
            Spacer(modifier = Modifier.padding(10.dp))
            SpecialText(text = "Android")
            Spacer(modifier = Modifier.padding(10.dp))
            SpecialTextField(string = myString){
                myString = it
            }



        }
    }

}


@Composable
fun SpecialText(text : String) {
    Text(text = text,
        fontSize = 26.sp,
        fontStyle = FontStyle.Italic
        )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecialTextField(string: String, function :  (String) -> Unit) {
    TextField(value = string, onValueChange = function)
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    Surface(color = Color.LightGray) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var myString by remember { mutableStateOf("Android Compose") }

            TextField(value = myString, onValueChange ={
                myString = it
                println(myString)
            })

            Spacer(modifier = Modifier.padding(10.dp))

            var textString = remember { mutableStateOf("Hello") }
            Text(text = textString.value,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = {
                textString.value = "Hello Android"
                println(textString)
            }) {
                Text(text = "Button")
                Text(text = "Test")
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Image(bitmap = ImageBitmap.imageResource(id = R.drawable.kenny),
                contentDescription = "Kenny Fotoğrafı"
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null)

            Spacer(modifier = Modifier.padding(10.dp))

            Image(painter = ColorPainter(Color.Black),
                contentDescription =null,
                modifier = Modifier.size(20.dp,20.dp)
            )


        }

    }




}

 */



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateManagmentComposeTheme {
        MainScreen()
    }
}