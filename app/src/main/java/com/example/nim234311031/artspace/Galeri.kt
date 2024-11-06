package com.example.nim234311031.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nim234311031.artspace.ui.theme.ArtSpaceTheme

class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryScreen(onBackClick = { finish() })
                }
            }
        }
    }
}

@Composable
fun ArtGalleryScreen(onBackClick: () -> Unit) {
    val images = listOf(
        R.drawable.foto1,
        R.drawable.foto2,
        R.drawable.foto3,
        R.drawable.foto4
    )
    val titles = listOf(
        "Menara Eiffel, Prancis",
        "Piramida Kuno Mesir",
        "Coloseum Roma Italy",
        "The Great Wall, China"
    )
    val artists = listOf(
        "Foto diatas diambil pada sore hari saat matahari tenggelam (2024)",
        "Langit yang cerah dengan kondisi tanah yang gersang (2024)",
        "Keindahan bangunan Coloseum (2024)",
        "Perjalanan yang jauh serta memiliki pemandangan yang indah (2024)"
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    fun nextImage() {
        if (currentIndex < images.size - 1) {
            currentIndex++
        }
    }

    fun previousImage() {
        if (currentIndex > 0) {
            currentIndex--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // tombol balek panah
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali ke Beranda"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp)
//                .border(4.dp, Color.Gray, RoundedCornerShape(8.dp))

                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = titles[currentIndex],
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = artists[currentIndex],
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // kanan kiri
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { previousImage() }, enabled = currentIndex > 0) {
                Text("Previous")
            }
            Button(onClick = { nextImage() }, enabled = currentIndex < images.size - 1) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryScreenPreview() {
    ArtSpaceTheme {
        ArtGalleryScreen(onBackClick = {})
    }
}
