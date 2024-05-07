@file:JsQualifier("chrome.tabs")
package chrome.tabs

import kotlin.js.Json

external fun sendMessage(
    tabId: Int,
    message: dynamic,
    options: Json = definedExternally,
    callback: (dynamic) -> Unit = definedExternally,
)
