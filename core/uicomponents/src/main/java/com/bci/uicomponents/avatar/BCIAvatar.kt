package com.bci.uicomponents.avatar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bci.uicomponents.utils.BaseView
import com.bci.uicomponents.avatar.factory.AvatarFactory
import com.bci.uicomponents.avatar.handler.BCIAvatarHandler
import com.bci.uicomponents.avatar.model.BCIAvatarType
import com.bci.uicomponents.R
import com.bci.uicomponents.databinding.ViewBciAvatarBinding

class BCIAvatar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), BaseView<ViewBciAvatarBinding> {

    private val binding: ViewBciAvatarBinding =
        ViewBciAvatarBinding.inflate(LayoutInflater.from(context), this, true)
    private val bciAvatarPresenter = BCIAvatarHandler(this, AvatarFactory())
    private var enableBackgroundColor: Int? = null

    var type: BCIAvatarType = BCIAvatarType.LETTERS
        set(value) {
            field = value
            bciAvatarPresenter.setAvatarType(value)
        }

    var letter: String = ""
        set(value) {
            binding.avatarLetterView.text = value
            field = value
        }

    var icon: Int = 0
        set(value) {
            binding.avatarPlaceHolderView.setImageResource(value)
            binding.avatarPlaceHolderView.tag = value
            field = value
        }

    var placeholder: Int = 0
        set(value) {
            field = value
            binding.avatarImageView.setImageResource(value)
            binding.avatarImageView.tag = value
        }

    var backgroundViewColor: Int = 0
        set(value) {
            field = value
            binding.avatarCardView.setCardBackgroundColor(value)
            if (isEnabled) enableBackgroundColor = value

        }

    var iconTint: Int = 0
        set(value) {
            field = value
            ImageViewCompat.setImageTintList(binding.avatarPlaceHolderView, ColorStateList.valueOf(value))

        }

    var letterColor: Int = 0
        set(value) {
            field = value
            binding.avatarLetterView.setTextColor(value)
        }

    init {
        setUpViews(attrs)
    }

    fun setImageWithUrl(imageUrl: String){
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(binding.avatarImageView)
    }

    private fun setUpViews(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.BCIAvatar) {
            type = BCIAvatarType.get(
                getInt(
                    R.styleable.BCIAvatar_bci_avatar_type,
                    BCIAvatarType.LETTERS.typeId
                )
            )
            getString(R.styleable.BCIAvatar_bci_avatar_letters)?.let { letter = it }
            icon = getResourceId(R.styleable.BCIAvatar_bci_avatar_icon, 0)
            placeholder = getResourceId(R.styleable.BCIAvatar_bci_avatar_placeholder, 0)
            backgroundViewColor = getInt(
                R.styleable.BCIAvatar_bci_avatar_background_color,
                context.getColor(com.bci.resources.R.color.purple_700)
            )
            iconTint = getInt(
                R.styleable.BCIAvatar_bci_avatar_icon_tint,
                context.getColor(com.bci.resources.R.color.white)
            )
            letterColor = getInt(
                R.styleable.BCIAvatar_bci_avatar_letter_color,
                context.getColor(com.bci.resources.R.color.white)
            )
        }
    }

    override fun getView(type: Int) = binding
}