package com.kameshflutter.myposkmp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.DinnerDining
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.LocalDrink
import androidx.compose.material.icons.outlined.LocalPizza
import androidx.compose.material.icons.outlined.LunchDining
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kameshflutter.myposkmp.model.Menu
import com.kameshflutter.myposkmp.model.MenuCategory
import com.kameshflutter.myposkmp.model.MenuList
import com.kameshflutter.myposkmp.viewmodel.CartViewModel
import com.kameshflutter.myposkmp.viewmodel.MenuViewModel
import myposkmp.composeapp.generated.resources.Res
import myposkmp.composeapp.generated.resources.burger
import myposkmp.composeapp.generated.resources.drinks
import myposkmp.composeapp.generated.resources.kidsmenu
import myposkmp.composeapp.generated.resources.pizza
import myposkmp.composeapp.generated.resources.salad
import myposkmp.composeapp.generated.resources.sandwich
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MenuCategoryNavigation(menuList: MenuList) {
    print(menuList.menuList.size)
//    val extras = MutableCreationExtras().apply {
//        set(MenuViewModel.MY_MENULIST_KEY, menuList)
//    }
//    val viewModel: MenuViewModel = viewModel(
//        factory = MenuViewModel.Factory,
//        extras = extras,
//    )
    val menuViewModel = viewModel<MenuViewModel>()
    val cartViewModel = viewModel<CartViewModel>()
    val selectedCategory by menuViewModel.selectedCategory.collectAsStateWithLifecycle()
    var menuItemsList = emptyList<Menu>()
    val menuItemHasMap = menuViewModel.menuCategoryMap(menuList)
    if(menuItemHasMap.isNotEmpty()) {
        LaunchedEffect(Unit){
            menuViewModel.changeSelectedCategoryValue(MenuCategory.valueOf(menuItemHasMap.keys.first()))
        }
        menuItemsList = menuItemHasMap[selectedCategory.name] as List<Menu>
    }
    var showCartContent by remember { mutableStateOf(false) }

    DetailedDrawer(menuViewModel, menuItemHasMap) { innerPadding ->
        Scaffold(floatingActionButton = {
            createFloatingActionButton(onClick = {
                showCartContent = !showCartContent
            })
        }) {
            Box(modifier = Modifier.padding(innerPadding).fillMaxWidth().fillMaxHeight()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    LazyVerticalGrid(
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        columns = GridCells.Adaptive(minSize = 150.dp),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(menuItemsList) {
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),
                                modifier = Modifier
                                    .size(width = 160.dp, height = 120.dp),
                                colors = CardColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black,
                                    disabledContainerColor = Color.Gray,
                                    disabledContentColor = Color.DarkGray
                                ),
                                onClick = {
                                    if(!showCartContent) {
                                        showCartContent = true
                                    }
                                    cartViewModel.updateTotalCartPriceAndList(it)
                                }
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painterResource(enumImageMenuDrawable(it.menuCategory)),
                                        null,
                                        alignment = Alignment.Center,
                                        modifier = Modifier.size(50.dp, 120.dp).clip(
                                            RoundedCornerShape(10.dp)),
                                        contentScale = ContentScale.FillHeight
                                    )
                                    Column(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth(), // Fill the maximum width available
                                        verticalArrangement = Arrangement.Top,
                                    ) {
                                        // Add your card content here, e.g., text, images, etc.
                                        Text(
                                            it.title,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Bold,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Start,
                                            softWrap = true,
                                            maxLines = 1
                                        )
                                        Text(
                                            it.description,
                                            style = MaterialTheme.typography.labelSmall,
                                            textAlign = TextAlign.Start,
                                            overflow = TextOverflow.Ellipsis,
                                            softWrap = true,
                                            maxLines = 3
                                        )
                                        Text(
                                            "${it.price} ${it.currencyString}",
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .align(Alignment.End),
                                            textAlign = TextAlign.End,
                                            softWrap = true,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                    showCartView(showCartContent, cartViewModel, onClick = {
                        showCartContent = !showCartContent
                    })
                }
            }
        }
    }
}

@Composable
private fun createFloatingActionButton(onClick: ()->Unit) {
    FloatingActionButton(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onClick()
        },
        modifier = Modifier.padding(
            start = 0.dp,
            top = 0.dp,
            bottom = 30.dp,
            end = 30.dp
        ),
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "ShoppingCart",
                tint = Color.White
            )
            Text("cart", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showCartView(showCartContent: Boolean, cartViewModel: CartViewModel, onClick: () -> Unit) {
    val totalPrice by cartViewModel.totalCartPrice.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    AnimatedVisibility(showCartContent) {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Order Number: ${cartViewModel.cartDetails.billNumber}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            },)
        }, modifier = Modifier.fillMaxHeight().fillMaxWidth(0.30f)) { innerPadding ->
            Box(modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(innerPadding)) {
                val cartDetails = cartViewModel.cartDetails
                val cartMenuSet = cartDetails.cartMenuSet
                     Column (modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
                         CartItemCardTitle("Name:", "Qty:", "Pr:")
                         cartMenuSet.forEach{ item ->
                             CartItemCard(item.menuItemName, "${item.quantity}", "${item.quantity * item.price}", onDeleteClick = {
                                 cartViewModel.deleteCartItemAndUpdteTotal(item, totalPrice - (item.quantity * item.price))
                             })
                         }
                         Text(text = "Total Price: $totalPrice kr", textAlign = TextAlign.End ,modifier = Modifier
                             .padding(25.dp)
                             .align(Alignment.End))
                         Row {
                             Button(onClick = {
                                 showDialog = true
                                              }, modifier = Modifier.padding(5.dp)) {
                                 Row(horizontalArrangement = Arrangement.SpaceBetween, modifier =  Modifier.align(Alignment.CenterVertically)) {
                                     Icon(Icons.Outlined.Payment, modifier = Modifier.padding(end = 5.dp), contentDescription = "payment button", tint = Color.White )
                                     Text(text = "Payment", color = Color.White)
                                 }
                             }
                             Button(onClick = {
                                 cartMenuSet.clear()
                                 cartDetails.totalPrice = 0.0
                                 cartViewModel.updateClearTotal()
                                 onClick()
                             }, modifier = Modifier.padding(5.dp)) {
                                 Row(horizontalArrangement = Arrangement.SpaceBetween, modifier =  Modifier.align(Alignment.CenterVertically)){
                                     Icon(
                                         Icons.Outlined.Delete,
                                         modifier = Modifier.padding(end = 5.dp),
                                         contentDescription = "delete all button",
                                         tint = Color.Red
                                     )
                                     Text (text = "Clear all", color = Color.White)
                                 }
                             }
                         }
                     }
                when {
                    showDialog -> {
                        showPaymentAlertDialog(onClickConfirm = {
                            showDialog = false
                            cartMenuSet.clear()
                            cartDetails.totalPrice = 0.0
                            cartViewModel.updateClearTotal()
                            onClick()
                        }, onDismissClick = {
                            showDialog = false
                        }, totalPrice = totalPrice)
                    }
                }
            }
        }
    }
}

@Composable
fun showPaymentAlertDialog(onClickConfirm: () -> Unit, onDismissClick: () -> Unit, totalPrice: Double) {
    val title = "Confirm Payment"
    val text =
        "The total amount you need to pay $totalPrice kr"
    val confirmButtonText = "Confirm Pay"
    val dismissButtonText = "Continue Update"

    val containerColor = AlertDialogDefaults.containerColor
    val titleContentColor = AlertDialogDefaults.titleContentColor
    val textContentColor = AlertDialogDefaults.textContentColor

    val dismissOnBackPress = true
    val dismissOnClickOutside = true

     AlertDialog(
            onDismissRequest = { onDismissClick() },
            confirmButton = {
                TextButton(
                    onClick = { onClickConfirm() }
                ) {
                    Text(confirmButtonText, color = textContentColor)
                }
            },
            dismissButton =
                {
                    TextButton(
                        onClick = { onDismissClick() }
                    ) {
                        Text(dismissButtonText, color = textContentColor)
                    }
                },
            title = { Text(title, color = titleContentColor, textAlign = TextAlign.Center) },
            text = { Text(text, color = textContentColor, textAlign = TextAlign.Center) },
            shape = MaterialTheme.shapes.large,
            backgroundColor = containerColor,
            contentColor = textContentColor,
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedDrawer(
    viewModel: MenuViewModel,
    menuList: Map<String, List<Menu>>,
    content: @Composable (PaddingValues) -> Unit
) {
    val menuCategoryList = menuList.entries
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Menu Category ", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider(modifier = Modifier.padding(bottom = 10.dp))
                    var selectedMenuItem by rememberSaveable {
                        mutableStateOf(menuCategoryList.first().key)
                    }
                    menuCategoryList.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.key) },
                            selected = selectedMenuItem == item.key,
                            badge = {
                                Text(text = menuList[item.key]?.size.toString())
                            },
                            icon = { Icon(enumImageVector(MenuCategory.valueOf(item.key)), contentDescription = null) },
                            onClick = {
                                selectedMenuItem = item.key
                                viewModel.changeSelectedCategoryValue(MenuCategory.valueOf(item.key))
                            }
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Item List") },
                )
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}

private fun enumImageVector(menuCategory: MenuCategory) : ImageVector {
    return when (menuCategory) {
        MenuCategory.PIZZA -> {
            Icons.Outlined.LocalPizza
        }
        MenuCategory.BURGER -> {
            Icons.Outlined.LunchDining
        }
        MenuCategory.SANDWICH -> {
            Icons.Outlined.Fastfood
        }
        MenuCategory.SALAD -> {
            Icons.Outlined.DinnerDining
        }
        MenuCategory.KIDSMENU -> {
            Icons.Outlined.Cookie
        }
        MenuCategory.DRINKS -> {
            Icons.Outlined.LocalDrink
        }
    }
}

private fun enumImageMenuDrawable(menuCategory: MenuCategory) : DrawableResource {
    return when (menuCategory) {
        MenuCategory.PIZZA -> {
            Res.drawable.pizza
        }
        MenuCategory.BURGER -> {
            Res.drawable.burger
        }
        MenuCategory.SANDWICH -> {
            Res.drawable.sandwich
        }
        MenuCategory.SALAD -> {
            Res.drawable.salad
        }
        MenuCategory.KIDSMENU -> {
            Res.drawable.kidsmenu
        }
        MenuCategory.DRINKS -> {
            Res.drawable.drinks
        }
    }
}