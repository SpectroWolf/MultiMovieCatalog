package com.multilaser.multimoviecatalog.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.multilaser.multimoviecatalog.MainCoroutineRule
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.repositories.Repository
import com.multilaser.multimoviecatalog.utils.Resource
import com.multilaser.multimoviecatalog.utils.Status
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private var repository: Repository = mockk()

    @Before
    fun setup() {
        viewModel = MainViewModel(repository)

    }

    @Test
    fun `should return mock movieList of movie when called any of the getMovieList in this case getPopularMovieList`() {
        coEvery { repository.getPopularMovieList() } returns Resource<MovieList>(
            Status.SUCCESS, MovieList(
                listOf<Movie>(
                    Movie(
                        "1",
                        "Mocked Movie",
                        "path",
                        "2022",
                        "123",
                        123,
                        "Nice movie",
                        "path1",
                        "1",
                        listOf(),
                        listOf()
                    ), Movie(
                        "2",
                        "Mocked Movie2",
                        "path2",
                        "2022",
                        "1234",
                        123,
                        "Nice movie2",
                        "path2",
                        "2",
                        listOf(),
                        listOf()
                    )
                )
            ), ""
        )
        runBlockingTest {
            viewModel.getPopularMovieList()
        }

        coVerify { repository.getPopularMovieList() }
    }

    @Test
    fun `should return mock movie details when called getMovieDetails`() {
        coEvery { repository.getMovieDetails("1") } returns Resource<Movie>(
            Status.SUCCESS,
            Movie(
                "1",
                "Mocked Movie",
                "path",
                "2022",
                "123",
                123,
                "Nice movie",
                "path1",
                "1",
                listOf(),
                listOf()
            ), ""
        )

        runBlockingTest {
            viewModel.getMovieDetails("1")
        }

        coVerify { repository.getMovieDetails("1") }
    }

}

