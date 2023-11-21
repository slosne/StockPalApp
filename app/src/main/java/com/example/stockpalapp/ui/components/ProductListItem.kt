package com.example.stockpalapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.viewmodels.PantryViewModel
import com.google.firebase.Timestamp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListItem(
    title: String,
    description: String?,
    amount: Number?,
    imageUrl: String?,
    date: Timestamp?,
    actions: @Composable () -> Unit

)
{

    val viewModel: PantryViewModel = hiltViewModel()

    Column {
        ListItem(
            modifier = Modifier.height(100.dp),
            headlineText = { Text(text = title) },
            supportingText = { Column {
                if (description != null) {
                    Text(text = description)
                }
                if (amount != null){
                    Text(text = stringResource(R.string.ammount) + amount.toString())
                }
                if (date != null) {
                    Text(text = stringResource(R.string.exp_date_title) + viewModel.convertTimestampToString(date))
                }
            } },
            leadingContent = {
                Surface(
                    modifier = Modifier.size(70.dp),
                    shape = RoundedCornerShape(20)
                ) {
                    val imagePainter = rememberImagePainter(data = imageUrl)
                    Image(
                        painter = imagePainter,
                        contentDescription = title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            },
            trailingContent = { actions() }
        )
        Divider()
    }
}