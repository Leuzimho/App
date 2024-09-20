package com.example.prova

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prova.ui.theme.ProvaTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
/*
// Produto data class
data class Produto(
    val nome: String,
    val categoria: String,
    val preco: Double,
    val quantidade: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaCadastroProduto()
        }
    }
}

@Composable
fun TelaCadastroProduto() {
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

    // Lista de produtos cadastrados (interna)
    val listaProdutos = remember { mutableStateListOf<Produto>() }

    // Função para validar e adicionar produto à lista
    fun adicionarProduto() {
        val precoDouble = preco.toDoubleOrNull()
        val quantidadeInt = quantidade.toIntOrNull()

        if (nome.isNotEmpty() && categoria.isNotEmpty() && precoDouble != null && quantidadeInt != null) {
            val novoProduto = Produto(nome, categoria, precoDouble, quantidadeInt)
            listaProdutos.add(novoProduto)
            // limpar os campos após cadastrar
            nome = ""
            categoria = ""
            preco = ""
            quantidade = ""

        }
    }

    // Interface de Cadastro de Produto
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cadastro de Produto")
        Spacer(modifier = Modifier.height(16.dp))

        // Campo Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Produto") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Categoria
        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoria") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Preço
        OutlinedTextField(
            value = preco,
            onValueChange = { preco = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Campo Quantidade em Estoque
        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade em Estoque") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Cadastrar
        Button(onClick = { adicionarProduto() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Cadastrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaCadastroProdutoPreview() {
    TelaCadastroProduto()
}


data class Produto(
    val nome: String,
    val categoria: String,
    val preco: Double,
    val quantidade: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacaoApp()
        }
    }
}

@Composable
fun NavegacaoApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "cadastro") {
        composable("cadastro") { TelaCadastroProduto(navController) }
        composable("lista") { TelaListaProdutos(navController) }
        composable("detalhes/{produtoNome}/{quantidade}") { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("produtoNome") ?: ""
            val quantidade = backStackEntry.arguments?.getString("quantidade") ?: ""
            TelaDetalhesProduto(nome, quantidade)
        }
    }
}

@Composable
fun TelaCadastroProduto(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

    // Lista de produtos cadastrados (interna)
    val listaProdutos = remember { mutableStateListOf<Produto>() }

    fun adicionarProduto() {
        val precoDouble = preco.toDoubleOrNull()
        val quantidadeInt = quantidade.toIntOrNull()

        if (nome.isNotEmpty() && categoria.isNotEmpty() && precoDouble != null && quantidadeInt != null) {
            val novoProduto = Produto(nome, categoria, precoDouble, quantidadeInt)
            listaProdutos.add(novoProduto)

            // Limpar os campos após cadastrar
            nome = ""
            categoria = ""
            preco = ""
            quantidade = ""

            // Navegar para a Tela de Lista de Produtos
            navController.navigate("lista")
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cadastro de Produto")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome do Produto") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoria") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = preco, onValueChange = { preco = it }, label = { Text("Preço") }, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = quantidade, onValueChange = { quantidade = it }, label = { Text("Quantidade em Estoque") }, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { adicionarProduto() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Cadastrar")
        }
    }
}

@Composable
fun TelaListaProdutos(navController: NavController) {
    val listaProdutos = remember { mutableStateListOf<Produto>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Lista de Produtos")
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(listaProdutos) { produto ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "${produto.nome} (${produto.quantidade} unidades)")
                    Button(onClick = {
                        navController.navigate("detalhes/${produto.nome}/${produto.quantidade}")
                    }) {
                        Text(text = "Detalhes")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TelaDetalhesProduto(nome: String, quantidade: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Detalhes do Produto")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nome: $nome")
        Text(text = "Quantidade: $quantidade")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaListaProdutos() {
    NavegacaoApp()
}


 */


data class Produto(
    val nome: String,
    val categoria: String,
    val preco: Double,
    val quantidade: Int
)

object Estoque {
    val listaProdutos = mutableListOf<Produto>()

    fun adicionarProduto(produto: Produto) {
        listaProdutos.add(produto)
    }

    fun calcularValorTotalEstoque(): Double {
        return listaProdutos.sumOf { it.preco * it.quantidade }
    }

    fun calcularQuantidadeTotal(): Int {
        return listaProdutos.sumOf { it.quantidade }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacaoApp()
        }
    }
}

@Composable
fun NavegacaoApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "cadastro") {
        composable("cadastro") { TelaCadastroProduto(navController) }
        composable("lista") { TelaListaProdutos(navController) }
        composable("detalhes/{produtoJson}") { backStackEntry ->
            val produtoJson = backStackEntry.arguments?.getString("produtoJson") ?: ""
            val produto = Gson().fromJson(produtoJson, Produto::class.java)
            TelaDetalhesProduto(produto, navController)
        }
        composable("estatisticas") { TelaEstatisticas(navController) }
    }
}

@Composable
fun TelaCadastroProduto(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cadastro de Produto")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome do Produto") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoria") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = preco, onValueChange = { preco = it }, label = { Text("Preço") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = quantidade, onValueChange = { quantidade = it }, label = { Text("Quantidade em Estoque") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val precoDouble = preco.toDoubleOrNull()
            val quantidadeInt = quantidade.toIntOrNull()

            if (nome.isBlank() || categoria.isBlank() || precoDouble == null || quantidadeInt == null || precoDouble < 0 || quantidadeInt < 1) {
                Toast.makeText(navController.context, "Todos os campos são obrigatórios e valores devem ser válidos.", Toast.LENGTH_SHORT).show()
            } else {
                val novoProduto = Produto(nome, categoria, precoDouble, quantidadeInt)
                Estoque.adicionarProduto(novoProduto)
                navController.navigate("lista")
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Cadastrar")
        }
    }
}

@Composable
fun TelaListaProdutos(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Lista de Produtos")
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(Estoque.listaProdutos) { produto ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "${produto.nome} (${produto.quantidade} unidades)")
                    Button(onClick = {
                        val produtoJson = Gson().toJson(produto)
                        navController.navigate("detalhes/$produtoJson")
                    }) {
                        Text(text = "Detalhes")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("estatisticas") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Estatísticas")
        }
    }
}

@Composable
fun TelaDetalhesProduto(produto: Produto, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Detalhes do Produto")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nome: ${produto.nome}")
        Text(text = "Categoria: ${produto.categoria}")
        Text(text = "Preço: R$ ${produto.preco}")
        Text(text = "Quantidade: ${produto.quantidade}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Voltar")
        }
    }
}

@Composable
fun TelaEstatisticas(navController: NavController) {
    val valorTotal = Estoque.calcularValorTotalEstoque()
    val quantidadeTotal = Estoque.calcularQuantidadeTotal()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Estatísticas")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Valor Total do Estoque: R$ $valorTotal")
        Text(text = "Quantidade Total de Produtos: $quantidadeTotal")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Voltar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaCadastroProduto() {
    NavegacaoApp()
}
