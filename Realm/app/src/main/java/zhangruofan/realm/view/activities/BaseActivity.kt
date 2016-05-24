package zhangruofan.realm.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import zhangruofan.realm.R

open class BaseActivity : AppCompatActivity() {

    var layout: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }
}
