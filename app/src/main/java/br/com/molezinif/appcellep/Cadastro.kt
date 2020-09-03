package br.com.molezinif.appcellep

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Criando uma Lista de opções para o Spinner
        val listaGenero = arrayListOf("Selecione o gênero", "Feminino", "Masculino", "Outros")

        //Criando um adaptador para a Lista e o Spinner
        val generoAdapter = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_spinner_dropdown_item, //Layout
            listaGenero //Lista que acabamos de criar
        )

        //Linkar o adaptador no Spinner
        spnSingUpGenre.adapter = generoAdapter

        //Executando o clique do botão Cadastrar
        btnCadastroCadastrar.setOnClickListener {

            //Capturar os dados digitados
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastroSenha.text.toString().trim()
            val genero = spnSingUpGenre.selectedItem.toString()

            //Validação dos campos
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == listaGenero[0]){
                //Apresentando um Toast de erro ao usuário
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }else{
                //Todos os campos forem preenchidos

                //Criando ou acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                //Editar o arquivo de preferencias compartilhadas
                val editPrefs = sharedPrefs.edit()

                //Preparando os dados a serem salvos no arquivo
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                //Salvando os dados no arquivo Shared Preferences
                editPrefs.apply()

                //Abrindo a MainActivity
                val mIntent = Intent(this, MainActivity::class.java)

                //Passando informações através da Intent
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                //Tirando todas as telas do empilhamento
                finishAffinity()
            }
        }
    }
}