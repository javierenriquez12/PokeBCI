package com.bci.uicomponents.avatar.handler

import com.bci.uicomponents.utils.BaseView
import com.bci.uicomponents.avatar.factory.AvatarFactory
import com.bci.uicomponents.avatar.model.BCIAvatarType
import com.bci.uicomponents.databinding.ViewBciAvatarBinding
import com.bci.uicomponents.utils.isVisible

internal class BCIAvatarHandler (avatarInterface: BaseView<ViewBciAvatarBinding>, private val factory: AvatarFactory) {
    private val view = avatarInterface.getView()

    fun setAvatarType(avatarType: BCIAvatarType){
        val visibleViews = factory.getAvatarType(avatarType).visibleViews()
        with(view){
            avatarLetterView.isVisible(visibleViews.first)
            avatarPlaceHolderView.isVisible(visibleViews.second)
            avatarImageView.isVisible(visibleViews.third)
        }
    }
}