package at.htlleonding.skeleton

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.`object`.conversation.Say

class MainActivity : RobotActivity(), RobotLifecycleCallbacks, View.OnClickListener {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);

        var androidlyButton = Button(this)
        androidlyButton.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Double the value"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnTest
        }

        androidlyButton.setOnClickListener(this)

        QiSDK.register(this, this)

        val game = Game()
    }

    override fun onDestroy() {
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        Log.d(tag,"onRobotFocusGained")

        val say : Say = SayBuilder.with(qiContext)
            .withText("Willkommen zum Rechenspiel")
            .build()

        say.run()
    }

    override fun onRobotFocusLost() {
    }

    override fun onRobotFocusRefused(reason: String?) {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTest -> {
                Log.d("Tag", "test")
            }
            else -> {
                Log.d("Tag", "test not worked")
            }
        }
    }

}
