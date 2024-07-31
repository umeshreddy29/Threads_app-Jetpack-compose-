package com.example.threadyoutube.item_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.threadyoutube.R
import com.example.threadyoutube.model.ThreadModel
import com.example.threadyoutube.model.UserModel
import com.example.threadyoutube.navigation.Routes

@Composable
fun UserItem(
    users: UserModel,
    navHostController: NavHostController
) {

    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    val routes = Routes.OtherUsers.routes.replace("{data}",users.uid)
                    navHostController.navigate(routes)
                }
        ) {

            val (userImage, userName, date, time, title, image) = createRefs()

            Image(painter = rememberAsyncImagePainter(model = users.imageUrl),
                contentDescription = "close",
                modifier = Modifier
                    .constrainAs(userImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop)

            Text(text = users.userName, style = TextStyle(
                fontSize = 20.sp,
            ), modifier = Modifier.constrainAs(userName) {
                top.linkTo(userImage.top)
                start.linkTo(userImage.end, margin = 12.dp)
            })

            Text(text = users.name, style = TextStyle(
                fontSize = 18.sp,
            ), modifier = Modifier.constrainAs(title) {
                top.linkTo(userName.bottom, margin = 2.dp)
                start.linkTo(userName.start)
            })


        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }


}
