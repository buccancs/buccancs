# LMS SDK Stub Implementation Guide

## Overview

This document describes the stub implementation of the proprietary Topdon LMS
SDK that was removed from the project. These stubs allow the code to compile and
provide placeholders for the actual SDK functionality.

- Location: `libapp/src/main/java/com/topdon/lms/sdk/`

---

## Package Structure

```
com.topdon.lms.sdk/
├── LMS.kt                          # Main SDK singleton
├── UrlConstant.kt                  # API constants
├── bean/
│   ├── CommonBean.kt               # Generic response wrapper
│   ├── ResponseBean.kt             # Response with utilities
│   └── UserInfo.kt                 # User data model
├── network/
│   ├── HttpProxy.kt                # HTTP client
│   └── IResponseCallback.kt        # Callback interface
├── utils/
│   ├── DateUtils.kt                # Date formatting
│   ├── LanguageUtil.kt             # Language utilities
│   └── StringUtils.kt              # String resources
├── view/ (or weiget/)
│   └── LmsLoadView.kt              # Loading view widget
├── weiget/
│   └── TToast.kt                   # Toast notifications
└── xutils/
    ├── x.kt                        # HTTP framework main
    ├── common/
    │   └── Callback.kt             # Async callbacks
    └── http/
        └── RequestParams.kt        # HTTP parameters
```

---

## Core Classes

### 1. LMS (Main SDK Class)

```kotlin
package com.topdon.lms.sdk

class LMS private constructor() {
    var token: String? = "stub_token"
    val isLogin: Boolean get() = token != null

    companion object {
        var mContext: Context? = null
        const val SUCCESS = 0
        fun getInstance(): LMS
    }

    // Methods
    fun checkAppUpdate(callback: (ResponseData) -> Unit)
    fun getStatement(type: String, callback: IResponseCallback)
    fun syncUserInfo(callback: ((CommonBean<UserInfo>) -> Unit)?)
    fun getUserInfo(callback: ((CommonBean<String>) -> Unit)?)
    fun getUserInfo(): UserInfo?
    fun bindDevice(sn: String, randomNum: String, callback: ((Int) -> Unit)?): Int
    fun bindDevice(sn: String, randomNum: String, p3: String, p4: String, callback: ((ResponseData) -> Unit)?)
}
```

- Usage:

```kotlin
// Initialize
LMS.mContext = applicationContext

// Check login
if (LMS.getInstance().isLogin) {
    // User is logged in
}

// Get user info
LMS.getInstance().getUserInfo { response ->
    // Handle response
}
```

---

### 2. HttpProxy (HTTP Client)

```kotlin
package com.topdon.lms.sdk.network

class HttpProxy private constructor() {
    companion object {
        val instant: HttpProxy
    }

    fun post(url: String, needToken: Boolean, params: RequestParams, callback: IResponseCallback?)
    fun get(url: String, needToken: Boolean, params: RequestParams, callback: IResponseCallback?)
}
```

- Usage:

```kotlin
val params = RequestParams()
params.addBodyParameter("key", "value")
params.uri = "https://api.example.com/endpoint"

HttpProxy.instant.post(UrlConstant.BASE_URL + "api/endpoint", true, params, object : IResponseCallback {
    override fun onResponse(response: String?) {
        // Handle success
    }

    override fun onFail(exception: Exception?) {
        // Handle error
    }
})
```

---

### 3. Data Models

#### CommonBean<T>

```kotlin
data class CommonBean<T>(
    var code: String? = null,
    var msg: String? = null,
    var data: T? = null
)
```

#### ResponseBean<T>

```kotlin
data class ResponseBean<T>(
    var code: Int? = null,
    var msg: String? = null,
    var data: T? = null,
    var success: Boolean = false
) {
    companion object {
        fun convertCommonBean(json: String?, defaultValue: CommonBean<*>?): CommonBean<*>
    }
}
```

#### UserInfo

```kotlin
data class UserInfo(
    var token: String? = null,
    var topdonId: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var nickname: String? = null,
    var userName: String? = null,
    var avatar: String? = null
)
```

---

### 4. Utilities

#### DateUtils

```kotlin
object DateUtils {
    fun formatDate(date: Date?, pattern: String = "yyyy-MM-dd HH:mm:ss"): String
    fun formatDate(timestamp: Long, pattern: String = "yyyy-MM-dd HH:mm:ss"): String
    fun format(timestamp: Long, pattern: String, timeZone: TimeZone): String
    fun parseDate(dateString: String?, pattern: String = "yyyy-MM-dd HH:mm:ss"): Date?
}
```

#### LanguageUtil

```kotlin
object LanguageUtil {
    fun getLanguageId(context: Context?): Int
    fun getLanguageCode(context: Context?): String
}
```

Language ID mapping:

- 0 = English (default)

- 1 = Chinese (zh)

- 2 = Spanish (es)

- 3 = French (fr)

- 4 = German (de)

- 5 = Japanese (ja)

- 6 = Korean (ko)

#### StringUtils

```kotlin
object StringUtils {
    fun getResString(context: Context?, resId: Int): String
}
```

#### TToast

```kotlin
object TToast {
    fun showShort(context: Context?, message: String?)
    fun showLong(context: Context?, message: String?)
    fun shortToast(context: Context?, messageResId: Int)
    fun longToast(context: Context?, messageResId: Int)
}
```

---

### 5. xutils3 HTTP Framework

#### Callback Interface

```kotlin
interface Callback {
    interface CommonCallback<T> {
        fun onSuccess(result: T?)
        fun onError(ex: Throwable?, isOnCallback: Boolean)
        fun onCancelled(cex: CancelledException?)
        fun onFinished()
    }
}
```

#### RequestParams

```kotlin
class RequestParams {
    var uri: String = ""
    var isAsJsonContent: Boolean = false

    fun addBodyParameter(key: String, value: Any?)
    fun addQueryStringParameter(key: String, value: String?)
    fun getBodyParams(): Map<String, Any?>
    fun getQueryParams(): Map<String, Any?>
}
```

#### Main HTTP Client

```kotlin
object x {
    fun http(): HttpManager

    object HttpManager {
        fun <T> get(params: RequestParams, callback: Callback.CommonCallback<T>?)
        fun <T> post(params: RequestParams, callback: Callback.CommonCallback<T>?)
        fun <T> put(params: RequestParams, callback: Callback.CommonCallback<T>?)
        fun <T> delete(params: RequestParams, callback: Callback.CommonCallback<T>?)
    }
}
```

---

### 6. UI Components

#### LmsLoadView

```kotlin
class LmsLoadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    fun show()
    fun hide()
    fun setLoadingText(text: String?)
}
```

- XML Usage:

```xml
<com.topdon.lms.sdk.weiget.LmsLoadView
    android:id="@+id/loadingView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

---

## Stub Behavior

All stub implementations follow these principles:

- No-op Operations: Methods that would normally perform network/SDK operations
  do nothing

- Safe Returns: Methods return safe default values (empty strings, nulls,
  success codes)

- Callback Invocation: Callbacks are invoked with stub data to prevent NPEs

- Error Simulation: HTTP operations call `onFail` callbacks to simulate
  unavailability

### Error Handling

When stub methods are called, they typically:

- Return `LMS.SUCCESS` (

0.

for status codes

- Return empty strings or null for data

- Invoke error callbacks with message: " Stub: {class} not implemented"

---

## Migration from Stubs to Real Implementation

When the actual LMS SDK becomes available:

- Remove stub files from `libapp/src/main/java/com/topdon/lms/sdk/`

- Add real SDK AAR to `libapp/libs/` directory

- Update dependencies in `libapp/build.gradle`:

```gradle
dependencies {
    api files('libs/lms_sdk.aar')
}
```

- Test all SDK integration points

- Update initialization in Application class

- Verify authentication flows

---

## Known Limitations

- No Network Calls: All HTTP operations are stubbed

- No Authentication: Login always fails or returns stub data

- No Cloud Sync: User data not synchronized

- No Analytics: Tracking/analytics not functional

- No Updates: Version checking returns no updates

---

## Dependencies

The stubs require only standard Android/Kotlin libraries:

- `android.content.Context` - `android.widget.Toast` - `com.google.gson.Gson`
  (for JSON parsing in ResponseBean)

- Standard Kotlin stdlib

No external dependencies are required for the stubs themselves.

---

## Testing

To test code that uses LMS SDK stubs:

```kotlin
@Test
fun testUserLogin() {
    // Given
    val lms = LMS.getInstance()

    // When
    val isLoggedIn = lms.isLogin

    // Then
    assertTrue(isLoggedIn) // Stub always has token
}
```

Mock the LMS class in unit tests if different behavior is needed.

---

## Support

If you encounter issues with the stubs:

1. Check that imports are correct: `import com.topdon.lms.sdk.*`

2. Verify the stub file exists in the expected location

3. Check for compilation errors in the stub classes

4. Ensure Java 21 is being used for compilation

---

- Document Version: 1.0

- Last Updated: 2025-10-16

- Status: Production-ready stubs
