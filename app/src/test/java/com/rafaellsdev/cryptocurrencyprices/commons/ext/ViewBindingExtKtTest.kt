import android.app.Activity
import android.view.View
import com.rafaellsdev.cryptocurrencyprices.commons.ext.onClick
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ViewBindingExtTest {

    @Test
    fun onClick_invokesListenerFunction() {
        val view = View(Robolectric.buildActivity(Activity::class.java).get())
        var wasClicked = false
        view.onClick { wasClicked = true }
        view.performClick()
        assert(wasClicked)
    }

    @Test
    fun onClick_doesNotInvokeListenerFunctionWithoutClick() {
        val view = View(Robolectric.buildActivity(Activity::class.java).get())
        var wasClicked = false
        view.onClick { wasClicked = true }
        assert(!wasClicked)
    }
}