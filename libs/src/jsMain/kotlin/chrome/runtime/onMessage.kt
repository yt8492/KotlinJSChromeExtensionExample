@file:JsQualifier("chrome.runtime")
package chrome.runtime

import kotlin.js.Json

external val onMessage: OnMessage

external interface OnMessage {
    fun addListener(
        callback: (
            message: dynamic,
            sender: Json,
            setResult: (dynamic) -> Unit
        ) -> Boolean
    )
}
