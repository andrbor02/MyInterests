package com.example.feature_authorization_ui.impl.presentation.navigation

import com.example.feature_authorization_ui.impl.presentation.ui.AccountFragment
import com.example.feature_authorization_ui.impl.presentation.ui.OrganizationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal object AuthorizationScreens {
    fun Organization() = FragmentScreen { OrganizationFragment() }
    fun Account() = FragmentScreen(clearContainer = false) { AccountFragment() }
}