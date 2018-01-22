#!/usr/bin/env bash
docker run -d --name sprio-grafana -p 80:80 -p 2003:2003 -p 8125:8125/udp -p 8126:8126 jlachowski/grafana-graphite-statsd
