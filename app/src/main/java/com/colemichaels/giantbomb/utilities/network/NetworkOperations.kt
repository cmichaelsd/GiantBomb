package com.colemichaels.giantbomb.utilities.network

import android.content.Context

interface NetworkOperations {
    fun networkAvailable(context: Context): Boolean
}