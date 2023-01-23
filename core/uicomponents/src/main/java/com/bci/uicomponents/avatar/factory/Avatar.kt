package com.bci.uicomponents.avatar.factory

class AvatarLetter : AvatarInterface {
    override fun visibleViews() = Triple(true,false,false)
}

class AvatarPlaceholder : AvatarInterface {
    override fun visibleViews() = Triple(false,true,false)
}

class AvatarImage : AvatarInterface {
    override fun visibleViews() = Triple(false,false, true)
}