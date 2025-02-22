package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.firstappjetpackcompose.ui.theme.FirstAppJetpackCOmposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppJetpackCOmposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Appcentent()
                }
            }
        }
    }
}

@Composable
fun Profile() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "",

        modifier = Modifier
            .size(width = 240.dp, height = 240.dp)
            .clip(shape = CircleShape)
            .border(
                3.dp,
                Color.Gray, CircleShape
            )
    )
}

@Composable
fun Circle(color: Color = Color.Red, startStroke: Float = 10f, endStroke: Float = 30f) {
    var transition = rememberInfiniteTransition()
    var alp = transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )
    var size = transition.animateValue(
        initialValue = 240.dp,
        targetValue = 260.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),

        )
    val strokeSize = transition.animateFloat(
        initialValue = startStroke,
        targetValue = endStroke,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
    )
    val sizeInPx = with(LocalDensity.current) { size.value.toPx() }
    val tl = with(LocalDensity.current) {
        var tl = (240.dp - size.value) / 2
        tl.toPx()
    }

    Canvas(modifier = Modifier.size(240.dp)) {
        drawArc(
            topLeft = Offset(tl, tl),
            color = color.copy(alpha = alp.value),  // กำหนดสี
            startAngle = 0f,     // จุดเริ่มต้นของ arc
            sweepAngle = 360f,   // มุมที่ครอบคลุม (360 องศาคือวงกลมเต็ม)
            useCenter = false,   // กำหนดให้วาดในรูปแบบวงกลม
            style = Stroke(strokeSize.value), // กำหนดการวาดเป็นเส้นขอบ
            size = Size(sizeInPx, sizeInPx)
        )
    }

}

@Composable
fun Appcentent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Profile()
            Circle(color = Color.Yellow, endStroke = 40f)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Appcentent()
}