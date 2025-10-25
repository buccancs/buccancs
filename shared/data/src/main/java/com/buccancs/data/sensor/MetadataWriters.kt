package com.buccancs.data.sensor

import java.io.File
import java.nio.charset.StandardCharsets

object MetadataWriters {
    fun writeMetadata(
        target: File,
        entries: List<Pair<String, String>>
    ) {
        val content =
            buildString {
                append(
                    '{'
                )
                entries.forEachIndexed { index, (key, value) ->
                    if (index > 0) append(
                        ','
                    )
                    append(
                        '"'
                    )
                    append(
                        key
                    )
                    append(
                        "\":"
                    )
                    append(
                        value
                    )
                }
                append(
                    '}'
                )
            }
        target.writeText(
            content,
            StandardCharsets.UTF_8
        )
    }

    fun stringValue(
        raw: String
    ): String {
        val builder =
            StringBuilder(
                raw.length + 2
            )
        builder.append(
            '"'
        )
        raw.forEach { ch ->
            when (ch) {
                '\\' -> builder.append(
                    "\\\\"
                )

                '"' -> builder.append(
                    "\\\""
                )

                else -> builder.append(
                    ch
                )
            }
        }
        builder.append(
            '"'
        )
        return builder.toString()
    }
}
