package es.devdiestrolopez.icon.compose.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import es.devdiestrolopez.icon.core.android.DrawableResource
import es.devdiestrolopez.icon.core.android.IconResource
import es.devdiestrolopez.icon.core.android.ImageVectorResource

/**
 * A Material-style icon component that handles both standard drawable resources and
 * Compose [ImageVector]s, all through a unified [IconResource] sealed interface.
 *
 * This component is designed as a direct replacement for the standard Compose Material 3 [Icon]
 * component, providing the same API and behavior but with added support for the custom
 * [IconResource] data types.
 *
 * It internally uses a [when] expression to decide whether to render a [DrawableResource]
 * using [painterResource] or an [ImageVectorResource] using a [rememberVectorPainter]. This
 * seamless logic allows a single component to handle all icon types from your generated icon set.
 *
 * @param iconResource [IconResource] to draw inside this icon. This can be either a
 * [DrawableResource] for vector drawables or an [ImageVectorResource] for vector graphics.
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents. This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take. This text should be localized,
 * such as by using [androidx.compose.ui.res.stringResource] or similar.
 * @param modifier the [Modifier] to be applied to this icon.
 * @param tint tint to be applied to [iconResource]. If [Color.Unspecified] is provided, then no tint
 * is applied.
 */
@Composable
fun Icon(
    iconResource: IconResource,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    when (iconResource) {
        is DrawableResource    -> {
            Icon(
                painter = painterResource(iconResource.id),
                contentDescription = contentDescription,
                modifier = modifier,
                tint = tint,
            )
        }

        is ImageVectorResource -> {
            Icon(
                imageVector = iconResource.vector,
                contentDescription = contentDescription,
                modifier = modifier,
                tint = tint,
            )
        }
    }
}

@Composable
private fun Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    Icon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint,
    )
}

@Composable
private fun Icon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    val colorFilter = remember(tint) {
        if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    }

    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }

    Box(
        modifier
            .toolingGraphicsLayer()
            .defaultSizeFor(painter)
            .paint(painter, colorFilter = colorFilter, contentScale = ContentScale.Fit)
            .then(semantics)
    )
}

private fun Modifier.defaultSizeFor(painter: Painter) =
    this.then(
        if (painter.intrinsicSize == Size.Unspecified || painter.intrinsicSize.isInfinite()) {
            DefaultIconSizeModifier
        } else {
            Modifier
        }
    )

private fun Size.isInfinite() = width.isInfinite() && height.isInfinite()

private val IconSize = 24.dp

// Default icon size, for icons with no intrinsic size information
private val DefaultIconSizeModifier = Modifier.size(IconSize)
