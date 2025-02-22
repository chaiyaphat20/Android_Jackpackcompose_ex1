package com.example.firstjackpackcompose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.rememberInfiniteTransition

@Composable
fun Circle(
    color: Color = Color.Red,
    startStroke: Float = 10f,
    endStroke: Float = 30f
) {
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

@Preview(showBackground = true)
@Composable
fun CirclePreview() {
    Circle(color = Color.Blue)
}
