#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"
CONFIG="${REPO_ROOT}/gradle/os-paths.properties"
SAMPLE="${REPO_ROOT}/gradle/os-paths.sample.properties"

TOOLCHAIN_ROOT="${REPO_ROOT}/toolchains"
OS_TOOLCHAIN_ROOT="${TOOLCHAIN_ROOT}/linux"
ANDROID_SDK_ROOT_DEFAULT="${OS_TOOLCHAIN_ROOT}/android-sdk"
ANDROID_NDK_ROOT_DEFAULT="${OS_TOOLCHAIN_ROOT}/android-ndk"
JAVA_HOME_DEFAULT="${OS_TOOLCHAIN_ROOT}/java"
GRADLE_USER_HOME_DEFAULT="${OS_TOOLCHAIN_ROOT}/gradle-user-home"

ANDROID_SDK="${ANDROID_SDK_ROOT:-$ANDROID_SDK_ROOT_DEFAULT}"
ANDROID_NDK="${ANDROID_NDK_ROOT:-$ANDROID_NDK_ROOT_DEFAULT}"
JAVA_HOME="${JAVA_HOME:-$JAVA_HOME_DEFAULT}"
GRADLE_USER_HOME="${GRADLE_USER_HOME:-$GRADLE_USER_HOME_DEFAULT}"

mkdir -p "${ANDROID_SDK}" "${GRADLE_USER_HOME}"
if [[ -n "${ANDROID_NDK}" ]]; then
    mkdir -p "${ANDROID_NDK}"
fi

if [[ ! -f "${CONFIG}" ]]; then
    mkdir -p "$(dirname "${CONFIG}")"
    cp "${SAMPLE}" "${CONFIG}"
fi

tmp_file="${CONFIG}.tmp"
trap 'rm -f "${tmp_file}"' EXIT

update_property() {
    local key="$1"
    local value="$2"

    local escaped
    escaped="$(printf '%s' "$value" | sed -e 's/[&/\]/\\&/g')"

    if grep -Eq "^[[:space:]]*${key}[[:space:]]*=" "${CONFIG}"; then
        if [[ -n "$value" ]]; then
            sed -E "s|^[[:space:]]*${key}[[:space:]]*=.*$|${key} = ${escaped}|" "${CONFIG}" > "${tmp_file}"
        else
            sed -E "s|^[[:space:]]*${key}[[:space:]]*=.*$|${key} =|" "${CONFIG}" > "${tmp_file}"
        fi
    else
        cat "${CONFIG}" > "${tmp_file}"
        if [[ -n "$value" ]]; then
            printf '%s = %s\n' "${key}" "${value}" >> "${tmp_file}"
        else
            printf '%s =\n' "${key}" >> "${tmp_file}"
        fi
    fi

    mv "${tmp_file}" "${CONFIG}"
}

update_property "linux.android.sdk" "${ANDROID_SDK}"
update_property "linux.android.ndk" "${ANDROID_NDK}"
update_property "linux.java.home" "${JAVA_HOME}"
update_property "linux.gradle.user.home" "${GRADLE_USER_HOME}"

printf 'Updated %s with Linux/WSL toolchain locations.\n' "${CONFIG}"

cat <<EOF

Current session exports:
  export ANDROID_SDK_ROOT=${ANDROID_SDK}
  export JAVA_HOME=${JAVA_HOME}
  export GRADLE_USER_HOME=${GRADLE_USER_HOME}
EOF

if [[ -n "${ANDROID_NDK}" ]]; then
cat <<EOF
  export ANDROID_NDK_ROOT=${ANDROID_NDK}
EOF
fi

cat <<EOF

Add the exports above to ~/.bashrc or ~/.zshrc if you want them persistent.
Toolchains are expected under ${OS_TOOLCHAIN_ROOT}. Populate android-sdk, android-ndk (optional),
java, and gradle-user-home inside that directory or update the paths above.
EOF
