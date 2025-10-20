# Tooling

Supporting utilities that complement the automation suites live here.

- `send_grpc_command.py` – Minimal gRPC helper that issues `START`/ `STOP`
  recording commands to a running desktop orchestrator. Handy when you need to
  verify command handling without driving the full UI.

Extend this directory with small, reusable helpers; larger automations belong in
`automation/suites/`.
