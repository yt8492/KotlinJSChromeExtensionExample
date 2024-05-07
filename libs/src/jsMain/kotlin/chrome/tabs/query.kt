@file:JsQualifier("chrome.tabs")
package chrome.tabs

import kotlin.js.Json

external fun query(
    queryInfo: Json,
    callback: (Array<Tab>) -> Unit = definedExternally,
)

external interface Tab {
    val id: Int
}
