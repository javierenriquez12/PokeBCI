package com.bci.uicomponents.avatar.model

enum class BCIAvatarType(val typeId: Int) {
    PLACEHOLDER(1),
    LETTERS(2),
    IMG(3);

    companion object {
        /**
         * Returns the Type identified by 'id'.
         * @throws IllegalStateException if 'id' is unknown.
         */
        @JvmStatic
        @Throws(Exception::class)
        fun get(id: Int): BCIAvatarType {
            return when (id) {
                PLACEHOLDER.typeId -> PLACEHOLDER
                LETTERS.typeId -> LETTERS
                IMG.typeId -> IMG
                else -> PLACEHOLDER
            }
        }
    }
}