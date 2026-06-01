package com.sanvi.ecommercetarget.screen.categories.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.Image
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.ImageResult
import coil3.request.crossfade
import com.sanvi.ecommercetarget.screen.home.Model.Category

@Composable
fun CategoriesItem(category: Category, onClick: ()-> Unit){

    Card(modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 8.dp)
        .fillMaxWidth()
        .height(160.dp)
        .clickable(onClick = onClick),

        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.surface)

    ) {

        Box(modifier = Modifier.fillMaxSize()){

            // Background gradient overlay
            Box(modifier = Modifier.fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent,
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f))
                ))
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                ){

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(category.iconUrl)
                                .crossfade(true)
                                .build(),
                        ),
                        contentDescription = "${category.name} Icon",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                }

                Spacer(modifier = Modifier.height(12.dp))

                // Category Name

                Text(
                    text = category.name,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xF4000000),
                    maxLines = 1
                )



            }

        }

    }

}