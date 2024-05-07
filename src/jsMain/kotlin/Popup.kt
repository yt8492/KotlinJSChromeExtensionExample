import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import kotlin.js.json

fun main() {
    val button = document.getElementById("execute") as HTMLButtonElement
    button.onclick = {
        val info = json("active" to true, "currentWindow" to true)
        chrome.tabs.query(info) { tabs ->
            val currentTab = tabs[0]
            chrome.tabs.sendMessage(
                tabId = currentTab.id,
                message = "execute",
            ) {
                console.log("executed: $it")
            }
        }
    }
}
