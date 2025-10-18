package com.topdon.module.thermal.ir.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.topdon.lib.core.config.ExtraKeyConfig
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.repository.GalleryRepository.DirType
import com.topdon.module.thermal.ir.fragment.IRGalleryTabFragment
import com.topdon.module.thermal.ir.viewmodel.IRGalleryTabViewModel

@Route(
    path = RouterConfig.IR_GALLERY_HOME
)
class IRGalleryHomeActivity :
    ComponentActivity() {
    private val viewModel: IRGalleryTabViewModel by viewModels()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        val dirType =
            intent.getIntExtra(
                ExtraKeyConfig.DIR_TYPE,
                0
            )

        setContent {
            val isEditMode by viewModel.isEditModeLD.observeAsState(
                false
            )

            BackHandler(
                enabled = isEditMode
            ) {
                viewModel.isEditModeLD.value =
                    false
            }

            // Use fragment container view for now to preserve functionality
            // TODO: Convert IRGalleryTabFragment to Compose
            AndroidView(
                factory = { context ->
                    val fragmentView =
                        layoutInflater.inflate(
                            com.topdon.module.thermal.ir.R.layout.activity_ir_gallery_home,
                            null,
                            false
                        )

                    if (savedInstanceState == null) {
                        val bundle =
                            Bundle().apply {
                                putBoolean(
                                    ExtraKeyConfig.CAN_SWITCH_DIR,
                                    false
                                )
                                putBoolean(
                                    ExtraKeyConfig.HAS_BACK_ICON,
                                    true
                                )
                                putInt(
                                    ExtraKeyConfig.DIR_TYPE,
                                    dirType
                                )
                            }
                        supportFragmentManager.beginTransaction()
                            .setReorderingAllowed(
                                true
                            )
                            .add(
                                com.topdon.module.thermal.ir.R.id.fragment_container_view,
                                IRGalleryTabFragment::class.java,
                                bundle
                            )
                            .commit()
                    }
                    fragmentView
                }
            )
        }
    }
}