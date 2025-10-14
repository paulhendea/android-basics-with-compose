package com.example.unscramble.ui.test

import com.example.unscramble.data.MAX_NUMBER_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.getUnscrambledWord
import com.example.unscramble.ui.GameViewModel
import org.junit.Assert
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_CorrectWordGuess_ScoreUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        Assert.assertFalse(currentGameUiState.isGuessWrong)
        Assert.assertEquals(SCORE_INCREASE, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val incorrectPlayerWord = "and"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        Assert.assertTrue(currentGameUiState.isGuessWrong)
        Assert.assertEquals(0, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unscrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        Assert.assertNotEquals(unscrambledWord, gameUiState.currentScrambledWord)
        Assert.assertTrue(gameUiState.currentWordCount == 1)
        Assert.assertTrue(gameUiState.score == 0)
        Assert.assertFalse(gameUiState.isGuessWrong)
        Assert.assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        repeat(MAX_NUMBER_OF_WORDS) {
            val correctWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            viewModel.updateUserGuess(correctWord)
            viewModel.checkUserGuess()
            expectedScore += SCORE_INCREASE
            currentGameUiState = viewModel.uiState.value

            Assert.assertEquals(expectedScore, currentGameUiState.score)
        }

        Assert.assertEquals(MAX_NUMBER_OF_WORDS, currentGameUiState.currentWordCount)
        Assert.assertTrue(currentGameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_WordSkip_ScoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = viewModel.uiState.value
        val correctWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount
        viewModel.skipWord()

        currentGameUiState = viewModel.uiState.value
        Assert.assertEquals(SCORE_INCREASE, currentGameUiState.score)
        Assert.assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
    }
}