package com.hellobike.thrio_example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hellobike.flutter.thrio.navigator.RouteSettings
import com.hellobike.flutter.thrio.navigator.ThrioNavigator
import kotlinx.android.synthetic.main.activity_native.btn_10
import kotlinx.android.synthetic.main.activity_native.btn_11
import kotlinx.android.synthetic.main.activity_native.btn_12
import kotlinx.android.synthetic.main.activity_native.btn_13
import kotlinx.android.synthetic.main.activity_native.btn_20
import kotlinx.android.synthetic.main.activity_native.btn_21
import kotlinx.android.synthetic.main.activity_native.btn_3
import kotlinx.android.synthetic.main.activity_native.tv_native
import kotlinx.android.synthetic.main.activity_native2.*

class Native2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native2)
        initView()
    }

    private fun initView() {
        tv_native.text = "native 2"

        btn_10.setOnClickListener {
            ThrioNavigator.push("/biz2/flutter2",
                    params = mapOf("k1" to 1),
                    result = {
                        Log.e("Thrio", "push result data $it")
                    },
                    poppedResult = {
                        Log.e("Thrio", "native2 poppedResult call params $it")
                    }
            )
        }

        btn_11.setOnClickListener {
            ThrioNavigator.remove("/biz2/flutter2") {
                Log.e("Thrio", "push result data $it")
            }
        }

        btn_12.setOnClickListener {
            ThrioNavigator.push("native1",
                    params = mapOf("k1" to 1),
                    result = {
                        Log.e("Thrio", "push result data $it")
                    },
                    poppedResult = {
                        Log.e("Thrio", "native2 poppedResult call params $it")
                    }
            )
        }

        btn_13.setOnClickListener {
            ThrioNavigator.remove("native1") {
                Log.e("Thrio", "push result data $it")
            }
        }

        btn_20.setOnClickListener {
            ThrioNavigator.popTo("native1")
        }

        btn_21.setOnClickListener {
            val intent = Intent(this, Native1Activity::class.java)
            startActivity(intent)
        }

        btn_3.setOnClickListener {
            ThrioNavigator.pop("native 2 popResult")
        }

        btn_1.setOnClickListener {
            ThrioNavigator.notify("/biz1/flutter1", name = "page1Notify", params = mapOf( 1 to 2 ))
        }
    }

    override fun onResume() {
        super.onResume()

        val data = intent.getSerializableExtra("NAVIGATION_ROUTE_SETTINGS")

        if (data != null) {
            val settings = RouteSettings.fromArguments(data as Map<String, Any>)
            tv_native.text = "native2 index " + settings.index
        }
    }
}
