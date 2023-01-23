package com.bci.uicomponents.avatar.factory

import com.bci.uicomponents.avatar.model.BCIAvatarType

class AvatarFactory {

    fun getAvatarType(type: BCIAvatarType) =
        when (type) {
            BCIAvatarType.LETTERS -> AvatarLetter()
            BCIAvatarType.IMG -> AvatarImage()
            BCIAvatarType.PLACEHOLDER -> AvatarPlaceholder()
        }

}