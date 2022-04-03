package to.boosty.cmit.holidaysyear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import to.boosty.cmit.holidaysyear.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        
    }
}