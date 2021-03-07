package com.colemichaels.giantbomb.utilities

import android.content.Context

class FileHelper {
    // static methods for FileHelper
    companion object {
        fun getTextFromResources(context: Context, resourceId: Int): String {
            return context.resources.openRawResource(resourceId).use { inputStream ->
                inputStream.bufferedReader().use { bufferedReader ->
                    bufferedReader.readText()
                }
            }
        }

        fun getTextFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use { inputStream ->
                inputStream.bufferedReader().use { bufferedReader ->
                    bufferedReader.readText()
                }
            }
        }
    }
}