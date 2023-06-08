
package com.desi.meuapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TicTac(){

    val board = remember { mutableStateOf(Array(3){ arrayOfNulls<String>(3) }) }
    val currentPlayer = remember { mutableStateOf("X") }
    val winner = remember { mutableStateOf<String?>(null) }
    val initialBoard= Array(3){ arrayOfNulls<String>(3) }
    val initialPlayer="X"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)) {
        
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center)
        {

            Text(text = "Jogo Da Velha",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface)

        }
      
        Box(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()){

            Column {
                for (row in 0..2){
                    Row {
                       for (col in 0.. 2){

                           Button(modifier = Modifier
                               .weight(1f)
                               .padding(2.dp),
                               onClick = {
                                    if (board.value[row][col] == null && winner.value == null ){
                                        board.value[row][col] = currentPlayer.value
                                        currentPlayer.value =
                                           if (currentPlayer.value == "X") "O" else "X"
                                        winner.value = checkForWinner(board.value)

                                    }

                           }) {

                               Text(
                                    text  = board.value[row][col] ?: "",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = Color.White
                               )
                           }

                       }
                    }
                }

                Text(
                    text = "Jogador atual: ${currentPlayer.value}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 16.dp))

                if (winner.value != null){
                    Text(
                        text = "Ganhador da partida: ${winner.value}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(4.dp)
                        )

                    LaunchedEffect(true){
                        delay(3000)
                        board.value=initialBoard
                        currentPlayer.value=initialPlayer
                        winner.value = null
                    }
                }



                Button(onClick = {
                board.value = initialBoard
                currentPlayer.value = initialPlayer
                winner.value=null}) {

                    Text(
                        text = "Reset",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )

                }
            }

        }
    }
}

fun checkForWinner(board:Array<Array<String?>>):String? {
    for (row in 0..2) {
        if (board[row][0] != null && board[row][0] == board[row][1] && board[row][1] == board[row][3]) {
            return board[row][0]
        }
    }
    for (col in 0..2){
        if (board[0][col] != null && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
            return board[0][col]
        }
    }

    if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
        return board[0][0]
    }
    if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
        return board[0][2]
    }
    return null
}






