package br.com.molezinif.appcellep

import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.invoke.LambdaConversionException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //Recuperando o email passado por meio da intent
        val email =  intent.getStringExtra("INTENT_EMAIL")
        val sharedPrefs = getSharedPreferences("cadastro_$email", MODE_PRIVATE)
        val name = sharedPrefs.getString("NOME","")
        val sobrenome = sharedPrefs.getString("SOBRENOME","")
        val genero = sharedPrefs.getString("GENERO","")
        //Exibir dados recuperados na tela
        txvMainNome.text = "$name $sobrenome"
        // não interpolou porque ja esxiste a val, só seria nescessário se fizesse uma frase
        txvMainEmail.text = email
        txvMainGenero.text = "$genero"
        // executando o botão sair
        btnMainSair.setOnClickListener{
            //criando um alerta
            AlertDialog.Builder(this,)
                .setTitle("Atenção!")
                .setMessage("Tem certeza que deseja sair?")
                .setPositiveButton("Sim, desejo sair"){_,_->
                    //executando o clique do botão "sim"
                    val mIntent = Intent(this, LoginActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }
                .setNeutralButton("Cancelar"){_,_->}
                .setCancelable(false)
                    // set cancelable, serve para quando clicar fora da caixa de diálogo, não cancelar a mensagem
                .create() //buildou, e depois criou
                .show() // mostrar o alerta
        }
        btnSiteCellep.setOnClickListener{
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)

        }
    }
}