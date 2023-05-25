package com.example.testefavoritos

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testefavoritos.model.catadores
import com.example.testefavoritos.model.Catador
import com.example.testefavoritos.model.materiais
import com.example.testefavoritos.ui.theme.Shapes
import com.example.testefavoritos.ui.theme.TesteFavoritosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteFavoritosTheme() {
                setContent {
                    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.fundo),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        Greeting(catadores)
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(catadores: List<Catador>) {

        var searchText by remember { mutableStateOf("") }

        Box(modifier = Modifier.padding()) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Transparent)
                    .clip(RoundedCornerShape(18.dp)), shape = RoundedCornerShape(18.dp),
                placeholder = {
                    Text(
                        "Pesquisar por catador",
                        style = MaterialTheme.typography.body1.copy(color = White)
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Ícone de pesquisa",
                        tint = White
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = White,
                    cursorColor = White,
                    placeholderColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        {
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(380.dp)
                    .height(700.dp)
                    .padding(top = 80.dp, bottom = 10.dp, end = 10.dp, start = 10.dp)
                    .clip(RoundedCornerShape((2.dp)))
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Catadores Favoritos",
                        fontSize = 22.sp,
                        color = Color(12, 139, 17),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 45.dp)
                ) {
                    items(catadores.filter {
                        it.nome.contains(
                            searchText,
                            ignoreCase = true
                        )
                    }) { catador ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp, horizontal = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.foto_usuario),
                                    contentDescription = "${catador.nome}",
                                    modifier = Modifier
                                        .size(65.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(12.dp))
                                Column (modifier = Modifier.width(130.dp).height(80.dp).padding(top = 12.dp)) {
                                    Text(
                                        text = catador.nome,
                                        fontSize = 21.sp,
                                        color = Color(12, 139, 17),
                                        modifier = Modifier.fillMaxWidth()
                                    )

                                    Text(
                                        text = "${catador.endereco}",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 10.dp)
                                    ) {
                                        Button(
                                            modifier = Modifier
                                                .height(35.dp)
                                                .width(100.dp),
                                            onClick = { /* ... */ },
                                            shape = RoundedCornerShape(13.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color(
                                                    12,
                                                    139,
                                                    17
                                                )
                                            )
                                        ) {
                                            Text(text = "PERFIL", color = White, fontSize = 13.sp)
                                        }
                                    }



                                }
                            }
                        }
                    }
                }
            }
        }



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TesteFavoritosTheme {
            Greeting(catadores)
        }
    }
}
