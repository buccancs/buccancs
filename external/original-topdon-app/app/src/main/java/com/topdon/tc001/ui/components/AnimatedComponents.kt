package com.topdon.tc001.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object AnimationConstants {
    const val DURATION_SHORT =
        200
    const val DURATION_MEDIUM =
        300
    const val DURATION_LONG =
        500
}

object NavigationTransitions {
    fun enterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        ) + fadeIn(
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        )
    }

    fun exitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { -it / 3 },
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        ) + fadeOut(
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        )
    }

    fun popEnterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { -it / 3 },
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        ) + fadeIn(
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        )
    }

    fun popExitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        ) + fadeOut(
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        )
    }
}

object ModalTransitions {
    fun enterTransition(): EnterTransition {
        return slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        ) + fadeIn(
            animationSpec = tween(
                AnimationConstants.DURATION_SHORT
            )
        )
    }

    fun exitTransition(): ExitTransition {
        return slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(
                AnimationConstants.DURATION_MEDIUM
            )
        ) + fadeOut(
            animationSpec = tween(
                AnimationConstants.DURATION_SHORT
            )
        )
    }
}

@Composable
fun AnimatedScreen(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = NavigationTransitions.enterTransition(),
        exit = NavigationTransitions.exitTransition(),
        modifier = modifier
    ) {
        content()
    }
}
