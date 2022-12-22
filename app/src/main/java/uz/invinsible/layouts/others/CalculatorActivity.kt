package uz.invinsible.layouts.others

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.databinding.CalculatorActivityLayoutBinding

class CalculatorActivity : AppCompatActivity(), OnClickListener {
    lateinit var binding: CalculatorActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculatorActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calc7.setOnClickListener(this)
        binding.calc8.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var text = binding.calcEdit.text.toString()

        when (view) {
            binding.calc7 -> {
                text = text.plus("7")
                binding.calcEdit.setText(text)
            }
            binding.calc8 -> {
                text = text.plus("8")
                binding.calcEdit.setText(text)
            }
        }
    }
}