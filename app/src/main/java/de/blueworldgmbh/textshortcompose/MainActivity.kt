package de.blueworldgmbh.textshortcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import de.blueworldgmbh.textshortcompose.ui.theme.TextShortComposeTheme

@ExperimentalUnitApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextShortComposeTheme {
                // A surface container using the 'background' color from the theme
                TextSample()
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun TextSample() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Arabic Diacritics (Vowel Marks)",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }

    ) { pv ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(pv)
        ) {
            val (fatha, dammah, kasra, sukun,
                fatha_tanween, dammah_tanween, kasra_tanween, sukun_2,
                madda, shadda, alif_khanjariyah, reset) = createRefs()

            val readOnlyText = createRef()

            val originalText = "بِسْمِ ٱللَّٰهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ\nبَيتٌ  بَيتٍ  بَيتًا\nسُكُونۡ الرّحْمٰٓن\n";
            var originalTextState by remember { mutableStateOf(AnnotatedString(originalText)) }

            val setColorOnClick = { query: String ->
                val foundIndices: MutableList<Int> = ArrayList()

                val filteredQuery = query.trim()

                var i = -1
                while (originalText.indexOf(filteredQuery, i + 1).also { i = it } != -1) {
                    foundIndices.add(i)
                }

                if (foundIndices.size > 0) {
                    val spanStyles = foundIndices.map {
                        AnnotatedString.Range(
                            item = SpanStyle(color = Color.Red),
                            start = it,
                            end = it + 1,
                            tag = it.toString()
                        )
                    }

                    originalTextState = AnnotatedString(
                        text = originalText,
                        spanStyles = spanStyles
                    )
                }
            }

            // row 1
            DiacriticButton(
                modifier = Modifier
                    .constrainAs(fatha) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(dammah.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "َ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(dammah) {
                        top.linkTo(parent.top)
                        start.linkTo(fatha.end)
                        end.linkTo(kasra.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ُ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(kasra) {
                        top.linkTo(parent.top)
                        start.linkTo(dammah.end)
                        end.linkTo(sukun.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ِ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(sukun) {
                        top.linkTo(parent.top)
                        start.linkTo(kasra.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ْ",
                onClick = setColorOnClick
            )

            createHorizontalChain(
                fatha, dammah, kasra, sukun,
                chainStyle = ChainStyle.Spread
            )

            // row 2
            DiacriticButton(
                modifier = Modifier
                    .constrainAs(fatha_tanween) {
                        top.linkTo(fatha.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(dammah_tanween.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ً",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(dammah_tanween) {
                        top.linkTo(dammah.bottom)
                        start.linkTo(fatha_tanween.end)
                        end.linkTo(kasra_tanween.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ٌ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(kasra_tanween) {
                        top.linkTo(kasra.bottom)
                        start.linkTo(dammah_tanween.end)
                        end.linkTo(sukun.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ٍ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(sukun_2) {
                        top.linkTo(sukun.bottom)
                        start.linkTo(kasra_tanween.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = " ۡ",
                onClick = setColorOnClick
            )

            createHorizontalChain(
                fatha_tanween, dammah_tanween, kasra_tanween, sukun_2,
                chainStyle = ChainStyle.Spread
            )

            // row 3
            DiacriticButton(
                modifier = Modifier
                    .constrainAs(madda) {
                        top.linkTo(fatha_tanween.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(shadda.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ٓ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(shadda) {
                        top.linkTo(dammah_tanween.bottom)
                        start.linkTo(madda.end)
                        end.linkTo(alif_khanjariyah.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ّ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(alif_khanjariyah) {
                        top.linkTo(kasra_tanween.bottom)
                        start.linkTo(shadda.end)
                        end.linkTo(reset.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = "ٰ",
                onClick = setColorOnClick
            )

            DiacriticButton(
                modifier = Modifier
                    .constrainAs(reset) {
                        top.linkTo(alif_khanjariyah.top)
                        start.linkTo(alif_khanjariyah.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(alif_khanjariyah.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                text = "RESET",
                fontSize = TextUnit(14f, TextUnitType.Sp),
                onClick = { originalTextState = AnnotatedString(originalText) }
            )

            createHorizontalChain(
                madda, shadda, alif_khanjariyah, reset,
                chainStyle = ChainStyle.Spread
            )

            val barrier = createBottomBarrier(
                madda,
                shadda,
                alif_khanjariyah,
                reset
            )

            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(readOnlyText){
                        top.linkTo(barrier, 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                text = originalTextState,
                textAlign = TextAlign.Center,
                fontSize = TextUnit(32f, TextUnitType.Sp)
            )
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
private fun DiacriticButton(
    modifier: Modifier = Modifier,
    text : String = "" ,
    fontSize: TextUnit = TextUnit(24f, TextUnitType.Sp),
    onClick: (String) -> Unit = {}
) {
    Button(
        modifier = modifier.padding(4.dp),
        onClick = { onClick(text) }
    ) {
        Text(
            modifier = Modifier,
            text = text,
            fontSize = fontSize
        )
    }
}

@Preview(showBackground = true)
@ExperimentalUnitApi
@Composable
fun DefaultPreview() {
    TextShortComposeTheme {
        TextSample()
    }
}