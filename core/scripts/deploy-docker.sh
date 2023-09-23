#!/usr/bin/env bash

if [ ! -f /.dockerenv ] && [ "$1" != -f ] ; then
    >&2 echo 'This script should only be run inside Docker.'
    exit 1
fi

# Start server.sh in the background
bash scripts/server.sh &

# Wait for server.sh to start by sleeping for a brief period (adjust as needed)
sleep 5

# Check if server.sh is still running; if not, exit with an error
if ! ps -p $! > /dev/null; then
    >&2 echo 'server.sh failed to start.'
    exit 1
fi

# Start worker.sh in the background
bash scripts/worker.sh &

# Wait for both server.sh and worker.sh to complete
wait
