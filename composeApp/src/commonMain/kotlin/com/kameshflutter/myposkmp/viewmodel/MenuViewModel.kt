package com.kameshflutter.myposkmp.viewmodel

import androidx.lifecycle.ViewModel
import com.kameshflutter.myposkmp.model.Menu
import com.kameshflutter.myposkmp.model.MenuCategory
import com.kameshflutter.myposkmp.model.MenuList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

 class MenuViewModel : ViewModel() {

//    companion object {
//
//        val MY_MENULIST_KEY = object : CreationExtras.Key<MenuList> {}
//
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val menuListJson = this[MY_MENULIST_KEY] as MenuList
//                MenuViewModel(
//                    menuListJson = menuListJson,
//                )
//            }
//        }
//    }

//    init {
//        changeSelectedCategoryValue(MenuCategory.PIZZA)
//    }

    private val _selectedMenuCategory = MutableStateFlow(MenuCategory.PIZZA)

    //Expose immutable flow using asStateFlow()
    val selectedCategory = _selectedMenuCategory.asStateFlow()

    fun changeSelectedCategoryValue(selectedValue : MenuCategory){
        _selectedMenuCategory.value = selectedValue
    }

    fun menuCategoryMap(menuListJson: MenuList) : Map<String, List<Menu>> {
        val menuCategoryList = HashMap<String, MutableList<Menu>>()
        menuListJson.menuList.forEach { if(menuCategoryList.containsKey(it.menuCategory.name)) {
            val listOfMenu: MutableList<Menu> = menuCategoryList[it.menuCategory.name] as MutableList
            listOfMenu.add(it)
            menuCategoryList[it.menuCategory.name] = listOfMenu
        } else {
            menuCategoryList[it.menuCategory.name] = mutableListOf(it)
        }
        }
        return menuCategoryList
    }
}