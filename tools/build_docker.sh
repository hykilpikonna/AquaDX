#!/usr/bin/env bash
docker build -t "hykilpikonna/aquadx:v1-dev" -t "hykilpikonna/aquadx:latest" .
docker push "hykilpikonna/aquadx:v1-dev"
docker push "hykilpikonna/aquadx:latest"
