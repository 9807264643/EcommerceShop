package com.sanvi.ecommercetarget.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar() {
    val currentRoute = ""

    val items = listOf(

        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = "Home"
        ),
        BottomNavItem(
            title = "Categories",
            icon = Icons.Default.Search,
            route = "Categories" // Fixed route name
        ),
        BottomNavItem(
            title = "Wishlist",
            icon = Icons.Default.Favorite,
            route = "Wishlist", // Fixed route name
            badgeCount = 5
        ),
        BottomNavItem(
            title = "Cart",
            icon = Icons.Default.ShoppingCart,
            route = "Cart",
            badgeCount = 3
        ),
        BottomNavItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = "Profile"
        ),

    )

    NavigationBar(
        modifier = Modifier.height(95.dp), // Increased height
        containerColor = Color.White,
        tonalElevation = 2.dp
    ) {
        items.forEach { item ->

            NavigationBarItem(
                icon = {

                    if (item.badgeCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text(item.badgeCount.toString())
                                }
                            }
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp) // Slightly larger icon
                            )
                        }
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                },

                label = {
                    Text(
                        text = item.title,
                        maxLines = 1, // Prevent multi-line text
                        overflow = TextOverflow.Ellipsis
                    )
                },

                selected = currentRoute == item.route,
                onClick = { /* TODO() */ },
                modifier = Modifier.padding(vertical = 4.dp) // Add vertical padding

            )
        }
    }
}

data class BottomNavItem(
    val title : String,
    val icon : ImageVector,
    val route : String,
    val badgeCount : Int = 0
)