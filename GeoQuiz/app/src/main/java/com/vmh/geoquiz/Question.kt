package com.vmh.geoquiz

import androidx.annotation.StringRes

data class Question(
        @StringRes val questionRes: Int,
        val answer: Boolean,
        var answered: Boolean = false,
        var userAnswer: Boolean = false
)
