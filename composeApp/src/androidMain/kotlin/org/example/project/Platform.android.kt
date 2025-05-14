package org.example.project

import android.os.Build
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import org.example.project.data.createHttpClient

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getHttpClientEngine(): HttpClientEngine {
    return OkHttp.create()
}