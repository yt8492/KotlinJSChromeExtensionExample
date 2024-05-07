import kotlinx.browser.document
import org.w3c.dom.MutationObserver
import org.w3c.dom.MutationObserverInit
import org.w3c.dom.Node
import org.w3c.dom.asList

fun main() {
    val body = document.body ?: return
    val config = MutationObserverInit(childList = true, subtree = true)
    val observer = MutationObserver { mutationRecords, observer ->
        mutationRecords.forEach { mutation ->
            if (mutation.type == "childList") {
                observer.disconnect()
                replaceElements()
                observer.observe(body, config)
            }
        }
    }
    chrome.runtime.onMessage.addListener { message, _, _ ->
        if (message == "execute") {
            observer.observe(body, config)
        }
        true
    }
}

private fun replaceElements() {
    val elements = document.querySelectorAll(
        "div[data-testid=cellInnerDiv]"
    )
    elements.asList().forEach {
        replaceText(it)
    }
}

private fun replaceText(target: Node) {
    var child = target.firstChild
    while (child != null) {
        child = replaceChild(child.nextSibling, child)
    }
}

private fun replaceChild(next: Node?, node: Node?): Node? {
    if (node == null) {
        return null
    }
    when (node.nodeType) {
        Node.TEXT_NODE -> {
            val replaced = document.createTextNode(
                node.textContent
                    ?.replace("な", "にゃ")
                    ?: ""
            )
            node.parentNode?.replaceChild(replaced, node)
            return next
        }
        Node.ELEMENT_NODE -> {
            var child = node.firstChild
            while (child != null) {
                child = replaceChild(child.nextSibling, child)
            }
            return next
        }
        else -> {
            return next
        }
    }
}
