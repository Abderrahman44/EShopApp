package com.abdat.ktorclient


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdat.ktorclient.data.remote.dto.NetworkServiceImpl
import com.abdat.ktorclient.data.remote.dto.PostResponse
import com.abdat.ktorclient.ui.theme.KtorClientTheme


class MainActivity : ComponentActivity() {
    private val ktorClient = NetworkServiceImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            val post = remember {
                mutableStateOf<List<PostResponse>>(emptyList())
            }
            LaunchedEffect(key1 = Unit, block = {
                 post.value = ktorClient.getPosts()
            })
            KtorClientTheme {

                LazyColumn {
                    item {
                        Text(text = "Posts")
                    }
                    items(post.value) { it ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = it.title, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = it.body, fontSize = 14.sp)
                        }
                    }

                }
            }
        }
    }
}

