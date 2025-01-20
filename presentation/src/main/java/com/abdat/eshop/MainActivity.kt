package com.abdat.eshop


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.abdat.eshop.ui.feature.home.HomeScreen

import com.abdat.eshop.ui.theme.KtorClientTheme
import com.abdat.ktorclient.R


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorClientTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                HomeScreen()
                            }
                            composable("cart") {
                                Text("Cart")
                            }
                            composable("profile") {
                                Text("Profile")
                            }
                        }

                    }

                }

            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        //get current route
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val items = listOf(
            Screens.Home,
            Screens.Cart,
            Screens.Profile
        )
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    //navigate to route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            //pop up to start destination
                            popUpTo(route) {
                                saveState = true
                            }
                            //avoid building up a large stack of destinations
                            //on the back stack as users select items
                            //
                            launchSingleTop = true
                            //restore state when reSelecting a previously selected item
                            restoreState = true
                        }
                    }
                },
                label = { Text(item.title) },
                icon = {
                    Image(
                        painter = painterResource(item.icon),
                        contentDescription = item.title
                    )
                }, colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray

                )
            )
        }
    }
}

sealed class Screens(val route: String, val title: String, val icon: Int) {
    object Home : Screens("home", "Home", R.drawable.ic_home)
    object Cart : Screens("cart", "Cart", R.drawable.ic_cart)
    object Profile : Screens("profile", "Profile", R.drawable.ic_profile)
}


