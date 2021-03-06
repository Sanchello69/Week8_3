package com.vas.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(navCommand: NavCommand? = null, hostId: Int? = null) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }

    if (navCommand == null)
        navController.navigateUp()
    else
        navController.navigate(navCommand.action, navCommand.args, navCommand.navOptions)
}