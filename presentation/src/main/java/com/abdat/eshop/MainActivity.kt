package com.abdat.eshop


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.abdat.eshop.model.UiProductModel
import com.abdat.eshop.navigation.CartScreen
import com.abdat.eshop.navigation.CustomNavType
import com.abdat.eshop.navigation.HomeScreen
import com.abdat.eshop.navigation.ProductDetailScreen
import com.abdat.eshop.navigation.ProfileScreen
import com.abdat.eshop.navigation.productNavType
import com.abdat.eshop.ui.feature.home.HomeScreen
import com.abdat.eshop.ui.theme.KtorClientTheme
import com.abdat.ktorclient.R
import kotlin.reflect.typeOf


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
                        NavHost(navController = navController, startDestination = HomeScreen) {
                            composable<HomeScreen> {
                                HomeScreen(navController )
                            }
                            composable<CartScreen> {
                                Text("Cart")
                            }
                            composable<ProfileScreen> {
                                Text("Profile")
                            }
                            composable<ProductDetailScreen>(
                                typeMap = mapOf(typeOf<UiProductModel>() to CustomNavType.ProductType)
                            ) {
                                val productRoute = it.toRoute<ProductDetailScreen>()
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = productRoute.product.title)
                                }
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
            BottomNavItem.Home,
            BottomNavItem.Cart,
            BottomNavItem.Profile
        )
        items.forEach { item ->
            val isSelected = currentRoute?.substringBefore("?") == item.route::class.qualifiedName
            NavigationBarItem(
                selected = isSelected,
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

sealed class BottomNavItem(val route: Any, val title: String, val icon: Int) {
    object Home : BottomNavItem(HomeScreen, "Home", R.drawable.ic_home)
    object Cart : BottomNavItem(CartScreen, "Cart", R.drawable.ic_cart)
    object Profile : BottomNavItem(ProfileScreen, "Profile", R.drawable.ic_profile)
}


