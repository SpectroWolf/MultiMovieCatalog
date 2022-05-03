package com.multilaser.multimoviecatalog.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.multilaser.multimoviecatalog.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkIfMainActivityIsVisible() {
        Espresso.onView(withId(R.id.layout_main_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkIfCategoryTitlesIsDisplayedCorrectly() {
        Espresso.onView(withId(R.id.tv_popular_movies))
            .check(ViewAssertions.matches(withText(R.string.filmes_populares)))
        Espresso.onView(withId(R.id.tv_top_rated_movies))
            .check(ViewAssertions.matches(withText(R.string.melhores_avaliados)))
        Espresso.onView(withId(R.id.tv_genre_terror))
            .check(ViewAssertions.matches(withText(R.string.terror_e_suspense)))
        Espresso.onView(withId(R.id.tv_genre_action))
            .check(ViewAssertions.matches(withText(R.string.acao_e_aventura)))
        Espresso.onView(withId(R.id.tv_genre_sci_fi))
            .check(ViewAssertions.matches(withText(R.string.sci_fi)))

    }

    @Test
    fun shouldSearchMovieWithTypedSearchAndIfTheScreenWillBeDisplayed() {
        Espresso.onView(withId(R.id.et_main_search)).perform(ViewActions.typeText("Sonic"))
        Espresso.onView(withId(R.id.btn_main_search)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.layout_searched_result))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}