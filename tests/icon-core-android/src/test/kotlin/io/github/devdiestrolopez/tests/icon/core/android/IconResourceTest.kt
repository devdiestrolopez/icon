package io.github.devdiestrolopez.tests.icon.core.android

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.devdiestrolopez.icon.core.android.DrawableResource
import io.github.devdiestrolopez.icon.core.android.IconResource
import io.github.devdiestrolopez.icon.core.android.ImageVectorResource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Unit tests for the [IconResource] sealed interface and its implementations.
 *
 * These tests focus on verifying the core behavior of the data classes, such as equality,
 * hashing, and the functionality of the sealed interface itself.
 */
internal class IconResourceTest {

    @Nested
    inner class DrawableResourceTest {

        @Test
        fun `drawables objects with the same id should be equal`() {
            val drawable1 = DrawableResource(id = 123)
            val drawable2 = DrawableResource(id = 123)
            assertEquals(drawable1, drawable2)
        }

        @Test
        fun `drawable objects with different id should not be equal`() {
            val drawable1 = DrawableResource(id = 123)
            val drawable2 = DrawableResource(id = 456)
            assertNotEquals(drawable1, drawable2)
        }

        @Test
        fun `drawable resource with same id should have same hash code`() {
            val drawable1 = DrawableResource(id = 123)
            val drawable2 = DrawableResource(id = 123)
            assertEquals(drawable1.hashCode(), drawable2.hashCode())
        }

        @Test
        fun `drawable resource copy function should create an equal object`() {
            val original = DrawableResource(id = 123)
            val copied = original.copy()
            assertEquals(original, copied)
        }
    }

    @Nested
    inner class ImageVectorResourceTest {

        @Test
        fun `image vector objects with the same vector should be equal`() {
            val vector1 = ImageVectorResource(vector = testImageVector)
            val vector2 = ImageVectorResource(vector = testImageVector)
            assertEquals(vector1, vector2)
        }

        @Test
        fun `image vector objects with different vector should not be equal`() {
            val vector1 = ImageVectorResource(vector = testImageVector)
            val vector2 = ImageVectorResource(
                vector = ImageVector.Builder(
                    name = "other_icon",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f
                ).build()
            )
            assertNotEquals(vector1, vector2)
        }

        @Test
        fun `image vector resource with same vector should have the same hash code`() {
            val vector1 = ImageVectorResource(vector = testImageVector)
            val vector2 = ImageVectorResource(vector = testImageVector)
            assertEquals(vector1.hashCode(), vector2.hashCode())
        }

        @Test
        fun `image vector resource copy function should create equal object`() {
            val original = ImageVectorResource(vector = testImageVector)
            val copied = original.copy()
            assertEquals(original, copied)
        }
    }

    @ParameterizedTest
    @MethodSource("iconResourceProvider")
    fun `should correctly handle all types in when expression`(
        iconResource: IconResource,
        expectedIsDrawable: Boolean,
        expectedIsImageVector: Boolean,
    ) {
        val isDrawable = when (iconResource) {
            is DrawableResource    -> true
            is ImageVectorResource -> false
        }
        assertEquals(expectedIsDrawable, isDrawable)

        val isImageVector = when (iconResource) {
            is DrawableResource    -> false
            is ImageVectorResource -> true
        }
        assertEquals(expectedIsImageVector, isImageVector)
    }

    companion object {

        @JvmStatic
        fun iconResourceProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(DrawableResource(id = 1), true, false),
                Arguments.of(ImageVectorResource(vector = testImageVector), false, true)
            )
        }
    }
}

/**
 * Helper property to create a dummy ImageVector for testing purposes.
 * Since [androidx.compose.ui.graphics.vector.ImageVector] is a final class, we cannot extend it. This approach
 * provides a simple, valid instance to be used in tests.
 */
internal val testImageVector: ImageVector = ImageVector.Builder(
    name = "test_icon",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).build()
