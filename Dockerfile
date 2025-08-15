FROM ubuntu:latest
LABEL authors="mitthernatch"

ENTRYPOINT ["top", "-b"]