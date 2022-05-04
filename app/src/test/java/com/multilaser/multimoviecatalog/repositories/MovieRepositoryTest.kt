package com.multilaser.multimoviecatalog.repositories

import com.multilaser.multimoviecatalog.MainCoroutineRule
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.services.MovieApiInterface
import com.multilaser.multimoviecatalog.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    private val movieApiInterface: MovieApiInterface = mockk()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
    }

    @Test
    fun `when getPopularMovieList() is called then it should call service getPopularMovieList`() {
        coEvery { movieApiInterface.getPopularMovieList() } returns listOf<Movie>(
            Movie(
                "",
                "",
                "",
                "",
                "",
                123,
                "",
                "",
                "",
                listOf(),
                listOf()
            )
        ) as Response<MovieList>
        runBlockingTest {
            MovieRepository(movieApiInterface).getPopularMovieList()
        }

        coVerify { movieApiInterface.getPopularMovieList() }
    }
}