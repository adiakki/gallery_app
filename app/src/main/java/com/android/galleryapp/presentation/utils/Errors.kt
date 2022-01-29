package com.android.galleryapp.presentation.utils

import kotlin.Error

open class CustomError(var error: Error? = null)
class NoConnectError() : CustomError()