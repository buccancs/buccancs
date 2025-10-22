#!/usr/bin/env bash
set -euo pipefail

# Usage: ./make_summaries.sh [source_root] [output_root]
# Defaults mirror tools/make_summaries.ps1

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_root="$(dirname "$script_dir")"

default_source="$repo_root/external/ShimmerCapturePure"
default_output="$repo_root/external/ShimmerCapturePure_summaries"

source_root="${1:-$default_source}"
output_root="${2:-$default_output}"

if ! command -v realpath >/dev/null 2>&1; then
  echo "Error: realpath is required." >&2
  exit 1
fi

source_root="$(realpath "$source_root")"
output_root="$(realpath -m "$output_root")"

echo "Source root : $source_root"
echo "Output root : $output_root"
echo

mkdir -p "$output_root"

find "$source_root" -type f \( -name '*.java' -o -name '*.kt' \) -print0 |
  while IFS= read -r -d '' file; do
    rel_path="${file#$source_root/}"
    rel_dir="$(dirname "$rel_path")"
    base_name="$(basename "$file")"
    stem="${base_name%.*}"

    target_dir="$output_root/$rel_dir"
    summary_file="$target_dir/$stem.summary.txt"

    mkdir -p "$target_dir"

    size_bytes=$(stat -c %s "$file")
    modified=$(stat -c %y "$file")

    {
      echo "// Summary for: $file"
      echo "// Size: $size_bytes bytes"
      echo "// Modified: $modified"
      echo
      cat "$file"
    } >"$summary_file"
  done

echo
echo "Summaries written to $output_root"
