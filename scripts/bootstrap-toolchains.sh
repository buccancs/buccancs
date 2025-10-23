#!/usr/bin/env bash
set -euo pipefail

# Default parameters
ANDROID_CMDLINE_TOOLS_VERSION="${ANDROID_CMDLINE_TOOLS_VERSION:-11076708}"
ANDROID_PACKAGES="${ANDROID_PACKAGES:-platform-tools build-tools;36.0.0 platforms;android-36}"
TEMURIN_JDK_VERSION="${TEMURIN_JDK_VERSION:-21.0.4_7}"
FORCE_REDOWNLOAD="${FORCE_REDOWNLOAD:-false}"

# Resolve repository root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="${REPO_ROOT:-$(cd "${SCRIPT_DIR}/.." && pwd)}"

echo "Repository root: ${REPO_ROOT}"

# Toolchain paths
TOOLCHAINS_ROOT="${REPO_ROOT}/toolchains"
ANDROID_SDK_DIR="${TOOLCHAINS_ROOT}/android-sdk"
ANDROID_NDK_DIR="${TOOLCHAINS_ROOT}/android-ndk"
JAVA_DIR="${TOOLCHAINS_ROOT}/java"
GRADLE_HOME_DIR="${TOOLCHAINS_ROOT}/gradle-user-home"
DOWNLOADS_DIR="${TOOLCHAINS_ROOT}/downloads"

# Download URLs and file names
CMDLINE_TOOLS_ZIP_NAME="commandlinetools-linux-${ANDROID_CMDLINE_TOOLS_VERSION}_latest.zip"
CMDLINE_TOOLS_URL="https://dl.google.com/android/repository/${CMDLINE_TOOLS_ZIP_NAME}"
CMDLINE_TOOLS_ZIP="${DOWNLOADS_DIR}/${CMDLINE_TOOLS_ZIP_NAME}"
CMDLINE_TOOLS_EXTRACT_ROOT="${ANDROID_SDK_DIR}/cmdline-tools"
CMDLINE_TOOLS_LATEST="${CMDLINE_TOOLS_EXTRACT_ROOT}/latest"

TEMURIN_ZIP_NAME="OpenJDK21U-jdk_x64_linux_hotspot_${TEMURIN_JDK_VERSION}.tar.gz"
TEMURIN_URL_VERSION="${TEMURIN_JDK_VERSION//_/+}"
TEMURIN_URL="https://github.com/adoptium/temurin21-binaries/releases/download/jdk-${TEMURIN_URL_VERSION}/${TEMURIN_ZIP_NAME}"
TEMURIN_ARCHIVE="${DOWNLOADS_DIR}/${TEMURIN_ZIP_NAME}"

# Function: Check prerequisites
check_prerequisites() {
    local missing_tools=()
    
    # Check for download tools (at least one required)
    if ! command -v curl >/dev/null 2>&1 && ! command -v wget >/dev/null 2>&1; then
        missing_tools+=("curl or wget")
    fi
    
    # Check for extraction tools
    if ! command -v unzip >/dev/null 2>&1; then
        missing_tools+=("unzip")
    fi
    
    if ! command -v tar >/dev/null 2>&1; then
        missing_tools+=("tar")
    fi
    
    # Report missing tools
    if [[ ${#missing_tools[@]} -gt 0 ]]; then
        echo "Error: Missing required tools: ${missing_tools[*]}"
        echo ""
        echo "Please install them using your package manager:"
        echo "  Ubuntu/Debian: sudo apt-get install curl unzip tar"
        echo "  RHEL/CentOS:   sudo yum install curl unzip tar"
        echo "  macOS:         brew install curl unzip"
        exit 1
    fi
}

# Function: Download file if missing
download_if_missing() {
    local url="$1"
    local destination="$2"
    local overwrite="${3:-false}"

    if [[ "$overwrite" != "true" ]] && [[ -f "$destination" ]]; then
        echo "✓ Reusing existing file $destination"
        return
    fi

    local dest_dir
    dest_dir="$(dirname "$destination")"
    mkdir -p "$dest_dir"

    echo "⇣ Downloading $url"
    if command -v curl >/dev/null 2>&1; then
        curl -L -o "$destination" "$url"
    elif command -v wget >/dev/null 2>&1; then
        wget -O "$destination" "$url"
    else
        echo "Error: Neither curl nor wget found. Please install one of them."
        exit 1
    fi
}

# Function: Extract zip archive
extract_zip() {
    local archive_path="$1"
    local destination="$2"
    local force="${3:-false}"

    if [[ ! -f "$archive_path" ]]; then
        echo "Error: Archive not found: $archive_path"
        exit 1
    fi

    if [[ -d "$destination" ]]; then
        if [[ "$force" != "true" ]]; then
            local file_count
            file_count=$(find "$destination" -type f 2>/dev/null | wc -l)
            if [[ "$file_count" -gt 0 ]]; then
                echo "✓ Reusing existing directory $destination"
                return
            fi
            echo "⚠ Existing directory is empty or invalid, removing: $destination"
        fi
        rm -rf "$destination"
    fi

    echo "⇢ Extracting $(basename "$archive_path") -> $destination"
    mkdir -p "$destination"
    unzip -q "$archive_path" -d "$destination"
}

# Function: Extract tar.gz archive
extract_tar_gz() {
    local archive_path="$1"
    local destination="$2"
    local force="${3:-false}"

    if [[ ! -f "$archive_path" ]]; then
        echo "Error: Archive not found: $archive_path"
        exit 1
    fi

    if [[ -d "$destination" ]]; then
        if [[ "$force" != "true" ]]; then
            local file_count
            file_count=$(find "$destination" -type f 2>/dev/null | wc -l)
            if [[ "$file_count" -gt 0 ]]; then
                echo "✓ Reusing existing directory $destination"
                return
            fi
            echo "⚠ Existing directory is empty or invalid, removing: $destination"
        fi
        rm -rf "$destination"
    fi

    echo "⇢ Extracting $(basename "$archive_path") -> $destination"
    mkdir -p "$destination"
    tar -xzf "$archive_path" -C "$destination"
}

# Function: Ensure cmdline-tools layout
ensure_cmdline_tools_layout() {
    local root="$1"
    local latest_dir="${root}/latest"
    local sdkmanager_path="${latest_dir}/bin/sdkmanager"

    if [[ -f "$sdkmanager_path" ]]; then
        echo "$sdkmanager_path"
        return
    fi

    # Search for sdkmanager in subdirectories
    local candidate
    candidate=$(find "$root" -type f -name "sdkmanager" 2>/dev/null | head -n 1)

    if [[ -z "$candidate" ]]; then
        echo "Error: Unable to locate sdkmanager under $root"
        exit 1
    fi

    echo "↺ Normalising command-line tools layout"

    # Remove existing latest directory if present
    if [[ -d "$latest_dir" ]]; then
        rm -rf "$latest_dir"
    fi

    # Move the parent directory containing sdkmanager to latest
    local candidate_dir
    candidate_dir="$(dirname "$(dirname "$candidate")")"
    mv "$candidate_dir" "$latest_dir"

    # Clean up empty parent directories
    local parent
    parent="$(dirname "$candidate_dir")"
    while [[ "$parent" != "$root" ]] && [[ -d "$parent" ]]; do
        if [[ -z "$(ls -A "$parent" 2>/dev/null)" ]]; then
            rmdir "$parent" 2>/dev/null || true
            parent="$(dirname "$parent")"
        else
            break
        fi
    done

    if [[ ! -f "$sdkmanager_path" ]]; then
        echo "Error: Failed to normalise command-line tools layout at $root"
        exit 1
    fi

    echo "$sdkmanager_path"
}

# Function: Run sdkmanager
run_sdkmanager() {
    local sdkmanager_path="$1"
    local sdk_root="$2"
    local java_home="$3"
    shift 3
    local args=("$@")

    if [[ ! -f "$sdkmanager_path" ]]; then
        echo "Error: sdkmanager not found at $sdkmanager_path"
        exit 1
    fi

    export ANDROID_SDK_ROOT="$sdk_root"
    export JAVA_HOME="$java_home"
    export PATH="${java_home}/bin:${PATH}"

    echo "Running: $sdkmanager_path --sdk_root=$sdk_root ${args[*]}"
    
    # Run sdkmanager with auto-accept for licenses
    yes 2>/dev/null | "$sdkmanager_path" --sdk_root="$sdk_root" "${args[@]}" || true
}

# Function: Install Android packages
install_android_packages() {
    local sdkmanager_path="$1"
    local sdk_root="$2"
    local java_home="$3"
    shift 3
    local packages=("$@")

    echo "⚒ Installing Android packages: ${packages[*]}"
    run_sdkmanager "$sdkmanager_path" "$sdk_root" "$java_home" "${packages[@]}"

    echo "⚖ Accepting Android SDK licenses"
    run_sdkmanager "$sdkmanager_path" "$sdk_root" "$java_home" "--licenses"
}

# Main execution
echo ""
echo "==> Checking prerequisites"
check_prerequisites

echo ""
echo "==> Preparing directories"
mkdir -p "$TOOLCHAINS_ROOT" "$ANDROID_SDK_DIR" "$ANDROID_NDK_DIR" "$JAVA_DIR" "$GRADLE_HOME_DIR" "$DOWNLOADS_DIR"

echo ""
echo "==> Android command-line tools"
download_if_missing "$CMDLINE_TOOLS_URL" "$CMDLINE_TOOLS_ZIP" "$FORCE_REDOWNLOAD"
extract_zip "$CMDLINE_TOOLS_ZIP" "$CMDLINE_TOOLS_EXTRACT_ROOT" "$FORCE_REDOWNLOAD"
SDKMANAGER_BIN=$(ensure_cmdline_tools_layout "$CMDLINE_TOOLS_EXTRACT_ROOT")

echo ""
echo "==> Temurin JDK"
download_if_missing "$TEMURIN_URL" "$TEMURIN_ARCHIVE" "$FORCE_REDOWNLOAD"

JAVA_READY=false
if [[ -f "${JAVA_DIR}/bin/java" ]]; then
    JAVA_READY=true
fi

if [[ "$JAVA_READY" != "true" ]] || [[ "$FORCE_REDOWNLOAD" == "true" ]]; then
    JDK_TEMP_DIR="${TOOLCHAINS_ROOT}/temp-jdk-extract"
    
    if [[ -d "$JDK_TEMP_DIR" ]]; then
        rm -rf "$JDK_TEMP_DIR"
    fi
    
    echo "⇢ Extracting JDK archive..."
    mkdir -p "$JDK_TEMP_DIR"
    tar -xzf "$TEMURIN_ARCHIVE" -C "$JDK_TEMP_DIR"
    
    # Find the JDK folder containing bin/java
    JDK_FOLDER=$(find "$JDK_TEMP_DIR" -type f -name "java" -path "*/bin/java" 2>/dev/null | head -n 1)
    
    if [[ -z "$JDK_FOLDER" ]]; then
        echo "Error: Unable to locate extracted JDK under $JDK_TEMP_DIR"
        exit 1
    fi
    
    # Get the parent directory (the actual JDK root)
    JDK_FOLDER="$(dirname "$(dirname "$JDK_FOLDER")")"
    
    echo "↺ Moving JDK to $JAVA_DIR"
    
    # Clear existing Java directory contents
    if [[ -d "$JAVA_DIR" ]]; then
        rm -rf "${JAVA_DIR:?}"/*
    fi
    
    # Move JDK contents to java directory
    mv "$JDK_FOLDER"/* "$JAVA_DIR/"
    
    # Clean up temp directory
    rm -rf "$JDK_TEMP_DIR"
    
    if [[ ! -f "${JAVA_DIR}/bin/java" ]]; then
        echo "Error: Failed to install JDK to $JAVA_DIR"
        exit 1
    fi
fi

JAVA_HOME_PATH="$JAVA_DIR"

echo ""
echo "==> Installing Android SDK packages"
# Convert space-separated string to array
IFS=' ' read -r -a PACKAGE_ARRAY <<< "$ANDROID_PACKAGES"
install_android_packages "$SDKMANAGER_BIN" "$ANDROID_SDK_DIR" "$JAVA_HOME_PATH" "${PACKAGE_ARRAY[@]}"

echo ""
echo "==> Updating Gradle OS paths"
SETUP_SCRIPT="${REPO_ROOT}/scripts/setup-wsl.sh"
export ANDROID_SDK_ROOT="$ANDROID_SDK_DIR"
export ANDROID_NDK_ROOT="$ANDROID_NDK_DIR"
export JAVA_HOME="$JAVA_HOME_PATH"
export GRADLE_USER_HOME="$GRADLE_HOME_DIR"
bash "$SETUP_SCRIPT"

echo ""
echo "All toolchains downloaded and configured."
echo "Android SDK: $ANDROID_SDK_DIR"
echo "JDK:         $JAVA_DIR"
echo "Gradle home: $GRADLE_HOME_DIR"
