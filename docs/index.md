# Documentation Index

This directory now carries a concise set of living documents for the BuccanCS
platform. Use the map below to jump to the guide that matches your current task.

## Core Guides

- `system-overview.md` – Mission statement, component topology, data flow, and
  hardware integration snapshot.

- `requirements.md` – Functional and non-functional commitments with current
  delivery status.

- `development.md` – Workstation provisioning, daily commands, troubleshooting
  notes, automation harness usage, and writing standards.

- `testing.md` – Automated coverage expectations plus manual drills (hardware
  validation, calibration, stress, recovery).

## Operating Rules & Planning

- `../AGENTS.md` – Canonical contributor playbook; read before running
  automation or making changes.

- `tasks/active-plan.md` – Current multi-modal capture objective, acceptance
  criteria, risks, and work streams. Update it as the goal evolves so automation
  and reviewers stay aligned.
- `tasks/topdon-integration-summary.md` – Completed TC001 hardware phases and
  candidate follow-up items.

## Tooling & Assets

- `automation/` – PowerShell and Bash harnesses for unattended CLI sessions
  (`auto_continue.ps1`, `auto_continue.sh`, templates, queues, logs).

- `docs/latex/` – Thesis chapters and academic artefacts retained for research
  deliverables.

- `ircamera-architecture-analysis.md` – Deep dive into the upstream Topdon app
  architecture for inspiration.
- `ircamera-topdon-reference.md` – Snapshot of official Topdon build targets,
  Gradle settings, and module layout.
- `ircamera-gradle-integration.md` & `ircamera-build-quickstart.md` – Build
  notes for the external IRCamera module (submodule sync, entry points, APK
  locations).

## Historical Material

Earlier deep-dive reports, project diaries, and visualisations have been removed
from the working set. Retrieve any prior version from Git history if you need
detailed background or archived diagrams.
