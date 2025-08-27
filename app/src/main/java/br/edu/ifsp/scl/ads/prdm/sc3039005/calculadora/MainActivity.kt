package br.edu.ifsp.scl.ads.prdm.sc3039005.calculadora

import android.os.Bundle
import android.widget.Toast
import br.edu.ifsp.scl.ads.prdm.sc3039005.calculadora.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.numeroZero.setOnClickListener { acrescentarExpressao("0", true) }
        binding.numeroUm.setOnClickListener { acrescentarExpressao("1", true) }
        binding.numeroDois.setOnClickListener { acrescentarExpressao("2", true) }
        binding.numeroTres.setOnClickListener { acrescentarExpressao("3", true) }
        binding.numeroQuatro.setOnClickListener { acrescentarExpressao("4", true) }
        binding.numeroCinco.setOnClickListener { acrescentarExpressao("5", true) }
        binding.numeroSeis.setOnClickListener { acrescentarExpressao("6", true) }
        binding.numeroSete.setOnClickListener { acrescentarExpressao("7", true) }
        binding.numeroOito.setOnClickListener { acrescentarExpressao("8", true) }
        binding.numeroNove.setOnClickListener { acrescentarExpressao("9", true) }
        binding.ponto.setOnClickListener { acrescentarExpressao(".", true) }

        binding.adicao.setOnClickListener { acrescentarExpressao("+", false) }
        binding.subtracao.setOnClickListener { acrescentarExpressao("-", false) }
        binding.multiplicacao.setOnClickListener { acrescentarExpressao("*", false) }
        binding.divisao.setOnClickListener { acrescentarExpressao("/", false) }

        binding.clear.setOnClickListener {
            binding.expressao.text = ""
            binding.resultado.text = ""
        }

        binding.apagar.setOnClickListener {
            val string = binding.expressao.text.toString()
            if (string.isNotBlank()) {
                binding.expressao.text = string.substring(0, string.length - 1)
            }
            binding.resultado.text = ""
        }

        binding.igual.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(binding.expressao.text.toString()).build()
                val resultado = expressao.evaluate().toString()
                val doubleResultado = resultado.toDouble()

                if (resultado == doubleResultado.toString()) {
                    binding.resultado.text = doubleResultado.toString()
                }
                else {
                    binding.resultado.text = resultado
                }
            }
            catch (e: ArithmeticException) {
                // Caso específico: divisão por zero
                binding.resultado.text = ""
                binding.expressao.text = ""
                Toast.makeText(this, "Erro: divisão por zero", Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception) {
                binding.resultado.text = "Error :("
                binding.expressao.text = ""
            }
        }
    }

    fun acrescentarExpressao(string: String, limpar_dados: Boolean) {
        if (binding.resultado.text.isNotEmpty()) {
            binding.expressao.text = ""
        }

        if (limpar_dados) {
            binding.resultado.text = ""
            binding.expressao.append(string)
        }

        else {
            binding.expressao.append(binding.resultado.text)
            binding.expressao.append(string)
            binding.resultado.text = ""
        }
    }
}