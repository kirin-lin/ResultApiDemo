package app.kirin.android.resultapidemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import app.kirin.android.resultapidemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPickImage.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, REQUEST_IMAGE)
        }

        binding.btnGetContent.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_GET_CONTENT
            )
            // 所有檔案
            intent.type = "image/*"
            // 所有檔案
//            intent.type = "*/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, REQUEST_CONTENT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_IMAGE ->
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "pick ", Toast.LENGTH_SHORT).show()
                    Log.d("KATA", "pick : " + data?.data.toString())
                    binding.tvMessage.text = data?.data.toString()
                }
            REQUEST_CONTENT ->
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "get content", Toast.LENGTH_SHORT).show()
                    Log.d("KATA", "get content: " + data?.data.toString())
                    binding.tvMessage.text = data?.data.toString()
                }

        }
    }

    companion object {
        const val REQUEST_IMAGE = 10
        const val REQUEST_CONTENT = 20
    }
}