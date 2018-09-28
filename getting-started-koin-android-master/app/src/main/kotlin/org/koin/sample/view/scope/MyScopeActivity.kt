package org.koin.sample.view.scope

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fr.ekito.myweatherapp.R
import kotlinx.android.synthetic.main.activity_simple.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.scopedWith
import org.koin.sample.view.viewmodel.MyViewModelActivity

class MyScopeActivity : AppCompatActivity() {

    // lazy injected MyScopePresenter
    val scopePresenter: MyScopePresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        // on ON_DESTROY release "ScopedView" module instances
        scopedWith("ScopedView")

        title = "MyScopeActivity"
        text.text = scopePresenter.sayHello()

        background.setOnClickListener { _ ->
            startActivity(Intent(this, MyViewModelActivity::class.java))
        }
    }
}
