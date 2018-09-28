package maks.android.com.printer

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mAdapter: BluetoothAdapter? = null
    private lateinit var con: BluetoothDriver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = BluetoothAdapter.getDefaultAdapter()
        if (!mAdapter!!.isEnabled) {
            startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0)
        }

        btnConnect.setOnClickListener {
            con = BluetoothDriver(mAdapter!!)
            con.connect("RPP02N")
        }

        btnPrint.setOnClickListener {
            val bitmap = BitmapFactory.decodeStream(resources.assets.open("colored.png"))
            con.print(con.draw2PxPoint(bitmap)!!)
//            con.print1(bitmap)
        }

        btnClose.setOnClickListener {
            con.disconnect()
        }
    }

    private fun base64ToBitmap(str: String): Bitmap {
        val decodeStr = Base64.decode(str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodeStr, 0, decodeStr.size)
    }
}