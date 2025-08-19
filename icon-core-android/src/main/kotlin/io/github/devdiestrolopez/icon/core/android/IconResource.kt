package io.github.devdiestrolopez.icon.core.android

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A sealed interface representing a type-safe and unified icon resource.
 *
 * This is the central API for the `iconset-generator` plugin, allowing you to handle
 * different types of icons—both vector and drawable—in a single, unified way.
 *
 * The sealed nature of this interface ensures that all possible icon types are known
 * at compile time, enabling exhaustive `when` expressions and guaranteeing type safety
 * throughout your application.
 */
sealed interface IconResource

/**
 * A type-safe wrapper for a standard Android drawable resource ID.
 *
 * Use this to represent vector drawables (XML files) stored in your app's `res/drawable` folder.
 *
 * @param id The resource ID of the drawable, annotated with [DrawableRes]
 * for compile-time validation.
 */
data class DrawableResource(@param:DrawableRes val id: Int) : IconResource

/**
 * A type-safe wrapper for a Compose [ImageVector].
 *
 * Use this to represent vector icons from libraries like Compose Material 3's
 * `Icons.Default` or `Icons.Outlined`, which are not traditional drawable resources.
 *
 * @param vector The [ImageVector] to be used as an icon.
 */
data class ImageVectorResource(val vector: ImageVector) : IconResource
