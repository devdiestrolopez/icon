package io.github.devdiestrolopez.icon.compose.android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.devdiestrolopez.icon.core.android.DrawableResource
import io.github.devdiestrolopez.icon.core.android.ImageVectorResource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * A comprehensive test class for the [Icon] composable.
 *
 * This class uses the `createComposeRule` to test the UI components,
 * ensuring they behave as expected under various conditions,
 * such as rendering different resource types and handling
 * accessibility properties.
 */
@RunWith(AndroidJUnit4::class)
internal class IconTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun imageVectorIconRendersCorrectly() {
        val testContentDescription = "Test Info Icon"
        val testImageVectorResource = ImageVectorResource(Icons.Default.Info)

        composeTestRule.setContent {
            Icon(
                iconResource = testImageVectorResource,
                contentDescription = testContentDescription
            )
        }

        composeTestRule.onNodeWithContentDescription(testContentDescription).assertExists()
    }

    @Test
    fun drawableIconRendersCorrectly() {
        val testContentDescription = "Test Drawable Icon"
        val testDrawableResource = DrawableResource(123)

        composeTestRule.setContent {
            Icon(
                iconResource = testDrawableResource,
                contentDescription = testContentDescription
            )
        }

        composeTestRule.onNodeWithContentDescription(testContentDescription).assertExists()
    }

    @Test
    fun nonNullContentDescriptionAddsSemantics() {
        val testContentDescription = "A simple decorative icon"
        val testImageVectorResource = ImageVectorResource(Icons.Default.Info)

        composeTestRule.setContent {
            Icon(
                iconResource = testImageVectorResource,
                contentDescription = testContentDescription
            )
        }

        val nodeMatcher = hasContentDescription(testContentDescription) and hasRole(Role.Image)

        composeTestRule.onNode(nodeMatcher).assertExists()
    }

    @Test
    fun nullContentDescriptionDoesNotAddSemantics() {
        val testImageVectorResource = ImageVectorResource(Icons.Default.Info)

        composeTestRule.setContent {
            Icon(
                iconResource = testImageVectorResource,
                contentDescription = null
            )
        }

        composeTestRule.onNodeWithContentDescription("").assertDoesNotExist()
    }

    private fun hasRole(role: Role) = SemanticsMatcher("${SemanticsProperties.Role.name} contains '$role'") {
        val roleProperty = it.config.getOrNull(SemanticsProperties.Role) ?: false
        roleProperty == role
    }
}