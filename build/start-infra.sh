#!/bin/sh

command=$1
cmdname=$(basename $0)

up() {
  echo 'running database container'
  docker-compose -f ./docker-compose-db.yml up -d
}

down() {
  docker-compose -f ./docker-compose-db.yml down -v
}

logsf() {
  docker-compose -f ./docker-compose-db.yml logs -f
}



usage() {
    cat << USAGE >&2

    Tool to manage Dockerized servers

    Usage

        $cmdname <command>

    Commands

        up                    Create and start containers
        down                  Stop and remove containers, networks, and volumes
        logsf                 Flow log the services

USAGE
exit 1
}


# checking for commands
case "$command" in
    up)
      up
    ;;
    down)
      down
    ;;
    logsf)
      logsf
    ;;
    *)
      usage
    ;;
esac










